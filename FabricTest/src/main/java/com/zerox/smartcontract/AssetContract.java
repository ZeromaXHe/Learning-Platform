package com.zerox.smartcontract;

import com.zerox.smartcontract.entity.Account;
import com.zerox.smartcontract.entity.Asset;
import com.zerox.smartcontract.entity.AssetTransHistory;
import com.zerox.utils.JsonUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.CompositeKey;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 10:19
 */
@Contract(
        name = "AssetContract",
        info = @Info(
                title = "Asset contract",
                description = "The hyperledger asset deal contract",
                version = "0.0.1-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                ),
                contact = @Contact(
                        email = "zhuxiaohe95@163.com",
                        name = "Zhu Xiaohe"
                )
        )
)
@Default
public class AssetContract {
    private static final Logger logger = LoggerFactory.getLogger(AssetContract.class);

    private static final String ASSET_TRANS_HISTORY_KEY = "ast_tx_his";

    private void throwChaincodeException(String msg, String payload) {
        logger.error(msg);
        throw new ChaincodeException(msg, payload);
    }

    @Transaction
    public Account createAccount(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        String key = getAccountKey(id);
        validateNonExistence(stub, key, "Account");
        Account account = new Account(id, new HashMap<>());
        stub.putStringState(key, JsonUtils.objectToJson(account));
        return account;
    }

    private String getAccountKey(String id) {
        return "account_" + id;
    }

    @Transaction
    public Asset createAsset(final Context ctx, final String id, final String ownersStr) {
        ChaincodeStub stub = ctx.getStub();
        String key = getAssetKey(id);
        validateNonExistence(stub, key, "Asset");
        Map<String, Integer> owners = parseOwners(ownersStr);
        Asset asset = new Asset(id, owners);
        stub.putStringState(key, JsonUtils.objectToJson(asset));
        for (Map.Entry<String, Integer> entry : owners.entrySet()) {
            CompositeKey compKey = stub.createCompositeKey(ASSET_TRANS_HISTORY_KEY, id, "INIT", entry.getKey());
            stub.putStringState(compKey.toString(),
                    JsonUtils.objectToJson(new AssetTransHistory(id, "INIT", entry.getKey(), entry.getValue(), 0L)));
        }
        return asset;
    }

    private String getAssetKey(String id) {
        return "asset_" + id;
    }

    private void validateNonExistence(ChaincodeStub stub, String key, String type) {
        String assetJson = stub.getStringState(key);
        // 对应 key 已经存在值
        if (StringUtils.hasLength(assetJson)) {
            throwChaincodeException(
                    String.format("%s key %s is already used", type, key),
                    type.toUpperCase() + "_KEY_USED");
        }
    }

    private Map<String, Integer> parseOwners(String ownersStr) {
        Map<String, Integer> owners = new HashMap<>();
        long sum = 10000L;
        try {
            String[] entries = ownersStr.split(",");
            for (String entry : entries) {
                String[] keyValue = entry.split("=");
                int share = Integer.parseInt(keyValue[1]);
                owners.put(keyValue[0], share);
                sum -= share;
            }
        } catch (Exception e) {
            // 解析失败
            throwChaincodeException(
                    String.format("can not parse ownersStr %s", ownersStr),
                    "OWNERS_STR_PARSE_FAIL");
        }
        // 没有 owner
        if (owners.isEmpty()) {
            throwChaincodeException("no owner", "NO_OWNER_ERROR");
        }
        // 所有者收益占比和应该为 10000
        if (sum != 0) {
            throwChaincodeException("sum of shares is not 10000", "SHARE_SUM_NOT_10000");
        }
        return owners;
    }

    @Transaction
    public Asset queryAsset(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        String assetJson = validateExistenceAndGetValueJson(getAssetKey(id), stub, "Asset");
        return JsonUtils.jsonToObject(assetJson, Asset.class);
    }

    @Transaction
    public List<AssetTransHistory> queryAssetTransHistory(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        QueryResultsIterator<KeyValue> result = stub.getStateByPartialCompositeKey(ASSET_TRANS_HISTORY_KEY, key);
        List<AssetTransHistory> list = new ArrayList<>();
        for (KeyValue kv : result) {
            list.add(JsonUtils.jsonToObject(kv.getStringValue(), AssetTransHistory.class));
        }
        return list;
    }

    private String validateExistenceAndGetValueJson(String key, ChaincodeStub stub, String valueType) {
        String assetJson = stub.getStringState(key);
        // 没找到对应值
        if (!StringUtils.hasLength(assetJson)) {
            throwChaincodeException(
                    String.format("%s %s does not exist", valueType, key),
                    valueType.toUpperCase() + "_NOT_FOUND");
        }
        return assetJson;
    }

    @Transaction
    public Asset changeAssetOwner(final Context ctx, final String id,
                                  final String from, final String to,
                                  final String shareStr, final String costStr) {
        int share = parseShare(shareStr);
        long cost = parseCost(costStr);
        ChaincodeStub stub = ctx.getStub();
        String key = getAssetKey(id);
        String assetJson = validateExistenceAndGetValueJson(key, stub, "Asset");
        Asset asset = JsonUtils.jsonToObject(assetJson, Asset.class);
        assert asset != null;
        Map<String, Integer> owners = asset.getOwners();
        int fromShare = validateAndGetFromShare(key, from, share, owners);
        if (fromShare == share) {
            owners.remove(from);
        } else {
            owners.put(from, fromShare - share);
        }
        owners.put(to, owners.getOrDefault(to, 0) + share);

        stub.putStringState(key, JsonUtils.objectToJson(asset));

        // 资产交易历史
        CompositeKey compositeKey = stub.createCompositeKey(ASSET_TRANS_HISTORY_KEY, id, from, to);
        stub.putStringState(compositeKey.toString(),
                JsonUtils.objectToJson(new AssetTransHistory(id, from, to, share, cost)));

        return asset;
    }

    private int parseShare(String shares) {
        try {
            return Integer.parseInt(shares);
        } catch (Exception e) {
            // 解析 share 失败
            throwChaincodeException(
                    String.format("can not parse shares %s", shares),
                    "SHARES_PARSE_FAIL");
        }
        // 走不到这里
        return 0;
    }

    private long parseCost(String cost) {
        try {
            return Long.parseLong(cost);
        } catch (Exception e) {
            // 解析 cost 失败
            throwChaincodeException(
                    String.format("can not parse cost %s", cost),
                    "COST_PARSE_FAIL");
        }
        // 走不到这里
        return 0;
    }

    private int validateAndGetFromShare(String key, String from, int share, Map<String, Integer> owners) {
        // from 不是所有者
        if (!owners.containsKey(from)) {
            throwChaincodeException(
                    String.format("%s is not owner of asset %s", from, key),
                    "FROM_NOT_OWNER");
        }
        // from 拥有的份数不足
        int fromShare = owners.get(from);
        if (fromShare < share) {
            throwChaincodeException(
                    String.format("%s's share of asset %s is less than %d", from, key, share),
                    "FROM_NOT_OWNER");
        }
        return fromShare;
    }
}

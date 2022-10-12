package com.zerox.smartcontract;

import com.zerox.constant.ContractConstants;
import com.zerox.smartcontract.entity.Account;
import com.zerox.smartcontract.entity.Asset;
import com.zerox.smartcontract.entity.AssetTransHistory;
import com.zerox.utils.ChaincodeJsonUtils;
import com.zerox.utils.ContractUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
public class AssetContract implements ContractInterface {
    private final static Logger LOG = Logger.getLogger(AssetContract.class.getName());

    @Transaction
    public void testLogText(final Context ctx, String text) {
        LOG.info(text);
    }

    @Transaction
    public void testThrowException(final Context ctx, String msg) {
        ContractUtils.throwChaincodeException(LOG, msg, "TEST_THROW_EXCEPTION");
    }

    @Transaction
    public Asset createAsset(final Context ctx, final String id, final String ownersStr) {
        ChaincodeStub stub = ctx.getStub();
        String key = getAssetKey(id);
        ContractUtils.validateNonExistence(LOG, stub, key, "Asset");
        Map<String, Integer> owners = parseOwners(ownersStr);
        Asset asset = new Asset(id, owners);
        stub.putStringState(key, ChaincodeJsonUtils.objectToJson(asset));
        long timestamp = System.currentTimeMillis();
        for (Map.Entry<String, Integer> entry : owners.entrySet()) {
            CompositeKey compKey = stub.createCompositeKey(ContractConstants.ASSET_TRANS_HISTORY_KEY,
                    id, "INIT", entry.getKey(), String.valueOf(timestamp));
            stub.putStringState(compKey.toString(), ChaincodeJsonUtils.objectToJson(
                    new AssetTransHistory(id, "INIT", entry.getKey(), entry.getValue(),
                            0L, timestamp)));
        }
        return asset;
    }

    private String getAssetKey(String id) {
        return "asset_" + id;
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
            ContractUtils.throwChaincodeException(LOG,
                    String.format("can not parse ownersStr %s", ownersStr),
                    "OWNERS_STR_PARSE_FAIL");
        }
        // 没有 owner
        if (owners.isEmpty()) {
            ContractUtils.throwChaincodeException(LOG, "no owner", "NO_OWNER_ERROR");
        }
        // 所有者收益占比和应该为 10000
        if (sum != 0) {
            ContractUtils.throwChaincodeException(LOG, "sum of shares is not 10000", "SHARE_SUM_NOT_10000");
        }
        return owners;
    }

    @Transaction
    public Asset queryAsset(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        String assetJson = ContractUtils.validateExistenceAndGetValueJson(LOG, stub, getAssetKey(id), "Asset");
        return ChaincodeJsonUtils.jsonToObject(assetJson, Asset.class);
    }

    @Transaction
    public String queryAssetTransHistory(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        QueryResultsIterator<KeyValue> result =
                stub.getStateByPartialCompositeKey(ContractConstants.ASSET_TRANS_HISTORY_KEY, id);
        List<AssetTransHistory> list = new ArrayList<>();
        for (KeyValue kv : result) {
            list.add(ChaincodeJsonUtils.jsonToObject(kv.getStringValue(), AssetTransHistory.class));
        }
        return ChaincodeJsonUtils.objectToJson(list);
    }

    @Transaction
    public Asset changeAssetOwner(final Context ctx, final String id,
                                  final String from, final String to,
                                  final String shareStr, final String costStr) {
        int share = parseShare(shareStr);
        long cost = parseCost(costStr);
        ChaincodeStub stub = ctx.getStub();
        String key = getAssetKey(id);
        String assetJson = ContractUtils.validateExistenceAndGetValueJson(LOG, stub, key, "Asset");
        Asset asset = ChaincodeJsonUtils.jsonToObject(assetJson, Asset.class);
        assert asset != null;
        Map<String, Integer> owners = asset.getOwners();
        int fromShare = validateAndGetFromShare(key, from, share, owners);
        if (fromShare == share) {
            owners.remove(from);
        } else {
            owners.put(from, fromShare - share);
        }
        owners.put(to, owners.getOrDefault(to, 0) + share);

        stub.putStringState(key, ChaincodeJsonUtils.objectToJson(asset));

        // 资产交易历史
        long timestamp = System.currentTimeMillis();
        CompositeKey compositeKey = stub.createCompositeKey(
                ContractConstants.ASSET_TRANS_HISTORY_KEY, id, from, to, String.valueOf(timestamp));
        stub.putStringState(compositeKey.toString(),
                ChaincodeJsonUtils.objectToJson(new AssetTransHistory(id, from, to, share, cost, timestamp)));

        return asset;
    }

    private int parseShare(String shares) {
        try {
            return Integer.parseInt(shares);
        } catch (Exception e) {
            // 解析 share 失败
            ContractUtils.throwChaincodeException(LOG,
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
            ContractUtils.throwChaincodeException(LOG,
                    String.format("can not parse cost %s", cost),
                    "COST_PARSE_FAIL");
        }
        // 走不到这里
        return 0;
    }

    private int validateAndGetFromShare(String key, String from, int share, Map<String, Integer> owners) {
        // from 不是所有者
        if (!owners.containsKey(from)) {
            ContractUtils.throwChaincodeException(LOG,
                    String.format("%s is not owner of asset %s", from, key),
                    "FROM_NOT_OWNER");
        }
        // from 拥有的份数不足
        int fromShare = owners.get(from);
        if (fromShare < share) {
            ContractUtils.throwChaincodeException(LOG,
                    String.format("%s's share of asset %s is less than %d", from, key, share),
                    "FROM_NOT_OWNER");
        }
        return fromShare;
    }
}

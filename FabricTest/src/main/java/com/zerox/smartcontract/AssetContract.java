package com.zerox.smartcontract;

import com.zerox.smartcontract.entity.AccountBonusHistory;
import com.zerox.smartcontract.entity.Asset;
import com.zerox.smartcontract.entity.AssetBonusHistory;
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
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.CompositeKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.zerox.constant.ContractConstants.*;

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
    public Asset createAsset(final Context ctx, final String id, final String ownersStr, final String timestamp) {
        ChaincodeStub stub = ctx.getStub();
        String key = getAssetKey(id);
        ContractUtils.validateNonExistence(LOG, stub, key, "Asset");
        Map<String, Integer> owners = parseOwners(ownersStr);
        Asset asset = new Asset(id, owners);
        stub.putStringState(key, ChaincodeJsonUtils.objectToJson(asset));
        for (Map.Entry<String, Integer> entry : owners.entrySet()) {
            CompositeKey compKey = stub.createCompositeKey(ASSET_TRANS_HISTORY_KEY,
                    id, "INIT", entry.getKey(), timestamp);
            stub.putStringState(compKey.toString(), ChaincodeJsonUtils.objectToJson(
                    new AssetTransHistory(id, "INIT", entry.getKey(), entry.getValue(),
                            0L, Long.parseLong(timestamp))));
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
        return ContractUtils.history(AssetTransHistory.class, ctx, ASSET_TRANS_HISTORY_KEY, id);
    }

    @Transaction
    public Asset changeAssetOwner(final Context ctx, final String id, final String from, final String to,
                                  final String shareStr, final String costStr, final String timestamp) {
        int share = parseShare(shareStr);
        long cost = parseLong(costStr);
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

        // cost 逻辑
        List<String> params = new ArrayList<>();
        params.add("AccountContract:changeAccountMoney");
        params.add(from);
        params.add(String.valueOf(cost));
        params.add("ASSET_SELL");
        params.add(timestamp);
        // 跨 Contract 调用
        stub.invokeChaincodeWithStringArgs(CHAINCODE_NAME, params);

        List<String> params2 = new ArrayList<>();
        params2.add("AccountContract:changeAccountMoney");
        params2.add(to);
        params2.add(String.valueOf(-cost));
        params2.add("ASSET_BUY");
        params2.add(timestamp);
        // 跨 Contract 调用
        stub.invokeChaincodeWithStringArgs(CHAINCODE_NAME, params2);

        // 资产交易历史
        CompositeKey compositeKey = stub.createCompositeKey(
                ASSET_TRANS_HISTORY_KEY, id, from, to, timestamp);
        stub.putStringState(compositeKey.toString(), ChaincodeJsonUtils.objectToJson(
                new AssetTransHistory(id, from, to, share, cost, Long.parseLong(timestamp))));

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

    private long parseLong(String cost) {
        try {
            return Long.parseLong(cost);
        } catch (Exception e) {
            // 解析 long 失败
            ContractUtils.throwChaincodeException(LOG,
                    String.format("can not parse long %s", cost),
                    "LONG_PARSE_FAIL");
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

    @Transaction
    public Asset bonus(final Context ctx, final String id, final String bonusStr, final String timestamp) {
        long bonus = parseLong(bonusStr);
        ChaincodeStub stub = ctx.getStub();
        String key = getAssetKey(id);
        String assetJson = ContractUtils.validateExistenceAndGetValueJson(LOG, stub, key, "Asset");
        Asset asset = ChaincodeJsonUtils.jsonToObject(assetJson, Asset.class);
        assert asset != null;
        Map<String, Integer> owners = asset.getOwners();
        Map<String, Long> ownerBonus = new HashMap<>();
        for (Map.Entry<String, Integer> entry : owners.entrySet()) {
            String accountId = entry.getKey();
            Integer share = entry.getValue();
            long accountBonus = (long) (bonus * (share / 10000.0));

            List<String> params = new ArrayList<>();
            params.add("AccountContract:changeAccountMoney");
            params.add(accountId);
            params.add(String.valueOf(accountBonus));
            params.add("BONUS");
            params.add(timestamp);
            // 跨 Contract 调用
            stub.invokeChaincodeWithStringArgs(CHAINCODE_NAME, params);

            // 账户分红历史
            CompositeKey acntBnsHisKey = stub.createCompositeKey(
                    ACCOUNT_BONUS_HISTORY_KEY, accountId, id, timestamp);
            stub.putStringState(acntBnsHisKey.toString(), ChaincodeJsonUtils.objectToJson(
                    new AccountBonusHistory(accountId, id, share, accountBonus, Long.parseLong(timestamp))));
        }

        // 资产分红历史
        CompositeKey compositeKey = stub.createCompositeKey(
                ASSET_BONUS_HISTORY_KEY, id, String.valueOf(timestamp));
        stub.putStringState(compositeKey.toString(), ChaincodeJsonUtils.objectToJson(
                new AssetBonusHistory(id, bonus, ownerBonus, Long.parseLong(timestamp))));

        return asset;
    }

    @Transaction
    public String queryAssetBonusHistory(final Context ctx, final String id) {
        return ContractUtils.history(AssetBonusHistory.class, ctx, ASSET_BONUS_HISTORY_KEY, id);
    }
}

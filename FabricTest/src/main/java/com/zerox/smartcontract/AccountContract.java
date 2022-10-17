package com.zerox.smartcontract;

import com.zerox.smartcontract.entity.Account;
import com.zerox.smartcontract.entity.AccountBonusHistory;
import com.zerox.smartcontract.entity.AccountMoneyHistory;
import com.zerox.smartcontract.entity.AccountMoneyTransHistory;
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

import java.util.HashMap;
import java.util.logging.Logger;

import static com.zerox.constant.ContractConstants.*;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/10/11 15:05
 */
@Contract(
        name = "AccountContract",
        info = @Info(
                title = "Account contract",
                description = "The hyperledger account deal contract",
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
public class AccountContract implements ContractInterface {
    private final static Logger LOG = Logger.getLogger(AccountContract.class.getName());

    @Transaction
    public void testLogText(final Context ctx, String text) {
        LOG.info(text);
    }

    @Transaction
    public void testThrowException(final Context ctx, String msg) {
        ContractUtils.throwChaincodeException(LOG, msg, "TEST_THROW_EXCEPTION");
    }

    @Transaction
    public Account createAccount(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        String key = getAccountKey(id);
        ContractUtils.validateNonExistence(LOG, stub, key, "Account");
        Account account = new Account(id, 0L, new HashMap<>());
        stub.putStringState(key, ChaincodeJsonUtils.objectToJson(account));

        long timestamp = stub.getTxTimestamp().toEpochMilli();
        CompositeKey compKey = stub.createCompositeKey(ACCOUNT_MONEY_HISTORY_KEY, id, String.valueOf(timestamp));
        stub.putStringState(compKey.toString(), ChaincodeJsonUtils.objectToJson(
                new AccountMoneyHistory(id, 0L, 0L, "INIT", timestamp)));
        return account;
    }

    private static String getAccountKey(String id) {
        return "account_" + id;
    }

    @Transaction
    public Account queryAccount(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        String accountJson = ContractUtils.validateExistenceAndGetValueJson(
                LOG, stub, getAccountKey(id), "Account");
        return ChaincodeJsonUtils.jsonToObject(accountJson, Account.class);
    }

    @Transaction
    public String queryAccountMoneyHistory(final Context ctx, final String id) {
        return ContractUtils.history(AccountMoneyHistory.class, ctx, ACCOUNT_MONEY_HISTORY_KEY, id);
    }

    @Transaction
    public Account changeAccountMoney(final Context ctx, final String id, final String moneyStr, final String reason) {
        ChaincodeStub stub = ctx.getStub();
        return changeAccountMoney(stub, id, moneyStr, reason);
    }

    /**
     * 供 AssetContract 中调用
     *
     * @param stub
     * @param id
     * @param moneyStr
     * @param reason
     * @return
     */
    public static Account changeAccountMoney(ChaincodeStub stub, String id, String moneyStr, String reason) {
        String key = getAccountKey(id);
        long money = parseMoney(moneyStr);
        String json = ContractUtils.validateExistenceAndGetValueJson(LOG, stub, key, "Account");
        Account account = ChaincodeJsonUtils.jsonToObject(json, Account.class);
        long moneyAfter = account.getMoney() + money;
        account = new Account(id, moneyAfter, account.getAssets());
        stub.putStringState(key, ChaincodeJsonUtils.objectToJson(account));

        long timestamp = stub.getTxTimestamp().toEpochMilli();
        CompositeKey compKey = stub.createCompositeKey(ACCOUNT_MONEY_HISTORY_KEY, id, String.valueOf(timestamp));
        stub.putStringState(compKey.toString(), ChaincodeJsonUtils.objectToJson(
                new AccountMoneyHistory(id, money, moneyAfter, reason, timestamp)));

        return account;
    }

    private static long parseMoney(String money) {
        try {
            return Long.parseLong(money);
        } catch (Exception e) {
            // 解析 share 失败
            ContractUtils.throwChaincodeException(LOG,
                    String.format("can not parse money %s", money),
                    "MONEY_PARSE_FAIL");
        }
        // 走不到这里
        return 0L;
    }

    @Transaction
    public AccountMoneyTransHistory transferAccountMoney(final Context ctx, final String from, final String to,
                                                         final String moneyStr) {
        if (moneyStr.startsWith("-")) {
            ContractUtils.throwChaincodeException(LOG, "transfer money can't be negative", "TRANSFER_MONEY_NEGATIVE");
        }
        ChaincodeStub stub = ctx.getStub();
        changeAccountMoney(stub, from, "-" + moneyStr, "TRANS_OUT");
        changeAccountMoney(stub, to, moneyStr, "TRANS_IN");
        long money = parseMoney(moneyStr);
        long timestamp = stub.getTxTimestamp().toEpochMilli();
        CompositeKey compKey = stub.createCompositeKey(
                ACCOUNT_MONEY_TRANS_HISTORY_KEY, from, to, String.valueOf(timestamp));
        AccountMoneyTransHistory history = new AccountMoneyTransHistory(from, to, money, timestamp);
        stub.putStringState(compKey.toString(), ChaincodeJsonUtils.objectToJson(history));
        return history;
    }

    @Transaction
    public String queryAccountMoneyTransFromHistory(final Context ctx, final String id) {
        return ContractUtils.history(AccountMoneyTransHistory.class, ctx, ACCOUNT_MONEY_TRANS_HISTORY_KEY, id);
    }

    @Transaction
    public String queryAccountBonusHistory(final Context ctx, final String id) {
        return ContractUtils.history(AccountBonusHistory.class, ctx, ACCOUNT_BONUS_HISTORY_KEY, id);
    }

    @Transaction
    public String queryAccountBonusFromAssetHistory(final Context ctx, final String accountId, final String assetId) {
        return ContractUtils.history(AccountBonusHistory.class, ctx, ACCOUNT_BONUS_HISTORY_KEY, accountId, assetId);
    }
}

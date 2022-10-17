package com.zerox.application.dao.impl;

import com.zerox.application.dao.AccountDao;
import com.zerox.application.dao.FabricManager;
import com.zerox.application.entity.domain.AccountBonusHistoryDO;
import com.zerox.application.entity.domain.AccountDO;
import com.zerox.application.entity.domain.AccountMoneyHistoryDO;
import com.zerox.application.entity.domain.AccountMoneyTransHistoryDO;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.zerox.constant.ContractConstants.ACCOUNT_CONTRACT_NAME;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 17:11
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    private final FabricManager fabricManager;

    public AccountDaoImpl(FabricManager fabricManager) {
        this.fabricManager = fabricManager;
    }

    @Override
    public AccountDO createAccount(String id) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AccountDO.class, ACCOUNT_CONTRACT_NAME, "createAccount", id);
    }

    @Override
    public AccountDO queryAccount(String id) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AccountDO.class, ACCOUNT_CONTRACT_NAME, "queryAccount", id);
    }

    @Override
    public AccountDO changeAccountMoney(String id, String money, String reason) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AccountDO.class, ACCOUNT_CONTRACT_NAME, "changeAccountMoney", id, money, reason);
    }

    @Override
    public List<AccountMoneyHistoryDO> queryAccountMoneyHistory(String id) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AccountMoneyHistoryDO.class, ACCOUNT_CONTRACT_NAME, "queryAccountMoneyHistory", id);
    }

    @Override
    public AccountMoneyTransHistoryDO transferAccountMoney(String from, String to, String money) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AccountMoneyTransHistoryDO.class, ACCOUNT_CONTRACT_NAME, "transferAccountMoney", from, to, money);
    }

    @Override
    public List<AccountMoneyTransHistoryDO> queryAccountMoneyTransFromHistory(String id) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AccountMoneyTransHistoryDO.class, ACCOUNT_CONTRACT_NAME, "queryAccountMoneyTransFromHistory", id);
    }

    @Override
    public List<AccountBonusHistoryDO> queryAccountBonusHistory(String id) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AccountBonusHistoryDO.class, ACCOUNT_CONTRACT_NAME, "queryAccountBonusHistory", id);
    }

    @Override
    public List<AccountBonusHistoryDO> queryAccountBonusFromAssetHistory(String accountId, String assetId) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AccountBonusHistoryDO.class, ACCOUNT_CONTRACT_NAME, "queryAccountBonusFromAssetHistory", accountId, assetId);
    }
}

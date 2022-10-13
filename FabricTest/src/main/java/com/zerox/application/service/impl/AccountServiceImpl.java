package com.zerox.application.service.impl;

import com.zerox.application.dao.AccountDao;
import com.zerox.application.entity.domain.AccountBonusHistoryDO;
import com.zerox.application.entity.domain.AccountDO;
import com.zerox.application.entity.domain.AccountMoneyHistoryDO;
import com.zerox.application.entity.domain.AccountMoneyTransHistoryDO;
import com.zerox.application.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 17:27
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public AccountDO createAccount(String id) {
        return accountDao.createAccount(id);
    }

    @Override
    public AccountDO changeAccountMoney(String id, String money, String reason) {
        return accountDao.changeAccountMoney(id, money, reason);
    }

    @Override
    public List<AccountMoneyHistoryDO> queryAccountMoneyHistory(String id) {
        return accountDao.queryAccountMoneyHistory(id);
    }

    @Override
    public AccountMoneyTransHistoryDO transferAccountMoney(String from, String to, String money) {
        return accountDao.transferAccountMoney(from, to, money);
    }

    @Override
    public List<AccountMoneyTransHistoryDO> queryAccountMoneyTransFromHistory(String id) {
        return accountDao.queryAccountMoneyTransFromHistory(id);
    }

    @Override
    public List<AccountBonusHistoryDO> queryAccountBonusHistory(String id) {
        return accountDao.queryAccountBonusHistory(id);
    }

    @Override
    public List<AccountBonusHistoryDO> queryAccountBonusFromAssetHistory(String accountId, String assetId) {
        return accountDao.queryAccountBonusFromAssetHistory(accountId, assetId);
    }
}

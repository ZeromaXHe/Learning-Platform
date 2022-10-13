package com.zerox.application.service;

import com.zerox.application.entity.domain.AccountBonusHistoryDO;
import com.zerox.application.entity.domain.AccountDO;
import com.zerox.application.entity.domain.AccountMoneyHistoryDO;
import com.zerox.application.entity.domain.AccountMoneyTransHistoryDO;

import java.util.List;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 17:26
 */
public interface AccountService {
    AccountDO createAccount(String id);

    AccountDO changeAccountMoney(String id, String money, String reason);

    List<AccountMoneyHistoryDO> queryAccountMoneyHistory(String id);

    AccountMoneyTransHistoryDO transferAccountMoney(String from, String to, String money);

    List<AccountMoneyTransHistoryDO> queryAccountMoneyTransFromHistory(String id);

    List<AccountBonusHistoryDO> queryAccountBonusHistory(String id);

    List<AccountBonusHistoryDO> queryAccountBonusFromAssetHistory(String accountId, String assetId);
}

package com.zerox.application.dao;

import com.zerox.application.entity.domain.AccountBonusHistoryDO;
import com.zerox.application.entity.domain.AccountDO;
import com.zerox.application.entity.domain.AccountMoneyHistoryDO;
import com.zerox.application.entity.domain.AccountMoneyTransHistoryDO;
import com.zerox.smartcontract.entity.AccountMoneyHistory;
import com.zerox.smartcontract.entity.AccountMoneyTransHistory;

import java.util.List;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 17:11
 */
public interface AccountDao {
    AccountDO createAccount(String id, String timestamp);

    AccountDO queryAccount(String id);

    AccountDO changeAccountMoney(String id, String money, String reason, String timestamp);

    List<AccountMoneyHistoryDO> queryAccountMoneyHistory(String id);

    AccountMoneyTransHistoryDO transferAccountMoney(String from, String to, String money, String timestamp);

    List<AccountMoneyTransHistoryDO> queryAccountMoneyTransFromHistory(String id);

    List<AccountBonusHistoryDO> queryAccountBonusHistory(String id);

    List<AccountBonusHistoryDO> queryAccountBonusFromAssetHistory(String accountId, String assetId);
}

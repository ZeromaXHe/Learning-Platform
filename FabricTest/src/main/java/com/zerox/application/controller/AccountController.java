package com.zerox.application.controller;

import com.zerox.application.entity.domain.AccountBonusHistoryDO;
import com.zerox.application.entity.domain.AccountDO;
import com.zerox.application.entity.domain.AccountMoneyHistoryDO;
import com.zerox.application.entity.domain.AccountMoneyTransHistoryDO;
import com.zerox.application.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 17:26
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/create")
    public AccountDO createAccount(@RequestParam("id") String id,
                                   @RequestParam("timestamp") String timestamp) {
        return accountService.createAccount(id, timestamp);
    }

    @RequestMapping("/query")
    public AccountDO createAccount(@RequestParam("id") String id) {
        return accountService.queryAccount(id);
    }

    @RequestMapping("/changeMoney")
    public AccountDO changeAccountMoney(@RequestParam("id") String id,
                                        @RequestParam("money") String money,
                                        @RequestParam("reason") String reason,
                                        @RequestParam("timestamp") String timestamp) {
        return accountService.changeAccountMoney(id, money, reason, timestamp);
    }

    @RequestMapping("/queryMoneyHistory")
    public List<AccountMoneyHistoryDO> queryAccountMoneyHistory(@RequestParam("id") String id) {
        return accountService.queryAccountMoneyHistory(id);
    }

    @RequestMapping("/transferMoney")
    public AccountMoneyTransHistoryDO transferAccountMoney(@RequestParam("from") String from,
                                                           @RequestParam("to") String to,
                                                           @RequestParam("money") String money,
                                                           @RequestParam("timestamp") String timestamp) {
        return accountService.transferAccountMoney(from, to, money, timestamp);
    }

    @RequestMapping("/queryMoneyTransFromHistory")
    public List<AccountMoneyTransHistoryDO> queryAccountMoneyTransFromHistory(@RequestParam("id") String id) {
        return accountService.queryAccountMoneyTransFromHistory(id);
    }

    @RequestMapping("/queryBonusHistory")
    public List<AccountBonusHistoryDO> queryAccountBonusHistory(@RequestParam("id") String id) {
        return accountService.queryAccountBonusHistory(id);
    }

    @RequestMapping("/queryBonusFromAssetHistory")
    public List<AccountBonusHistoryDO> queryAccountBonusFromAssetHistory(@RequestParam("accountId") String accountId,
                                                                         @RequestParam("assetId") String assetId) {
        return accountService.queryAccountBonusFromAssetHistory(accountId, assetId);
    }
}

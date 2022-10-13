package com.zerox.application.controller;

import com.zerox.application.entity.domain.AccountBonusHistoryDO;
import com.zerox.application.entity.domain.AccountDO;
import com.zerox.application.entity.domain.AccountMoneyHistoryDO;
import com.zerox.application.entity.domain.AccountMoneyTransHistoryDO;
import com.zerox.application.service.AccountService;
import com.zerox.utils.JsonUtils;
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
    public String createAccount(@RequestParam("id") String id) {
        AccountDO account = accountService.createAccount(id);
        return JsonUtils.objectToJson(account);
    }

    @RequestMapping("/changeMoney")
    public String changeAccountMoney(@RequestParam("id") String id,
                                     @RequestParam("money") String money,
                                     @RequestParam("reason") String reason) {
        AccountDO account = accountService.changeAccountMoney(id, money, reason);
        return JsonUtils.objectToJson(account);
    }

    @RequestMapping("/queryMoneyHistory")
    public String queryAccountMoneyHistory(@RequestParam("id") String id) {
        List<AccountMoneyHistoryDO> list = accountService.queryAccountMoneyHistory(id);
        return JsonUtils.objectToJson(list);
    }

    @RequestMapping("/queryMoneyHistory")
    public String transferAccountMoney(@RequestParam("from") String from,
                                       @RequestParam("to") String to,
                                       @RequestParam("money") String money) {
        AccountMoneyTransHistoryDO history = accountService.transferAccountMoney(from, to, money);
        return JsonUtils.objectToJson(history);
    }

    @RequestMapping("/queryMoneyTransFromHistory")
    public String queryAccountMoneyTransFromHistory(@RequestParam("id") String id) {
        List<AccountMoneyTransHistoryDO> list = accountService.queryAccountMoneyTransFromHistory(id);
        return JsonUtils.objectToJson(list);
    }

    @RequestMapping("/queryBonusHistory")
    public String queryAccountBonusHistory(@RequestParam("id") String id) {
        List<AccountBonusHistoryDO> list = accountService.queryAccountBonusHistory(id);
        return JsonUtils.objectToJson(list);
    }

    @RequestMapping("/queryBonusFromAssetHistory")
    public String queryAccountBonusFromAssetHistory(@RequestParam("accountId") String accountId,
                                                    @RequestParam("assetId") String assetId) {
        List<AccountBonusHistoryDO> list = accountService.queryAccountBonusFromAssetHistory(accountId, assetId);
        return JsonUtils.objectToJson(list);
    }
}

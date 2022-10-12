package com.zerox.application.service.impl;

import com.zerox.application.dao.AccountDao;
import com.zerox.application.service.AccountService;
import org.springframework.stereotype.Service;

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


}

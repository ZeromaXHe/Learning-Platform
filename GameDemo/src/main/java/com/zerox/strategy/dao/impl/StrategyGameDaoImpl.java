package com.zerox.strategy.dao.impl;

import com.zerox.strategy.dao.StrategyGameDao;

/**
 * @Author: zhuxi
 * @Time: 2021/5/31 14:32
 * @Description:
 * @Modified By: zhuxi
 */
public class StrategyGameDaoImpl implements StrategyGameDao {
    private static StrategyGameDaoImpl singleton = new StrategyGameDaoImpl();

    public static StrategyGameDao getSingletonInstance() {
        return singleton;
    }

    private StrategyGameDaoImpl() {
    }
}

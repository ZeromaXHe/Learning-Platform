package com.zerox.strategy.controller;

import com.zerox.strategy.entity.MapSquare;
import com.zerox.strategy.service.StrategyGameService;
import com.zerox.strategy.service.impl.StrategyGameServiceImpl;

/**
 * @Author: zhuxi
 * @Time: 2021/5/31 14:22
 * @Description:
 * @Modified By: zhuxi
 */
public class StrategyGameController {
    private static StrategyGameController singleton = new StrategyGameController();

    public static StrategyGameController getSingletonInstance() {
        return singleton;
    }

    private StrategyGameService strategyGameService = StrategyGameServiceImpl.getSingletonInstance();

    public void initMap(int x, int y) {
        strategyGameService.initMap(x, y);
    }

    public MapSquare[][] getMap(){
        return strategyGameService.getMap();
    }
}

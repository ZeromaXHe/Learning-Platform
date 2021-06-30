package com.zerox.strategy.service.impl;

import com.zerox.strategy.entity.MapSquare;
import com.zerox.strategy.entity.gamemap.CircleGameMap;
import com.zerox.strategy.entity.gamemap.GameMap;
import com.zerox.strategy.service.StrategyGameService;

/**
 * @Author: zhuxi
 * @Time: 2021/5/31 14:23
 * @Description:
 * @Modified By: zhuxi
 */
public class StrategyGameServiceImpl implements StrategyGameService {
    private static StrategyGameServiceImpl singleton = new StrategyGameServiceImpl();

    public static StrategyGameService getSingletonInstance() {
        return singleton;
    }

    private GameMap gameMap;

    private StrategyGameServiceImpl() {

    }

    @Override
    public void initMap(int x, int y) {
        gameMap = new CircleGameMap(x, y);
    }

    @Override
    public MapSquare[][] getMap() {
        return gameMap.getMap();
    }
}

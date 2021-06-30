package com.zerox.strategy.service;

import com.zerox.strategy.entity.MapSquare;

/**
 * @Author: zhuxi
 * @Time: 2021/5/31 14:22
 * @Description:
 * @Modified By: zhuxi
 */
public interface StrategyGameService {
    void initMap(int x, int y);

    MapSquare[][] getMap();
}

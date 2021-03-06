package com.zerox.strategy.entity.gamemap;

import com.zerox.strategy.constant.GeoConstant;
import com.zerox.strategy.entity.MapSquare;
import com.zerox.strategy.entity.MapSquareGeo;

/**
 * @Author: zhuxi
 * @Time: 2021/6/30 10:35
 * @Description:
 * @Modified By: zhuxi
 */
public class CircleGameMap implements GameMap {
    private MapSquare[][] map;

    public CircleGameMap(int x, int y) {
        this.map = new MapSquare[x][y];
        int middleX = x / 2;
        int middleY = y / 2;
        int min = Math.min(x, y);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int distanceSquare = (i - middleX) * (i - middleX) + (j - middleY) * (j - middleY);

                MapSquareGeo geo;
                if (distanceSquare <= min * min / 400) {
                    // 距离中心的距离 小于 最小坐标的一半的1/10
                    geo = new MapSquareGeo(GeoConstant.MOUNTAIN_MIN_HEIGHT);
                } else if (distanceSquare <= min * min / 36) {
                    // 距离中心的距离 小于 最小坐标的一半的1/3
                    geo = new MapSquareGeo(GeoConstant.HILL_MIN_HEIGHT);
                } else if (distanceSquare <= min * min / 9) {
                    // 距离中心的距离 小于 最小坐标的一半的2/3
                    geo = new MapSquareGeo(GeoConstant.PLAIN_MIN_HEIGHT);
                } else {
                    geo = new MapSquareGeo(-1000);
                }

                this.map[i][j] = new MapSquare(i, j, geo);
            }
        }
    }

    public MapSquare[][] getMap() {
        return map;
    }
}

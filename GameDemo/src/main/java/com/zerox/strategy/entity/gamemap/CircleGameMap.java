package com.zerox.strategy.entity.gamemap;

import com.zerox.strategy.entity.MapSquare;
import com.zerox.strategy.entity.MapSquareGeo;

/**
 * @Author: zhuxi
 * @Time: 2021/6/30 10:35
 * @Description:
 * @Modified By: zhuxi
 */
public class CircleGameMap implements GameMap {
    MapSquare[][] map;

    public CircleGameMap(int x, int y) {
        this.map = new MapSquare[x][y];
        int middleX = x / 2;
        int middleY = y / 2;
        int min = Math.min(x, y);
        int max = Math.max(x, y);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int distanceSquare = (i - middleX) * (i - middleX) + (j - middleY) * (j - middleY);

                MapSquareGeo geo;
                if (distanceSquare <= min * min / 400) {
                    // 距离中心的距离 小于 最小坐标的一半的1/10
                    geo = new MapSquareGeo(5000);
                } else if (distanceSquare <= min * min / 36) {
                    // 距离中心的距离 小于 最小坐标的一半的1/3
                    geo = new MapSquareGeo(2000);
                } else if (distanceSquare <= min * min / 9) {
                    // 距离中心的距离 小于 最小坐标的一半的2/3
                    geo = new MapSquareGeo(500);
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

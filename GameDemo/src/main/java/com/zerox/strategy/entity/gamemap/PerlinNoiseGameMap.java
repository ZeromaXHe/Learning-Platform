package com.zerox.strategy.entity.gamemap;

import com.zerox.gameFramework.utils.PerlinNoiseUtils;
import com.zerox.strategy.constant.GeoConstant;
import com.zerox.strategy.entity.MapSquare;
import com.zerox.strategy.entity.MapSquareGeo;

/**
 * @Author: zhuxi
 * @Time: 2021/7/1 10:48
 * @Description:
 * @Modified By: zhuxi
 */
public class PerlinNoiseGameMap implements GameMap {
    private MapSquare[][] map;

    public PerlinNoiseGameMap(int x, int y) {
        this.map = new MapSquare[x][y];
        double max = 0;
        double[][] perlin = new double[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                perlin[i][j] = PerlinNoiseUtils.perlin2D(i / 50.0, j / 50.0);
//                System.out.println("i:" + i + ", j:" + j + ", perlin:" + perlin[i][j]);
                if (perlin[i][j] > max) {
                    max = perlin[i][j];
                }
            }
        }
//        System.out.println("max:" + max);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                MapSquareGeo geo;
                if (perlin[i][j] >= max * 19 / 20) {
                    geo = new MapSquareGeo(GeoConstant.MOUNTAIN_MIN_HEIGHT);
                } else if (perlin[i][j] >= max * 2 / 3) {
                    geo = new MapSquareGeo(GeoConstant.HILL_MIN_HEIGHT);
                } else if (perlin[i][j] >= 0) {
                    // 距离中心的距离 小于 最小坐标的一半的2/3
                    geo = new MapSquareGeo(GeoConstant.PLAIN_MIN_HEIGHT);
                } else {
                    geo = new MapSquareGeo(-1000);
                }
                this.map[i][j] = new MapSquare(i, j, geo);
            }
        }

    }

    @Override
    public MapSquare[][] getMap() {
        return map;
    }
}

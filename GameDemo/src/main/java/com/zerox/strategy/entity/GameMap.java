package com.zerox.strategy.entity;

/**
 * @Author: zhuxi
 * @Time: 2021/5/31 14:43
 * @Description:
 * @Modified By: zhuxi
 */
public class GameMap {
    private MapSquare[][] map;

    public GameMap(int x, int y) {
        this.map = new MapSquare[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.map[i][j] = new MapSquare(i, j);
            }
        }
    }

    public MapSquare[][] getMap() {
        return map;
    }
}

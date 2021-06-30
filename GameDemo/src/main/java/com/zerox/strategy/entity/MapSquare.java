package com.zerox.strategy.entity;

/**
 * @Author: zhuxi
 * @Time: 2021/5/31 14:26
 * @Description:
 * @Modified By: zhuxi
 */
public class MapSquare {
    private Integer x;
    private Integer y;
    private MapSquareGeo geo;
    private MapSquareUnit unit;
    private MapSquareBuild build;
    private MapSquarePop pop;

    public MapSquare(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public MapSquare(Integer x, Integer y, MapSquareGeo geo) {
        this.x = x;
        this.y = y;
        this.geo = geo;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public MapSquareGeo getGeo() {
        return geo;
    }
}

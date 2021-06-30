package com.zerox.strategy.entity;

/**
 * @Author: zhuxi
 * @Time: 2021/5/31 15:14
 * @Description:
 * @Modified By: zhuxi
 */
public class MapSquareGeo {
    private GeoEnum type;
    private int height;

    public MapSquareGeo(int height) {
        this.height = height;
        this.type = determineTypeByHeight(height);
    }

    public enum GeoEnum {
        OCEAN,
        PLAIN,
        HILL,
        MOUNTAIN
    }

    private GeoEnum determineTypeByHeight(int height) {
        if (height <= 0) {
            return GeoEnum.OCEAN;
        } else if (height <= 1000) {
            return GeoEnum.PLAIN;
        } else if (height <= 4000) {
            return GeoEnum.HILL;
        } else {
            return GeoEnum.MOUNTAIN;
        }
    }

    public GeoEnum getType() {
        return type;
    }

    public int getHeight() {
        return height;
    }
}

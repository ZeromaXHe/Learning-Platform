package com.zerox.strategy.entity;

import com.zerox.strategy.constant.GeoConstant;

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
        if (height < GeoConstant.PLAIN_MIN_HEIGHT) {
            return GeoEnum.OCEAN;
        } else if (height < GeoConstant.HILL_MIN_HEIGHT) {
            return GeoEnum.PLAIN;
        } else if (height < GeoConstant.MOUNTAIN_MIN_HEIGHT) {
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

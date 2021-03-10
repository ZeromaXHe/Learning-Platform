package com.zerox.gobang.entity.vo;

import com.zerox.gobang.constant.GoBangEnum;

/**
 * @Author: zhuxi
 * @Time: 2021/3/10 18:35
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangStepResultVO {
    private GoBangEnum buttonSide;
    private GoBangEnum dominateSide;

    public GoBangEnum getButtonSide() {
        return buttonSide;
    }

    public void setButtonSide(GoBangEnum buttonSide) {
        this.buttonSide = buttonSide;
    }

    public GoBangEnum getDominateSide() {
        return dominateSide;
    }

    public void setDominateSide(GoBangEnum dominateSide) {
        this.dominateSide = dominateSide;
    }
}

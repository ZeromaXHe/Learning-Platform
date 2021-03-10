package com.zerox.gobang.entity.vo;

/**
 * @Author: zhuxi
 * @Time: 2021/3/10 19:32
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangRegretResultVO {
    private int[] regretStep;
    private boolean moreRegret;

    public int[] getRegretStep() {
        return regretStep;
    }

    public void setRegretStep(int[] regretStep) {
        this.regretStep = regretStep;
    }

    public boolean isMoreRegret() {
        return moreRegret;
    }

    public void setMoreRegret(boolean moreRegret) {
        this.moreRegret = moreRegret;
    }
}

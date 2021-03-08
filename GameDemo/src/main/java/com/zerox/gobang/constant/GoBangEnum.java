package com.zerox.gobang.constant;

/**
 * @Author: zhuxi
 * @Time: 2021/3/5 11:50
 * @Description:
 * @Modified By: zhuxi
 */
public enum GoBangEnum {
    EMPTY("空",0),
    BLACK("黑方", 1),
    WHITE("白方", 2),
    TIE("平局", 3);
    private String name;
    private int val;

    GoBangEnum(String name, int val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public int getVal() {
        return val;
    }
}

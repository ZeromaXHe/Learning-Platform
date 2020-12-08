package com.zerox.entity;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 14:53
 * @Description: 结果返回码
 * @Modified By: zhuxiaohe
 */
public enum ResultCode {
    SUCCESS(0, "查询成功"),
    NONE(1, "查询无结果"),
    ERROR(2, "查询异常");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

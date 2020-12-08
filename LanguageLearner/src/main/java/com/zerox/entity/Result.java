package com.zerox.entity;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 14:44
 * @Description: 查询结果
 * @Modified By: zhuxiaohe
 */
public class Result<T> {
    /**
     * 返回码
     */
    private ResultCode resultCode;
    /**
     * 数据
     */
    private T data;

    public Result(T data) {
        this.resultCode = ResultCode.SUCCESS;
        this.data = data;
    }

    public Result(ResultCode code, T data) {
        this.resultCode = code;
        this.data = data;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

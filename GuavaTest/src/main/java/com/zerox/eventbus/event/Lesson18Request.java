package com.zerox.eventbus.event;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:53
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18Request {
    private final String orderNo;

    public Lesson18Request(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    @Override
    public String toString() {
        return "Lesson18Request{" +
                "orderNo='" + orderNo + '\'' +
                '}';
    }
}

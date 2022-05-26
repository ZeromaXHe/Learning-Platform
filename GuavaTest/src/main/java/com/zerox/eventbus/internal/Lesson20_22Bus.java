package com.zerox.eventbus.internal;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:46
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Lesson20_22Bus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void post(Object event, String topic);

    void close();

    String getBusName();
}

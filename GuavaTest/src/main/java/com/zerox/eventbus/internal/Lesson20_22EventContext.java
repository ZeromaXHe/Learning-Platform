package com.zerox.eventbus.internal;

import java.lang.reflect.Method;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:53
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Lesson20_22EventContext {
    String getSource();
    Object getSubscriber();
    Method getSubscribe();
    Object getEvent();
}

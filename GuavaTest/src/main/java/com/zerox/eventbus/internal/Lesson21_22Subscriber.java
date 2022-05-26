package com.zerox.eventbus.internal;

import java.lang.reflect.Method;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 17:19
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson21_22Subscriber {
    private final Object subscribeObject;
    private final Method method;
    private boolean disable = false;

    public Lesson21_22Subscriber(Object subscribeObject, Method method) {
        this.subscribeObject = subscribeObject;
        this.method = method;
    }

    public Object getSubscribeObject() {
        return subscribeObject;
    }

    public Method getMethod() {
        return method;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}

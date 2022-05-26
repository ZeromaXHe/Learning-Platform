package com.zerox.eventbus;

import com.google.common.eventbus.EventBus;
import com.zerox.eventbus.listener.Lesson18DeadEventListener;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18DeadEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus("DeadEventBus"){
            @Override
            public String toString() {
                return "DEAD-EVENT-BUS";
            }
        };
        final Lesson18DeadEventListener deadEventListener = new Lesson18DeadEventListener();
        eventBus.register(deadEventListener);
        eventBus.post("hello");
        eventBus.unregister(deadEventListener);
        eventBus.post("hello2");
    }
}

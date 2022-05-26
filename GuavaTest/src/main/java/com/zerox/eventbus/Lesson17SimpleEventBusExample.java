package com.zerox.eventbus;

import com.google.common.eventbus.EventBus;
import com.zerox.eventbus.listener.Lesson17SimpleListener;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 13:46
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17SimpleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new Lesson17SimpleListener());
        System.out.println("post the simple event.");
        eventBus.post("Simple Event");
    }
}

package com.zerox.eventbus;

import com.google.common.eventbus.EventBus;
import com.zerox.eventbus.listener.Lesson17MultipleEventListeners;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 13:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17MultipleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new Lesson17MultipleEventListeners());
        System.out.println("post the string event.");
        eventBus.post("this is a String Event");
        System.out.println("post the int event.");
        eventBus.post(1000);
    }
}

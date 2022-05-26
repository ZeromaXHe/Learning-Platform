package com.zerox.eventbus.test;

import com.zerox.eventbus.internal.Lesson20_22EventBus;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 18:15
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson21MyEventBusExample {
    public static void main(String[] args) {
        Lesson20_22EventBus eventBus = new Lesson20_22EventBus((cause, context) -> {
            cause.printStackTrace();
            System.out.println("-----------");
            System.out.println("source: " + context.getSource());
            System.out.println("subscribe: " + context.getSubscribe());
            System.out.println("event: " + context.getEvent());
            System.out.println("subscriber: " + context.getSubscriber());
        });
        eventBus.register(new Lesson21MySimpleListener());
        eventBus.register(new Lesson21MySimpleListener2());
        eventBus.post("normal message");
        System.out.println("=============");
        eventBus.post("signed-topic message", "alex-topic");
        System.out.println("=============");
        eventBus.post(1000, "alex-topic");
        System.out.println("=============");
        eventBus.post(1000, "test-topic");
    }
}

package com.zerox.eventbus.test;

import com.zerox.eventbus.internal.Lesson22AsyncEventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 18:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson22MyAsyncBusExample {
    public static void main(String[] args) {
        Lesson22AsyncEventBus eventBus = new Lesson22AsyncEventBus((cause, context) -> {
            cause.printStackTrace();
            System.out.println("-----------");
            System.out.println("source: " + context.getSource());
            System.out.println("subscribe: " + context.getSubscribe());
            System.out.println("event: " + context.getEvent());
            System.out.println("subscriber: " + context.getSubscriber());
        }, (ThreadPoolExecutor) Executors.newFixedThreadPool(4));
        eventBus.register(new Lesson21MySimpleListener());
        eventBus.register(new Lesson21MySimpleListener2());
        eventBus.post(1000, "alex-topic");
        System.out.println("=============");
        eventBus.post(1000, "test-topic");
        System.out.println("=============");
        eventBus.post("normal message");
        System.out.println("=============");
        eventBus.post("signed-topic message", "alex-topic");

        // 不 close 的话，线程池不会停
        eventBus.close();
    }
}

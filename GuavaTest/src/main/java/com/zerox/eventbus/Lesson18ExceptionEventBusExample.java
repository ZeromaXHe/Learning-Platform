package com.zerox.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.zerox.eventbus.listener.Lesson18ExceptionListener;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:38
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18ExceptionEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus(new ExceptionHandler());
//        final EventBus eventBus = new EventBus((exception, context) -> {
//            System.out.println("event: " + context.getEvent());
//            System.out.println("eventBus: " + context.getEventBus());
//            System.out.println("subscriber: " + context.getSubscriber());
//            System.out.println("subscriberMethod: " + context.getSubscriberMethod());
//        });
        eventBus.register(new Lesson18ExceptionListener());
        eventBus.post("exception post");
    }

    static class ExceptionHandler implements SubscriberExceptionHandler {
        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            System.out.println("event: " + context.getEvent());
            System.out.println("eventBus: " + context.getEventBus());
            System.out.println("subscriber: " + context.getSubscriber());
            System.out.println("subscriberMethod: " + context.getSubscriberMethod());
        }
    }
}

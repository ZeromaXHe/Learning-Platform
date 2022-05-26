package com.zerox.eventbus;

import com.google.common.eventbus.EventBus;
import com.zerox.eventbus.event.Lesson17Apple;
import com.zerox.eventbus.event.Lesson17Fruit;
import com.zerox.eventbus.listener.Lesson17FruitEaterListener;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:07
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17InheritEventsEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new Lesson17FruitEaterListener());
        System.out.println("post Apple");
        eventBus.post(new Lesson17Apple("apple"));
        System.out.println("post Fruit");
        eventBus.post(new Lesson17Fruit("apple"));
    }
}

package com.zerox.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.zerox.eventbus.listener.Lesson17SimpleListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 19:18
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson22AsyncEventBusExample {
    public static void main(String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(4);
        AsyncEventBus eventBus = new AsyncEventBus(executor);
        eventBus.register(new Lesson17SimpleListener());
        eventBus.post("hello");
        executor.shutdown();
    }
}

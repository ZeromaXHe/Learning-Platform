package com.zerox.eventbus.internal;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 18:45
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson22AsyncEventBus extends Lesson20_22EventBus {
    public Lesson22AsyncEventBus(String busName, Lesson20_22EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super(busName, exceptionHandler, executor);
    }
    public Lesson22AsyncEventBus(Lesson20_22EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super("default-async", exceptionHandler, executor);
    }

    public Lesson22AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        // 还是不默认 Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2) 了
        super(busName, null, executor);
    }

    public Lesson22AsyncEventBus(ThreadPoolExecutor executor) {
        super("default-async", null, executor);
    }
}

package com.zerox.eventbus.internal;

import java.util.concurrent.Executor;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson20_22EventBus implements Lesson20_22Bus {
    private final Lesson20_22Registry registry = new Lesson20_22Registry();

    private String busName;

    private final static String DEFAULT_BUS_NAME = "default";

    private final static String DEFAULT_TOPIC = "default-topic";

//    private final Lesson20_22EventExceptionHandler exceptionHandler;

    private final Lesson20_22Dispatcher dispatcher;

    public Lesson20_22EventBus() {
        this(DEFAULT_BUS_NAME, null, Lesson20_22Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public Lesson20_22EventBus(String busName) {
        this(busName, null, Lesson20_22Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public Lesson20_22EventBus(String busName, Lesson20_22EventExceptionHandler exceptionHandler) {
        this(busName, exceptionHandler, Lesson20_22Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public Lesson20_22EventBus(Lesson20_22EventExceptionHandler exceptionHandler) {
        this(DEFAULT_BUS_NAME, exceptionHandler, Lesson20_22Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    Lesson20_22EventBus(String busName, Lesson20_22EventExceptionHandler exceptionHandler, Executor executor) {
        this.busName = busName;
//        this.exceptionHandler = exceptionHandler;
        this.dispatcher = Lesson20_22Dispatcher.newDispatcher(exceptionHandler, executor);
    }

    @Override
    public void register(Object subscriber) {
        this.registry.bind(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        this.registry.unbind(subscriber);
    }

    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }

    @Override
    public void post(Object event, String topic) {
        this.dispatcher.dispatch(this, registry, event, topic);
    }

    @Override
    public void close() {
        this.dispatcher.close();
    }

    @Override
    public String getBusName() {
        return this.busName;
    }
}

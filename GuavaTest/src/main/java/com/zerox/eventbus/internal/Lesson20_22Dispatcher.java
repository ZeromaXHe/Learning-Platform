package com.zerox.eventbus.internal;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson20_22Dispatcher {
    private final Executor executorService;
    private final Lesson20_22EventExceptionHandler exceptionHandler;
    public final static Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;
    public final static Executor PER_THREAD_EXECUTOR_SERVICE = PerThreadExecutorService.INSTANCE;

    public Lesson20_22Dispatcher(Executor executorService, Lesson20_22EventExceptionHandler exceptionHandler) {
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    public void dispatch(Lesson20_22Bus bus, Lesson20_22Registry registry, Object event, String topic) {
        final ConcurrentLinkedQueue<Lesson21_22Subscriber> subscribers = registry.scanSubscriber(topic);
        if (subscribers == null) {
            if (exceptionHandler != null) {
                exceptionHandler.handle(new IllegalArgumentException("The topic " + topic + " not bind yet"),
                        new BaseMyEventContext(bus.getBusName(), null, event));
            }
            return;
        }
        subscribers.stream()
                .filter(subscriber -> !subscriber.isDisable())
                .filter(subscriber -> {
                    final Method method = subscriber.getMethod();
                    final Class<?> aClass = method.getParameterTypes()[0];
                    return aClass.isAssignableFrom(event.getClass());
                })
                .forEach(subscriber -> realInvokeSubscribe(subscriber, event, bus));
    }

    private void realInvokeSubscribe(Lesson21_22Subscriber subscriber, Object event, Lesson20_22Bus bus) {
        final Method method = subscriber.getMethod();
        final Object object = subscriber.getSubscribeObject();
        executorService.execute(() -> {
            try {
                method.invoke(object, event);
            } catch (Exception e) {
                if (exceptionHandler != null) {
                    exceptionHandler.handle(e, new BaseMyEventContext(bus.getBusName(), subscriber, event));
                }
            }
        });
    }

    public void close() {
        if (executorService instanceof ExecutorService) {
            ((ExecutorService) executorService).shutdown();
        }
    }

    static Lesson20_22Dispatcher newDispatcher(Lesson20_22EventExceptionHandler exceptionHandler, Executor executor) {
        return new Lesson20_22Dispatcher(executor, exceptionHandler);
    }

    static Lesson20_22Dispatcher seqDispatcher(Lesson20_22EventExceptionHandler exceptionHandler) {
        return new Lesson20_22Dispatcher(SEQ_EXECUTOR_SERVICE, exceptionHandler);
    }

    static Lesson20_22Dispatcher perThreadDispatcher(Lesson20_22EventExceptionHandler exceptionHandler) {
        return new Lesson20_22Dispatcher(PER_THREAD_EXECUTOR_SERVICE, exceptionHandler);
    }

    private static class SeqExecutorService implements Executor {
        private final static SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class PerThreadExecutorService implements Executor {
        private final static PerThreadExecutorService INSTANCE = new PerThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private static class BaseMyEventContext implements Lesson20_22EventContext {
        private final String eventBusName;
        private final Lesson21_22Subscriber subscriber;
        private final Object event;

        public BaseMyEventContext(String eventBusName, Lesson21_22Subscriber subscriber, Object event) {
            this.eventBusName = eventBusName;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public String getSource() {
            return eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return subscriber != null ? subscriber.getSubscribeObject() : null;
        }

        @Override
        public Method getSubscribe() {
            return subscriber != null ? subscriber.getMethod() : null;
        }

        @Override
        public Object getEvent() {
            return event;
        }
    }
}

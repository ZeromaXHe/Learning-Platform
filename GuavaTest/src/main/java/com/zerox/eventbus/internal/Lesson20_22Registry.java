package com.zerox.eventbus.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:48
 * @Description: <pre>
 *     topic1 -> subscriber -> subscribe
 *            -> subscriber -> subscribe
 *            -> subscriber -> subscribe
 *     topic2 -> subscriber -> subscribe
 *            -> subscriber -> subscribe
 *            -> subscriber -> subscribe
 * </pre>
 * @ModifiedBy: zhuxi
 */
class Lesson20_22Registry {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Lesson21_22Subscriber>> subscriberContainer
            = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscriberMethods(subscriber);
        subscribeMethods.forEach(method -> tierSubscriber(subscriber, method));
    }

    private List<Method> getSubscriberMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            final Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    .filter(method -> method.isAnnotationPresent(Lesson20_22Subscribe.class)
                            && method.getParameterCount() == 1
                            && (method.getModifiers() & Modifier.PUBLIC) != 0)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }

    private void tierSubscriber(Object subscriber, Method method) {
        final Lesson20_22Subscribe subscribe = method.getDeclaredAnnotation(Lesson20_22Subscribe.class);
        String topic = subscribe.topic();
        subscriberContainer.putIfAbsent(topic, new ConcurrentLinkedQueue<>());
        // 等效的写法
//        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
//        ConcurrentLinkedQueue<Lesson21_22Subscriber> subscribers = subscriberContainer.get(topic);
//        if (subscribers == null) {
//            subscribers = new ConcurrentLinkedQueue<>();
//            subscriberContainer.put(topic, subscribers);
//        }
        subscriberContainer.get(topic).add(new Lesson21_22Subscriber(subscriber, method));
    }

    public void unbind(Object subscriber) {
        subscriberContainer.forEach((key, queue) -> queue.forEach(s -> {
            if (s.getSubscribeObject() == subscriber) {
                s.setDisable(true);
            }
        }));
    }

    public ConcurrentLinkedQueue<Lesson21_22Subscriber> scanSubscriber(final String topic) {
        return subscriberContainer.get(topic);
    }
}

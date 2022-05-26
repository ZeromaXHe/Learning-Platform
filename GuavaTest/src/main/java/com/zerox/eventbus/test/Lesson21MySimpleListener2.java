package com.zerox.eventbus.test;

import com.zerox.eventbus.internal.Lesson20_22Subscribe;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 18:30
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson21MySimpleListener2 {
    @Lesson20_22Subscribe
    public void test1(String x) {
        System.out.println("Lesson21MySimpleListener2.test1:" + x);
    }

    @Lesson20_22Subscribe(topic = "alex-topic")
    public void test2(Integer x) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Lesson21MySimpleListener2.test2:" + x);
    }

    @Lesson20_22Subscribe(topic = "test-topic")
    public void test3(Integer x) {
        throw new RuntimeException();
    }
}

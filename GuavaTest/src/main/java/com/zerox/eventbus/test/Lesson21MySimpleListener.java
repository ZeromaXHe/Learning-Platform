package com.zerox.eventbus.test;

import com.zerox.eventbus.internal.Lesson20_22Subscribe;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 18:14
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson21MySimpleListener {
    @Lesson20_22Subscribe
    public void test1(String x) {
        System.out.println("Lesson21MySimpleListener.test1:" + x);
    }

    @Lesson20_22Subscribe(topic = "alex-topic")
    public void test2(String x) {
        System.out.println("Lesson21MySimpleListener.test2:" + x);
    }
}

package com.zerox.utilities;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/20 14:53
 * @Description: 学习 汪文君Google Guava 第07讲，对比{@link Lesson07ElapsedExample}
 * @Modified By: ZeromaXHe
 */
public class Lesson07StopWatchExample {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson07StopWatchExample.class);

    public static void main(String[] args) throws InterruptedException {
        process("3463542353");
    }

    private static void process(String orderNo) throws InterruptedException {
        LOGGER.info("start process the order [{}]\n", orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MILLISECONDS.sleep(100);

        LOGGER.info("The orderNo [{}] process successful and elapsed [{}]\n",
                orderNo, stopwatch.stop());

    }
}

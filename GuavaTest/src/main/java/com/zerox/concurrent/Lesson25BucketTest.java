package com.zerox.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 10:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson25BucketTest {
    public static void main(String[] args) {
        final Lesson25Bucket bucket = new Lesson25Bucket();
        final AtomicInteger DATA_CREATOR = new AtomicInteger(0);

        IntStream.range(0, 5).forEach(i -> new Thread(() -> {
            for (; ; ) {
                int data = DATA_CREATOR.getAndIncrement();
                bucket.submit(data);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    if (e instanceof IllegalStateException) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }).start());

        IntStream.range(0, 5).forEach(i -> new Thread(() -> {
            for (; ; ) {
                bucket.takeThenConsume(x-> System.out.println(Thread.currentThread() + " consumed " + x));
            }
        }).start());
    }
}

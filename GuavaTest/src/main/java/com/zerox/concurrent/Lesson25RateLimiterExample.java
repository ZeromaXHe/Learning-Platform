package com.zerox.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 10:28
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson25RateLimiterExample {
    private final static RateLimiter LIMITER = RateLimiter.create(0.5);
    private final static Semaphore SEMAPHORE = new Semaphore(1);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
//        IntStream.range(0, 10).forEach(i -> service.submit(Lesson25RateLimiterExample::testLimiter));
        IntStream.range(0, 10).forEach(i -> service.submit(Lesson25RateLimiterExample::testSemaphore));
        service.shutdown();
    }

    private static void testLimiter() {
        System.out.println(Thread.currentThread() + " waiting " + LIMITER.acquire());
    }

    private static void testSemaphore() {
        try {
            SEMAPHORE.acquire();
            System.out.println(Thread.currentThread() + " is coming and do work");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            SEMAPHORE.release();
            System.out.println(Thread.currentThread() + " release the semaphore");
        }
    }
}

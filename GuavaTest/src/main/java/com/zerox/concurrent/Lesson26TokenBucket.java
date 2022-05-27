package com.zerox.concurrent;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 11:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson26TokenBucket {
    private AtomicInteger phoneNumbers = new AtomicInteger(0);
    private final static int LIMIT = 100;
    private RateLimiter rateLimiter = RateLimiter.create(10);

    private final int saleLimit;

    public Lesson26TokenBucket() {
        this(LIMIT);
    }

    public Lesson26TokenBucket(int saleLimit) {
        this.saleLimit = saleLimit;
    }

    public int buy() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        final boolean success = rateLimiter.tryAcquire(10, TimeUnit.SECONDS);
        if (success) {
            if (phoneNumbers.get() >= saleLimit) {
                throw new IllegalStateException("No phone available, please wait for next time.");
            }
            final int phoneNo = phoneNumbers.getAndIncrement();
            handleOrder();
            System.out.println(Thread.currentThread() + " user get the phone: " + phoneNo + ", ELT: " + stopwatch.stop());
            return phoneNo;
        } else {
            stopwatch.stop();
            throw new RuntimeException("Sorry, occur exception when buying phone");
        }
    }

    private void handleOrder() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

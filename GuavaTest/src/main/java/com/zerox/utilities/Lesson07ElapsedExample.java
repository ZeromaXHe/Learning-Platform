package com.zerox.utilities;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/20 14:27
 * @Description: 学习 汪文君Google Guava 第07讲
 * @Modified By: ZeromaXHe
 */
public class Lesson07ElapsedExample {
    public static void main(String[] args) throws InterruptedException {
        process("3463542353");
    }

    private static void process(String orderNo) throws InterruptedException {
        System.out.printf("start process the order %s\n", orderNo);
        long currentTime = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);

        System.out.printf("The orderNo %s process successful and elapsed %d ns\n",
                orderNo, System.nanoTime() - currentTime);

    }
}

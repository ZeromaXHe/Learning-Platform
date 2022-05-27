package com.zerox.concurrent;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 11:15
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson26TokenBucketExample {
    public static void main(String[] args) {
        final Lesson26TokenBucket tokenBucket = new Lesson26TokenBucket();
        for (int i = 0; i < 110; i++) {
            new Thread(tokenBucket::buy).start();
        }
    }
}

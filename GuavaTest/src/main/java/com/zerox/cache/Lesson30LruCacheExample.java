package com.zerox.cache;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 14:55
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson30LruCacheExample {
    /**
     * 在 JVM 设置中添加 -Xmx128M -Xms64M -XX:+PrintGCDetails
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
//        final Lesson28LinkedHashLruCache<String, byte[]> cache = new Lesson28LinkedHashLruCache<>(100);
        final Lesson30SoftLruCache<String, byte[]> cache = new Lesson30SoftLruCache<>(100);
        for (int i = 0; i < 100; i++) {
            cache.put(String.valueOf(i), new byte[2 * 1024 * 1024]);
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println("The " + i + " entity is put");
        }
    }
}

package com.zerox.cache;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 12:01
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson28LinkedHashLruCacheExample {
    public static void main(String[] args) {
        Lesson28LruCache<String, String> cache = new Lesson28LinkedHashLruCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache);
        cache.put("4", "4");
        System.out.println(cache);
        System.out.println(cache.get("2"));
        System.out.println(cache);
    }
}

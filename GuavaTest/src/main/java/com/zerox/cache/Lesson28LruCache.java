package com.zerox.cache;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 11:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Lesson28LruCache<K, V> {
    void put(K key, V value);

    V get(K key);

    void remove(K key);

    int size();

    void clear();

    int limit();
}

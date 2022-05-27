package com.zerox.cache;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 13:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson28LinkedListLruCache<K, V> implements Lesson28LruCache<K, V> {
    private final int limit;

    private final LinkedList<K> keys = new LinkedList<>();

    private final Map<K, V> cache = new HashMap<>();

    public Lesson28LinkedListLruCache(int limit) {
        this.limit = limit;
    }

    @Override
    public void put(K key, V value) {
        K newKey = Preconditions.checkNotNull(key);
        V newValue = Preconditions.checkNotNull(value);
        if (keys.size() >= limit) {
            K oldestKey = keys.removeFirst();
            cache.remove(oldestKey);
        }
        keys.addLast(key);
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        boolean exist = keys.remove(key);
        if (!exist) {
            return null;
        }
        keys.addLast(key);
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        boolean exist = keys.remove(key);
        if (exist) {
            cache.remove(key);
        }
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void clear() {
        this.keys.clear();
        this.cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (K k : keys) {
            builder.append(k).append('-').append(cache.get(k)).append(';');
        }
        return builder.toString();
    }
}

package com.zerox.cache;

import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 11:52
 * @Description: 非线程安全
 * @ModifiedBy: zhuxi
 */
public class Lesson28LinkedHashLruCache<K, V> implements Lesson28LruCache<K, V> {
    private static class InternalLruCache<K, V> extends LinkedHashMap<K, V> {
        private final int limit;

        public InternalLruCache(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }

    private final int limit;

    private InternalLruCache<K, V> internalLruCache;

    public Lesson28LinkedHashLruCache(int limit) {
        Preconditions.checkArgument(limit > 0, "The limit must be bigger than zero");
        this.limit = limit;
        this.internalLruCache = new InternalLruCache<>(limit);
    }

    @Override
    public void put(K key, V value) {
        this.internalLruCache.put(key, value);
    }

    @Override
    public V get(K key) {
        return this.internalLruCache.get(key);
    }

    @Override
    public void remove(K key) {
        this.internalLruCache.remove(key);
    }

    @Override
    public int size() {
        return this.internalLruCache.size();
    }

    @Override
    public void clear() {
        this.internalLruCache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return internalLruCache.toString();
    }
}

package com.zerox.cache;

import com.google.common.base.Preconditions;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 14:59
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson30SoftLruCache<K, V> implements Lesson28LruCache<K, V> {
    private static class InternalLruCache<K, V> extends LinkedHashMap<K, SoftReference<V>> {
        private final int limit;

        public InternalLruCache(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, SoftReference<V>> eldest) {
            return this.size() > limit;
        }
    }

    private final int limit;

    private InternalLruCache<K, V> cache;

    public Lesson30SoftLruCache(int limit) {
        Preconditions.checkArgument(limit > 0, "The limit must be bigger than zero");
        this.limit = limit;
        this.cache = new InternalLruCache<>(limit);
    }

    @Override
    public void put(K key, V value) {
        this.cache.put(key, new SoftReference<>(value));
    }

    @Override
    public V get(K key) {
        SoftReference<V> reference = this.cache.get(key);
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    @Override
    public void remove(K key) {
        this.cache.remove(key);
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public void clear() {
        this.cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }
}

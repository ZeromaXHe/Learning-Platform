package com.zerox.cache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.Weigher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 15:13
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson31_34CacheLoaderTest {
    private boolean load = false;

    @Test
    public void testBasic() throws ExecutionException, InterruptedException {
        LoadingCache<String, Lesson31_32Employee> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterAccess(30, TimeUnit.MILLISECONDS)
                .build(createCacheLoader());
        Lesson31_32Employee employee = cache.get("Alex");
        Assert.assertThat(employee, CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
        employee = cache.get("Alex");
        Assert.assertThat(employee, CoreMatchers.notNullValue());
        assertLoadedFromCache();

        TimeUnit.MILLISECONDS.sleep(31);
        employee = cache.get("Alex");
        Assert.assertThat(employee, CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
    }

    @Test
    public void testEvictionBySize() {
        CacheLoader<String, Lesson31_32Employee> cacheLoader = createCacheLoader();
        LoadingCache<String, Lesson31_32Employee> cache = CacheBuilder.newBuilder().maximumSize(3).build(cacheLoader);

        cache.getUnchecked("Alex");
        assertLoadedFromDbThenReset();
        cache.getUnchecked("Jack");
        assertLoadedFromDbThenReset();
        cache.getUnchecked("Gavin");
        assertLoadedFromDbThenReset();

        Assert.assertThat(cache.size(), CoreMatchers.equalTo(3L));
        cache.getUnchecked("Susan");
        Assert.assertThat(cache.getIfPresent("Alex"), CoreMatchers.nullValue());
        Assert.assertThat(cache.getIfPresent("Susan"), CoreMatchers.notNullValue());
    }

    @Test
    public void testEvictionByWeight() {
        Weigher<String, Lesson31_32Employee> weigher = (key, employee) ->
                employee.getName().length() + employee.getDept().length() + employee.getEmpId().length();
        LoadingCache<String, Lesson31_32Employee> cache = CacheBuilder.newBuilder()
                .maximumWeight(45)
                .concurrencyLevel(1)
                .weigher(weigher)
                .build(createCacheLoader());

        cache.getUnchecked("Kevin");
        assertLoadedFromDbThenReset();
        cache.getUnchecked("Allen");
        assertLoadedFromDbThenReset();
        cache.getUnchecked("Gavin");
        assertLoadedFromDbThenReset();

        Assert.assertEquals(3L, cache.size());
        // 注意：这里拿了一次 Kevin
        Assert.assertNotNull(cache.getIfPresent("Kevin"));

        cache.getUnchecked("Jason");
        assertLoadedFromDbThenReset();
        Assert.assertEquals(3L, cache.size());
        // 前面拿过一次 Kevin，所以最老的是 Allen
        Assert.assertNull(cache.getIfPresent("Allen"));
    }

    // ============================【第 32 课内容】===================================
    @Test
    public void testEvictionByAccessTime() throws InterruptedException {
        LoadingCache<String, Lesson31_32Employee> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(createCacheLoader());
        Assert.assertThat(cache.getUnchecked("Alex"), CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
        Assert.assertThat(cache.size(), CoreMatchers.equalTo(1L));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertThat(cache.getIfPresent("Alex"), CoreMatchers.nullValue());

        Assert.assertThat(cache.getUnchecked("Guava"), CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertThat(cache.getIfPresent("Guava"), CoreMatchers.notNullValue());
        assertLoadedFromCache();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertThat(cache.getIfPresent("Guava"), CoreMatchers.notNullValue());
        assertLoadedFromCache();
    }

    @Test
    public void testEvictionByWriteTime() throws InterruptedException {
        LoadingCache<String, Lesson31_32Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(createCacheLoader());
        Assert.assertThat(cache.getUnchecked("Alex"), CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
        Assert.assertThat(cache.size(), CoreMatchers.equalTo(1L));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertThat(cache.getIfPresent("Alex"), CoreMatchers.nullValue());

        Assert.assertThat(cache.getUnchecked("Guava"), CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertThat(cache.getIfPresent("Guava"), CoreMatchers.notNullValue());
        assertLoadedFromCache();
        TimeUnit.MILLISECONDS.sleep(900);
        Assert.assertThat(cache.getIfPresent("Guava"), CoreMatchers.notNullValue());
        assertLoadedFromCache();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertThat(cache.getIfPresent("Guava"), CoreMatchers.nullValue());
    }

    @Test
    public void testWeakKey() throws InterruptedException {
        LoadingCache<String, Lesson31_32Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build(createCacheLoader());
        Assert.assertThat(cache.getUnchecked("Alex"), CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
        Assert.assertThat(cache.getUnchecked("Guava"), CoreMatchers.notNullValue());
        assertLoadedFromDbThenReset();
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertThat(cache.getIfPresent("Alex"), CoreMatchers.nullValue());
        Assert.assertThat(cache.getIfPresent("Guava"), CoreMatchers.nullValue());
    }

    /**
     * 在 JVM 设置中添加 -Xmx128M -Xms64M -XX:+PrintGCDetails
     * 可以使用 java jdk 路径下的 bin/jvisualvm.exe 查看虚拟机运行情况
     *
     * @throws InterruptedException
     */
    @Test
    public void testSoftKey() throws InterruptedException {
        LoadingCache<String, Lesson31_32Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .softValues()
                .build(createCacheLoader());
//        int i = 0;
//        for (; ; ) {
        for (int i = 0; i < 100; i++) {
            cache.put("Alex" + i, findEmployeeByName("Alex" + i));
            System.out.println("The employee [" + i + "] is put into cache");
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }

    // ============================【第 33 课内容】===================================
    @Test
    public void testLoadNullValue() {
        CacheLoader<String, Lesson31_32Employee> cacheLoader = CacheLoader.from(k ->
                k.equals("null") ? null : new Lesson31_32Employee(k, k, k));
        LoadingCache<String, Lesson31_32Employee> loadingCache = CacheBuilder.newBuilder().build(cacheLoader);

        Lesson31_32Employee alex = loadingCache.getUnchecked("Alex");
        Assert.assertThat(alex, CoreMatchers.notNullValue());
        Assert.assertThat(alex.getName(), CoreMatchers.equalTo("Alex"));

        try {
            // 会抛出 InvalidCacheLoadException
            Assert.assertThat(loadingCache.getUnchecked("null"), CoreMatchers.notNullValue());
            Assert.fail("should not process to here");
        } catch (Exception e) {
            Assert.assertThat(e instanceof CacheLoader.InvalidCacheLoadException, CoreMatchers.is(true));
        }
    }

    @Test
    public void testLoadNullValueUseOptional() {
        // 用的是 guava 的 Optional
        CacheLoader<String, Optional<Lesson31_32Employee>> loader = new CacheLoader<String, Optional<Lesson31_32Employee>>() {
            @Override
            public Optional<Lesson31_32Employee> load(String key) throws Exception {
                if ("null".equals(key)) {
                    return Optional.absent();
                } else {
                    return Optional.fromNullable(new Lesson31_32Employee(key, key, key));
                }
            }
        };

        LoadingCache<String, Optional<Lesson31_32Employee>> cache = CacheBuilder.newBuilder().build(loader);

        Assert.assertThat(cache.getUnchecked("Alex").get(), CoreMatchers.notNullValue());
        Assert.assertThat(cache.getUnchecked("null").orNull(), CoreMatchers.nullValue());
        Lesson31_32Employee def = cache.getUnchecked("null").or(new Lesson31_32Employee("default", "default", "default"));
        Assert.assertThat(def.getName(), CoreMatchers.equalTo("default"));
    }

    @Test
    public void testCacheRefresh() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        CacheLoader<String, Long> cacheLoader = CacheLoader.from(k -> {
            counter.incrementAndGet();
            return System.currentTimeMillis();
        });
        LoadingCache<String, Long> cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(cacheLoader);
        Long result1 = cache.getUnchecked("Alex");
        TimeUnit.SECONDS.sleep(2);
        Long result2 = cache.getUnchecked("Alex");
        Assert.assertThat(result1.longValue() != result2.longValue(), CoreMatchers.is(true));
        Assert.assertThat(counter.get(), CoreMatchers.equalTo(2));
    }

    @Test
    public void testCachePreload() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);

        Map<String, String> preData = new HashMap<>();
        preData.put("alex", "ALEX");
        // 违背规则
        preData.put("hello", "hello");

        cache.putAll(preData);
        Assert.assertThat(cache.size(), CoreMatchers.equalTo(2L));
        Assert.assertThat(cache.getUnchecked("alex"), CoreMatchers.equalTo("ALEX"));
        Assert.assertThat(cache.getUnchecked("hello"), CoreMatchers.equalTo("hello"));
    }

    @Test
    public void testCacheRemovedNotification() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        RemovalListener<String, String> listener = notification -> {
            if (notification.wasEvicted()) {
                RemovalCause cause = notification.getCause();
                Assert.assertThat(cause, Is.is(RemovalCause.SIZE));
                Assert.assertThat(notification.getKey(), CoreMatchers.equalTo("Alex"));
            }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build(loader);
        cache.getUnchecked("Alex");
        cache.getUnchecked("Each");
        cache.getUnchecked("Jack");
        cache.getUnchecked("Jenny");
    }

    // ============================【第 34 课内容】===================================
    @Test
    public void testCacheStat() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().recordStats().build(loader);
        commonStatTest(cache);
    }

    @Test
    public void testCacheSpec() {
        String spec = "maximumSize=5,recordStats";
        CacheBuilderSpec builderSpec = CacheBuilderSpec.parse(spec);
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.from(builderSpec).build(loader);
        commonStatTest(cache);
    }

    private void commonStatTest(LoadingCache<String, String> cache) {
        Assert.assertThat(cache.getUnchecked("alex"), CoreMatchers.equalTo("ALEX"));
        CacheStats stats = cache.stats();
        Assert.assertThat(stats.hitCount(), CoreMatchers.equalTo(0L));
        Assert.assertThat(stats.missCount(), CoreMatchers.equalTo(1L));

        Assert.assertThat(cache.getUnchecked("alex"), CoreMatchers.equalTo("ALEX"));
        // 不可变对象
        CacheStats stats2 = cache.stats();
        Assert.assertThat(stats2.hitCount(), CoreMatchers.equalTo(1L));
        Assert.assertThat(stats2.missCount(), CoreMatchers.equalTo(1L));
        Assert.assertEquals(0.5, stats2.hitRate(), 0.001);
        Assert.assertEquals(0.5, stats2.missRate(), 0.001);
    }

    private void assertLoadedFromCache() {
        Assert.assertThat(false, CoreMatchers.equalTo(load));
    }

    private void assertLoadedFromDbThenReset() {
        Assert.assertThat(true, CoreMatchers.equalTo(load));
        this.load = false;
    }

    private Lesson31_32Employee findEmployeeByName(final String name) {
        System.out.println("The employee " + name + " is loaded from DB");
        load = true;
        return new Lesson31_32Employee(name, name, name);
    }

    private CacheLoader<String, Lesson31_32Employee> createCacheLoader() {
//        return CacheLoader.from(this::findEmployeeByName);
        return new CacheLoader<String, Lesson31_32Employee>() {
            @Override
            public Lesson31_32Employee load(String key) throws Exception {
                return findEmployeeByName(key);
            }
        };
    }
}

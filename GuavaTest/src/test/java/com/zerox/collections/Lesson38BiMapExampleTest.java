package com.zerox.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 9:37
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson38BiMapExampleTest {
    @Test
    public void testCreateAndPut() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "2");
        biMap.put("1", "3");
        Assert.assertThat(biMap.containsKey("1"), CoreMatchers.is(true));
        Assert.assertThat(biMap.size(), CoreMatchers.equalTo(1));

        try {
            // 不能有重复的值，抛出错误：java.lang.IllegalArgumentException: value already present: 3
            biMap.put("2", "3");
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBiMapInverse() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "2");
        biMap.put("2", "3");
        biMap.put("3", "4");
        Assert.assertThat(biMap.containsKey("1"), CoreMatchers.is(true));
        Assert.assertThat(biMap.containsKey("2"), CoreMatchers.is(true));
        Assert.assertThat(biMap.containsKey("3"), CoreMatchers.is(true));
        Assert.assertThat(biMap.size(), CoreMatchers.equalTo(3));
        BiMap<String, String> inverse = biMap.inverse();
        Assert.assertThat(inverse.containsKey("2"), CoreMatchers.is(true));
        Assert.assertThat(inverse.containsKey("3"), CoreMatchers.is(true));
        Assert.assertThat(inverse.containsKey("4"), CoreMatchers.is(true));
        Assert.assertThat(inverse.size(), CoreMatchers.equalTo(3));
    }

    @Test
    public void testCreateAndForcePut() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "2");
        Assert.assertThat(biMap.containsKey("1"), CoreMatchers.is(true));
        biMap.forcePut("2", "2");
        Assert.assertThat(biMap.containsKey("1"), CoreMatchers.is(false));
        Assert.assertThat(biMap.containsKey("2"), CoreMatchers.is(true));
    }
}

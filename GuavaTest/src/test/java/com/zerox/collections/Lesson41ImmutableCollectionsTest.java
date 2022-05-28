package com.zerox.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 10:17
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson41ImmutableCollectionsTest {
    @Test(expected = UnsupportedOperationException.class)
    public void testOf() {
        ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
        Assert.assertThat(list, CoreMatchers.notNullValue());
        list.add(4);
        Assert.fail();
    }

    @Test
    public void testCopy() {
        Integer[] array = {1, 2, 3, 4, 5};
        System.out.println(ImmutableList.copyOf(array));
    }

    @Test
    public void testBuilder() {
        ImmutableList<Object> list = ImmutableList.builder()
                .add(1)
                .add(2, 3, 4)
                .addAll(Arrays.asList(5, 6))
                .build();
        System.out.println(list);
    }

    @Test
    public void testImmutableMap() {
        ImmutableMap<String, String> map = ImmutableMap.of("Java", "1.8", "Scala", "2.3");
        ImmutableMap<String, String> map2 = ImmutableMap.<String, String>builder()
                .put("Oracle", "12c")
                .put("MySQL", "7.0")
                .build();
        System.out.println(map);
        System.out.println(map2);
        try {
            map.put("Scala", "2.3.0");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e instanceof UnsupportedOperationException, CoreMatchers.is(true));
        }
    }
}

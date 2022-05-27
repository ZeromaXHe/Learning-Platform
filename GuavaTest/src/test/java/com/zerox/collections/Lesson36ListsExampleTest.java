package com.zerox.collections;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 17:49
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson36ListsExampleTest {
    @Test
    public void testCartesianProduct() {
        List<List<String>> result = Lists.cartesianProduct(
                Lists.newArrayList("1", "2"), Lists.newArrayList("A", "B"));
        System.out.println(result);
    }

    @Test
    public void testTransform() {
        ArrayList<String> sourceList = Lists.newArrayList("Scala", "Guava", "Lists");
        List<String> transform = Lists.transform(sourceList, String::toUpperCase);
        System.out.println(transform);
    }

    @Test
    public void testNewArrayListWithCapacity() {
        ArrayList<String> result = Lists.newArrayListWithCapacity(2);
        result.add("x");
        result.add("y");
        result.add("z");
        System.out.println(result);
    }

    @Test
    public void testNewArrayListWithExpectedSize() {
        ArrayList<String> result = Lists.newArrayListWithExpectedSize(2);
        result.add("x");
        result.add("y");
        result.add("z");
        System.out.println(result);
    }

    @Test
    public void testReverse() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3");
        Assert.assertThat(Joiner.on(",").join(list), CoreMatchers.equalTo("1,2,3"));
        List<String> result = Lists.reverse(list);
        Assert.assertThat(Joiner.on(",").join(result), CoreMatchers.equalTo("3,2,1"));
    }

    @Test
    public void testPartition() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4");
        List<List<String>> result = Lists.partition(list, 3);
        System.out.println(result);
    }
}

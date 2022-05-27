package com.zerox.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 18:24
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson37SetsExampleTest {
    @Test
    public void testCreate() {
        HashSet<Integer> set = Sets.newHashSet(1, 2, 3, 1);
        Assert.assertThat(set.size(), CoreMatchers.equalTo(3));

        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 1);
        Assert.assertThat(list.size(), CoreMatchers.equalTo(4));

        HashSet<Integer> set2 = Sets.newHashSet(list);
        Assert.assertThat(set2.size(), CoreMatchers.equalTo(3));
    }

    @Test
    public void testCartesianProduct() {
        Set<List<Integer>> set = Sets.cartesianProduct(Sets.newHashSet(1, 2), Sets.newHashSet(3, 4), Sets.newHashSet(5, 6));
        System.out.println(set);
    }

    @Test
    public void testCombinations() {
        HashSet<Integer> set = Sets.newHashSet(1, 2, 3);
        Set<Set<Integer>> combinations = Sets.combinations(set, 1);
        Set<Set<Integer>> combinations2 = Sets.combinations(set, 2);
        Set<Set<Integer>> combinations3 = Sets.combinations(set, 3);
        combinations.forEach(System.out::println);
        System.out.println("===================");
        combinations2.forEach(System.out::println);
        System.out.println("===================");
        combinations3.forEach(System.out::println);
    }

    @Test
    public void testDifference() {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 4, 5);
        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        System.out.println(difference);
        Sets.SetView<Integer> difference2 = Sets.difference(set2, set1);
        System.out.println(difference2);
    }

    @Test
    public void testIntersection() {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 4, 5);
        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
        System.out.println(intersection);
    }

    @Test
    public void testUnion() {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 4, 5);
        Sets.SetView<Integer> union = Sets.union(set1, set2);
        System.out.println(union);
    }
}

package com.zerox.collections;

import com.google.common.collect.Ordering;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 10:30
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson41OrderingExampleTest {
    @Test
    public void testJDKOrder() {
        List<Integer> list = Arrays.asList(1, 5, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test(expected = NullPointerException.class)
    public void testJDKOrderIssue() {
        List<Integer> list = Arrays.asList(1, 5, null, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void testOrderNaturalByNullFirst() {
        List<Integer> list = Arrays.asList(1, 5, null, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list, Ordering.natural().nullsFirst());
        System.out.println(list);
    }

    @Test
    public void testOrderNaturalByNullLast() {
        List<Integer> list = Arrays.asList(1, 5, null, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list, Ordering.natural().nullsLast());
        System.out.println(list);
    }

    @Test
    public void testOrderNatural() {
        List<Integer> list = Arrays.asList(1, 5, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Assert.assertThat(Ordering.natural().isOrdered(list), CoreMatchers.is(true));
    }

    @Test
    public void testOrderReverse() {
        List<Integer> list = Arrays.asList(1, 5, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list, Ordering.natural().reverse());
        System.out.println(list);
        Assert.assertThat(Ordering.natural().isOrdered(list), CoreMatchers.is(false));
        Assert.assertThat(Ordering.natural().reverse().isOrdered(list), CoreMatchers.is(true));
    }


}

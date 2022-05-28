package com.zerox.collections;

import com.google.common.collect.BoundType;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 9:56
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson39_40RangeExampleTest {
    @Test
    public void testClosedRange() {
        Range<Integer> closed = Range.closed(0, 9);
        System.out.println(closed);

        Assert.assertThat(closed.contains(5), CoreMatchers.is(true));
        Assert.assertThat(closed.lowerEndpoint(), CoreMatchers.equalTo(0));
        Assert.assertThat(closed.upperEndpoint(), CoreMatchers.equalTo(9));
    }

    @Test
    public void testOpenRange() {
        Range<Integer> open = Range.open(0, 9);
        System.out.println(open);

        Assert.assertThat(open.contains(5), CoreMatchers.is(true));
        Assert.assertThat(open.contains(0), CoreMatchers.is(false));
        Assert.assertThat(open.contains(9), CoreMatchers.is(false));
        Assert.assertThat(open.lowerEndpoint(), CoreMatchers.equalTo(0));
        Assert.assertThat(open.upperEndpoint(), CoreMatchers.equalTo(9));
    }

    @Test
    public void testClosedOpenRange() {
        Range<Integer> closedOpen = Range.closedOpen(0, 9);
        System.out.println(closedOpen);

        Assert.assertThat(closedOpen.contains(5), CoreMatchers.is(true));
        Assert.assertThat(closedOpen.contains(0), CoreMatchers.is(true));
        Assert.assertThat(closedOpen.contains(9), CoreMatchers.is(false));
        Assert.assertThat(closedOpen.lowerEndpoint(), CoreMatchers.equalTo(0));
        Assert.assertThat(closedOpen.upperEndpoint(), CoreMatchers.equalTo(9));
    }

    @Test
    public void testOpenClosedRange() {
        Range<Integer> openClosed = Range.openClosed(0, 9);
        System.out.println(openClosed);

        Assert.assertThat(openClosed.contains(5), CoreMatchers.is(true));
        Assert.assertThat(openClosed.contains(0), CoreMatchers.is(false));
        Assert.assertThat(openClosed.contains(9), CoreMatchers.is(true));
        Assert.assertThat(openClosed.lowerEndpoint(), CoreMatchers.equalTo(0));
        Assert.assertThat(openClosed.upperEndpoint(), CoreMatchers.equalTo(9));
    }

    @Test
    public void testGreaterThan() {
        Range<Integer> range = Range.greaterThan(10);
        System.out.println(range);
        Assert.assertThat(range.contains(10), CoreMatchers.is(false));
        Assert.assertThat(range.contains(Integer.MAX_VALUE), CoreMatchers.is(true));
    }

    @Test
    public void testMapRange() {
        TreeMap<String, Integer> treeMap = Maps.newTreeMap();
        treeMap.put("Scala", 1);
        treeMap.put("Guava", 2);
        treeMap.put("Java", 3);
        treeMap.put("Kafka", 4);
        System.out.println(treeMap);

        NavigableMap<String, Integer> result = Maps.subMap(treeMap, Range.closed("G", "Javatest"));
        System.out.println(result);
    }

    @Test
    public void testOtherMethod() {
        Range<Integer> atLeast = Range.atLeast(2);
        System.out.println(atLeast);
        Assert.assertThat(atLeast.contains(2), CoreMatchers.is(true));
        System.out.println(Range.atMost(5));
        System.out.println(Range.all());
        System.out.println(Range.lessThan(10));
        System.out.println(Range.upTo(10, BoundType.OPEN));
        System.out.println(Range.downTo(5, BoundType.CLOSED));
    }

    @Test
    public void testRangeMap() {
        TreeRangeMap<Integer, String> gradeScale = TreeRangeMap.create();
        gradeScale.put(Range.closedOpen(0, 60), "F");
        gradeScale.put(Range.closedOpen(60, 70), "D");
        gradeScale.put(Range.closedOpen(70, 80), "C");
        gradeScale.put(Range.closedOpen(80, 90), "B");
        gradeScale.put(Range.closed(90, 100), "A");

        Assert.assertThat(gradeScale.get(77), CoreMatchers.equalTo("C"));
    }
}

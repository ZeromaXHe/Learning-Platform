package com.zerox.collections;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 17:16
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson35FluentIterableExampleTest {
    @Test
    public void testFilter() {
        FluentIterable<String> fit = build();
        Assert.assertThat(fit.size(), CoreMatchers.equalTo(4));
        FluentIterable<String> result = fit.filter(e -> e != null && e.length() > 4);
        Assert.assertThat(result.size(), CoreMatchers.equalTo(2));
    }

    @Test
    public void testAppend() {
        FluentIterable<String> fit = build();
        Assert.assertThat(fit.size(), CoreMatchers.equalTo(4));
        FluentIterable<String> result = fit.append("APPEND");
        Assert.assertThat(result.size(), CoreMatchers.equalTo(5));
        Assert.assertThat(result.contains("APPEND"), CoreMatchers.is(true));
        FluentIterable<String> result2 = result.append("APPEND2");
        Assert.assertThat(result2.size(), CoreMatchers.equalTo(6));
        Assert.assertThat(result2.contains("APPEND2"), CoreMatchers.is(true));
        Assert.assertThat(result2.contains("Alex"), CoreMatchers.is(true));
    }

    @Test
    public void testMatch() {
        FluentIterable<String> fit = build();
        boolean result = fit.allMatch(e -> e != null && e.length() >= 4);
        Assert.assertThat(result, CoreMatchers.is(true));
        boolean result2 = fit.anyMatch(e -> e != null && e.length() == 5);
        Assert.assertThat(result2, CoreMatchers.is(true));
        Optional<String> result3 = fit.firstMatch(e -> e != null && e.length() == 5);
        Assert.assertThat(result3.isPresent(), CoreMatchers.is(true));
        Assert.assertThat(result3.get(), CoreMatchers.equalTo("Guava"));
    }

    @Test
    public void testFirstLast() {
        FluentIterable<String> fit = build();
        Optional<String> first = fit.first();
        Assert.assertThat(first.isPresent(), CoreMatchers.is(true));
        Assert.assertThat(first.get(), CoreMatchers.equalTo("Alex"));
        Optional<String> last = fit.last();
        Assert.assertThat(last.isPresent(), CoreMatchers.is(true));
        Assert.assertThat(last.get(), CoreMatchers.equalTo("Scala"));
    }

    @Test
    public void testLimit() {
        FluentIterable<String> fit = build();
        FluentIterable<String> limit = fit.limit(3);
        System.out.println(limit);
        Assert.assertThat(limit.contains("Scala"), CoreMatchers.is(false));
        FluentIterable<String> limit2 = fit.limit(300);
        System.out.println(limit2);
        Assert.assertThat(limit2.contains("Scala"), CoreMatchers.is(true));
    }

    @Test
    public void testCopyInto() {
        FluentIterable<String> fit = build();
        ArrayList<String> list = Lists.newArrayList("Java");
        ArrayList<String> result = fit.copyInto(list);
        Assert.assertThat(result.size(), CoreMatchers.equalTo(5));
        Assert.assertThat(result.contains("Scala"), CoreMatchers.is(true));
        Assert.assertThat(result.contains("Java"), CoreMatchers.is(true));
        Assert.assertThat(result == list, CoreMatchers.is(true));
    }

    @Test
    public void testCycle() {
        FluentIterable<String> fit = build();
        FluentIterable<String> cycle = fit.cycle().limit(20);
        System.out.println(cycle);
    }

    @Test
    public void testTransform() {
        FluentIterable<String> fit = build();
        FluentIterable<Integer> transform = fit.transform(e -> e.length());
        System.out.println(transform);
    }

    @Test
    public void testTransfromAndConcat() {
        FluentIterable<String> fit = build();
        ArrayList<Integer> list = Lists.newArrayList(1);
        FluentIterable<Integer> result = fit.transformAndConcat(e -> list);
        System.out.println(result);
    }

    @Test
    public void testTransfromAndConcatInAction() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2);
        FluentIterable<Lesson35Customer> customers = FluentIterable.from(list).transformAndConcat(this::search);
        System.out.println(customers);
    }

    private List<Lesson35Customer> search(int type) {
        if (type == 1) {
            return Lists.newArrayList(new Lesson35Customer(type, "Alex"), new Lesson35Customer(type, "Tina"));
        } else {
            return Lists.newArrayList(new Lesson35Customer(type, "Wang"), new Lesson35Customer(type, "Wen"),
                    new Lesson35Customer(type, "Jun"));
        }
    }

    @Test
    public void testJoin() {
        FluentIterable<String> fit = build();
        String join = fit.join(Joiner.on(','));
        Assert.assertThat(join, CoreMatchers.equalTo("Alex,Wang,Guava,Scala"));
    }

    private FluentIterable<String> build() {
        ArrayList<String> list = Lists.newArrayList("Alex", "Wang", "Guava", "Scala");
        return FluentIterable.from(list);
    }
}

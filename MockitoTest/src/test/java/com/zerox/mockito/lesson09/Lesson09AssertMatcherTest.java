package com.zerox.mockito.lesson09;

import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/29 16:43
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson09AssertMatcherTest {

    @Test
    public void test() {
        int i = 10;
        assertThat(i, equalTo(10));
        assertThat(i, not(equalTo(20)));
        assertThat(i, is(10));
        assertThat(i, is(not(20)));

    }

    @Test
    public void test2() {
        double price = 23.45;
        assertThat(price, either(equalTo(23.45)).or(equalTo(23.54)));
        assertThat(price, both(equalTo(23.45)).and(not(equalTo(23.54))));
        assertThat(price, anyOf(is(23.45), equalTo(23.54), not(35.24)));
        assertThat(price, allOf(is(23.45), not(equalTo(23.54)), not(35.24)));

        assertThat(Stream.of(1, 2, 3).anyMatch(i -> i > 2), equalTo(true));
        assertThat(Stream.of(1, 2, 3).allMatch(i -> i > 0), equalTo(true));
    }

    @Ignore
    @Test
    public void test3() {
        double price = 23.45;
        assertThat("the double value assertion failed", price,
                either(equalTo(23.65)).or(equalTo(23.54)));
    }
}

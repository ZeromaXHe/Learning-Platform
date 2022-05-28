package com.zerox.mockito.lesson07;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 18:34
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson07ArgumentMatcherTest {
    @Test
    public void basicTest() {
        List<Integer> list = Mockito.mock(ArrayList.class);

        Mockito.when(list.get(ArgumentMatchers.eq(0))).thenReturn(100);
        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo(100));
        MatcherAssert.assertThat(list.get(1), CoreMatchers.nullValue());
    }

    /**
     * isA, any
     */
    @Test
    public void testComplex() {
        Foo foo = Mockito.mock(Foo.class);
        Mockito.when(foo.function(ArgumentMatchers.isA(Child1.class))).thenReturn(100);
        int result = foo.function(new Child1());
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(100));

        result = foo.function(new Child2());
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(0));

        Mockito.reset(foo);

        Mockito.when(foo.function(ArgumentMatchers.any(Child1.class))).thenReturn(100);
        result = foo.function(new Child2());
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(100));
    }

    static class Foo {
        int function(Parent p) {
            return p.work();
        }
    }

    interface Parent {
        int work();
    }

    class Child1 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Child2 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
}

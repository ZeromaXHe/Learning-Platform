package com.zerox.mockito.lesson06;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 18:19
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson06SpyingAnnotationTest {
    @Spy
    private List<String> list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSpy() {
        list.add("Mockito");
        list.add("PowerMock");

        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo("Mockito"));
        MatcherAssert.assertThat(list.get(1), CoreMatchers.equalTo("PowerMock"));
        MatcherAssert.assertThat(list.isEmpty(), CoreMatchers.equalTo(false));

        // Spy 可以覆盖实现类的部分方法，其他的都会调用真实的方法
        Mockito.when(list.isEmpty()).thenReturn(true);
        Mockito.when(list.size()).thenReturn(0);

        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo("Mockito"));
        MatcherAssert.assertThat(list.get(1), CoreMatchers.equalTo("PowerMock"));
        MatcherAssert.assertThat(list.isEmpty(), CoreMatchers.equalTo(true));
        MatcherAssert.assertThat(list.size(), CoreMatchers.equalTo(0));
    }
}

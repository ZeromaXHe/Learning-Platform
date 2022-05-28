package com.zerox.mockito.lesson06;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 18:11
 * @Description:
 * @ModifiedBy: zhuxi
 */
@RunWith(MockitoJUnitRunner.class)
public class Lesson06SpyingTest {
    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = Mockito.spy(realList);

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

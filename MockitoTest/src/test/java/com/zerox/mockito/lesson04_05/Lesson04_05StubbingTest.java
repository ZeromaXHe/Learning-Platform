package com.zerox.mockito.lesson04_05;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 17:41
 * @Description:
 * @ModifiedBy: zhuxi
 */
@RunWith(MockitoJUnitRunner.class)
public class Lesson04_05StubbingTest {
    private List<String> list;

    @Before
    public void init() {
        this.list = Mockito.mock(ArrayList.class);
    }

    @Test
    public void howToUseStubbing() {
        Mockito.when(list.get(0)).thenReturn("first");
        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo("first"));

        // Mockito 继承了 ArgumentMatcher
        Mockito.when(list.get(Mockito.anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            Assert.fail();
        } catch (Exception e) {
            MatcherAssert.assertThat(e, CoreMatchers.instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void howToStubVoidMethod() {
        Mockito.doNothing().when(list).clear();
        list.clear();
        Mockito.verify(list, Mockito.times(1)).clear();

        Mockito.doThrow(RuntimeException.class).when(list).clear();
        try {
            list.clear();
            Assert.fail();
        } catch (Exception e) {
            MatcherAssert.assertThat(e, CoreMatchers.instanceOf(RuntimeException.class));
        }
    }

    // =================== 【第 05 课内容】 ===================
    @Test
    public void stubbingDoReturn() {
        Mockito.when(list.get(0)).thenReturn("first");
        Mockito.doReturn("second").when(list).get(1);
        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo("first"));
        MatcherAssert.assertThat(list.get(1), CoreMatchers.equalTo("second"));
    }

    @Test
    public void iterateStubbing() {
        // 等价
        Mockito.when(list.size()).thenReturn(1, 2, 3, 4);
        Mockito.when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);

        MatcherAssert.assertThat(list.size(), CoreMatchers.equalTo(1));
        MatcherAssert.assertThat(list.size(), CoreMatchers.equalTo(2));
        MatcherAssert.assertThat(list.size(), CoreMatchers.equalTo(3));
        MatcherAssert.assertThat(list.size(), CoreMatchers.equalTo(4));
        MatcherAssert.assertThat(list.size(), CoreMatchers.equalTo(4));
    }

    @Test
    public void stubbingWithAnswer() {
        Mockito.when(list.get(Mockito.anyInt())).thenAnswer(invocation -> {
            int index = invocation.getArgument(0, Integer.class);
            return String.valueOf(index * 10);
        });

        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo("0"));
        MatcherAssert.assertThat(list.get(999), CoreMatchers.equalTo("9990"));
    }

    @Test
    public void stubbingWithRealCall() {
        Lesson05StubbingService service = Mockito.mock(Lesson05StubbingService.class);
        System.out.println(service.getClass());
        Mockito.when(service.getS()).thenReturn("Alex");
        MatcherAssert.assertThat(service.getS(), CoreMatchers.equalTo("Alex"));
        Mockito.when(service.getI()).thenCallRealMethod();
        MatcherAssert.assertThat(service.getI(), CoreMatchers.equalTo(10));
    }

    @After
    public void destory() {
        Mockito.reset(this.list);
    }
}

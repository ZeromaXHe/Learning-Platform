package com.zerox.mockito.lesson08;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/29 16:20
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
@RunWith(MockitoJUnitRunner.class)
public class Lesson08WildcardArgumentMatcherTest {
    @Mock
    private Lesson08SimpleService service;

    @Test
    public void wildcardMathod1() {
        Mockito.when(service.method1(Mockito.anyInt(), Mockito.anyString(), Mockito.anyCollection(),
                Mockito.isA(Serializable.class))).thenReturn(100);
        int result = service.method1(1, "Alex", Collections.emptyList(), "Mockito");
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(100));

        result = service.method1(1, "Wang", Collections.emptyList(), "MockitoForJava");
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(100));
    }

    @Test
    public void wildcardMathod1WithSpec() {
        Mockito.when(service.method1(Mockito.anyInt(), Mockito.eq("Alex"), Mockito.anyCollection(),
                Mockito.isA(Serializable.class))).thenReturn(100);
        Mockito.when(service.method1(Mockito.anyInt(), Mockito.eq("Wang"), Mockito.anyCollection(),
                Mockito.isA(Serializable.class))).thenReturn(200);
        int result = service.method1(1, "Alex", Collections.emptyList(), "Mockito");
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(100));

        result = service.method1(1, "Wang", Collections.emptyList(), "MockitoForJava");
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(200));

        result = service.method1(1, "Test", Collections.emptyList(), "MockitoForJava");
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(0));
    }

    @Test
    public void wildcardMathod2() {
        Mockito.doNothing().when(service).method2(Mockito.anyInt(), Mockito.anyString(), Mockito.anyCollection(),
                Mockito.isA(Serializable.class));
        service.method2(1, "Alex", Collections.emptyList(), "Mockito");
        Mockito.verify(service, Mockito.times(1))
                .method2(1, "Alex", Collections.emptyList(), "Mockito");
        Mockito.verify(service, Mockito.times(1)).method2(Mockito.anyInt(),
                Mockito.eq("Alex"), Mockito.anyCollection(), Mockito.isA(Serializable.class));
        Mockito.verify(service, Mockito.times(0)).method2(Mockito.anyInt(),
                Mockito.eq("Wang"), Mockito.anyCollection(), Mockito.isA(Serializable.class));
    }

    @After
    public void destroy() {
        Mockito.reset(service);
    }
}

package com.zerox.mockito.lesson03;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 17:31
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson03DeepMockTest {
//    @Mock
//    private Lesson03Entity entity;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Lesson03Service service;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeepMock() {
//        Mockito.when(service.get()).thenReturn(entity);

        Lesson03Entity entity = service.get();
        entity.foo();
    }
}

package com.zerox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/26 23:51
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
@RunWith(MockitoJUnitRunner.class)
public class Lesson05MockitoRunnerTest {
    @Mock
    private List<String> list;

    @Test
    public void shouldAddElementToListCorrect() {
        // given
        when(list.add("java")).thenReturn(true);
        when(list.size()).thenReturn(10);
        // when
        // then
        assertEquals(10, list.size());
        assertTrue(list.add("java"));
    }
}

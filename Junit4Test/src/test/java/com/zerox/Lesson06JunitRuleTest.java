package com.zerox;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/26 23:55
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
@RunWith(Theories.class)
public class Lesson06JunitRuleTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

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

    @DataPoints
    public static int[] data() {
        return new int[]{1, 10, 100};
    }

    @Theory
    public void sumTwoNumericGreaterThanThird(int a, int b) {
        assertTrue(a + b > a);
        System.out.printf("%d+%d>%d\n", a, b, a);
    }
}

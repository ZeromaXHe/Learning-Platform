package com.zerox;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/26 23:43
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
@RunWith(Theories.class)
public class Lesson05JUnit4TheoriesTest {
    /**
     * 类似笛卡尔积的方式（还包括自己和自己）把所有组合循环一遍
     *
     * @return
     */
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

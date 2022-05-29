package com.zerox.mockito.lesson10;

import org.junit.Test;

import static com.zerox.mockito.lesson10.Lesson10CompareNumber.gt;
import static com.zerox.mockito.lesson10.Lesson10CompareNumber.lt;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/29 16:52
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson10SimpleTest {
    @Test
    public void test() {
        assertThat(10, gt(5));
        assertThat(10, lt(15));
        assertThat(10, both(lt(15)).and(gt(5)));
    }
}

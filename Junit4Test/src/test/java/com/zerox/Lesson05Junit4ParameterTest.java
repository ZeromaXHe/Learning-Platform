package com.zerox;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/26 23:36
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
@RunWith(Parameterized.class)
public class Lesson05Junit4ParameterTest {
    /**
     * 必须 public 变量
     */
    @Parameterized.Parameter(0)
    public String literal;
    @Parameterized.Parameter(1)
    public int length;

    @Parameterized.Parameters(name="{index} <===> literal = {0} length = {1}")
    public static Collection<Object[]> data() {
        ArrayList<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"JUnit", 5});
        list.add(new Object[]{"Java", 4});
        list.add(new Object[]{"Programming", 11});
        return list;
    }

    @Test
    public void theLiteralLengthShouldCorrect() {
        Assert.assertEquals(length, literal.length());
    }
}

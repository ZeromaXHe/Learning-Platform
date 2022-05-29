package com.zerox.mockito.lesson08;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/29 16:12
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson08SimpleService {
    public int method1(int i, String s, Collection<?> c, Serializable ser) {
        throw new RuntimeException();
    }

    public void method2(int i, String s, Collection<?> c, Serializable ser) {
        throw new RuntimeException();
    }
}

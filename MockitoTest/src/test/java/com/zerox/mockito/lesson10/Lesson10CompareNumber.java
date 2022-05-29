package com.zerox.mockito.lesson10;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/29 16:53
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson10CompareNumber<T extends Number> extends BaseMatcher<T> {
    private final T value;
    private final Compare<T> compare;

    public Lesson10CompareNumber(T value, boolean greaterThan) {
        this.value = value;
        this.compare = new DefaultNumberCompare<>(greaterThan);
    }

    @Override
    public boolean matches(Object o) {
        return this.compare.compare(value, (T) o);
    }

    @Factory
    public static <T extends Number> Lesson10CompareNumber<T> gt(T value) {
        return new Lesson10CompareNumber<>(value, true);
    }

    @Factory
    public static <T extends Number> Lesson10CompareNumber<T> lt(T value) {
        return new Lesson10CompareNumber<>(value, false);
    }

    private interface Compare<T extends Number> {
        boolean compare(T expected, T actual);
    }

    private static class DefaultNumberCompare<T extends Number> implements Compare<T> {

        private final boolean greaterThan;

        public DefaultNumberCompare(boolean greaterThan) {
            this.greaterThan = greaterThan;
        }

        @Override
        public boolean compare(T expected, T actual) {
            Class<?> clazz = actual.getClass();
            if (clazz == Integer.class) {
                return greaterThan ? (Integer) actual > (Integer) expected : (Integer) actual < (Integer) expected;
            } else if (clazz == Short.class) {
                return greaterThan ? (Short) actual > (Short) expected : (Short) actual < (Short) expected;
            } else if (clazz == Byte.class) {
                return greaterThan ? (Byte) actual > (Byte) expected : (Byte) actual < (Byte) expected;
            } else if (clazz == Double.class) {
                return greaterThan ? (Double) actual > (Double) expected : (Double) actual < (Double) expected;
            } else if (clazz == Float.class) {
                return greaterThan ? (Float) actual > (Float) expected : (Float) actual < (Float) expected;
            } else if (clazz == Long.class) {
                return greaterThan ? (Long) actual > (Long) expected : (Long) actual < (Long) expected;
            } else {
                throw new AssertionError("the number type " + clazz + "not supported");
            }
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compare two numbers failed");
    }
}

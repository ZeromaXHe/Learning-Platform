package Functional_Programming_in_Java.chapter2;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 11:10
 * @Description:
 * @ModifiedBy: zhuxi
 */
@FunctionalInterface
public interface Function2<T, U, V> {
    V apply(T t, U u);
}

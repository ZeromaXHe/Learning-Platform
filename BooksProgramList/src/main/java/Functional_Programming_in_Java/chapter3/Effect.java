package Functional_Programming_in_Java.chapter3;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 13:55
 * @Description: 也许你更喜欢用 Java 8 中的 Consumer 接口。虽然类名起得不太好，但是做事情都一样。
 * @ModifiedBy: zhuxi
 */
public interface Effect<T> {
    void apply(T t);
}
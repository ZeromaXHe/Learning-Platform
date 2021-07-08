package Functional_Programming_in_Java.chapter2;

import java.util.Objects;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 11:14
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Tuple<T, U> {
    public final T _1;
    public final U _2;

    public Tuple(T t, U u) {
        this._1 = Objects.requireNonNull(t);
        this._2 = Objects.requireNonNull(u);
    }

    @Override
    public String toString() {
        return "Tuple{" + _1 + ", " + _2 + '}';
    }
}

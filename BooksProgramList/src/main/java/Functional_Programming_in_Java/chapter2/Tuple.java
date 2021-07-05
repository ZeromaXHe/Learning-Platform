package Functional_Programming_in_Java.chapter2;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 11:14
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Tuple<T, U> {
    public T _1;
    public U _2;

    public Tuple(T _1, U _2) {
        this._1 = _1;
        this._2 = _2;
    }
}

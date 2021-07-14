package Functional_Programming_in_Java.chapter12;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:17
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class StateTuple<A, S> {
    public final A value;
    public final S state;

    public StateTuple(A a, S s) {
        value = a;
        state = s;
    }
}

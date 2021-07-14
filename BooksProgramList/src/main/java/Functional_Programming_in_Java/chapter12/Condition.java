package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Function;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:19
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Condition<I, S> extends Function<StateTuple<I, S>, Boolean> {
}

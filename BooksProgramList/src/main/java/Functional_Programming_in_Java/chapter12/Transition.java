package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Function;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:20
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Transition<A,S> extends Function<StateTuple<A, S>, S> {
}

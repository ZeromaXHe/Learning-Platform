package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Tuple;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 15:01
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface RNG {
    Tuple<Integer, RNG> nextInt();
}

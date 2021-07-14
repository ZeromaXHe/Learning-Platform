package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:05
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class RandomClass<A> extends State<RNG, A> {
    public RandomClass(Function<RNG, Tuple<A, RNG>> run) {
        super(run);
    }

    public static State<RNG, Integer> intRnd = new RandomClass<>(RNG::nextInt);
}

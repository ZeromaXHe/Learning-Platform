package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter4.TailCall;
import Functional_Programming_in_Java.chapter5.List;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 15:05
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Generator {
    public static Tuple<Integer, RNG> integer(RNG rng) {
        return rng.nextInt();
    }

    /**
     * 12.1.2 实现随机数发生器 - 练习12.1 在Generator类中编写一个方法以返回一个小于参数但是大于等于0的随机正整数。
     *
     * @param rng
     * @param limit
     * @return
     */
    public static Tuple<Integer, RNG> integer(RNG rng, int limit) {
        Tuple<Integer, RNG> random = rng.nextInt();
        return new Tuple<>(Math.abs(random._1 % limit), random._2);
    }

    /**
     * 12.1.2 实现随机数发生器 - 练习12.2 编写一个方法以返回由n个随机整数组成的列表。
     * 它还需要返回转换为最新的RNG的当前状态，因此可以用于生成下一个整数。
     *
     * @param rng
     * @param length
     * @return
     */
    public static Tuple<List<Integer>, RNG> integers(RNG rng, int length) {
        Tuple<List<Tuple<Integer, RNG>>, RNG> result = List.range(0, length)
                .foldLeft(new Tuple<>(List.list(), rng), tuple -> i -> {
                    Tuple<Integer, RNG> t = integer(tuple._2);
                    return new Tuple<>(tuple._1.cons(t), t._2);
                });
        List<Integer> list = result._1.map(x -> x._1);
        return new Tuple<>(list, result._2);
    }

    public static Tuple<List<Integer>, RNG> integers2(RNG rng, int length) {
        List<Tuple<Integer, RNG>> result = List.range(0, length)
                .foldLeft(List.list(), lst -> i -> lst.cons(integer(rng)));
        List<Integer> list = result.map(x -> x._1);
        Result<Tuple<List<Integer>, RNG>> result2 = result.headOption().map(tr -> new Tuple<>(list, tr._2));
        return result2.getOrElse(new Tuple<>(List.list(), rng));
    }

    public static Tuple<List<Integer>, RNG> integers3(RNG rng, int length) {
        return integers3_(rng, length, List.list()).eval();
    }

    private static TailCall<Tuple<List<Integer>, RNG>> integers3_(RNG rng, int length, List<Integer> xs) {
        if (length <= 0) {
            return TailCall.ret(new Tuple<>(xs, rng));
        } else {
            Tuple<Integer, RNG> t1 = rng.nextInt();
            return TailCall.sus(() -> integers3_(t1._2, length - 1, xs.cons(t1._1)));
        }
    }
}

package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter5.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 15:28
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Random<A> extends Function<RNG, Tuple<A, RNG>> {
    Random<Integer> intRnd = RNG::nextInt;

    /**
     * 12.2.1 使用状态操作 - 练习12.3 使用map方法生成随机Boolean值。通过在Random接口中创建一个函数来达成。
     */
    Random<Boolean> booleanRnd = Random.map(intRnd, x -> x % 2 == 0);

    /**
     * 12.2.1 使用状态操作 - 练习12.4 实现返回随机生成Double的函数
     */
    Random<Double> doubleRnd = Random.map(intRnd, x -> x / (((double) Integer.MAX_VALUE) + 1.0));

    /**
     * 12.2.3 递归状态操作 - 练习12.7 编写一个flatMap方法并用它实现notMultipleOfFiveRnd
     */
    Random<Integer> notMultipleOfFiveRnd = Random.flatMap(intRnd, x -> {
        int mod = x % 5;
        return mod != 0 ?
                unit(x) :
                Random.notMultipleOfFiveRnd;
    });

    static <A> Random<A> unit(A a) {
        return rng -> new Tuple<>(a, rng);
    }

    /**
     * 12.2.3 递归状态操作 - 练习12.8 通过flatMap实现map和map2
     *
     * @param s
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    static <A, B> Random<B> map(Random<A> s, Function<A, B> f) {
//        return rng -> {
//            Tuple<A, RNG> t = s.apply(rng);
//            return new Tuple<>(f.apply(t._1), t._2);
//        };
        return flatMap(s, a -> unit(f.apply(a)));
    }

    /**
     * 12.2.2 复合状态操作 - 练习12.5 实现一个接收RNG并返回一对整数的函数
     *
     * @param ra
     * @param rb
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    static <A, B, C> Random<C> map2(Random<A> ra, Random<B> rb, Function<A, Function<B, C>> f) {
//        return rng -> {
//            Tuple<A, RNG> t1 = ra.apply(rng);
//            Tuple<B, RNG> t2 = rb.apply(t1._2);
//            return new Tuple<>(f.apply(t1._1).apply(t2._1), t2._2);
//        };
        return flatMap(ra, a -> map(rb, b -> f.apply(a).apply(b)));
    }

    /**
     * 12.2.2 复合状态操作 - 练习12.6 实现一个函数，接收RNG并返回随机生成的整型列表
     *
     * @param rs
     * @param <A>
     * @return
     */
    static <A> Random<List<A>> sequence(List<Random<A>> rs) {
        return rs.foldLeft(unit(List.list()), acc -> r ->
                map2(r, acc, x -> y -> y.cons(x)));
    }

//    Function<Integer, Random<List<Integer>>> integersRnd =
//            length -> sequence(List.fill(length, ()-> Generator.intRnd));

    /**
     * 12.2.3 递归状态操作 - 练习12.7 编写一个flatMap方法并用它实现notMultipleOfFiveRnd
     *
     * @param s
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    static <A, B> Random<B> flatMap(Random<A> s, Function<A, Random<B>> f) {
        return rng -> {
            Tuple<A, RNG> t = s.apply(rng);
            return f.apply(t._1).apply(t._2);
        };
    }

}

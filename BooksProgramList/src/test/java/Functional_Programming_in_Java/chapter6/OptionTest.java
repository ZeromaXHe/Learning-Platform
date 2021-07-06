package Functional_Programming_in_Java.chapter6;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter4.TailCall;
import Functional_Programming_in_Java.chapter5.List;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/6 17:24
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class OptionTest {
    /**
     * 6.3.4 Option的用例 - 练习6.7 用flatMap实现variance函数。
     * 一组值的方差（variance）表示这些值如何分布在平均值周围。
     * 如果所有值都非常接近平均值，则方差较低。当所有值都等于平均值时，方差为0。
     * 一组方差是Math.pow(x-m, 2)的平均值，x为该组中每个元素的值，m为该组值的平均值。
     */
    static Function<List<Double>, Double> sum = ds -> ds.foldLeft(0.0, a -> b -> a + b);

    static Function<List<Double>, Option<Double>> mean = ds ->
            ds.isEmpty() ?
                    Option.none() :
                    Option.some(sum.apply(ds) / ds.length());

    static Function<List<Double>, Option<Double>> variance =
            ds -> mean.apply(ds)
                    .flatMap(m -> mean.apply(ds.map(x -> Math.pow(x - m, 2))));

    public static Double sum(List<Double> ds) {
        return sum_(0.0, ds).eval();
    }

    private static TailCall<Double> sum_(Double acc, List<Double> ds) {
        return ds.isEmpty() ?
                TailCall.ret(acc) :
                TailCall.sus(() -> sum_(acc + ds.head(), ds.tail()));
    }

    public static Option<Double> mean(List<Double> ds) {
        return ds.isEmpty() ?
                Option.none() :
                Option.some(sum(ds) / ds.length());
    }

    public static Option<Double> variance(List<Double> ds) {
        // 有两个原因让函数式方法更易使用。
        // 首先，你不需要在函数名和参数之间编写.apply。
        // 其次，由于不需要写下Function，因此类型更加简短。
        // 正因如此，你应该尽量使用函数式方法而非函数。
        return mean(ds).flatMap(m -> mean(ds.map(x -> Math.pow(x - m, 2))));
    }

    @Test
    public void test6_3_6_practice6_11() {
        // 这生成了预期的结果，但是效率有点低，因为map方法和sequence方法都将调用foldRight。
        Function<Integer, Function<String, Integer>> parseWithRadix =
                radix -> string -> Integer.parseInt(string, radix);
        Function<String, Option<Integer>> parse16 = Option.hlift(parseWithRadix.apply(16));
        List<String> list = List.list("4", "5", "6", "7", "8", "9");
        Option<List<Integer>> result = Option.sequence(list.map(parse16));
    }
}

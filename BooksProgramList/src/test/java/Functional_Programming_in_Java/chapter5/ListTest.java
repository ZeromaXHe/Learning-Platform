package Functional_Programming_in_Java.chapter5;

import Functional_Programming_in_Java.chapter2.Function;

/**
 * @Author: zhuxi
 * @Time: 2021/7/6 14:09
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ListTest {
    public static Integer sum(List<Integer> list) {
        return List.foldRight(list, 0, x -> y -> x + y);
    }

    public static Double product(List<Double> list) {
        return List.foldRight(list, 1.0, x -> y -> x * y);
    }

    /**
     * 5.4 使用高阶函数递归折叠列表 - 练习5.11 使用你的新foldLeft方法创建全新栈安全版本的sum、product和length。
     */
    public static Integer sumViaFoldLeft(List<Integer> list) {
        return list.foldLeft(0, x -> y -> x + y);
    }

    public static Double productViaFoldLeft(List<Double> list) {
        return list.foldLeft(1.0, x -> y -> x * y);
    }

    public static <A> Integer lengthViaFoldLeft(List<A> list) {
        return list.foldLeft(0, x -> ignore -> x + 1);
    }

    /**
     * 5.4 使用高阶函数递归折叠列表 - 练习5.12 使用foldLeft编写一个用于反转列表的静态函数式方法
     *
     * @param list
     * @param <A>
     * @return
     */
    public static <A> List<A> reverseViaFoldLeft(List<A> list) {
        return list.foldLeft(List.list(), x -> x::cons);
    }

    /**
     * 5.4 使用高阶函数递归折叠列表 - 练习5.13 通过foldLeft编写foldRight
     *
     * @param list
     * @param identity
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> B foldRightViaFoldLeft(List<A> list, B identity, Function<A, Function<B, B>> f) {
        return list.reverse().foldLeft(identity, x -> y -> f.apply(y).apply(x));
    }

    /**
     * 5.4.2 映射和过滤列表 - 练习5.17 编写一个函数式方法，接收一个整型列表并将其每个元素乘以3
     *
     * @param list
     * @return
     */
    public static List<Integer> triple(List<Integer> list) {
        return List.foldRight(list, List.<Integer>list(), h -> t -> t.cons(h * 3));
    }

    /**
     * 5.4.2 映射和过滤列表 - 练习5.18 编写一个函数，将List<Double>中的每个值都转换为String
     *
     * @param list
     * @return
     */
    public static List<String> doubleToString(List<Double> list) {
        return List.foldRight(list, List.<String>list(), h -> t -> t.cons(Double.toString(h)));
    }
}

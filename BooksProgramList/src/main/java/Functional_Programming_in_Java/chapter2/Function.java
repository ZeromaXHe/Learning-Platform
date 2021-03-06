package Functional_Programming_in_Java.chapter2;

import Functional_Programming_in_Java.chapter3.CollectionUtilities;

import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 9:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
@FunctionalInterface
public interface Function<T, U> {
    U apply(T arg);

    default <V> Function<V, U> compose(Function<V, T> f) {
        return x -> apply(f.apply(x));
    }

    default <V> Function<T, V> andThen(Function<U, V> f) {
        return x -> f.apply(apply(x));
    }

    /**
     * 2.3.11 恒等函数
     *
     * @param <T>
     * @return
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }

    /**
     * 2.2.4 多态函数 - 练习2.1 用这两个新函数编写compose函数
     * 2.2.5 通过lambda简化代码 - 练习2.2 使用lambda编写一个新版本的compose方法
     * <p>
     * 2.3.5 使用匿名函数 - 类型推断
     *
     * @param f
     * @param g
     * @return
     */
    static <T, U, V> Function<V, U> compose(Function<T, U> f, Function<V, T> g) {
        return x -> f.apply(g.apply(x));
    }

    static <T, U, V> Function<T, V> andThen(Function<T, U> f, Function<U, V> g) {
        return x -> g.apply(f.apply(x));
    }

    static <T, U, V> Function<Function<T, U>,
            Function<Function<U, V>,
                    Function<T, V>>> compose() {
        return x -> y -> y.compose(x);
    }

    static <T, U, V> Function<Function<T, U>,
            Function<Function<V, T>,
                    Function<V, U>>> andThen() {
        return x -> y -> y.andThen(x);
    }

    /**
     * 2.3.4 多态高阶函数 - 练习2.5（难） 编写一个compose函数的多态版本
     *
     * @param <T>
     * @param <U>
     * @param <V>
     * @return
     */
    static <T, U, V> Function<Function<U, V>,
            Function<Function<T, U>,
                    Function<T, V>>> higherCompose() {
//        return tuFunc -> uvFunc -> t -> tuFunc.apply(uvFunc.apply(t));
        return (Function<U, V> f) -> (Function<T, U> g) -> (T x) -> f.apply(g.apply(x));
    }

    /**
     * 2.3.4 多态高阶函数 - 练习2.6（这回容易了！） 编写用于反向复合函数的higherAndThen函数，
     * 也就是说，higherCompose(f, g) 相当于 higherAndThen(g, f)
     *
     * @param <T>
     * @param <U>
     * @param <V>
     * @return
     */
    static <T, U, V> Function<Function<T, U>,
            Function<Function<U, V>,
                    Function<T, V>>> higherAndThen() {
        return f -> g -> x -> g.apply(f.apply(x));
    }

    /**
     * 2.3.8 部分函数应用和自动柯里化 - 练习2.7（非常简单） 编写一个函数式方法来部分应用一个双参柯里化函数的第一个参数
     *
     * @param a
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    static <A, B, C> Function<B, C> partialA(A a, Function<A, Function<B, C>> f) {
        return f.apply(a);
    }

    /**
     * 2.3.8 部分函数应用和自动柯里化 - 练习2.8 编写一个方法来部分应用一个双参柯里化函数的第二个参数
     *
     * @param b
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    static <A, B, C> Function<A, C> partialB(B b, Function<A, Function<B, C>> f) {
        return a -> f.apply(a).apply(b);
    }

    /**
     * 2.3.8 部分函数应用和自动柯里化 - 练习2.10 编写一个从Tuple<A, B>到C的柯里化函数
     *
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    static <A, B, C> Function<A, Function<B, C>> curry(Function<Tuple<A, B>, C> f) {
        return a -> b -> f.apply(new Tuple<>(a, b));
    }

    /**
     * 2.3.9 交换部分应用函数的参数 - 练习2.11 编写一个方法来交换柯里化函数的参数
     *
     * @param f
     * @param <T>
     * @param <U>
     * @param <V>
     * @return
     */
    static <T, U, V> Function<U, Function<T, V>> reverseArgs(Function<T, Function<U, V>> f) {
        return u -> t -> f.apply(t).apply(u);
    }

    /**
     * 4.3 复合大量函数 - 练习4.6 编写一个函数composeAll，接收从T到T的函数列表为参数，并返回复合列表中所有函数的结果。
     * 4.3 复合大量函数 - 练习4.7 解决了这个问题你就能复合（几乎）数量不限的函数。
     *
     * @param list
     * @param <T>
     * @return
     */
    static <T> Function<T, T> composeAll(List<Function<T, T>> list) {
        /// 右折叠
        return CollectionUtilities.foldRight(list, identity(), x -> x::compose);
        /// 命令式
//        return x -> {
//            //你不能直接使用 x，因为它会创建一个闭包，所以它实际上应该是 final。这就是为什么你要创建一个副本。
//            T y = x;
//            for(Function<T, T> f : list){
//                y = f.apply(y);
//            }
//            return y;
//        };
    }

    /**
     * 4.3 复合大量函数 - 练习4.7 解决了这个问题你就能复合（几乎）数量不限的函数。
     * 4.3 复合大量函数 - 练习4.8 代码有两个问题，刚刚修复了一个。你能看到另一个问题并解决它吗？
     *
     * @param list
     * @param <T>
     * @return
     */
    static <T> Function<T, T> composeAllViaFoldLeft(List<Function<T, T>> list) {
        return x -> CollectionUtilities.foldLeft(CollectionUtilities.reverse(list), x, a -> b -> b.apply(a));
    }

    /**
     * 4.3 复合大量函数 - 练习4.7 解决了这个问题你就能复合（几乎）数量不限的函数。
     *
     * @param list
     * @param <T>
     * @return
     */
    static <T> Function<T, T> composeAllViaFoldRight(List<Function<T, T>> list) {
//        return x -> CollectionUtilities.foldRight(list, x, a -> a::apply);
        return x -> CollectionUtilities.foldRight(list, x, identity());
    }

    /**
     * 4.3 复合大量函数 - 练习4.8 代码有两个问题，刚刚修复了一个。你能看到另一个问题并解决它吗？
     *
     * @param list
     * @param <T>
     * @return
     */
    static <T> Function<T, T> andThenAllViaFoldLeft(List<Function<T, T>> list) {
        return x -> CollectionUtilities.foldLeft(list, x, a -> b -> b.apply(a));
    }

    /**
     * 4.3 复合大量函数 - 练习4.8 代码有两个问题，刚刚修复了一个。你能看到另一个问题并解决它吗？
     *
     * @param list
     * @param <T>
     * @return
     */
    static <T> Function<T, T> andThenAllViaFoldRight(List<Function<T, T>> list) {
//        return x -> CollectionUtilities.foldRight(CollectionUtilities.reverse(list), x, a -> a::apply);
        return x -> CollectionUtilities.foldRight(CollectionUtilities.reverse(list), x, identity());
    }
}

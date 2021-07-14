package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter10.Nothing;
import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter5.List;
import Functional_Programming_in_Java.chapter7.Result;
import Functional_Programming_in_Java.chapter9.Stream;

import java.util.Objects;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 19:16
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface IO<A> {
    A run();

    IO<Nothing> empty = () -> Nothing.instance;

    static <A> IO<A> unit(A a) {
        return () -> a;
    }

    /// 没有泛型时的方法
//    static IO println(String message) {
//        return () -> System.out.print(message);
//    }
//
//    static <A> String toString(Result<A> rd) {
//        return rd.map(Objects::toString).getOrElse(rd::toString);
//    }
//
//    static Result<Double> inverse(int i) {
//        return i == 0 ?
//                Result.failure("Div by 0") :
//                Result.success(1.0 / i);
//    }
//
//    /**
//     * 13.3.3 合并IO - 练习13.5 在IO接口中创建一个方法，用于将两个IO实例合并为一个。该方法称为add，并且会有一个默认实现。
//     *
//     * @param io
//     * @return
//     */
//    default IO add(IO io) {
//        return () -> {
//            IO.this.run();
//            io.run();
//        };
//    }

    /**
     * 13.3.4 用IO处理输入 - 练习13.6 在IO<A>中定义一个map方法，以从A到B的函数为参数，并返回一个IO<B>，使其称为IO接口中的default实现。
     *
     * @param f
     * @param <B>
     * @return
     */
    default <B> IO<B> map(Function<A, B> f) {
        return () -> f.apply(this.run());
    }

    /**
     * 13.3.4 用IO处理输入 - 练习13.7 编写一个flatMap方法，以一个从A到IO<B>的函数为参数，并返回一个IO<B>
     *
     * @param f
     * @param <B>
     * @return
     */
    default <B> IO<B> flatMap(Function<A, IO<B>> f) {
        return () -> f.apply(this.run()).run();
    }

    /**
     * 13.3.5 扩展IO类型 - 练习13.8 在具有以下签名的IO接口中实现repeat为一个静态方法
     *
     * @param ioa
     * @param iob
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    static <A, B, C> IO<C> map2(IO<A> ioa, IO<B> iob, Function<A, Function<B, C>> f) {
        return ioa.flatMap(a -> iob.map(b -> f.apply(a).apply(b)));
    }

    // Stream.fill未实现
//    static <A> IO<List<A>> repeat(int n, IO<A> io) {
//        return Stream.fill(n, () -> io)
//                .foldRight(() -> unit(List.list()),
//                        ioa -> sioLa -> map2(ioa, sioLa.get(), a -> la -> List.cons(a, la)));
//    }
}

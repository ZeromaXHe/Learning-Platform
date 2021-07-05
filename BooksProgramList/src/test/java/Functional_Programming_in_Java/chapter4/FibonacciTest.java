package Functional_Programming_in_Java.chapter4;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter3.CollectionUtilities;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 17:31
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class FibonacciTest {
    /**
     * 4.2.3 双递归函数：斐波那契数列示例 - 练习 4.1 创建一个尾递归版本的斐波那契函数式方法
     */
    @Test
    public void test4_2_3_practice4_1() {
        System.out.println(fib4_1(1000));
    }

    public static BigInteger fib4_1(int x) {
        // 你仍然不能用这个方法处理 7500 以上的值
        return fib4_1_(BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(x));
    }

    private static BigInteger fib4_1_(BigInteger acc1, BigInteger acc2, BigInteger x) {
        if (x.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        } else if (x.equals(BigInteger.ONE)) {
            return acc1.add(acc2);
        } else {
            return fib4_1_(acc2, acc1.add(acc2), x.subtract(BigInteger.ONE));
        }
    }

    /**
     * 4.2.3 双递归函数：斐波那契数列示例 - 练习 4.2 将这个方法转换为栈安全的递归方法
     */
    @Test
    public void test4_2_3_practice4_2() {
        System.out.println(fib(10000));
    }

    public static BigInteger fib(int x) {
        return fib_(BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(x)).eval();
    }

    private static TailCall<BigInteger> fib_(BigInteger acc1, BigInteger acc2, BigInteger x) {
        if (x.equals(BigInteger.ZERO)) {
            return TailCall.ret(BigInteger.ZERO);
        } else if (x.equals(BigInteger.ONE)) {
            return TailCall.ret(acc1.add(acc2));
        } else {
            return TailCall.sus(() -> fib_(acc2, acc1.add(acc2), x.subtract(BigInteger.ONE)));
        }
    }

    /**
     * 4.4.2 递归函数的记忆化 - 练习4.9 编写一个栈安全的尾递归函数，以整型n为参数，并返回一个字符串，
     * 表示斐波那契数列从0到n的值，用逗号加空格分隔
     */
    @Test
    public void test4_4_2() {
        System.out.println(fibo(100));
    }

    public static String fibo(int number) {
        // 本示例演示了使用隐式记忆化。不要认为这是解决问题的最佳方案。许多问题换一种方式更容易解决
        List<BigInteger> list = fibo_(CollectionUtilities.list(BigInteger.ZERO),
                BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(number)).eval();
        return makeString(list, ", ");
    }

    private static TailCall<List<BigInteger>> fibo_(List<BigInteger> acc, BigInteger acc1,
                                                    BigInteger acc2, BigInteger x) {
        return x.equals(BigInteger.ZERO) ?
                TailCall.ret(acc) :
                TailCall.sus(() -> fibo_(CollectionUtilities.append(acc, acc1.add(acc2)),
                        acc2, acc1.add(acc2), x.subtract(BigInteger.ONE)));
    }

    private static <T> String makeString(List<T> list, String separator) {
        return list.isEmpty() ?
                "" :
                CollectionUtilities.tail(list).isEmpty() ?
                        CollectionUtilities.head(list).toString() :
                        CollectionUtilities.head(list) + CollectionUtilities.foldLeft(CollectionUtilities.tail(list),
                                "", x -> y -> x + separator + y);
    }

//    public static String fiboCorecursive(int number) {
//        Tuple<BigInteger, BigInteger> seed = new Tuple<>(BigInteger.ZERO, BigInteger.ONE);
//        Function<Tuple<BigInteger, BigInteger>, Tuple<BigInteger, BigInteger>> f =
//                x -> new Tuple<>(x._2, x._1.add(x._2));
//        List<BigInteger> list = CollectionUtilities.map(List.iterate(seed, f, number + 1), x -> x._1);
//        return makeString(list, ", ");
//    }
}
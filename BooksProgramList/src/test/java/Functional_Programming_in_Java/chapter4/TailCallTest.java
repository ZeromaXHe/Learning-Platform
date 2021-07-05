package Functional_Programming_in_Java.chapter4;

import Functional_Programming_in_Java.chapter2.Function;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 17:04
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class TailCallTest {
    private static TailCall<Integer> add4_1_5(int x, int y) {
//        return y == 0
//                ? new TailCall.Return<>(x)
//                : new TailCall.Suspend<>(() -> add4_1_5(x + 1, y - 1));
        return y == 0 ? TailCall.ret(x) : TailCall.sus(() -> add4_1_5(x + 1, y - 1));
    }

    /**
     * 4.1.5 抽象递归
     */
    @Test
    public void test4_1_5() {
        TailCall<Integer> tailCall = add4_1_5(3, 100000000);
        while (tailCall.isSuspend()) {
            tailCall = tailCall.resume();
        }
        System.out.println(tailCall.eval());
    }

    public static int add(int x, int y) {
        return addRec(x, y).eval();
    }

    private static TailCall<Integer> addRec(int x, int y) {
        return y == 0 ? TailCall.ret(x) : TailCall.sus(() -> addRec(x + 1, y - 1));
    }

    /**
     * 4.1.6 为基于栈的递归方法使用一个直接替代品
     */
    @Test
    public void test4_1_6() {
        System.out.println(add(3, 100000000));
    }

    static Function<Integer, Function<Integer, Integer>> add = x -> y -> {
        class AddHelper {
            Function<Integer, Function<Integer, TailCall<Integer>>> addHelper =
                    a -> b -> b == 0 ? TailCall.ret(a) : TailCall.sus(() -> this.addHelper.apply(a + 1).apply(b - 1));
        }
        return new AddHelper().addHelper.apply(x).apply(y).eval();
    };

    /**
     * 4.2.1 使用局部定义的函数
     */
    @Test
    public void test4_2_1() {
        System.out.println(add.apply(3).apply(100000000));
    }
}

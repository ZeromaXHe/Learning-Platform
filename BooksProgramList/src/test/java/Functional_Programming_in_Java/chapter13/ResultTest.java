package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter3.Effect;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 17:05
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ResultTest {
    public static void main(String[] args) {
        Result<Integer> ra = Result.success(4);
        Result<Integer> rb = Result.success(0);

        Function<Integer, Result<Double>> inverse = x -> x != 0 ?
                Result.success((double) 1 / x) :
                Result.failure("Division by 0");

        Effect<Double> print = System.out::println;

        Result<Double> rt1 = ra.flatMap(inverse);
        Result<Double> rt2 = rb.flatMap(inverse);

        System.out.print("Inverse of 4: ");
        // Inverse of 4: 0.25
        rt1.forEachOrFail(print).forEach(ResultTest::log);

        System.out.print("Inverse of 0: ");
        // Inverse of 0: Division by 0
        rt2.forEachOrFail(print).forEach(ResultTest::log);
    }

    private static void log(String s) {
        System.out.println(s);
    }
}

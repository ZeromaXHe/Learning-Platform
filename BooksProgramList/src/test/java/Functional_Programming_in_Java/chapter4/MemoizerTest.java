package Functional_Programming_in_Java.chapter4;

import Functional_Programming_in_Java.chapter2.Function;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 19:38
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class MemoizerTest {
    private static Integer longCalculation(Integer x) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x * 2;
    }

    private static Function<Integer, Integer> f = MemoizerTest::longCalculation;

    private static Function<Integer, Integer> g = Memoizer.memoize(f);

    /**
     * Memoizer示范
     */
    @Test
    public void automaticMemoizationExample() {
        long startTIme = System.currentTimeMillis();
        Integer result1 = g.apply(1);
        long time1 = System.currentTimeMillis() - startTIme;
        startTIme = System.currentTimeMillis();
        Integer result2 = g.apply(1);
        long time2 = System.currentTimeMillis() - startTIme;
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2);
    }

    Function<Integer, Function<Integer, Function<Integer, Integer>>> f3m =
            Memoizer.memoize(x ->
                    Memoizer.memoize(y ->
                            Memoizer.memoize(z ->
                                    longCalculation(x) + longCalculation(y) - longCalculation(z))));

    /**
     * 测试具有三个参数的记忆函数的性能
     */
    @Test
    public void automaticMemoizationExample2() {
        long startTIme = System.currentTimeMillis();
        Integer result1 = f3m.apply(2).apply(3).apply(4);
        long time1 = System.currentTimeMillis() - startTIme;
        startTIme = System.currentTimeMillis();
        Integer result2 = f3m.apply(2).apply(3).apply(4);
        long time2 = System.currentTimeMillis() - startTIme;
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2);
    }

    Function<Tuple3<Integer, Integer, Integer>, Integer> ft =
            x -> longCalculation(x._1) + longCalculation(x._2) - longCalculation(x._3);

    Function<Tuple3<Integer, Integer, Integer>, Integer> ftm = Memoizer.memoize(ft);

    /**
     * Tuple3的记忆函数
     */
    @Test
    public void automaticMemoizationExample3() {
        long startTIme = System.currentTimeMillis();
        Integer result1 = ftm.apply(new Tuple3<>(2, 3, 4));
        long time1 = System.currentTimeMillis() - startTIme;
        startTIme = System.currentTimeMillis();
        Integer result2 = ftm.apply(new Tuple3<>(2, 3, 4));
        long time2 = System.currentTimeMillis() - startTIme;
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2);
    }
}
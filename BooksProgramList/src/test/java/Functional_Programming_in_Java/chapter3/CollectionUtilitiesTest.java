package Functional_Programming_in_Java.chapter3;

import Functional_Programming_in_Java.chapter2.Function;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 15:09
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class CollectionUtilitiesTest {
    /**
     * 3.3.5 化简和折叠列表 - 练习3.5
     */
    @Test
    public void foldTest() {
        List<Integer> list = CollectionUtilities.list(1, 2, 3, 4, 5);
        int result = CollectionUtilities.fold(list, 0, x -> y -> x + y);
        Assert.assertEquals(15, result);
    }

    Function<Double, Double> addTax = x -> x * 1.09;
    Function<Double, Double> addShipping = x -> x + 3.50;
    List<Double> prices = CollectionUtilities.list(10.10, 23.45, 32.07, 9.23);
    List<Double> pricesIncludingTax = CollectionUtilities.map(prices, addTax);
    List<Double> pricesIncludingShipping = CollectionUtilities.map(pricesIncludingTax, addShipping);

    /**
     * 3.3.6 复合映射和映射复合
     */
    @Test
    public void test3_3_6() {
        // [14.509, 29.0605, 38.456300000000006, 13.5607]
        System.out.println(pricesIncludingShipping);
        // 但这样仍然映射了两次。一个更好的办法是复合函数而不是复合映射，或者换句话说，映射复合而不是复合映射
        System.out.println(CollectionUtilities.map(prices, addShipping.compose(addTax)));
        System.out.println(CollectionUtilities.map(prices, addTax.andThen(addShipping)));
    }

    Effect<Double> printWith2decimals = x -> {
        System.out.printf("%.2f", x);
        System.out.println();
    };

    /**
     * 3.3.7 对列表应用作用
     */
    @Test
    public void test3_3_7() {
        CollectionUtilities.forEach(pricesIncludingShipping, printWith2decimals);
    }

    /**
     * 3.3.8 处理函数式的输出
     */
    @Test
    public void test3_3_8() {
        Function<Executable, Function<Executable, Executable>> compose =
                x -> y -> () -> {
                    x.exec();
                    y.exec();
                };

        Executable ez = () -> {
        };

        Executable program = CollectionUtilities.foldLeft(pricesIncludingShipping, ez, e -> d -> compose.apply(e).apply(() -> printWith2decimals.apply(d)));
        program.exec();
    }
}

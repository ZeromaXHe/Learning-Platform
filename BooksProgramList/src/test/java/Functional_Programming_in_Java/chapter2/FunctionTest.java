package Functional_Programming_in_Java.chapter2;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 9:58
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class FunctionTest {
    /**
     * 2.2.4 多态函数
     */
    @Test
    public void test2_2_4() {
        Function<Integer, Integer> triple = arg -> arg * 3;
        Function<Integer, Integer> square = arg -> arg * arg;
    }

    /**
     * 2.3.1 多参函数怎么样 - 练习2.3 编写一个函数以对两个整数求和
     */
    @Test
    public void test2_3_1() {
        // Integer -> Integer -> Integer 由于结合律，它等价于 Integer -> (Integer -> Integer)
        BinaryOperator add = x -> y -> x + y;
        BinaryOperator mult = x -> y -> x * y;
    }

    /**
     * 2.3.3 高阶函数 - 练习2.4 编写一个函数，用于复合在练习2.2里用过的square和tripe这两个函数
     */
    @Test
    public void test2_3_3() {
        Function<Function<Integer, Integer>,
                Function<Function<Integer, Integer>,
                        Function<Integer, Integer>>> compose = x -> y -> z -> x.apply(y.apply(z));
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> f = compose.apply(square).apply(triple);
        // (2 * 3) ^ 2 = 36
        Assert.assertThat(f.apply(2), IsEqual.equalTo(36));
    }

    /**
     * 2.3.4 多态高阶函数
     */
    @Test
    public void test2_3_4() {
        Integer x = Function.<Integer, Integer, Integer>higherCompose().apply(arg -> arg).apply(arg -> arg).apply(1);
        Assert.assertThat(x, IsEqual.equalTo(1));

        // 使用不同类型函数的测试
        Function<Double, Integer> f = a -> (int) (a * 3);
        Function<Long, Double> g = a -> a + 2.0;
        Assert.assertEquals(Integer.valueOf(9), f.apply((g.apply(1L))));
        Assert.assertEquals(Integer.valueOf(9), Function.<Long, Double, Integer>higherCompose().apply(f).apply(g).apply(1L));
        // 请注意，Java无法推断类型，所以你需要在调用higherCompose函数时提供类型
    }

    /**
     * 2.3.5 使用匿名函数
     */
    @Test
    public void test2_3_5() {
        Double cos = Function.compose(z -> Math.PI / 2 - z, Math::sin).apply(2.0);
        Double cos2 = Function.<Double, Double, Double>higherCompose().apply(z -> Math.PI / 2 - z).apply(Math::sin).apply(2.0);
        // 第二个参数从方法引用替换为lambda的话，需要加上类型标记(Function<Double, Double>)，否则Java无法猜出第二个参数的类型
        Double cos3 = Function.compose(z -> Math.PI / 2 - z, (Function<Double, Double>) a -> Math.sin(a)).apply(2.0);
        Assert.assertEquals(cos, cos2);
        Assert.assertEquals(cos, cos3);
    }

    /**
     * 2.3.7 闭包
     */
    @Test
    public void test2_3_7() {
        // 用元组作为函数的参数是让程序更加模块化的一种方式
        Function<Tuple<Double, Double>, Double> addTax_Tuple = tuple -> tuple._2 + tuple._2 * tuple._1;

        // 为元组函数定义一个指定的接口 Function2
        // 你也可以用 Java 8 定义的BiFunction类，它模拟了接收二元组参数的函数。
        // 还有BinaryOperater，相当于类型相同的二元组参数的函数，还有DoubleBinaryOperater，接受的都是double原始类型的二元组。
        Function2<Double, Double, Double> addTax = (taxRate, price) -> price + price * taxRate;
        double priceIncludingTax = addTax.apply(0.09, 12.0);
        Assert.assertEquals(Double.valueOf(priceIncludingTax), addTax_Tuple.apply(new Tuple<>(0.09, 12.0)));

        // 但如果需要三个或更多的参数，那该怎么办？可以定义Function3，Function4，如此这般
        // 但是柯里化是一个更棒的解决方案
        Function<Double, Function<Double, Double>> addTax_Currying = taxRate -> price -> price + price * taxRate;
        Assert.assertEquals(Double.valueOf(priceIncludingTax), addTax_Currying.apply(0.09).apply(12.0));
    }

    /**
     * 2.3.10 递归函数
     */
    @Test
    public void test2_3_10() {
        Assert.assertEquals(Integer.valueOf(2), factorial.apply(2));
        Assert.assertEquals(factorial.apply(2), factorial_static.apply(2));
        Assert.assertEquals(factorial_final.apply(2), factorial_static_final.apply(2));
        Assert.assertEquals(factorial_static.apply(2), factorial_static_final.apply(2));
    }

    private Function<Integer, Integer> factorial;

    {
        factorial = n -> n <= 1 ? n : n * factorial.apply(n - 1);
    }

    private static Function<Integer, Integer> factorial_static;

    static {
        factorial_static = n -> n <= 1 ? n : n * factorial_static.apply(n - 1);
    }

    private final Function<Integer, Integer> factorial_final = n -> n <= 1 ? n : n * this.factorial_final.apply(n - 1);

    private static final Function<Integer, Integer> factorial_static_final =
            n -> n <= 1 ? n : n * FunctionTest.factorial_static_final.apply(n - 1);
}

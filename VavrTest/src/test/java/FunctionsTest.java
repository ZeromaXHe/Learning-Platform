import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function5;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/13 15:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class FunctionsTest {

//    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
    Function2<Integer, Integer, Integer> sum = Integer::sum;

    Function3<String, String, String, String> function3 = Function3.of(this::methodWhichAccepts3Parameters);

    @Test
    public void function_CompositionTest() {
        /// Composition
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
        Function1<Integer, Integer> add1AndMultiplyBy2_compose = multiplyByTwo.compose(plusOne);
        Assert.assertEquals(Integer.valueOf(6), add1AndMultiplyBy2.apply(2));
        Assert.assertEquals(Integer.valueOf(6), add1AndMultiplyBy2_compose.apply(2));
    }

    private String methodWhichAccepts3Parameters(String s1, String s2, String s3) {
        return null;
    }

    @Test
    public void function_LiftingTest() {
        /// Lifting
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);
        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0);
        System.out.println(i1);
        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2);
        System.out.println(i2);
    }

    @Test
    public void function_PartialApplicationTest() {
        /// Partial application
        Function1<Integer, Integer> add2 = sum.apply(2);
        Assert.assertEquals(Integer.valueOf(6), add2.apply(4));
        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum5 = (a, b, c, d, e) -> a + b + c + d + e;
        Function2<Integer, Integer, Integer> add6 = sum5.apply(2, 3, 1);
        Assert.assertEquals(Integer.valueOf(13), add6.apply(4, 3));
    }

    @Test
    public void function_CurryingTest() {
        /// Currying
        Function1<Integer, Integer> add2_currying = sum.curried().apply(2);
        Assert.assertEquals(Integer.valueOf(6), add2_currying.apply(4));
        Function3<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
        final Function1<Integer, Function1<Integer, Integer>> add2_currying2 = sum3.curried().apply(2);
        Assert.assertEquals(Integer.valueOf(9), add2_currying2.apply(4).apply(3));
    }

    @Test
    public void function_MemoizationTest() {
        /// Memoization
        Function0<Double> hashCache = Function0.of(Math::random).memoized();
        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();
        Assert.assertEquals(randomValue1, randomValue2, 0.0);
    }

}

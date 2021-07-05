package Functional_Programming_in_Java.chapter4;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter3.CollectionUtilities;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 18:15
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class FunctionTest {
    @Test
    public void test4_3() {
        // 运行这段代码显示 500。因为它是复合 500 个对参数加 1 的函数的结果。
        // 如果 500 替换为 10000 会怎样？你会得到一个 StackOverflowException。原因显而易见。
        Function<Integer, Integer> add = y -> y + 1;
        System.out.println(
                Function.composeAll(
                        CollectionUtilities.map(
                                CollectionUtilities.range(0, 500),
                                x -> add
                        )
                ).apply(0)
        );
    }
}

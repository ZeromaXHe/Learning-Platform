package Functional_Programming_in_Java.chapter8;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter5.List;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/8 17:17
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ListTest {
    @Test
    public void test8_3_1_practice8_9() {
        // [Tuple{1, 4}, Tuple{1, 5}, Tuple{1, 6}, Tuple{2, 4}, Tuple{2, 5}, Tuple{2, 6}, Tuple{3, 4}, Tuple{3, 5}, Tuple{3, 6}, NIL]
        System.out.println(List.product(List.list(1, 2, 3), List.list(4, 5, 6), x -> y -> new Tuple<>(x, y)));
        // [Tuple{3, 6}, Tuple{2, 5}, Tuple{1, 4}, NIL]
        System.out.println(List.zipWith(List.list(1, 2, 3), List.list(4, 5, 6), x -> y -> new Tuple<>(x, y)));
    }
}

package Functional_Programming_in_Java.chapter9;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter5.List;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/9 18:27
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class StreamTest {
    private static Function<Integer, Integer> f = x -> {
        System.out.println("Mapping " + x);
        return x * 3;
    };

    private static Function<Integer, Boolean> p = x -> {
        System.out.println("Filtering " + x);
        return x % 2 == 0;
    };

    @Test
    public void test9_6_1_List() {
        List<Integer> list = List.list(1, 2, 3, 4, 5).map(f).filter(p);
        System.out.println(list);
        // Mapping 5
        // Mapping 4
        // Mapping 3
        // Mapping 2
        // Mapping 1
        // Filtering 15
        // Filtering 12
        // Filtering 9
        // Filtering 6
        // Filtering 3
        // [6, 12, NIL]
    }

    private static Stream<Integer> stream =
            Stream.cons(() -> 1,
                    Stream.cons(() -> 2,
                            Stream.cons(() -> 3,
                                    Stream.cons(() -> 4,
                                            Stream.cons(() -> 5, Stream.empty())))));

    @Test
    public void test9_6_1_Stream() {
        Stream<Integer> result = stream.map(f).filter(p);
        System.out.println(result.toList());
        // Mapping 1
        // Filtering 3
        // Mapping 2
        // Filtering 6
        // Mapping 3
        // Filtering 9
        // Mapping 4
        // Filtering 12
        // Mapping 5
        // Filtering 15
        // [6, 12, NIL]
    }
}

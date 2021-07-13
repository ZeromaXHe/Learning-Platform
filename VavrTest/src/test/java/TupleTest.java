import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/13 10:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class TupleTest {

    @Test
    public void tupleTest() {
        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        System.out.println(java8);
        // "Java"
        String s1 = java8._1;
        System.out.println(s1);
        // 8
        Integer i1 = java8._2;
        System.out.println(i1);

        // (vavr, 1)
        Tuple2<String, Integer> that = java8.map(
                s -> s.substring(2) + "vr",
                i -> i / 8
        );
        System.out.println(that);

        // (vavr, 1)
        Tuple2<String, Integer> that2 = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );
        System.out.println(that2);

        // "vavr 1"
        String thatStr = java8.apply(
                (s, i) -> s.substring(2) + "vr " + i / 8
        );
        System.out.println(thatStr);
    }

}

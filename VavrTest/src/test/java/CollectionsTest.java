import io.vavr.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: zhuxi
 * @Time: 2021/7/13 15:05
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class CollectionsTest {

    @Test
    public void collections_ListTest() {
        /// Java 8
//        Optional<Integer> reduce = Arrays.asList(1, 2, 3).stream().reduce((i, j) -> i + j);
        Optional<Integer> reduce = Stream.of(1, 2, 3).reduce(Integer::sum);
        Integer sum = IntStream.of(1, 2, 3).sum();
        Assert.assertEquals(sum, reduce.get());

        /// Vavr
        Number sum1 = List.of(1, 2, 3).sum();
        Assert.assertEquals(Integer.valueOf(sum1.intValue()), sum);
    }

    @Test
    public void collections_StreamTest() {
        // 2, 4, 6, ...
        io.vavr.collection.Stream.from(1).filter(i -> i % 2 == 0);
    }
}

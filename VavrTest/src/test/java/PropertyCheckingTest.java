import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/13 15:41
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class PropertyCheckingTest {

    @Test
    public void propertyCheckingTest() {
        Arbitrary<Integer> ints = Arbitrary.integer();

        Property.def("square(int) >= 0")
                .forAll(ints)
                .suchThat(i -> i * i >= 0)
                .check()
                .assertIsSatisfied();
    }

}

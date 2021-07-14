package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Tuple;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 15:07
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class JavaRNGTest {
    @Test
    public void testInteger() {
        RNG rng = JavaRNG.rng(0);
        Tuple<Integer, RNG> t1 = Generator.integer(rng);
        Assert.assertEquals(Integer.valueOf(384748), t1._1);
        Tuple<Integer, RNG> t2 = Generator.integer(t1._2);
        Assert.assertEquals(Integer.valueOf(-1155484576), t2._1);
        Tuple<Integer, RNG> t3 = Generator.integer(t2._2);
        Assert.assertEquals(Integer.valueOf(-723955400), t3._1);
    }
}

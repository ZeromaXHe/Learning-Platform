package Java8_in_Action.chapter4;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhuxi
 * @Time: 2021/7/15 10:55
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class IntroducingStreamTest {
    /**
     * 4.2 流简介
     */
    @Test
    public void test4_2() {
        List<String> threeHighCaloricDishNames =
                Dish.menu.stream()
                        .filter(d -> d.getCalories() > 300).map(Dish::getName)
                        .limit(3)
                        .collect(Collectors.toList());
        // [pork, beef, chicken]
        System.out.println(threeHighCaloricDishNames);
    }

    /**
     * 4.3.1 只能遍历一次
     */
    @Test(expected = IllegalStateException.class)
    public void test4_3_1() {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        // java.lang.IllegalStateException：流已被操作或关闭
        s.forEach(System.out::println);
        Assert.fail();
    }

    @Test
    public void test4_4_1() {
        List<String> names =
                Dish.menu.stream()
                        .filter(d -> {
                            System.out.println("filtering " + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("mapping " + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(Collectors.toList());
        // filtering pork
        // mapping pork
        // filtering beef
        // mapping beef
        // filtering chicken
        // mapping chicken
        // [pork, beef, chicken]
        System.out.println(names);
    }
}

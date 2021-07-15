package Java8_in_Action.chapter5;

import Java8_in_Action.chapter4.Dish;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: zhuxi
 * @Time: 2021/7/15 11:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class UsingStreamTest {
    /**
     * 5.1.1 用谓词筛选
     */
    @Test
    public void test5_1_1() {
        List<Dish> vegetarianMenu = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        // [french fries, rice, season fruit, pizza]
        System.out.println(vegetarianMenu);
    }

    /**
     * 5.1.2 筛选各异的元素
     */
    @Test
    public void test5_1_2() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        // 2
        // 4
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 5.1.3 截短流
     */
    @Test
    public void test5_1_3() {
        List<Dish> dishes = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        // [pork, beef, chicken]
        System.out.println(dishes);
    }

    /**
     * 5.1.4 跳过元素
     */
    @Test
    public void test5_1_4() {
        List<Dish> dishes = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        // [chicken, french fries, rice, pizza, salmon]
        System.out.println(dishes);
    }

    /**
     * 5.2.2 流的扁平化
     */
    @Test
    public void test5_2_2() {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        List<String> uniqueCharacters = streamOfwords
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);
    }

    /**
     * 测验5.2：映射
     */
    @Test
    public void test5_2_2_practive5_2() {
        // （2）给定两个数字列表，如何返回所有的数对呢？
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());
        // [1, 3][1, 4][2, 3][2, 4][3, 3][3, 4]
        pairs.stream().map(Arrays::toString).forEach(System.out::print);
    }

    /**
     * 5.3.1 检查谓词是否至少匹配一个元素
     */
    @Test
    public void test5_3_1() {
        if (Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }

    /**
     * 5.3.2 检查谓词是否匹配所有元素
     */
    @Test
    public void test5_3_2() {
        boolean isHealthy = Dish.menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        boolean isHealthy2 = Dish.menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy);
        System.out.println(isHealthy2);
        Assert.assertEquals(isHealthy, isHealthy2);
    }

    /**
     * 5.3.3 查找元素
     */
    @Test
    public void test5_3_3() {
        Optional<Dish> dish =
                Dish.menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    /**
     * 5.3.4 查找第一个元素
     */
    @Test
    public void test5_3_4() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
        firstSquareDivisibleByThree.ifPresent(System.out::println);
        // 何时使用findFirst和findAny：
        // 你可能会想，为什么会同时有findFirst和findAny呢？
        // 答案是并行。找到第一个元素在并行上限制更多。
        // 如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流时限制较少。
    }

    /**
     * 5.4 规约
     * 5.4.1 元素求和
     */
    @Test
    public void test5_4_1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, Integer::sum);
        Optional<Integer> sum2 = numbers.stream().reduce(Integer::sum);
        Assert.assertEquals(Integer.valueOf(sum), sum2.orElse(-1));
        Assert.assertEquals(Integer.valueOf(15), sum2.orElse(-1));
    }

    /**
     * 5.4.2 最大值和最小值
     */
    @Test
    public void test5_4_2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        Assert.assertEquals(Integer.valueOf(5), max.orElse(-1));
        Assert.assertEquals(Integer.valueOf(1), min.orElse(-1));
    }

    /**
     * 5.5 付诸实践
     * 5.5.2 解答
     */
    @Test
    public void test5_5_2() {
        // (1)找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> tr2011 =
                Transaction.transactions.stream()
                        .filter(transaction -> transaction.getYear() == 2011)
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .collect(Collectors.toList());
        System.out.println("(1):" + tr2011);

        // (2)交易员都在哪些不同的城市工作过
        List<String> cities =
                Transaction.transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(Collectors.toList());
        Set<String> cities2 =
                Transaction.transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .collect(Collectors.toSet());
        System.out.println("(2):" + cities);
        System.out.println("(2):" + cities2);

        // (3)查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> traders =
                Transaction.transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(Comparator.comparing(Trader::getName))
                        .collect(Collectors.toList());
        System.out.println("(3):" + traders);

        // (4)返回所有交易员的姓名字符串，按字母顺序排序
        // 此解决方案效率不高（所有字符串都被反复连接，每次迭代的时候都要建立一个新的String对象）
        String traderStr =
                Transaction.transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        // 你将看到一个更为高效的解决方案，它像下面这样使用joining（其内部会用到StringBuilder）
        String traderStr2 =
                Transaction.transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining());
        System.out.println("(4):" + traderStr);
        System.out.println("(4):" + traderStr2);

        // (5)有没有交易员是在米兰工作的
        boolean milanBased =
                Transaction.transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("(5):" + milanBased);

        // (6)打印生活在剑桥的交易员的所有交易额
        Transaction.transactions.stream()
                .filter(t -> "Cambridge" .equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // (7)所有交易中，最高的交易额是多少
        Optional<Integer> highestValue =
                Transaction.transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);
        highestValue.ifPresent(integer -> System.out.println("(7):" + highestValue));

        // (8)找到交易额最小的交易
        Optional<Transaction> smallestTransaction =
                Transaction.transactions.stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);
        Optional<Transaction> smallestTransaction2 =
                Transaction.transactions.stream()
                        .min(Comparator.comparing(Transaction::getValue));
        smallestTransaction.ifPresent(transaction -> System.out.println("(8):" + transaction));
        smallestTransaction2.ifPresent(transaction -> System.out.println("(8):" + transaction));
    }

    /**
     * 5.6 数值流
     * 5.6.1 原始类型流特化
     */
    @Test
    public void test5_6_1() {
        // 映射到数值流
        int calories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);
        // 转换回对象流
        IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
        // 默认值OptionalInt
        OptionalInt maxCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);
        System.out.println(max);
    }

    /**
     * 5.6.2 数值范围
     */
    @Test
    public void test5_6_2() {
        // 包括结束值100
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
        // 不包括结束值100
        IntStream evenNumbers2 = IntStream.range(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers2.count());
    }

    /**
     * 5.6.3 数值流应用：勾股数
     */
    @Test
    public void test5_6_3() {
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );
        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        // 目前的解决办法并不是最优的，因为你要求两次平方根。
        // 让代码更为紧凑的一种可能的方法是，先生成所有的三元数(a*a, b*b, a*a+b*b)，然后再筛选符合条件的
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                        .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples2.limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }

    /**
     * 5.7 构建流
     * 5.7.1 由值创建流
     */
    @Test
    public void test5_7_1() {
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // 你可以使用empty得到一个空流
        Stream<String> emptyStream = Stream.empty();
    }

    /**
     * 5.7.2 由数组创建流
     */
    @Test
    public void test5_7_2() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    /**
     * 5.7.3 由文件生成流
     */
    @Test
    public void test5_7_3() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {

        }
        System.out.println(uniqueWords);
    }

    /**
     * 5.7.4 由函数生成流：创建无限流
     */
    @Test
    public void test5_7_4() {
        // 迭代
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // 生成
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        // 斐波那契
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    /**
     * 测验5.4：斐波那契元组序列
     */
    @Test
    public void test5_7_4_practice5_4() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
    }
}

package Functional_Programming_in_Java.chapter3;

import Functional_Programming_in_Java.chapter2.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 14:27
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class CollectionUtilities {
    /**
     * 3.3.1 使用映射抽象列表操作
     *
     * @param list
     * @param f
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        List<U> newList = new ArrayList<>();
        for (T value : list) {
            newList.add(f.apply(value));
        }
        return newList;
    }

    /**
     * 3.3.2 创建列表 - 练习3.3 编写方法来创建一个空列表、包含一个元素的列表、包含一个集合里所有元素的列表，
     * 以及一个从变长参数列表里创建列表的方法。所有的这些列表都是不可变的。
     * <p>
     * 创建一个空列表
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> list() {
        return Collections.emptyList();
    }

    /**
     * 3.3.2 创建列表 - 练习3.3
     * 包含一个元素的列表
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> list(T t) {
        return Collections.singletonList(t);
    }

    /**
     * 3.3.2 创建列表 - 练习3.3
     * 包含一个集合里所有元素的列表
     *
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> List<T> list(List<T> ts) {
        return Collections.unmodifiableList(new ArrayList<>(ts));
    }

    /**
     * 3.3.2 创建列表 - 练习3.3
     * 以及一个从变长参数列表里创建列表的方法
     *
     * @param t
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> List<T> list(T... t) {
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
    }

    /**
     * 3.3.3 使用head和tail操作 - 练习3.4 创建两个方法分别返回列表的头部（head）和尾部（tail) 。
     * 不允许修改作为参数传递的列表。由于你需要创建列表的副本，所以还需要定义一个 copy 方法。tail 返回的列表必须是不可变的。
     * <p>
     * 返回列表的头部（head）
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T head(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalStateException("head of empty list");
        }
        return list.get(0);
    }

    /**
     * 3.3.3 使用head和tail操作 - 练习3.4
     * copy 方法
     *
     * @param ts
     * @param <T>
     * @return
     */
    private static <T> List<T> copy(List<T> ts) {
        return new ArrayList<>(ts);
    }

    /**
     * 3.3.3 使用head和tail操作 - 练习3.4
     * 返回列表的尾部（tail), tail 返回的列表必须是不可变的
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> tail(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalStateException("tail of empty list");
        }
        List<T> workList = copy(list);
        workList.remove(0);
        return Collections.unmodifiableList(workList);
    }

    /**
     * 3.3.4 函数式地添加列表元素
     *
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> append(List<T> list, T t) {
        List<T> ts = copy(list);
        ts.add(t);
        return Collections.unmodifiableList(ts);
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习3.5 创建一个用于折叠整型列表的方法，例如对列表的元素求和。
     * 该方法接收一个整型列表、一个整型初始值和一个函数为参数
     *
     * @param is
     * @param identity
     * @param f
     * @return
     */
    public static Integer fold(List<Integer> is, Integer identity, Function<Integer, Function<Integer, Integer>> f) {
        int result = identity;
        for (Integer i : is) {
            result = f.apply(result).apply(i);
        }
        return result;
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习3.6 将fold方法归纳为foldLeft，以便它可以应用左折叠于任意类型的元素列表
     *
     * @param ts
     * @param identity
     * @param f
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> U foldLeft(List<T> ts, U identity, Function<U, Function<T, U>> f) {
        U result = identity;
        for (T t : ts) {
            result = f.apply(result).apply(t);
        }
        return result;
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习3.7 编写一个 foldRight 方法的命令式版本
     *
     * @param ts
     * @param identity
     * @param f
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> U foldRight_imperative(List<T> ts, U identity, Function<T, Function<U, U>> f) {
        U result = identity;
        for (int i = ts.size(); i > 0; i--) {
            result = f.apply(ts.get(i - 1)).apply(result);
        }
        return result;
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习3.8 编写 foldRight 的递归版本。
     * 注意，一个初始递归的版本并不能在 Java 中工作得天衣无缝，因为它使用栈来累积中间计算。
     * 在第 4 章中 ，你将学习如何创建栈安全的递归（ stack-safe recursion ）。
     *
     * @param ts
     * @param identity
     * @param f
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> U foldRight(List<T> ts, U identity, Function<T, Function<U, U>> f) {
        return ts.isEmpty() ? identity : f.apply(head(ts)).apply(foldRight(tail(ts), identity, f));
    }

    /**
     * 3.3.5 化简和折叠列表 - 反转列表
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> reverse_imperative(List<T> list) {
        List<T> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return Collections.unmodifiableList(result);
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习3.9（难） 定义不用循环的 reverse 方法。改为用你迄今为止开发过的方法。
     * 你可以先定义一个 prepend 的函数式方法，它允许在列表前添加一个元素。
     * 可以通过左折叠列表来完成，用一个包含了待添加元素的累加器而非空列表
     * <p>
     * 【警告】
     * 不要在生产代码中使用答案 3.9 reverse 和 prepend 的实现。
     * 它们都悄悄地遍历了整个列表好几次，所以很慢。
     * 在第5章中，你将学习如何创建在所有场合都表现良好的函数式不可变列表。
     *
     * @param t
     * @param list
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> List<T> prepend(T t, List<T> list) {
        return foldLeft(list, list(t), a -> b -> append(a, b));
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习3.9（难）
     * 可以将 reverse 方法定义为左折叠，始于空列表并以 prepend 方法为操作
     * <p>
     * 【警告】
     * 不要在生产代码中使用答案 3.9 reverse 和 prepend 的实现。
     * 它们都悄悄地遍历了整个列表好几次，所以很慢。
     * 在第5章中，你将学习如何创建在所有场合都表现良好的函数式不可变列表。
     *
     * @param list
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> List<T> reverse(List<T> list) {
        return foldLeft(list, list(), x -> y -> prepend(y, x));
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习 3.10（难） 在 3.10 节中，你通过对每个元素应用一个操作定义了一个映射列表的方法。
     * 这个操作的实现包括了一个折叠。用 foldLeft 或 foldRight 重写 map 方法。
     * <p>
     * 用 foldLeft 重写 map 方法。
     *
     * @param list
     * @param f
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> mapViaFoldLeft(List<T> list, Function<T, U> f) {
        return foldLeft(list, list(), x -> y -> append(x, f.apply(y)));
    }

    /**
     * 3.3.5 化简和折叠列表 - 练习 3.10（难）
     * 用 foldRight 重写 map 方法。
     *
     * @param list
     * @param f
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> mapViaFoldRight(List<T> list, Function<T, U> f) {
        return foldRight(list, list(), x -> y -> prepend(f.apply(x), y));
    }

    /**
     * 3.3.7 对列表应用作用
     *
     * @param ts
     * @param e
     * @param <T>
     */
    public static <T> void forEach(Collection<T> ts, Effect<T> e) {
        for (T t : ts) {
            e.apply(t);
        }
    }

    /**
     * 3.3.9 构建反递归列表 - 练习3.11 用一个起始值、一个上限和函数 x -> x + 1 来编写一个生成列表的方法。你将称此方法为 range
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> range(int start, int end) {
        List<Integer> result = new ArrayList<>();
        int temp = start;
        while (temp < end) {
            result = append(result, temp);
            temp = temp + 1;
        }
        return result;
    }

    /**
     * 3.3.9 构建反递归列表 - 练习3.12 编写一个任何类型和任何条件都适用的通用 range 方法。
     * 因为区间（range)的概念主要用于数字，让我们称这个方法为 unfold
     *
     * @param seed
     * @param f
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> unfold(T seed, Function<T, T> f, Function<T, Boolean> p) {
        List<T> result = new ArrayList<>();
        T temp = seed;
        while (p.apply(temp)) {
            result = append(result, temp);
            temp = f.apply(temp);
        }
        return result;
    }

    /**
     * 3.3.9 构建反递归列表 - 练习3.13 用 unfold 方法来实现 range 方法
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> range_viaUnfold(int start, int end) {
        return unfold(start, x -> x + 1, x -> x < end);
    }

    /**
     * 3.3.9 构建反递归列表 - 练习3.14 根据你在先前章节中定义的函数方法来编写range的递归版本
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> range_recursive(int start, int end) {
        return end <= start ?
                CollectionUtilities.list() :
                CollectionUtilities.prepend(start, range_recursive(start + 1, end));
    }
}

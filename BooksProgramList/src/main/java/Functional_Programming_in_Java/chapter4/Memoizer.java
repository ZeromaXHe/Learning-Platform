package Functional_Programming_in_Java.chapter4;

import Functional_Programming_in_Java.chapter2.Function;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 19:34
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Memoizer<T, U> {
    private final Map<T, U> cache = new ConcurrentHashMap<>();

    private Memoizer() {
    }

    public static <T, U> Function<T, U> memoize(Function<T, U> function) {
        // 记忆化方法返回其函数参数的记忆化版本
        return new Memoizer<T, U>().doMemorize(function);
    }

    private Function<T, U> doMemorize(Function<T, U> function) {
        // doMemorize 方法处理计算，并在必要时调用原来的函数
        return input -> cache.computeIfAbsent(input, function::apply);
    }
}
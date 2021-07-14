package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter10.Nothing;
import Functional_Programming_in_Java.chapter2.Function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 19:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Console {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static IO<String> readLine(Nothing nothing) {
        return () -> {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        };
    }

    public static IO<Nothing> printLine(Object o) {
        return () -> {
            System.out.println(o.toString());
            return Nothing.instance;
        };
    }

    public static IOClass<String> readLine_IOClass(Nothing nothing) {
        return new IOClass.Suspend<>(() -> {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    /**
     * readLine 函数使用方法引用的一种简单实现
     */
    public static Function<Nothing, IOClass<String>> readLine = Console::readLine_IOClass;

    /**
     * 无须提供 Nothing 即可调用 readLine 方法的一个简便的辅助手法
     */
    public static IOClass<String> readLine_IOClass() {
        return readLine_IOClass(Nothing.instance);
    }

    public static IOClass<Nothing> printLine_IOClass(Object s) {
        return new IOClass.Suspend<>(() -> println(s));
    }

    private static Nothing println(Object s) {
        System.out.println(s);
        return Nothing.instance;
    }

    public static IOClass<Nothing> printLine_(Object s) {
        return new IOClass.Suspend<>(() -> {
            System.out.println(s);
            return Nothing.instance;
        });
    }

    public static Function<String, IOClass<Nothing>> printLine_ =
            s -> new IOClass.Suspend<>(() -> {
                System.out.println(s);
                return Nothing.instance;
            });

    public static Function<String, IOClass<Nothing>> printLine_IOClass = Console::printLine_IOClass;
}

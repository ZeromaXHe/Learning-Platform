package Functional_Programming_in_Java.chapter9;

import Functional_Programming_in_Java.chapter3.Supplier;

/**
 * @Author: zhuxi
 * @Time: 2021/7/9 9:55
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class BooleanMethods {
    public static void main(String[] args) {
        System.out.println(getFirst() || getSecond());
        System.out.println(or(() -> getFirst(), () -> getSecond()));
    }

    public static boolean getFirst() {
        return true;
    }

    public static boolean getSecond() {
        throw new IllegalStateException();
    }

    public static boolean or(Supplier<Boolean> a, Supplier<Boolean> b) {
//        return a.get() ? true : b.get() ? true : false;
        return a.get() || (b.get());
    }

    public static boolean and(Supplier<Boolean> a, Supplier<Boolean> b) {
//        return a.get() ? b.get() ? true : false : false;
        return a.get() && (b.get());
    }
}

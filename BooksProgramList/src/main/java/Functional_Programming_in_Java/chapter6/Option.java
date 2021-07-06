package Functional_Programming_in_Java.chapter6;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter3.Supplier;
import Functional_Programming_in_Java.chapter5.List;

import java.util.Objects;

/**
 * @Author: zhuxi
 * @Time: 2021/7/6 16:37
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Option<A> {
    @SuppressWarnings("rawtypes")
    private static Option none = new None();

    protected abstract A getOrThrow();

    /**
     * 6.3.1 从Option中取值 - 练习6.1 实现getOrElse方法，如果包含的值存在，则将其返回，否则返回一个提供的默认值
     * 6.3.1 从Option中取值 - 练习6.2 通过对getOrElse方法的参数使用惰性求值来修复先前的问题
     *
     * @param defaultValue
     * @return
     */
    public abstract A getOrElse(Supplier<A> defaultValue);

    /**
     * 6.3.2 将函数应用于可选值 - 练习6.3 创建一个map方法，通过应用从A到B的函数将Option<A>更改为Option<B>
     *
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> Option<B> map(Function<A, B> f);

    private static class None<A> extends Option<A> {
        private None() {
        }

        @Override
        protected A getOrThrow() {
            throw new IllegalStateException("get called on None");
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            // 请注意，虽然this和none都引用了同一个对象，但是由于用参数化A的原因而不能返回this。
            // none指向同一对象，但具有原始类型（无参）。这就是为什么使用@SuppressWarning("rawtypes")注解可避免将编译器的警告泄露给调用者。
            // 同样你并不直接访问none实例，而是调用none()的工厂方法，
            // 以避免在none()方法中已经通过@SuppressWarning("unchecked")注释避免了“未检查赋值警告（Unchecked asssignment warning）”
            return none();
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof None;
        }

        @Override
        public String toString() {
            return "None";
        }
    }

    private static class Some<A> extends Option<A> {
        private final A value;

        private Some(A a) {
            value = a;
        }

        @Override
        protected A getOrThrow() {
            return this.value;
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return this.value;
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return new Some<>(f.apply(this.value));
        }

        @Override
        public boolean equals(Object o) {
            return (this == o || o instanceof Some) && this.value.equals(((Some<?>) o).value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return String.format("Some(%s)", this.value);
        }
    }

    public static <A> Option<A> some(A a) {
        return new Some<>(a);
    }

    @SuppressWarnings("unchecked")
    public static <A> Option<A> none() {
        return none;
    }

    /**
     * 6.3.3 复合Option处理 - 练习6.4 创建一个flatMap实例方法，接收一个从A到Option<B>的函数为参数，并返回一个Option<B>
     *
     * @param f
     * @param <B>
     * @return
     */
    public <B> Option<B> flatMap(Function<A, Option<B>> f) {
        return map(f).getOrElse(Option::none);
    }

    /**
     * 6.3.3 复合Option处理 - 练习6.5 正如需要一个办法来映射返回一个Option（引出flatMap）的函数，
     * 你将需要一个getOrElse的版本作为Option的默认值
     *
     * @param defaultValue
     * @return
     */
    public Option<A> orElse(Supplier<Option<A>> defaultValue) {
        return map(x -> this).getOrElse(defaultValue);
    }

    /**
     * 6.3.3 复合Option处理 - 练习6.6 在第5章中，你创建了一个filter方法，用于从列表中删除所有不满足条件的元素，
     * 条件通过断言（换句话说，它是一个返回Boolean的函数）的形式来表达。为Option创建相同的方法。
     *
     * @param f
     * @return
     */
    public Option<A> filter(Function<A, Boolean> f) {
        return flatMap(x -> f.apply(x) ? this : none());
    }

    /**
     * 6.3.5 复合Option的其他方法 - 练习6.8 定义一个lift方法，它接收从A到B的函数为参数，并返回一个从Option<A>到Option<B>的函数。
     * 6.3.5 复合Option的其他方法 - 练习6.9 这样的解决方案对抛出异常的方法无效。编写一个lift方法，使其适用于抛出异常的方法。
     *
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> Function<Option<A>, Option<B>> lift(Function<A, B> f) {
        return x -> {
            try {
                return x.map(f);
            } catch (Exception e) {
                return Option.none();
            }
        };
    }

    public static <A, B> Function<A, Option<B>> hlift(Function<A, B> f) {
        return x -> {
            try {
                return Option.some(x).map(f);
            } catch (Exception e) {
                return Option.none();
            }
        };
    }

    /**
     * 6.3.5 复合Option的其他方法 - 练习6.10 编写一个map2方法，
     * 接收Option<A>、Option<B>和从(A,B)到C的柯里化形式的函数为参数，并返回一个Option<C>
     *
     * @param a
     * @param b
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    public static <A, B, C> Option<C> map2(Option<A> a, Option<B> b,
                                           Function<A, Function<B, C>> f) {
        return a.flatMap(ax -> b.map(bx -> f.apply(ax).apply(bx)));
    }

    public static <A, B, C, D> Option<D> map3(Option<A> a, Option<B> b, Option<C> c,
                                              Function<A, Function<B, Function<C, D>>> f) {
        return a.flatMap(ax -> b.flatMap(bx -> c.map(cx -> f.apply(ax).apply(bx).apply(cx))));
    }

    /**
     * 6.3.6 复合Option和List - 练习6.11 编写一个将List<Option<T>>转换为一个Option<List<T>>的sequence函数。
     * 如果原始列表中的所有值都是Some实例，则为Some<List<T>>，否则为None<List<T>>。
     * 6.3.6 复合Option和List - 练习6.12 定义一个生成相同结果但仅调用一次foldRight的traverse方法。
     *
     * @param list
     * @param <A>
     * @return
     */
    public static <A> Option<List<A>> sequence(List<Option<A>> list) {
//        return list.foldRight(some(List.list()), x -> y -> map2(x, y, a -> b -> b.cons(a)));
        return traverse(list, x -> x);
    }

    /**
     * 6.3.6 复合Option和List - 练习6.12 定义一个生成相同结果但仅调用一次foldRight的traverse方法。
     *
     * @param list
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> Option<List<B>> traverse(List<A> list, Function<A, Option<B>> f) {
        return list.foldRight(some(List.list()), x -> y -> map2(f.apply(x), y, a -> b -> b.cons(a)));
    }
}

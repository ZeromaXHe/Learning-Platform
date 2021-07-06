package Functional_Programming_in_Java.chapter5;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter4.TailCall;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/7/6 9:56
 * @Description: List实现为一个抽象类，类型参数为自己元素的类型，由类型变量A表示
 * @ModifiedBy: zhuxi
 */
public abstract class List<A> {
    public abstract A head();

    public abstract List<A> tail();

    public abstract boolean isEmpty();

    public abstract List<A> setHead(A h);

    /**
     * 5.3.1 更多列表操作 - 练习5.4 虽然tail方法不会以任何方式改变列表，但其效果与删除第一个元素相同。
     * 编写一个更通用的drop方法，从列表中删除前n个元素。当然，这个方法不会删除元素，但是会返回一个与预期结果相对应的新列表。
     * 这个“新”列表并没有什么新东西，因为将使用数据共享，所以不会创建任何东西。
     *
     * @param n
     * @return
     */
    public abstract List<A> drop(int n);

    /**
     * 5.3.1 更多列表操作 - 练习5.5 实现dropWhile方法，只要条件为真，就删除List的head元素
     * <p>
     * 请注意，当在空列表上调用dropWhile时，可能会遇到问题。原因是Java无法从传递给dropWhile方法的函数中推断列表的类型。
     * 可以使用这个方案： List<Integer> list = list(); list.dropWhile(); 或 List.<Integer>list().dropWhile(f);
     *
     * @param f
     * @return
     */
    public abstract List<A> dropWhile(Function<A, Boolean> f);

    /**
     * 5.3.1 更多列表操作 - 练习5.6 编写一个方法从列表中删除最后一个元素。该方法返回的结果应该是删除之后的列表。
     *
     * @return
     */
    public abstract List<A> init();

    public abstract List<A> reverse();

    /**
     * 5.4 使用高阶函数递归折叠列表 - 练习5.10 foldRight方法使用了递归，但它不是尾递归，因此会迅速栈溢出。
     * 创建一个尾递归的foldLeft方法替换foldRight，可以实现栈安全。
     *
     * @param identity
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> B foldLeft(B identity, Function<B, Function<A, B>> f);

    /**
     * 5.4.1 基于堆的foldRight递归版 - 练习5.14 使用第4章中学到的内容编写一个基于堆递归的实例版foldRight方法。
     *
     * @param identity
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> B foldRight(B identity, Function<A, Function<B, B>> f);

    /**
     * 5.4 使用高阶函数递归折叠列表 - 练习5.9 编写一个方法来计算列表的长度。该方法将调用foldRight方法
     *
     * @return
     */
    public abstract int length();

    // 用一个单例来表示空列表
    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil();

    private List() {
    }

    /**
     * Nil（不在列表里）子类表示空列表
     *
     * @param <A>
     */
    private static class Nil<A> extends List<A> {
        // Nil子类的无参私有构造函数
        private Nil() {
        }

        @Override
        public A head() {
            throw new IllegalStateException("head called on empty list");
        }

        @Override
        public List<A> tail() {
            throw new IllegalStateException("tail called on empty list");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public List<A> setHead(A h) {
            throw new IllegalStateException("setHead called on empty list");
        }

        @Override
        public List<A> drop(int n) {
            return this;
        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> f) {
            return this;
        }

        @Override
        public List<A> init() {
            throw new IllegalStateException("init called on empty list");
        }

        @Override
        public List<A> reverse() {
            return this;
        }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f) {
            return identity;
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f) {
            return identity;
        }

        @Override
        public int length() {
            return 0;
        }

        @Override
        public String toString() {
            return "[NIL]";
        }
    }

    /**
     * Cons(construct)子类表示非空列表
     *
     * @param <A>
     */
    private static class Cons<A> extends List<A> {
        private final A head;
        private final List<A> tail;

        // Cons子类的构造函数接收一个A(head)和一个List(tail)
        public Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public A head() {
            return head;
        }

        @Override
        public List<A> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<A> setHead(A h) {
            return new Cons<>(h, tail());
        }

        @Override
        public List<A> drop(int n) {
            return n <= 0 ?
                    this :
                    drop_(this, n).eval();
        }

        private TailCall<List<A>> drop_(List<A> list, int n) {
            return n <= 0 || list.isEmpty() ?
                    TailCall.ret(list) :
                    TailCall.sus(() -> drop_(list.tail(), n - 1));
        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> f) {
            return dropWhile_(this, f).eval();
        }

        private TailCall<List<A>> dropWhile_(List<A> list, Function<A, Boolean> f) {
            return !list.isEmpty() && f.apply(list.head()) ?
                    TailCall.sus(() -> dropWhile_(list.tail(), f)) :
                    TailCall.ret(list);
        }

        @Override
        public List<A> init() {
            return reverse().tail().reverse();
        }

        @Override
        public List<A> reverse() {
            return reverse_(list(), this).eval();
        }

        private TailCall<List<A>> reverse_(List<A> acc, List<A> list) {
            return list.isEmpty() ?
                    TailCall.ret(acc) :
                    TailCall.sus(() -> reverse_(new Cons<>(list.head(), acc), list.tail()));
        }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f) {
            return foldLeft_(identity, this, f).eval();
        }

        private <B> TailCall<B> foldLeft_(B acc, List<A> list, Function<B, Function<A, B>> f) {
            return list.isEmpty() ?
                    TailCall.ret(acc) :
                    TailCall.sus(() -> foldLeft_(f.apply(acc).apply(list.head()), list.tail(), f));
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f) {
//            return foldRight_(identity, this.reverse(), identity, f).eval();
            return reverse().foldLeft(identity, x -> y -> f.apply(y).apply(x));
        }

        private <B> TailCall<B> foldRight_(B acc, List<A> ts, B identity, Function<A, Function<B, B>> f) {
            return ts.isEmpty() ?
                    TailCall.ret(acc) :
                    TailCall.sus(() -> foldRight_(f.apply(ts.head()).apply(acc), ts.tail(), identity, f));
        }

        @Override
        public int length() {
            return foldRight(this, 0, x -> y -> y + 1);
        }

        @Override
        public String toString() {
            return String.format("[%sNIL]", toString(new StringBuilder(), this).eval());
        }

        private TailCall<StringBuilder> toString(StringBuilder acc, List<A> list) {
            return list.isEmpty() ?
                    TailCall.ret(acc) :
                    TailCall.sus(() -> toString(acc.append(list.head()).append(", "), list.tail()));
        }
    }

    @SuppressWarnings("unchecked")
    public static <A> List<A> list() {
        return NIL;
    }

    @SafeVarargs
    public static <A> List<A> list(A... a) {
        List<A> n = list();
        for (int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }
        return n;
    }

    /**
     * 请确保不要使用这个实现，因为它比命令式的实现慢10000倍。
     * 这就是不要盲目地追求函数式的一个绝佳示例。命令式版本有一个函数式接口，而这就是你所需要的。
     *
     * @param as
     * @param <A>
     * @return
     */
    @Deprecated
    @SafeVarargs
    public static <A> List<A> list_recursive(A... as) {
        return list_(list(), as).eval();
    }

    /**
     * 请注意递归并不是问题，使用TailCall的递归几乎和迭代一样快。这里的问题是copyOfRange方法，它真的非常慢。
     *
     * @param acc
     * @param as
     * @param <A>
     * @return
     */
    private static <A> TailCall<List<A>> list_(List<A> acc, A[] as) {
        return as.length == 0 ?
                TailCall.ret(acc) :
                TailCall.sus(() -> list_(new Cons<>(as[as.length - 1], acc),
                        Arrays.copyOfRange(as, 0, as.length - 1)));
    }

    /**
     * 5.3 在列表操作中共享数据 - 练习5.1 实现函数式示例方法cons，在列表的开头添加一个元素。（记住，cons代表construct）
     *
     * @param a
     * @return
     */
    public List<A> cons(A a) {
        return new Cons<>(a, this);
    }

    /**
     * 5.3 在列表操作中共享数据 - 练习5.2 实现实例方法setHead，用新值替换List的第一个元素
     *
     * @param list
     * @param h
     * @param <A>
     * @return
     */
    public static <A> List<A> setHead(List<A> list, A h) {
        return list.setHead(h);
    }

    public static <A, B> B foldRight(List<A> list, B n, Function<A, Function<B, B>> f) {
        /// 清单5.2 实现foldRight并将其用于sum和product
//        return list.isEmpty() ?
//                n :
//                f.apply(list.head()).apply(foldRight(list.tail(), n, f));
        return list.foldRight(n, f);
    }

    /**
     * 5.4.1 基于堆的foldRight递归版 - 练习5.15 通过foldLeft或foldRight来实现concat
     *
     * @param list1
     * @param list2
     * @param <A>
     * @return
     */
    public static <A> List<A> concat(List<A> list1, List<A> list2) {
        return foldRight(list1, list2, x -> y -> new Cons<>(x, y));
    }

    /**
     * 5.4.1 基于堆的foldRight递归版 - 练习5.16 编写一个方法，用于将列表的列表展平为包含每个子列表的所有元素的列表。
     *
     * @param list
     * @param <A>
     * @return
     */
    public static <A> List<A> flatten(List<List<A>> list) {
        return foldRight(list, List.<A>list(), x -> y -> concat(x, y));
        /// 使用flatMap
//        return list.flatMap(x -> x);
    }

    /**
     * 5.4.2 映射和过滤列表 - 练习5.19 编写一个通用的函数式方法map，允许你通过向列表中的每个元素应用指定的函数来修改它。
     * 这次使它成为List的一个实例方法。
     *
     * @param f
     * @param <B>
     * @return
     */
    public <B> List<B> map(Function<A, B> f) {
        return foldRight(list(), h -> t -> new Cons<>(f.apply(h), t));
    }

    /**
     * 5.4.2 映射和过滤列表 - 练习5.20 编写一个filter方法，从列表中删除不符合给定断言的元素。再次将其实现为实例方法。
     *
     * @param f
     * @return
     */
    public List<A> filter(Function<A, Boolean> f) {
        return foldRight(list(), h -> t -> f.apply(h) ? new Cons<>(h, t) : t);
    }

    /**
     * 5.4.2 映射和过滤列表 - 练习5.21 编写一个flatMap方法，该方法对List<A>的每个元素应用一个从A到List<B>的函数，并返回一个List<B>
     *
     * @param f
     * @param <B>
     * @return
     */
    public <B> List<B> flatMap(Function<A, List<B>> f) {
        return foldRight(list(), h -> t -> concat(f.apply(h), t));
    }

    /**
     * 5.4.2 映射和过滤列表 - 练习5.22 基于flatMap创建一个新版本的filter
     *
     * @param list
     * @param p
     * @param <A>
     * @return
     */
    public static <A> List<A> filterViaFlatMap(List<A> list, Function<A, Boolean> p) {
        return list.flatMap(a -> p.apply(a) ? List.list(a) : List.list());
    }
}

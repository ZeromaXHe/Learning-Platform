package Functional_Programming_in_Java.chapter5;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter4.TailCall;
import Functional_Programming_in_Java.chapter4.Tuple3;
import Functional_Programming_in_Java.chapter6.Map;
import Functional_Programming_in_Java.chapter7.Result;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

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
     * 8.3.2 通过索引访问元素 - 练习8.13（难，可选） 找到一个方案，使得基于折叠的版本一旦找到结果就会终止。
     * 为此你需要一个特定版的foldLeft
     * 8.3.3 拆分列表 - 练习8.15（如果你完成了练习8.13，就没那么难了） 你能否想出一个使用折叠而不是显式递归的实现？
     *
     * @param identity
     * @param zero
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> Tuple<B, List<A>> foldLeft(B identity, B zero, Function<B, Function<A, B>> f);

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

    /**
     * 8.1.3 记忆化的缺点 - 练习8.1 创建一个记忆化版的length方法
     *
     * @return
     */
    public abstract int lengthMemoized();

    /**
     * 8.2.1 List中返回Result的方法 - 练习8.2 在List<A>中实现headOption方法以返回一个Result<A>
     *
     * @return
     */
    public abstract Result<A> headOption();

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
        public <B> Tuple<B, List<A>> foldLeft(B identity, B zero, Function<B, Function<A, B>> f) {
            return new Tuple<>(identity, list());
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
        public int lengthMemoized() {
            return 0;
        }

        @Override
        public Result<A> headOption() {
            return Result.empty();
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
        private final int length;

        // Cons子类的构造函数接收一个A(head)和一个List(tail)
        public Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
            this.length = tail.length() + 1;
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
        public <B> Tuple<B, List<A>> foldLeft(B identity, B zero, Function<B, Function<A, B>> f) {
            return foldLeft_(identity, zero, this, f).eval();
        }

        private <B> TailCall<Tuple<B, List<A>>> foldLeft_(B acc, B zero, List<A> list, Function<B, Function<A, B>> f) {
            // 唯一的区别是如果发现累加器的值为“零”，则停止递归并且将其返回
            return list.isEmpty() || acc.equals(zero) ?
                    TailCall.ret(new Tuple<>(acc, list)) :
                    TailCall.sus(() -> foldLeft_(f.apply(acc).apply(list.head()), zero, list.tail(), f));
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
            /// Scala List类中借用的length命令式风格实现
            // 它的速度只比基于折叠的实现快了5倍
//            List<A> these = this;
//            int len = 0;
//            while (!these.isEmpty()) {
//                len += 1;
//                these = these.tail();
//            }
//            return len;
        }

        @Override
        public int lengthMemoized() {
            return length;
        }

        @Override
        public Result<A> headOption() {
            return Result.success(head);
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

    public static <T> List<T> cons(T t, List<T> list) {
        return list.cons(t);
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

    /**
     * 8.2.1 List中返回Result的方法 - 练习8.3 创建一个lastOption方法以返回列表中最后一个元素的Result
     *
     * @return
     */
    public Result<A> lastOption() {
        return foldLeft(Result.empty(), x -> Result::success);
        /// 显式递归
//        return isEmpty() ?
//                Result.empty() :
//                tail().isEmpty() ?
//                        Result.success(head()) :
//                        tail().lastOption();
    }

    /**
     * 8.2.2 将List<Result>转换为Result<List> - 练习8.5 编写一个名为flattenResult的方法，
     * 以List<Result<A>>为参数并返回一个List<A>，其中包含原始列表中所有值为success的元素，并忽略failure和empty值。
     *
     * @param list
     * @param <A>
     * @return
     */
    public static <A> List<A> flattenResult(List<Result<A>> list) {
        return flatten(list.foldRight(list(), x -> y -> y.cons(x.map(List::list).getOrElse(list()))));
    }

    /**
     * 8.2.2 将List<Result>转换为Result<List> - 练习8.6 编写将List<Result<T>>复合为Result<List<T>>的sequence函数。
     * 如果原始列表中所有值均为Success实例，则为Success<List<T>>，否则就是Failure<List<T>>。
     *
     * @param list
     * @param <A>
     * @return
     */
    public static <A> Result<List<A>> sequence(List<Result<A>> list) {
        // 请注意，这个实现把一个空的Result作为Failure处理，并返回遇到的第一个failure，它可能是Failure或Empty。
//        return list.foldRight(Result.success(List.list()), x -> y -> Result.map2(x, y, a -> b -> b.cons(a)));
        return traverse(list, x -> x);
    }

    /// 如果你坚持认为Empty表示可选数据，那就需要先过滤列表以删除Empty元素
//    public static <A> Result<List<A>> sequence2(List<Result<A>> list) {
//        return list.filter(a -> a.isSuccess() || a.isFailure())
//                .foldRight(Result.success(List.list()), x -> y -> Result.map2(x, y, a -> b -> b.cons(a)));
//    }

    /**
     * 8.2.2 将List<Result>转换为Result<List> - 练习8.7 定义一个更通用的traverse方法，遍历A列表，
     * 同时应用从A到Result<B>的函数并生成Result<List<B>>。
     *
     * @param list
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> Result<List<B>> traverse(List<A> list, Function<A, Result<B>> f) {
        return list.foldRight(Result.success(List.list()), x -> y -> Result.map2(f.apply(x), y, a -> b -> b.cons(a)));
    }

    /**
     * 8.3.1 压缩和解压缩列表 - 练习8.8 编写一个zipWith方法，给定一个函数参数，复合两个不同类型的列表元素以产生一个新的列表。
     *
     * @param list1
     * @param list2
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    public static <A, B, C> List<C> zipWith(List<A> list1, List<B> list2, Function<A, Function<B, C>> f) {
        return zipWith_(list(), list1, list2, f).eval();
    }

    private static <A, B, C> TailCall<List<C>> zipWith_(List<C> acc, List<A> list1, List<B> list2, Function<A, Function<B, C>> f) {
        return list1.isEmpty() || list2.isEmpty() ?
                TailCall.ret(acc) :
                TailCall.sus(() -> zipWith_(new Cons<>(f.apply(list1.head()).apply(list2.head()), acc), list1.tail(), list2.tail(), f));
    }

    /**
     * 8.3.1 压缩和解压缩列表 - 练习8.9 上一个练习包含了用两个列表中索引匹配的元素创建一个列表。
     * 编写一个product方法，用于生成一个列表，它包含了从两个列表中获取的索引可能组合。
     *
     * @param list1
     * @param list2
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    public static <A, B, C> List<C> product(List<A> list1, List<B> list2, Function<A, Function<B, C>> f) {
        return list1.flatMap(a -> list2.map(b -> f.apply(a).apply(b)));
    }

    /**
     * 8.3.1 压缩和解压缩列表 - 练习8.10 编写一个unzip静态方法将一个元组列表转换为列表元组。
     *
     * @param list
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> Tuple<List<A>, List<B>> unzip(List<Tuple<A, B>> list) {
        return list.foldRight(new Tuple<>(list(), list()), t -> t1 -> new Tuple<>(t1._1.cons(t._1), t1._2.cons(t._2)));
    }

    /**
     * 8.3.1 压缩和解压缩列表 - 练习8.11 泛化unzip函数，使其可以将任何类型的列表转换为列表元组，
     * 给定一个以列表类型的对象为参数的函数，并生成一个元组。
     *
     * @param f
     * @param <A1>
     * @param <A2>
     * @return
     */
    public <A1, A2> Tuple<List<A1>, List<A2>> unzip(Function<A, Tuple<A1, A2>> f) {
        // 一件重要的事情是：函数被用了两次。为了避免应用两次该函数，你需要使用一个多行lambda。
        return this.foldRight(new Tuple<>(list(), list()), a -> t1 -> {
            Tuple<A1, A2> t = f.apply(a);
            return new Tuple<>(t1._1.cons(t._1), t1._2.cons(t._2));
        });
    }

    /**
     * 8.3.2 通过索引访问元素 - 练习8.12 编写一个getAt方法，它以一个索引为参数，并返回相应的元素。
     * 在索引超出范围的情况下，该方法不应该抛出异常。
     * 8.3.2 通过索引访问元素 - 练习8.13（难，可选） 找到一个方案，使得基于折叠的版本一旦找到结果就会终止。
     *
     * @param index
     * @return
     */
    public Result<A> getAt(int index) {
        /// 显式递归
//        return index < 0 || index >= length() ?
//                Result.failure("Index out of bound") :
//                getAt_(this, index).eval();

        /// 左折叠
        // - 不易读；
        // - 必须使用中间结果rt，因为Java不能推断出正确的类型；
        // - 效率较低，因为即使找到了要搜索的值，它仍将继续折叠整个列表。
//        Tuple<Result<A>, Integer> identity = new Tuple<>(Result.failure("Index out of bound"), index);
//        Tuple<Result<A>, Integer> rt = index < 0 || index >= length() ?
//                identity :
//                foldLeft(identity, ta -> a -> ta._2 < 0 ?
//                        ta :
//                        new Tuple<>(Result.success(a), ta._2 - 1));
//        return rt._1;

        /// 一旦找到结果就会终止
        class Tuple<T, U> {
            public final T _1;
            public final U _2;

            public Tuple(T t, U u) {
                this._1 = Objects.requireNonNull(t);
                this._2 = Objects.requireNonNull(u);
            }

            @Override
            public boolean equals(Object o) {
                if (!(o.getClass() == this.getClass())) {
                    return false;
                } else {
                    @SuppressWarnings("rawtypes")
                    Tuple that = (Tuple) o;
                    return _2.equals(that._2);
                }
            }
        }

        Tuple<Result<A>, Integer> zero = new Tuple<>(Result.failure("Index out of bound"), -1);
        Tuple<Result<A>, Integer> identity = new Tuple<>(Result.failure("Index out of bound"), index);
        Tuple<Result<A>, Integer> rt = index < 0 || index >= length() ?
                identity :
                foldLeft(identity, zero, ta -> a -> ta._2 < 0 ?
                        ta :
                        new Tuple<>(Result.success(a), ta._2 - 1))._1;
        return rt._1;
    }

    private static <A> TailCall<Result<A>> getAt_(List<A> list, int index) {
        return index == 0 ?
                TailCall.ret(Result.success(list.head())) :
                TailCall.sus(() -> getAt_(list.tail(), index - 1));
    }

    /**
     * 8.3.3 拆分列表 - 练习8.14 编写一个splitAt方法，它以int为参数，在给定位置拆分列表并返回两个列表。
     * 不该有任何的IndexOutOfBoundExceptions。相反，小于0的索引应被视为0，而高于max的索引应被视为索引的最大值。
     * 8.3.3 拆分列表 - 练习8.15（如果你完成了练习8.13，就没那么难了） 你能否想出一个使用折叠而不是显式递归的实现？
     *
     * @param index
     * @return
     */
    public Tuple<List<A>, List<A>> splitAt(int index) {
        /// 显式递归
//        return index < 0 ?
//                splitAt(0) :
//                index > length() ?
//                        splitAt(length()) :
//                        splitAt_(list(), this.reverse(), this.length() - index).eval();

        /// 折叠
//        int ii = index < 0 ? 0 : Math.min(index, length());
//        Tuple3<List<A>, List<A>, Integer> identity = new Tuple3<>(List.list(), List.list(), ii);
//        Tuple3<List<A>, List<A>, Integer> rt =
//                foldLeft(identity, ta -> a -> ta._3 == 0 ?
//                        new Tuple3<>(ta._1, ta._2.cons(a), ta._3) :
//                        new Tuple3<>(ta._1.cons(a), ta._2, ta._3 - 1));
//        return new Tuple<>(rt._1.reverse(), rt._2.reverse());

        /// 特殊的foldLeft
        class Tuple3<T, U, V> {
            public final T _1;
            public final U _2;
            public final V _3;

            public Tuple3(T t, U u, V v) {
                this._1 = Objects.requireNonNull(t);
                this._2 = Objects.requireNonNull(u);
                this._3 = Objects.requireNonNull(v);
            }

            @Override
            public boolean equals(Object o) {
                if (!(o.getClass() == this.getClass())) {
                    return false;
                } else {
                    @SuppressWarnings("rawtypes")
                    Tuple3 that = (Tuple3) o;
                    return _3.equals(that._3);
                }
            }
        }

        Tuple3<List<A>, List<A>, Integer> zero = new Tuple3<>(list(), list(), 0);
        Tuple3<List<A>, List<A>, Integer> identity = new Tuple3<>(list(), list(), index);
        Tuple<Tuple3<List<A>, List<A>, Integer>, List<A>> rt = index <= 0 ?
                new Tuple<>(identity, this) :
                foldLeft(identity, zero, ta -> a -> ta._3 < 0 ?
                        ta :
                        new Tuple3<>(ta._1.cons(a), ta._2, ta._3 - 1));
        return new Tuple<>(rt._1._1.reverse(), rt._2);
    }

    private TailCall<Tuple<List<A>, List<A>>> splitAt_(List<A> acc, List<A> list, int i) {
        return i == 0 || list.isEmpty() ?
                TailCall.ret(new Tuple<>(list.reverse(), acc)) :
                TailCall.sus(() -> splitAt_(acc.cons(list.head()), list.tail(), i - 1));
    }

    /**
     * 8.3.4 搜索子列表 - 练习8.16 实现一个hasSubList方法来检查以一个列表是否是另一个的子列表。
     *
     * @param list
     * @param sub
     * @param <A>
     * @return
     */
    public static <A> boolean hasSubList(List<A> list, List<A> sub) {
        return hasSubList_(list, sub).eval();
    }

    private static <A> TailCall<Boolean> hasSubList_(List<A> list, List<A> sub) {
        return list.isEmpty() ?
                TailCall.ret(sub.isEmpty()) :
                startWith(list, sub) ?
                        TailCall.ret(true) :
                        TailCall.sus(() -> hasSubList_(list.tail(), sub));
    }

    /**
     * 8.3.4 搜索子列表 - 练习8.16 实现一个hasSubList方法来检查以一个列表是否是另一个的子列表。
     * 你首先需要实现 startsWith 方法来确定列表是否以子列表开头。一旦完成，你就可以从列表的每个元素开始递归检查该方法。
     *
     * @param list
     * @param sub
     * @param <A>
     * @return
     */
    public static <A> Boolean startWith(List<A> list, List<A> sub) {
        /// 显式递归
//        return sub.isEmpty() ?
//                true :
//                list.isEmpty() ?
//                        false :
//                        list.head().equals(sub.head()) ?
//                                startWith(list.tail(), sub.tail()) :
//                                false;
        /// 显式递归（化简后）
//        return sub.isEmpty() || (!list.isEmpty() && (list.head().equals(sub.head()) ?
//                startWith(list.tail(), sub.tail()) :
//                false));

        /// TailCall基于堆的版本
        return startWith_(list, sub).eval();
    }

    private static <A> TailCall<Boolean> startWith_(List<A> list, List<A> sub) {
        return sub.isEmpty() ?
                TailCall.ret(Boolean.TRUE) :
                list.isEmpty() ?
                        TailCall.ret(Boolean.FALSE) :
                        list.head().equals(sub.head()) ?
                                TailCall.sus(() -> startWith_(list.tail(), sub.tail())) :
                                TailCall.ret(Boolean.FALSE);
    }

    /**
     * 8.3.5 使用列表的其他函数 - 练习8.17 创建一个以从A到B的函数为参数并返回Map的groupBy方法，
     * 其键为把函数应用于列表中每个元素的结果，而值为与每个键相对应的元素列表。
     *
     * @param f
     * @param <B>
     * @return
     */
    public <B> Map<B, List<A>> groupBy_imperative(Function<A, B> f) {
        List<A> workList = this;
        Map<B, List<A>> m = Map.empty();
        while (!workList.isEmpty()) {
            final B k = f.apply(workList.head());
            List<A> rt = m.get(k).getOrElse(list()).cons(workList.head());
            m = m.put(k, rt);
            workList = workList.tail();
        }
        return m;
    }

    public <B> Map<B, List<A>> groupBy(Function<A, B> f) {
        return foldRight(Map.empty(), t -> mt -> {
            final B k = f.apply(t);
            return mt.put(k, mt.get(k).getOrElse(list()).cons(t));
        });
    }

    /**
     * 8.3.5 使用列表的其他函数 - 练习8.18 编写一个unfold方法，以一个起始元素S和一个从S到Result<Tuple<A, S>>的函数f为参数，
     * 并通过依次将f应用于S值，在结果为Success时生成List<A>。
     *
     * @param z
     * @param f
     * @param <A>
     * @param <S>
     * @return
     */
    public static <A, S> List<A> unfold(S z, Function<S, Result<Tuple<A, S>>> f) {
        return unfold_(list(), z, f).eval().reverse();
    }

    private static <A, S> TailCall<List<A>> unfold_(List<A> acc, S z, Function<S, Result<Tuple<A, S>>> f) {
        // 但是请注意，这样做反转了列表。对于小列表而言，这可能不是一个大问题。
        // 但是对于大列表而言，这就是一个大问题。在这种情况下，恢复到命令式风格可能是一个选择。
        Result<Tuple<A, S>> r = f.apply(z);
        Result<TailCall<List<A>>> result = r.map(rt -> TailCall.sus(() -> unfold_(acc.cons(rt._1), rt._2, f)));
        return result.getOrElse(TailCall.ret(acc));
    }

    /**
     * 8.3.5 使用列表的其他函数 - 练习8.19 编写一个以两个整型为参数的range方法，并生成大于等于第一个且小于第二个整型的所有整型的列表。
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> range(int start, int end) {
        return List.unfold(start, i -> i < end ?
                Result.success(new Tuple<>(i, i + 1)) :
                Result.empty());
    }

    /**
     * 8.3.5 使用列表的其他函数 - 练习8.20 创建一个exists方法，接收一个从A到Boolean的函数以表示条件，
     * 如果列表至少包含一个满足该条件的元素，则返回true。不要使用显式递归，而是尝试使用已经定义过的方法。
     *
     * @param p
     * @return
     */
    public boolean exists(Function<A, Boolean> p) {
        return foldLeft(false, true, x -> y -> x || p.apply(y))._1;
    }

    /**
     * 8.3.5 使用列表的其他函数 - 练习8.21 创建一个forAll方法，它接收一个从A到Boolean的函数用以表示条件，
     * 如果列表中的所有元素都满足此条件，则返回true
     *
     * @param p
     * @return
     */
    public boolean forAll(Function<A, Boolean> p) {
//        return foldLeft(true, false, x -> y -> x && p.apply(y))._1;
        return !exists(x -> !p.apply(x));
    }

    /**
     * 8.4.2 将列表拆分为子列表 - 练习8.22 编写一个divide(int depth)方法，将一个列表拆分为多个子列表。
     * 该列表将被拆分为两部分，每个子列表递归拆分为两部分，depth参数表示递归的步骤数。
     * 首先你要定义一个新版的splitListAt
     *
     * @param i
     * @return
     */
    public List<List<A>> splitListAt(int i) {
        return splitListAt_(list(), this.reverse(), i).eval();
    }

    private TailCall<List<List<A>>> splitListAt_(List<A> acc, List<A> list, int i) {
        return i == 0 || list.isEmpty() ?
                TailCall.ret(List.list(list.reverse(), acc)) :
                TailCall.sus(() -> splitListAt_(acc.cons(list.head()), list.tail(), i - 1));
    }

    /**
     * 8.4.2 将列表拆分为子列表 - 练习8.22 编写一个divide(int depth)方法，将一个列表拆分为多个子列表。
     * 该列表将被拆分为两部分，每个子列表递归拆分为两部分，depth参数表示递归的步骤数。
     *
     * @param depth
     * @return
     */
    public List<List<A>> divide(int depth) {
        return this.isEmpty() ?
                list(this) :
                divide_(list(this), depth);
    }

    private List<List<A>> divide_(List<List<A>> list, int depth) {
        return list.head().length() < depth || depth < 2 ?
                list :
                divide_(list.flatMap(x -> x.splitListAt(x.length() / 2)), depth / 2);
    }

    /**
     * 8.4.3 并行处理子列表 - 练习8.23 在List<A>中创建一个parFoldLeft方法，它将接收与foldLeft相同的参数并加上ExecutorService，
     * 以及一个从B到B到B并返回Result<List<B>>的函数。额外的函数将用于复合子列表的结果。
     *
     * @param es
     * @param identity
     * @param f
     * @param m
     * @param <B>
     * @return
     */
    public <B> Result<B> parFoldLeft(ExecutorService es, B identity, Function<B, Function<A, B>> f,
                                     Function<B, Function<B, B>> m) {
        final int chunks = 1024;
        final List<List<A>> dList = divide(chunks);

        try {
            List<B> result = dList.map(x -> es.submit(() -> x.foldLeft(identity, f))).map(x -> {
                try {
                    return x.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
            return Result.success(result.foldLeft(identity, m));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    /**
     * 8.4.3 并行处理子列表 - 练习8.24 虽然可以通过折叠来实现映射（并且可以因此受益于自动并行化），但是它也可以不用折叠就实现并行。
     * 这也许是能在列表中实现的最简单的自动化并行处理。创建一个parMap方法，它会自动将给定的函数并行应用于列表中的所有元素。
     *
     * @param es
     * @param g
     * @param <B>
     * @return
     */
    public <B> Result<List<B>> parMap(ExecutorService es, Function<A, B> g) {
        try {
            return Result.success(this.map(x -> es.submit(() -> g.apply(x))).map(x -> {
                try {
                    return x.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}

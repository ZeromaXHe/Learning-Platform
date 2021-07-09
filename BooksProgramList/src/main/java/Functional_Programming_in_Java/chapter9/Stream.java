package Functional_Programming_in_Java.chapter9;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter3.Supplier;
import Functional_Programming_in_Java.chapter4.TailCall;
import Functional_Programming_in_Java.chapter5.List;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/9 10:09
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Stream<A> {
    private static Stream EMPTY = new Empty();

    public abstract Tuple<A, Stream<A>> head();

    public abstract Stream<A> tail();

    public abstract Boolean isEmpty();

    /**
     * 9.5.1 记忆已计算的值 - 练习9.1 编写一个headOption方法，返回流的已计算的head
     * 9.8 避免null引用和可变字段
     *
     * @return
     */
    public abstract Tuple<Result<A>, Stream<A>> headOption();

    /**
     * 9.5.2 对流的操作 - 练习9.3 编写一个take(n)方法以返回流的前n个元素，还有在删除前n个元素后返回剩余流的drop(n)方法。
     * 请注意，你必须确保在调用这些方法时不会求值。
     *
     * @param n
     * @return
     */
    public abstract Stream<A> take(int n);

    public abstract Stream<A> drop(int n);

    /**
     * 9.5.2 对流的操作 - 练习9.4 编写一个takeWhile方法，只要条件匹配，它将返回包含所有起始元素的Stream
     *
     * @param p
     * @return
     */
    public abstract Stream<A> takeWhile(Function<A, Boolean> p);

    /**
     * 9.6.1 折叠流 - 练习9.7 为流创建一个foldRight方法。此方法将类似于List.foldRight方法，但你应该关注惰性。
     *
     * @param z
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> B foldRight(Supplier<B> z, Function<A, Function<Supplier<B>, B>> f);

    private Stream() {
    }

    private static class Empty<A> extends Stream<A> {
        @Override
        public Tuple<A, Stream<A>> head() {
            throw new IllegalStateException("head called on empty");
        }

        @Override
        public Stream<A> tail() {
            throw new IllegalStateException("tail called on empty");
        }

        @Override
        public Boolean isEmpty() {
            return true;
        }

        @Override
        public Tuple<Result<A>, Stream<A>> headOption() {
            return new Tuple<>(Result.empty(), this);
        }

        @Override
        public Stream<A> take(int n) {
            return this;
        }

        @Override
        public Stream<A> drop(int n) {
            return this;
        }

        @Override
        public Stream<A> takeWhile(Function<A, Boolean> p) {
            return this;
        }

        @Override
        public <B> B foldRight(Supplier<B> z, Function<A, Function<Supplier<B>, B>> f) {
            return z.get();
        }
    }

    private static class Cons<A> extends Stream<A> {
        private final Supplier<A> head;
        private Result<A> h;
        private final Supplier<Stream<A>> tail;

        private Cons(Supplier<A> h, Supplier<Stream<A>> t) {
            this.head = h;
            this.tail = t;
            this.h = Result.empty();
        }

        private Cons(A h, Supplier<Stream<A>> t) {
            this.head = () -> h;
            this.tail = t;
            this.h = Result.empty();
        }

        @Override
        public Tuple<A, Stream<A>> head() {
            A a = h.getOrElse(head.get());
            return h.isEmpty()
                    ? new Tuple<>(a, new Cons<>(a, tail))
                    : new Tuple<>(a, this);
        }

        @Override
        public Stream<A> tail() {
            return tail.get();
        }

//        public A head() {
//            if (h == null) {
//                h = Result.success(head.get());
//            }
//            return h;
//        }

//        @Override
//        public Stream<A> tail() {
//            if (t == null) {
//                t = tail.get();
//            }
//            return t;
//        }

        @Override
        public Boolean isEmpty() {
            return false;
        }

        @Override
        public Tuple<Result<A>, Stream<A>> headOption() {
            Tuple<A, Stream<A>> t = head();
            return new Tuple<>(Result.success(t._1), t._2);
        }

//        @Override
//        public Result<A> headOption() {
//            return Result.success(head());
//        }

        @Override
        public Stream<A> take(int n) {
            return n <= 0 ?
                    empty() :
                    cons(head, () -> tail().take(n - 1));
        }

        @Override
        public Stream<A> drop(int n) {
            return drop_(this, n).eval();
        }

        private TailCall<Stream<A>> drop_(Stream<A> acc, int n) {
            return n <= 0 ?
                    TailCall.ret(acc) :
                    TailCall.sus(() -> drop_(acc.tail(), n - 1));
        }

        @Override
        public Stream<A> takeWhile(Function<A, Boolean> p) {
            return p.apply(head()._1) ?
                    cons(head, () -> tail().takeWhile(p)) :
                    empty();
        }

        @Override
        public <B> B foldRight(Supplier<B> z, Function<A, Function<Supplier<B>, B>> f) {
            // 注意，这个方法不是栈安全的，因此不应该把此类计算用于对大于1000个整数的列表求和。
            return f.apply(head()._1).apply(() -> tail().foldRight(z, f));
        }
    }

    static <A> Stream<A> cons(Supplier<A> hd, Supplier<Stream<A>> tl) {
        return new Cons<>(hd, tl);
    }

    static <A> Stream<A> cons(Supplier<A> hd, Stream<A> tl) {
        return new Cons<>(hd, () -> tl);
    }


    @SuppressWarnings("unchecked")
    public static <A> Stream<A> empty() {
        return EMPTY;
    }

    /**
     * 9.7 处理无限流 - 练习9.16 编写一个iterate方法来泛化from和repeat方法，
     * iterate方法具有两个参数：用于第一个值的seed和计算下一个值的函数。
     * 9.7 处理无限流 - 练习9.18 iterate方法可以进一步被泛化。编写一个unfold方法
     *
     * @param i
     * @return
     */
    public static Stream<Integer> from(int i) {
//        return cons(() -> i, () -> from(i + 1));
        /// iterate版
//        return iterate(i, x -> x + 1);
        /// unfold版
        return unfold(i, x -> Result.success(new Tuple<>(x, x + 1)));
    }

    /**
     * 9.5.2 对流的操作 - 练习9.2 创建一个toList方法将Stream转换为List
     *
     * @return
     */
    public List<A> toList() {
        return toList_(this, List.list()).eval().reverse();
    }

    private TailCall<List<A>> toList_(Stream<A> s, List<A> acc) {
        // 请注意，在如Stream.from(1)创建的无限流上调用toList会创建无限列表。
        // 与流不同，该列表被及早求值，所以理论上它会导致永不停止的程序。（实际上，它将以OutOfMemoryError告终。）
        return s.isEmpty() ?
                TailCall.ret(acc) :
                TailCall.sus(() -> toList_(s.tail(), List.cons(s.head()._1, acc)));
    }

    /**
     * 9.5.2 对流的操作 - 练习9.5 编写一个dropWhile方法，返回一个前面那些满足条件的元素被删除的流
     *
     * @param p
     * @return
     */
    public Stream<A> dropWhile(Function<A, Boolean> p) {
        return dropWhile_(this, p).eval();
    }

    private TailCall<Stream<A>> dropWhile_(Stream<A> acc, Function<A, Boolean> p) {
        return acc.isEmpty() ?
                TailCall.ret(acc) :
                p.apply(acc.head()._1) ?
                        TailCall.sus(() -> dropWhile_(acc.tail(), p)) :
                        TailCall.ret(acc);
    }

    /**
     * 9.6 惰性的真正本质 - 练习9.6 为Stream创建一个exists方法。该方法应该对元素求值，直到满足条件为止。
     * 如果没有满足条件，则将对所有元素求值。
     *
     * @param p
     * @return
     */
    public boolean exists(Function<A, Boolean> p) {
        return exists_(this, p).eval();
    }

    private TailCall<Boolean> exists_(Stream<A> s, Function<A, Boolean> p) {
        return s.isEmpty() ?
                TailCall.ret(false) :
                p.apply(s.head()._1) ?
                        TailCall.ret(true) :
                        TailCall.sus(() -> exists_(s.tail(), p));
    }

    /**
     * 9.6.1 折叠流 - 练习9.8 通过foldRight实现takeWhile方法。验证它在长列表上的表现。
     *
     * @param p
     * @return
     */
    public Stream<A> takeWhileViaFoldRight(Function<A, Boolean> p) {
        return foldRight(Stream::empty, a -> b -> p.apply(a) ?
                cons(() -> a, b) :
                empty());
    }

    /**
     * 9.6.1 折叠流 - 练习9.9 通过foldRight实现takeWhile方法。验证它在长列表上的表现。
     *
     * @return
     */
    public Result<A> headOptionViaFoldRight() {
        return foldRight(Result::empty, a -> ignore -> Result.success(a));
    }

    /**
     * 9.6.1 折叠流 - 练习9.10 通过foldRight实现map。验证这个方法不会对任何流元素求值。
     *
     * @param f
     * @param <B>
     * @return
     */
    public <B> Stream<B> map(Function<A, B> f) {
        return foldRight(Stream::empty, a -> b -> cons(() -> f.apply(a), b));
    }

    /**
     * 9.6.1 折叠流 - 练习9.11 通过foldRight实现filter。验证此方法不会对比所需更多的流元素求值。
     * 9.8 避免null引用和可变字段 - 练习9.19 用foldRight来实现各种方法是一种巧妙的技术。不幸的是，它并不适用于filter。
     * 如果你使用不匹配超过1000个或2000个连续元素的断言来检查该方法，就会导致栈溢出。
     * 用新的Stream类但不用null或可变字段，来编写一个栈安全的filter方法。
     *
     * @param p
     * @return
     */
    public Stream<A> filter(Function<A, Boolean> p) {
        /// foldRight版
//        return foldRight(Stream::empty, a -> b -> p.apply(a) ?
//                cons(() -> a, b) :
//                b.get());
        /// dropWhile版
//        Stream<A> stream = this.dropWhile(x -> !p.apply(x));
//        return stream.isEmpty() ?
//                stream :
//                cons(() -> stream.head()._1, () -> stream.tail().filter(p));
        /// headOption版
        Stream<A> stream = this.dropWhile(x -> !p.apply(x));
        return stream.headOption()._1
                .map(a -> cons(() -> a, () -> stream.tail().filter(p)))
                .getOrElse(empty());
    }

    /**
     * 9.6.1 折叠流 - 练习9.12 通过foldRight实现append。在append方法的参数中应该是非严格的。
     *
     * @param s
     * @return
     */
    public Stream<A> append(Supplier<Stream<A>> s) {
        return foldRight(s, a -> b -> cons(() -> a, b));
    }

    /**
     * 9.6.1 折叠流 - 练习9.13 通过foldRight实现flatMap
     *
     * @param f
     * @param <B>
     * @return
     */
    public <B> Stream<B> flatMap(Function<A, Stream<B>> f) {
        return foldRight(Stream::empty, a -> b -> f.apply(a).append(b));
    }

    /**
     * 9.6.1 折叠流 - 练习9.14 编写一个find方法，以断言（一个从A到Boolean的函数）为参数并返回一个Result<A>。
     * 如果找到了一个匹配断言的元素，则返回结果为Success，否则为Empty。
     *
     * @param p
     * @return
     */
    public Result<A> find(Function<A, Boolean> p) {
        return filter(p).headOption()._1;
    }

    /**
     * 9.7 处理无限流 - 练习9.15 编写一个repeat方法，它以一个对象为参数并返回同一对象的无限流
     * 9.7 处理无限流 - 练习9.16 编写一个iterate方法来泛化from和repeat方法，
     * iterate方法具有两个参数：用于第一个值的seed和计算下一个值的函数。
     *
     * @param a
     * @param <A>
     * @return
     */
    public static <A> Stream<A> repeat(A a) {
//        return cons(() -> a, () -> repeat(a));
        return iterate(a, x -> x);
    }

    /**
     * 9.7 处理无限流 - 练习9.16 编写一个iterate方法来泛化from和repeat方法，
     * iterate方法具有两个参数：用于第一个值的seed和计算下一个值的函数。
     *
     * @param seed
     * @param f
     * @param <A>
     * @return
     */
    public static <A> Stream<A> iterate(A seed, Function<A, A> f) {
        return cons(() -> seed, () -> iterate(f.apply(seed), f));
    }

    public static <A> Stream<A> iterate(Supplier<A> seed, Function<A, A> f) {
        return cons(seed, () -> iterate(f.apply(seed.get()), f));
    }

    /**
     * 9.7 处理无限流 - 练习9.17 编写一个fibs函数，生成斐波那契数列的无限流：0、1、1、2、3、5、8等。
     * 9.7 处理无限流 - 练习9.18 iterate方法可以进一步被泛化。编写一个unfold方法
     *
     * @return
     */
    public static Stream<Integer> fibs() {
        /// iterate版
//        return iterate(new Tuple<>(0, 1), x -> new Tuple<>(x._2, x._1 + x._2)).map(x -> x._1);
        /// unfold版
        return unfold(new Tuple<>(1, 1), x -> Result.success(new Tuple<>(x._1, new Tuple<>(x._2, x._1 + x._2))));
    }

    /**
     * 9.7 处理无限流 - 练习9.18 iterate方法可以进一步被泛化。编写一个unfold方法，
     * 以一个起始状态S类型和一个从S到Result<Tuple<A, S>>的函数为参数并返回A的流。返回Result可以指明应该停止还是应该继续这个流。
     * 使用状态S意味着数据生成的源不必与生成数据的类型相同。要应用这个新方法，可以使用unfold方法来编写新版本的fibs。
     *
     * @param z
     * @param f
     * @param <A>
     * @param <S>
     * @return
     */
    public static <A, S> Stream<A> unfold(S z, Function<S, Result<Tuple<A, S>>> f) {
        return f.apply(z).map(x -> cons(() -> x._1, () -> unfold(x._2, f))).getOrElse(empty());
    }
}

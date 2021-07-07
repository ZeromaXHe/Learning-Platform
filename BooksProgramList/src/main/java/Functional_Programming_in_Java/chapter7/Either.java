package Functional_Programming_in_Java.chapter7;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter3.Supplier;

/**
 * @Author: zhuxi
 * @Time: 2021/7/6 18:24
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Either<E, A> {
    /**
     * 7.2.1 复合Either - 练习7.1 定义一个map方法，接收一个从A到B的函数，并将Either<E, A>转换为Either<E, B>
     *
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> Either<E, B> map(Function<A, B> f);

    /**
     * 7.2.1 复合Either - 练习7.2 定义flatMap方法，给定从A到Either<E, B>的函数，并将Either<E, A>转化为Either<E, B>
     *
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> Either<E, B> flatMap(Function<A, Either<E, B>> f);

    /**
     * 7.2.1 复合Either - 练习7.3 使用以下签名定义方法getOrElse和orElse
     *
     * @param defaultValue
     * @return
     */
    public abstract A getOrElse(Supplier<A> defaultValue);

    private static class Left<E, A> extends Either<E, A> {
        private final E value;

        private Left(E value) {
            this.value = value;
        }

        @Override
        public <B> Either<E, B> map(Function<A, B> f) {
            return new Left<>(value);
        }

        @Override
        public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
            return new Left<>(value);
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public String toString() {
            return String.format("Left(%s)", value);
        }
    }

    private static class Right<E, A> extends Either<E, A> {
        private final A value;

        private Right(A value) {
            this.value = value;
        }

        @Override
        public <B> Either<E, B> map(Function<A, B> f) {
            return new Right<>(f.apply(value));
        }

        @Override
        public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
            return f.apply(value);
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return value;
        }

        @Override
        public String toString() {
            return String.format("Right(%s)", value);
        }
    }

    public static <E, A> Either<E, A> left(E value) {
        return new Left<>(value);
    }

    public static <E, A> Either<E, A> right(A value) {
        return new Right<>(value);
    }

    /**
     * 7.2.1 复合Either - 练习7.3 使用以下签名定义方法getOrElse和orElse
     *
     * @param defaultValue
     * @return
     */
    public Either<E, A> orElse(Supplier<Either<E, A>> defaultValue) {
        return map(x -> this).getOrElse(defaultValue);
    }
}

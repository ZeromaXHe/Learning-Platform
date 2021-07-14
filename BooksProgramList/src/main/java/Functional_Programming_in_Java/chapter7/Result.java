package Functional_Programming_in_Java.chapter7;

import Functional_Programming_in_Java.chapter10.Nothing;
import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter3.Effect;
import Functional_Programming_in_Java.chapter3.Supplier;

import java.io.Serializable;

/**
 * @Author: zhuxi
 * @Time: 2021/7/7 9:35
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Result<T> implements Serializable {
    public abstract Boolean isSuccess();

    public abstract Boolean isFailure();

    public abstract Boolean isEmpty();

    /**
     * 7.3.1 为Result类添加方法 - 练习7.4 为Result类定义map、flatMap、getOrElse和orElse。
     * 对getOrElse而言，你可以定义两个方法：其一接收一个值为参数，另一个接收一个Supplier。
     *
     * @param defaultValue
     * @return
     */
    public abstract T getOrElse(final T defaultValue);

    public abstract T getOrElse(final Supplier<T> defaultValue);

    public abstract <U> Result<U> map(Function<T, U> f);

    public abstract <U> Result<U> flatMap(Function<T, Result<U>> f);

    /**
     * 7.5.2 映射Failure - 练习7.7 定义一个mapFailure方法，该方法以一个String为参数，并以其为错误消息将一个Failure转换为另一个Failure。
     * 如果Result为Empty或Success，这个方法应该什么也不做。
     *
     * @param s
     * @return
     */
    public abstract Result<T> mapFailure(String s);

    public abstract Result<T> mapFailure(String s, Exception e);

    public abstract Result<T> mapFailure(Exception e);

    public abstract Result<T> failIfEmpty(String message);

    /**
     * 7.5.4 应用作用 - 练习7.9 定义一个forEach方法，它以一个Effect为参数，并将其应用于包装值
     *
     * @param ef
     */
    public abstract void forEach(Effect<T> ef);

    /**
     * 7.5.4 应用作用 - 练习7.10 定义forEachOrThrow方法来处理这个用例
     *
     * @param ef
     */
    public abstract void forEachOrThrow(Effect<T> ef);

    /**
     * 7.5.4 应用作用 - 练习7.11 将作用应用于Result时，常见的用例是对Success应用一个作用，而对Failure则以某种方式来处理异常。
     * forEachOrThrow方法可以抛出异常，但有时你只是想记录错误并继续。
     * 定义一个forEachOrException方法而不是定义记录日志的方法，当存在值时应用一个作用并返回一个Result。
     * 如果原始的Result是一个Success，则Result将Empty；如果是一个Failure，则Result将为Empty或Success<RuntimeException>
     *
     * @param ef
     * @return
     */
    public abstract Result<RuntimeException> forEachOrException(Effect<T> ef);

    /**
     * 13.1.3 用于失败情况的更强大的作用 - 练习13.2 在第7章中，你在Result类型中编写了一个forEachOrException方法，它在Empty和Success中的工作方式类似于forEach，
     * 额外还会返回一个Result.Empty，而在Failure类中返回一个Result.Success<Exception>。
     * 编写一个forEachOrFail方法，它将返回带有异常消息的Result<String>而非异常本身。
     *
     * @param e
     * @return
     */
    public abstract Result<String> forEachOrFail(Effect<T> e);

    public abstract Result<Nothing> mapEmpty();

    @SuppressWarnings("rawtypes")
    private static Result empty = new Empty();

    private Result() {
    }

    private static class Empty<T> extends Result<T> {
        public Empty() {
            super();
        }

        @Override
        public Boolean isSuccess() {
            return false;
        }

        @Override
        public Boolean isFailure() {
            return false;
        }

        @Override
        public Boolean isEmpty() {
            return true;
        }

        @Override
        public T getOrElse(T defaultValue) {
            return defaultValue;
        }

        @Override
        public T getOrElse(Supplier<T> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <U> Result<U> map(Function<T, U> f) {
            return empty();
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> f) {
            return empty();
        }

        @Override
        public Result<T> mapFailure(String s) {
            return this;
        }

        @Override
        public Result<T> mapFailure(String s, Exception e) {
            return this;
        }

        @Override
        public Result<T> mapFailure(Exception e) {
            return this;
        }

        @Override
        public Result<T> failIfEmpty(String message) {
            return failure(message);
        }

        @Override
        public void forEach(Effect<T> ef) {
            // Empty. Do Nothing
        }

        @Override
        public void forEachOrThrow(Effect<T> ef) {
            // Empty的实现更成问题。你可以选择什么也不做，认为Empty不是一个错误。
            // 你也可以决定调用forEachOrThrow，也就是说，把数据的缺失转换为一个错误。
            // 这是一个非常艰难的决定。Empty本身不是错误。
            // 如果要使它成为一个错误，你可以使用一个mapFailure方法，所以在Empty中最好让forEachOrThrow实现为一个什么也不做的方法。
        }

        @Override
        public Result<RuntimeException> forEachOrException(Effect<T> ef) {
            return empty();
        }

        @Override
        public Result<String> forEachOrFail(Effect<T> e) {
            return empty();
        }

        @Override
        public Result<Nothing> mapEmpty() {
            return success(Nothing.instance);
        }

        @Override
        public String toString() {
            return "Empty()";
        }
    }

    private static class Failure<T> extends Empty<T> {
        private final RuntimeException exception;

        private Failure(String message) {
            super();
            this.exception = new IllegalStateException(message);
        }

        private Failure(RuntimeException e) {
            super();
            this.exception = e;
        }

        private Failure(Exception e) {
            super();
            this.exception = new IllegalStateException(e.getMessage(), e);
        }

        @Override
        public Boolean isSuccess() {
            return false;
        }

        @Override
        public Boolean isFailure() {
            return true;
        }

        @Override
        public <U> Result<U> map(Function<T, U> f) {
            return failure(exception);
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> f) {
            return failure(exception);
        }

        @Override
        public Result<T> mapFailure(String s) {
            return failure(new IllegalStateException(s, exception));
        }

        @Override
        public Result<T> mapFailure(String s, Exception e) {
            return failure(new IllegalStateException(s, e));
        }

        @Override
        public Result<T> mapFailure(Exception e) {
            return failure(e);
        }

        @Override
        public Result<T> failIfEmpty(String message) {
            return this;
        }

        @Override
        public void forEachOrThrow(Effect<T> ef) {
            throw exception;
        }

        @Override
        public Result<RuntimeException> forEachOrException(Effect<T> ef) {
            return success(exception);
        }

        @Override
        public Result<String> forEachOrFail(Effect<T> e) {
            return success(exception.getMessage());
        }

        @Override
        public Result<Nothing> mapEmpty() {
            return failure(this);
        }

        @Override
        public String toString() {
            return String.format("Failure(%s)", exception.getMessage());
        }
    }

    private static class Success<T> extends Result<T> {
        private final T value;

        private Success(T value) {
            super();
            this.value = value;
        }

        @Override
        public Boolean isSuccess() {
            return true;
        }

        @Override
        public Boolean isFailure() {
            return false;
        }

        @Override
        public Boolean isEmpty() {
            return false;
        }

        @Override
        public T getOrElse(T defaultValue) {
            return value;
        }

        @Override
        public T getOrElse(Supplier<T> defaultValue) {
            return value;
        }

        @Override
        public <U> Result<U> map(Function<T, U> f) {
//            try {
//                return f.apply(successValue());
//            } catch (Exception e) {
//                return failure(e.getMessage(), e);
//            }
            return success(f.apply(value));
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> f) {
//            try {
//                return f.apply(successValue());
//            } catch (Exception e) {
//                return failure(e.getMessage());
//            }
            return f.apply(value);
        }

        @Override
        public Result<T> mapFailure(String s) {
            return this;
        }

        @Override
        public Result<T> mapFailure(String s, Exception e) {
            return this;
        }

        @Override
        public Result<T> mapFailure(Exception e) {
            return this;
        }

        @Override
        public Result<T> failIfEmpty(String message) {
            return this;
        }

        @Override
        public void forEach(Effect<T> ef) {
            ef.apply(value);
        }

        @Override
        public void forEachOrThrow(Effect<T> ef) {
            ef.apply(value);
        }

        @Override
        public Result<RuntimeException> forEachOrException(Effect<T> ef) {
            ef.apply(value);
            return empty();
        }

        @Override
        public Result<String> forEachOrFail(Effect<T> e) {
            e.apply(this.value);
            return empty();
        }

        @Override
        public Result<Nothing> mapEmpty() {
            return failure("Not empty");
        }

        @Override
        public String toString() {
            return String.format("Success(%s)", value.toString());
        }
    }

    public static <T, U> Result<T> failure(Failure<U> failure) {
        return new Failure<>(failure.exception);
    }

    public static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }

    public static <T> Result<T> failure(Exception e) {
        return new Failure<>(e);
    }

    public static <T> Result<T> failure(RuntimeException e) {
        return new Failure<>(e);
    }

    public static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * 7.3.1 为Result类添加方法 - 练习7.4 为Result类定义map、flatMap、getOrElse和orElse。
     *
     * @param defaultValue
     * @return
     */
    public Result<T> orElse(Supplier<Result<T>> defaultValue) {
        return map(x -> this).getOrElse(defaultValue);
    }

    @SuppressWarnings("unchecked")
    public static <T> Result<T> empty() {
        return empty;
    }

    /**
     * 7.5.1 应用断言 - 练习7.5 编写一个filter方法，接收一个条件并返回一个Result<T>，条件由从T到Boolean函数T表示。
     * 视包装的值是否满足条件而定，Result会是一个Success或Failure。
     *
     * @param p
     * @return
     */
    public Result<T> filter(Function<T, Boolean> p) {
        return flatMap(x -> p.apply(x)
                ? this
                : failure("Condition not matched"));
    }

    public Result<T> filter(Function<T, Boolean> p, String message) {
        return flatMap(x -> p.apply(x)
                ? this
                : failure(message));
    }

    /**
     * 7.5.1 应用断言 - 练习7.6 定义一个exists方法，接收从T到Boolean的函数，并在包装的值与条件匹配时返回true，否则返回false。
     *
     * @param p
     * @return
     */
    public boolean exists(Function<T, Boolean> p) {
        return map(p).getOrElse(false);
    }

    /**
     * 7.5.3 增加工厂方法 - 练习7.8 定义这些静态工厂方法
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> Result<T> of(T value) {
        return value != null ?
                success(value) :
                Result.failure("Null value");
    }

    public static <T> Result<T> of(T value, String message) {
        return value != null ?
                success(value) :
                failure(message);
    }

    public static <T> Result<T> of(Function<T, Boolean> predicate, T value) {
        try {
            return predicate.apply(value) ?
                    success(value) :
                    empty();
        } catch (Exception e) {
            String errMessage = String.format("Exception while evaluating predicate: %s", value);
            return Result.failure(new IllegalStateException(errMessage, e));
        }
    }

    public static <T> Result<T> of(Function<T, Boolean> predicate, T value, String message) {
        try {
            return predicate.apply(value) ?
                    Result.success(value) :
                    Result.failure(String.format(message, value));
        } catch (Exception e) {
            String errMessage = String.format("Exception while evaluating predicate: %s", String.format(message, value));
            return Result.failure(new IllegalStateException(errMessage, e));
        }
    }

    /**
     * 7.5.5 Result复合进阶 - 练习7.12 为Result编写lift方法。它是一个在Result类中具有以下签名的静态方法
     *
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> Function<Result<A>, Result<B>> lift(final Function<A, B> f) {
        return x -> {
            try {
                return x.map(f);
            } catch (Exception e) {
                return failure(e);
            }
        };
    }

    /**
     * 7.5.5 Result复合进阶 - 练习7.13 定义lift2用于提升从A到B到C的函数，lift3用于从A到B到C到D的函数
     *
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    public static <A, B, C> Function<Result<A>, Function<Result<B>, Result<C>>> lift2(Function<A, Function<B, C>> f) {
        return a -> b -> a.map(f).flatMap(b::map);
    }

    public static <A, B, C, D> Function<Result<A>, Function<Result<B>,
            Function<Result<C>, Result<D>>>> lift3(Function<A, Function<B, Function<C, D>>> f) {
        return a -> b -> c -> a.map(f).flatMap(b::map).flatMap(c::map);
    }

    /**
     * 7.5.5 Result复合进阶 - 练习7.14 在第6章中，你定义了一个map2方法，接收一个Option<A>，一个Option<B>，
     * 以及一个从A到B到C的函数为参数，并返回一个Option<C>。为Result定义一个map2方法。
     *
     * @param a
     * @param b
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    public static <A, B, C> Result<C> map2(Result<A> a, Result<B> b, Function<A, Function<B, C>> f) {
        return lift2(f).apply(a).apply(b);
    }
}

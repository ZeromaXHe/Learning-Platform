package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter10.Nothing;
import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter3.Supplier;
import Functional_Programming_in_Java.chapter4.TailCall;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 20:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class IOClass<A> {
    protected abstract boolean isReturn();

    protected abstract boolean isSuspend();

    private static IOClass<Nothing> EMPTY = new IOClass.Suspend<>(() -> Nothing.instance);

    public static IOClass<Nothing> empty() {
        return EMPTY;
    }

    public A run() {
        return run(this);
    }

    public A run(IOClass<A> io) {
        return run_(io).eval();
    }

    private TailCall<A> run_(IOClass<A> io) {
        if (io.isReturn()) {
            return TailCall.ret(((Return<A>) io).value);
        } else if (io.isSuspend()) {
            return TailCall.ret(((Suspend<A>) io).resume.get());
        } else {
            Continue<A, A> ct = (Continue<A, A>) io;
            IOClass<A> sub = ct.sub;
            Function<A, IOClass<A>> f = ct.f;
            if (sub.isReturn()) {
                return TailCall.sus(() -> run_(f.apply(((Return<A>) sub).value)));
            } else if (sub.isSuspend()) {
                return TailCall.sus(() -> run_(f.apply(((Suspend<A>) sub).resume.get())));
            } else {
                Continue<A, A> ct2 = (Continue<A, A>) sub;
                IOClass<A> sub2 = ct2.sub;
                Function<A, IOClass<A>> f2 = ct2.f;
                return TailCall.sus(() -> run_(sub2.flatMap(x -> f2.apply(x).flatMap(f))));
            }
        }
    }

    public <B> IOClass<B> map(Function<A, B> f) {
        return flatMap(f.andThen(Return::new));
    }

    @SuppressWarnings("unchecked")
    public <B> IOClass<B> flatMap(Function<A, IOClass<B>> f) {
        return (IOClass<B>) new Continue<>(this, f);
    }

    static <A> IOClass<A> unit(A a) {
        return new IOClass.Suspend<>(() -> a);
    }

    /**
     * Return表示完成的计算，意味着你只需返回结果即可
     *
     * @param <T>
     */
    final static class Return<T> extends IOClass<T> {
        // 这个value将由计算返回
        public final T value;

        protected Return(T value) {
            this.value = value;
        }

        @Override
        protected boolean isReturn() {
            return true;
        }

        @Override
        protected boolean isSuspend() {
            return false;
        }
    }

    /**
     * Suspend表示一个暂停的计算，在恢复当前的计算之前需要应用一些作用
     *
     * @param <T>
     */
    final static class Suspend<T> extends IOClass<T> {
        public final Supplier<T> resume;

        protected Suspend(Supplier<T> resume) {
            this.resume = resume;
        }

        @Override
        protected boolean isReturn() {
            return false;
        }

        @Override
        protected boolean isSuspend() {
            return true;
        }
    }

    /**
     * Continue表示一个状态，程序必须首先应用一个子计算，然后才继续下一个
     *
     * @param <T>
     * @param <U>
     */
    final static class Continue<T, U> extends IOClass<T> {
        // 该IO首先执行，生成一个值
        public final IOClass<T> sub;
        // 通过将此函数应用于返回值来继续计算
        public final Function<T, IOClass<U>> f;

        protected Continue(IOClass<T> sub, Function<T, IOClass<U>> f) {
            this.sub = sub;
            this.f = f;
        }

        @Override
        protected boolean isReturn() {
            return false;
        }

        @Override
        protected boolean isSuspend() {
            return false;
        }
    }
}

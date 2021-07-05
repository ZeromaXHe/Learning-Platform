package Functional_Programming_in_Java.chapter4;

import Functional_Programming_in_Java.chapter3.Supplier;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 17:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class TailCall<T> {
    public abstract TailCall<T> resume();

    public abstract T eval();

    public abstract boolean isSuspend();

    private TailCall() {
    }

    private static class Return<T> extends TailCall<T> {
        private final T t;

        public Return(T t) {
            this.t = t;
        }

        @Override
        public T eval() {
            return t;
        }

        @Override
        public boolean isSuspend() {
            return false;
        }

        @Override
        public TailCall<T> resume() {
            throw new IllegalStateException("Return has no resume");
        }
    }

    private static class Suspend<T> extends TailCall<T> {
        private final Supplier<TailCall<T>> resume;

        private Suspend(Supplier<TailCall<T>> resume) {
            this.resume = resume;
        }

        @Override
        public T eval() {
            TailCall<T> tailRec = this;
            while (tailRec.isSuspend()) {
                tailRec = tailRec.resume();
            }
            return tailRec.eval();
        }

        @Override
        public boolean isSuspend() {
            return true;
        }

        @Override
        public TailCall<T> resume() {
            return resume.get();
        }
    }

    public static <T> Return<T> ret(T t) {
        return new Return<>(t);
    }

    public static <T> Suspend<T> sus(Supplier<TailCall<T>> s) {
        return new Suspend<>(s);
    }
}
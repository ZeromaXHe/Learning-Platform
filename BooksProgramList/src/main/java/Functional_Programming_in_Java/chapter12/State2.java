package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter10.Nothing;
import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter5.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:46
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class State2<S, A> {
    public final Function<S, StateTuple<A, S>> run;

    public State2(Function<S, StateTuple<A, S>> run) {
        this.run = run;
    }

    public static <S, A> State2<S, A> unit(A a) {
        return new State2<>(s -> new StateTuple<>(a, s));
    }

    public static <S> State2<S, S> get() {
        return new State2<>(s -> new StateTuple<>(s, s));
    }

    public static <S> State2<S, Nothing> sequence(Function<S, S> f) {
        return new State2<>(s -> new StateTuple<>(Nothing.instance, f.apply(s)));
    }

    public static <S, A> State2<S, A> sequence(Function<S, S> f, A value) {
        return new State2<>(s -> new StateTuple<>(value, f.apply(s)));
    }

    public static <S, A> State2<S, List<A>> sequence(List<State2<S, A>> fs) {
        return fs.foldRight(State2.unit(List.<A>list()), f -> acc -> f.map2(acc, a -> b -> b.cons(a)));
    }

    public <B> State2<S, B> flatMap(Function<A, State2<S, B>> f) {
        return new State2<>(s -> {
            StateTuple<A, S> temp = run.apply(s);
            return f.apply(temp.value).run.apply(temp.state);
        });
    }

    public static <S> State2<S, Nothing> set(S s) {
        return new State2<>(x -> new StateTuple<>(Nothing.instance, s));
    }

    public <B> State2<S, B> map(Function<A, B> f) {
        return flatMap(a -> State2.unit(f.apply(a)));
    }

    public <B, C> State2<S, C> map2(State2<S, B> sb, Function<A, Function<B, C>> f) {
        return flatMap(a -> sb.map(b -> f.apply(a).apply(b)));
    }

    public A eval(S s) {
        return run.apply(s).value;
    }
}

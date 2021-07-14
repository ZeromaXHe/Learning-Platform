package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter10.Nothing;
import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter5.List;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:18
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class StateMachine<A, S> {
    Function<A, State2<S, Nothing>> function;

    public StateMachine(List<Tuple<Condition<A, S>, Transition<A, S>>> transitions) {
        function = a -> State2.sequence(m ->
                Result.success(new StateTuple<>(a, m)).flatMap((StateTuple<A, S> t) ->
                        transitions.filter((Tuple<Condition<A, S>, Transition<A, S>> x) -> x._1.apply(t))
                                .headOption()
                                .map((Tuple<Condition<A, S>, Transition<A, S>> y) -> y._2.apply(t))).getOrElse(m));
    }

    public static <S, A> State2<S, List<A>> compose(List<State2<S, A>> fs) {
        return fs.foldRight(State2.unit(List.<A>list()), f -> acc -> f.map2(acc, a -> b -> b.cons(a)));
    }
}

package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter5.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:58
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Atm {

    public static StateMachine<Input, Outcome> createMachine() {

        Condition<Input, Outcome> predicate1 = t -> t.value.isDeposit();
        Transition<Input, Outcome> transition1 = t -> new Outcome(t.state.account + t.value.getAmount(), t.state.operations.cons(t.value.getAmount()));

        Condition<Input, Outcome> predicate2 = t -> t.value.isWithdraw() && t.state.account >= t.value.getAmount();
        Transition<Input, Outcome> transition2 = t -> new Outcome(t.state.account - t.value.getAmount(), t.state.operations.cons(- t.value.getAmount()));

        Condition<Input, Outcome> predicate3 = t -> true;
        Transition<Input, Outcome> transition3 = t -> t.state;

        List<Tuple<Condition<Input, Outcome>, Transition<Input, Outcome>>> transitions = List.list(
                new Tuple<>(predicate1, transition1),
                new Tuple<>(predicate2, transition2),
                new Tuple<>(predicate3, transition3));

        return new StateMachine<>(transitions);
    }

}
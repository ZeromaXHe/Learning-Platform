package Functional_Programming_in_Java.chapter12;

import Functional_Programming_in_Java.chapter5.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:56
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Outcome {

    public final Integer account;
    public final List<Integer> operations;

    public Outcome(Integer account, List<Integer> operations) {
        super();
        this.account = account;
        this.operations = operations;
    }

    public String toString() {
        return "(" + account.toString() + "," + operations.toString() + ")";
    }
}
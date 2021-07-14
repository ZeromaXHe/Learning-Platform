package Functional_Programming_in_Java.chapter12;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:55
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Input {

    Type type();
    boolean isDeposit();
    boolean isWithdraw();
    int getAmount();

    enum Type {DEPOSIT,WITHDRAW}
}

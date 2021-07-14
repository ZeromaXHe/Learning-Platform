package Functional_Programming_in_Java.chapter12;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:55
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Withdraw implements Input {

    private final int amount;

    public Withdraw(int amount) {
        super();
        this.amount = amount;
    }

    @Override
    public Type type() {
        return Type.WITHDRAW;
    }

    @Override
    public boolean isDeposit() {
        return false;
    }

    @Override
    public boolean isWithdraw() {
        return true;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }
}

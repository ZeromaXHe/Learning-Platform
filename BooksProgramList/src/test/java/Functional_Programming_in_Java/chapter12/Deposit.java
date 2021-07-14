package Functional_Programming_in_Java.chapter12;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 16:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Deposit implements Input {

    private final int amount;

    public Deposit(int amount) {
        super();
        this.amount = amount;
    }

    @Override
    public Type type() {
        return Type.DEPOSIT;
    }

    @Override
    public boolean isDeposit() {
        return true;
    }

    @Override
    public boolean isWithdraw() {
        return false;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }
}

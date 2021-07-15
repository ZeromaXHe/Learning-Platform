package Java8_in_Action.chapter5;

/**
 * @Author: zhuxi
 * @Time: 2021/7/15 11:49
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}

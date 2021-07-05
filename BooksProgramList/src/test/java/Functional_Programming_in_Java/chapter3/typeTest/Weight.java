package Functional_Programming_in_Java.chapter3.typeTest;

import Functional_Programming_in_Java.chapter2.Function;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 16:41
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Weight {
    public static final Weight ZERO = new Weight(0.0);

    public final double weight;

    private Weight(double weight) {
        this.weight = weight;
    }

    public static Weight weight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        } else {
            return new Weight(weight);
        }
    }

    public static Function<Weight, Function<OrderLine, Weight>> sum = x -> y -> x.add(y.getWeight());

    public Weight add(Weight that) {
        return new Weight(this.weight + that.weight);
    }

    public Weight mult(int count) {
        return new Weight(this.weight * count);
    }

    @Override
    public String toString() {
        return Double.toString(this.weight);
    }
}

package Functional_Programming_in_Java.chapter3.typeTest;

import Functional_Programming_in_Java.chapter2.Function;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 16:40
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Price {
    public static final Price ZERO = new Price(0.0);

    public final double value;

    private Price(double value) {
        this.value = value;
    }

    public static Price price(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        } else {
            return new Price(value);
        }
    }

    public static Function<Price, Function<OrderLine, Price>> sum = x -> y -> x.add(y.getAmount());

    public Price add(Price that) {
        return new Price(this.value + that.value);
    }

    public Price mult(int count) {
        return new Price(this.value * count);
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }
}

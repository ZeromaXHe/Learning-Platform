package com.zerox.javafxLearning.lesson051_064;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @Author: zhuxi
 * @Time: 2021/8/4 17:04
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson063BindingCustomization {
    public static void main(String[] args) {
        SimpleIntegerProperty sip = new SimpleIntegerProperty(10);
        MyIntegerBinding myib = new MyIntegerBinding(5);
        System.out.println(myib.get());

        sip.bind(myib);
        System.out.println("myib = " + myib.get());
        System.out.println("sip = " + sip.get());

        myib.setX(20);
        System.out.println("myib = " + myib.get());
        System.out.println("sip = " + sip.get());
    }
}

class MyIntegerBinding extends IntegerBinding {
    private SimpleIntegerProperty x = new SimpleIntegerProperty(10);

    public MyIntegerBinding(int value) {
        this.bind(x);
        x.set(value);
    }

    @Override
    protected int computeValue() {
        return x.get() * 2;
    }

    public void setX(int x) {
        this.x.set(x);
    }
}

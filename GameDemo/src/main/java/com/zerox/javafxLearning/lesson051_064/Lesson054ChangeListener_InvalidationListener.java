package com.zerox.javafxLearning.lesson051_064;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/28 22:27
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson054ChangeListener_InvalidationListener {
    public static void main(String[] args) {
        SimpleIntegerProperty sip = new SimpleIntegerProperty(1);
        // ChangeListener 立即计算
//        sip.addListener((o, ov, nv) -> System.out.println("更改监听"));
        Change change = new Change();
//        sip.addListener(change);
        WeakChangeListener<Number> weak = new WeakChangeListener<>(change);
        sip.addListener(weak);
        // InvalidationListener 延迟计算
//        sip.addListener(observable -> System.out.println("失效监听"));
        Inva inva = new Inva();
        sip.addListener(inva);
        sip.set(2);
        sip.set(2);
        sip.set(3);
        sip.removeListener(inva);
        sip.set(4);
    }

    static class Inva implements InvalidationListener {
        @Override
        public void invalidated(Observable observable) {
            System.out.println("失效监听");
        }
    }

    static class Change implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            System.out.println("更改监听");
        }
    }

}

package com.zerox.javafxLearning;

import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/14 15:37
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson064FXCollections {
    public static void main(String[] args) {
        ObservableList<String> list1 = FXCollections.observableArrayList();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        list1.add("e");
        FXCollections.rotate(list1, 1);
        list1.forEach(System.out::println);

        System.out.println("------------------------------------");

        SimpleStringProperty s1 = new SimpleStringProperty("a");
        SimpleStringProperty s2 = new SimpleStringProperty("b");
        ObservableList<SimpleStringProperty> list = FXCollections.observableArrayList(param -> {
            System.out.println("call = " + param);
            // 传递的参数供后面监听onChanged中使用
            Observable[] obsArray = new Observable[]{param};
//            Observable[] obsArray = new Observable[]{new SimpleStringProperty()};
            return obsArray;
        });

        list.addListener((ListChangeListener<SimpleStringProperty>) c -> {
            System.out.println("onChanged = " + c);
            while (c.next()) {
                System.out.println(c.wasUpdated());
            }
        });

        list.add(s1);
        s1.set("c");
        list.add(s2);

        System.out.println(list.get(0).get());
        list.forEach(System.out::println);

        System.out.println("------------------------------------");

        SimpleStringProperty s3 = new SimpleStringProperty("a");
        ObservableList<SimpleStringProperty> list2 = FXCollections.observableArrayList(param -> {
            System.out.println("call = " + param);
            Observable[] obsArray = new Observable[]{param};
            return obsArray;
        });

        SimpleListProperty<SimpleStringProperty> slp = new SimpleListProperty<>(list2);

        slp.addListener((ListChangeListener<SimpleStringProperty>) c -> {
            while (c.next()) {
                System.out.println("onChanged = " + c + " wasUpdated = " + c.wasUpdated());
            }
        });

        list2.add(s3);
        s3.set("b");
    }
}

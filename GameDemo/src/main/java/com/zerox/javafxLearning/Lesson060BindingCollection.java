package com.zerox.javafxLearning;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;

import java.util.Comparator;

/**
 * @Author: zhuxi
 * @Time: 2021/8/3 17:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson060BindingCollection {
    public static void main(String[] args) {
        ObservableList<String> obsList1 = FXCollections.observableArrayList();
        SimpleListProperty<String> list1 = new SimpleListProperty<>(obsList1);
        list1.add("A");
        list1.add("B");

        ObservableList<String> obsList2 = FXCollections.observableArrayList();
        SimpleListProperty<String> list2 = new SimpleListProperty<>(obsList2);
        list2.add("C");
        list2.add("D");

        System.out.println("list1 = " + list1.getValue() + " | list2 = " + list2.getValue());
        /// bind和bindBidirectional输出效果一样
//        list1.bind(list2);
//        list1.bindBidirectional(list2);
        /// bindContent 各自维护各自列表, list2修改影响list1
//        list1.bindContent(list2);
        list1.bindContentBidirectional(list2);
        System.out.println("list1 = " + list1.getValue() + " | list2 = " + list2.getValue());

        list1.add("E");
        list2.add("F");
        System.out.println("list1 = " + list1.getValue() + " | list2 = " + list2.getValue());
        System.out.println("obsList1 = " + obsList1 + " | obsList2 = " + obsList2);

        list1.sort(Comparator.reverseOrder());
        System.out.println("list1 = " + list1.getValue() + " | list2 = " + list2.getValue());
        System.out.println("obsList1 = " + obsList1 + " | obsList2 = " + obsList2);

        System.out.println("------------------------------------------");

        ObservableSet<String> obsSet1 = FXCollections.observableSet("A", "B");
        SimpleSetProperty<String> set1 = new SimpleSetProperty<>(obsSet1);
        ObservableSet<String> obsSet2 = FXCollections.observableSet("C", "D");
        SimpleSetProperty<String> set2 = new SimpleSetProperty<>(obsSet2);

        System.out.println("set1 = " + set1.getValue() + " | set2 = " + set2.getValue());

        set1.bind(set2);

        System.out.println("set1 = " + set1.getValue() + " | set2 = " + set2.getValue());

        set1.add("E");
        set2.add("F");
        System.out.println("set1 = " + set1.getValue() + " | set2 = " + set2.getValue());
        System.out.println("obsSet1 = " + obsSet1 + " | obsSet2 = " + obsSet2);

        System.out.println("------------------------------------------");

        ObservableMap<String, String> obsMap1 = FXCollections.observableHashMap();
        SimpleMapProperty<String, String> map1 = new SimpleMapProperty<>(obsMap1);
        map1.put("A", "1");
        map1.put("B", "2");

        ObservableMap<String, String> obsMap2 = FXCollections.observableHashMap();
        SimpleMapProperty<String, String> map2 = new SimpleMapProperty<>(obsMap2);
        map1.put("C", "1");
        map1.put("D", "2");

        System.out.println("map1 = " + map1.getValue() + " | map2 = " + map2.getValue());
//        map1.bind(map2);
//        map1.bindBidirectional(map2);
        map1.bindContent(map2);
        System.out.println("map1 = " + map1.getValue() + " | map2 = " + map2.getValue());

        map1.put("E", "3");
        map2.put("F", "3");
        System.out.println("map1 = " + map1.getValue() + " | map2 = " + map2.getValue());
        System.out.println("obsMap1 = " + obsMap1 + " | obsMap2 = " + obsMap2);
    }
}

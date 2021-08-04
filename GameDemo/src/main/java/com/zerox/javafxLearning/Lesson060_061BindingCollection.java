package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Comparator;

/**
 * @Author: zhuxi
 * @Time: 2021/8/3 17:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson060_061BindingCollection extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static void test() {
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

        System.out.println("-----------------------------------");

        ObservableList<String> obsList = FXCollections.observableArrayList();
        SimpleListProperty<String> list = new SimpleListProperty<>(obsList);
        list.add("A");
        list.add("B");
        list.add("C");

        ObjectBinding<String> objBind = list.valueAt(0);
        System.out.println("objBind.get(): " + objBind.get());
        list.set(0, "F");
        System.out.println("objBind.get(): " + objBind.get());

        System.out.println("-----------------------------------");

        SimpleIntegerProperty index = new SimpleIntegerProperty(2);
        ObjectBinding<String> obj = list.valueAt(index);
        System.out.println("obj.get(): " + obj.get());
        list.set(2, "T");
        System.out.println("obj.get(): " + obj.get());
        index.set(1);
        System.out.println("obj.get(): " + obj.get());

        System.out.println("-----------------------------------");

        ObservableMap<String, String> obsMap = FXCollections.observableHashMap();
        SimpleMapProperty<String, String> map = new SimpleMapProperty<>(obsMap);
        map.put("A", "1");
        map.put("B", "2");

        ObjectBinding<String> objMap = map.valueAt("A");
        System.out.println("objMap.get(): " + objMap.get());
        map.put("A", "R");
        System.out.println("objMap.get(): " + objMap.get());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        test();

        ObservableList<String> obsList = FXCollections.observableArrayList();
        SimpleListProperty<String> list = new SimpleListProperty<>(obsList);
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        AnchorPane an = new AnchorPane();

        HBox hBox = new HBox(5);
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        hBox.getChildren().addAll(t1, t2);

        VBox vBox = new VBox();

        VBox listData = new VBox(10);

        for (int i = 0; i < list.size(); i++) {
            Label label = new Label();
            label.setFont(new Font(30));
//            label.setText(list.get(i));
            ObjectBinding<String> objBind = list.valueAt(i);
            label.textProperty().bind(objBind);
            listData.getChildren().add(label);
        }

        vBox.getChildren().addAll(hBox, listData);

        an.getChildren().addAll(vBox);

        AnchorPane.setTopAnchor(vBox, 100.0);
        AnchorPane.setLeftAnchor(vBox, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        t2.textProperty().addListener((o, ov, nv) -> {
            if ("".equals(t1.getText())) {
                return;
            }
            try {
                int index = Integer.parseInt(t1.getText());
                String value = t2.getText();
                list.set(index, value);
            } catch (NumberFormatException e) {
                System.out.println("异常");
                return;
            }
        });
    }
}

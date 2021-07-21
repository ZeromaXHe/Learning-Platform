package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Comparator;
import java.util.function.Function;

/**
 * @Author: zhuxi
 * @Time: 2021/7/21 13:39
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson036_037ChoiceBox extends Application {
    private Student changeStudent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        ChoiceBox<String> cbStr = new ChoiceBox<>();
        cbStr.getItems().addAll("str1", "str2", "str3");
        cbStr.setValue("str2");
        cbStr.getSelectionModel().select("str3");
        cbStr.getSelectionModel().selectPrevious();

        cbStr.setPrefWidth(100);
        AnchorPane.setTopAnchor(cbStr, 100.0);
        AnchorPane.setLeftAnchor(cbStr, 10.0);

        TextField tf = new TextField();
        Button button = new Button("修改名称");
        AnchorPane.setTopAnchor(tf, 150.0);
        AnchorPane.setLeftAnchor(tf, 10.0);
        AnchorPane.setTopAnchor(button, 150.0);
        AnchorPane.setLeftAnchor(button, 200.0);

        ChoiceBox<Student> cb = new ChoiceBox<>();
        Student s1 = new Student("lil A", 18, 90);
        Student s2 = new Student("lil B", 12, 100);
        Student s3 = new Student("lil C", 40, 50);
        Student s4 = new Student("lil D", 20, 60.5);
        Student s5 = new Student("lil E", 90, 250);
        cb.getItems().addAll(s1, s2, s3, s4, s5);
        cb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                String value = object.getName() + " - " + object.getAge() + " - " + object.getScore();
                System.out.println("toSting invoked on [" + value + "]");
                return value;
            }

            @Override
            public Student fromString(String string) {
                return null;
            }
        });

        cb.setPrefWidth(150);
        AnchorPane.setTopAnchor(cb, 200.0);
        AnchorPane.setLeftAnchor(cb, 10.0);

//        SimpleListProperty<String> slp = new SimpleListProperty<>();
        ObservableList<String> list1 = FXCollections.observableArrayList();
        list1.addAll("数字", "字母");
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("1", "2", "3", "4", "5", "6");
        ObservableList<String> list3 = FXCollections.observableArrayList();
        list3.addAll("A", "B", "C", "D", "E");

        ChoiceBox<String> cbStr2 = new ChoiceBox<>();
        cbStr2.setItems(list1);
        ChoiceBox<String> cbStr3 = new ChoiceBox<>();
        Button bu = new Button("倒序排列");

        cbStr2.setPrefWidth(100);
        AnchorPane.setTopAnchor(cbStr2, 300.0);
        AnchorPane.setLeftAnchor(cbStr2, 10.0);

        cbStr3.setPrefWidth(100);
        AnchorPane.setTopAnchor(cbStr3, 300.0);
        AnchorPane.setLeftAnchor(cbStr3, 200.0);

        AnchorPane.setTopAnchor(bu, 300.0);
        AnchorPane.setLeftAnchor(bu, 400.0);

        an.getChildren().addAll(cbStr, cb, tf, button, cbStr2, cbStr3, bu);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChoiceBox");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

//        cbStr.show();
//        cbStr.hide();
        cbStr.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> System.out.println(nv));

        button.setOnAction(event -> {
            /// 会导致name不变时也调用toStirng
//            String name_value = tf.getText();
//            int i = cb.getItems().indexOf(changeStudent);
//            changeStudent.setName(name_value);
//            cb.getItems().set(i, changeStudent);
            String name_value = tf.getText();
            changeStudent.setName(name_value);
        });

        cb.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            changeStudent = nv;
            System.out.println(changeStudent == null ? "changeStudent is null" : changeStudent.getName());
        });

        cb.getItems().forEach(item -> {
            item.getNameProperty().addListener((o, ov, nv) -> {
                int i = cb.getItems().indexOf(changeStudent);
                cb.getItems().set(i, changeStudent);
                System.out.println("" + ov + "->" + nv);
            });
        });

        cbStr2.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv.equals("数字")) {
                cbStr3.setItems(list2);
            } else if (nv.equals("字母")) {
                cbStr3.setItems(list3);
            }
        });

//        bu.setOnAction(event -> list2.sort(Comparator.comparing(Function.identity(), String::compareTo).reversed()));
        bu.setOnAction(event -> list2.sort(Comparator.comparing(Function.<String>identity()).reversed()));


    }
}

class Student {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleDoubleProperty score = new SimpleDoubleProperty();

    public Student(String name, int age, double score) {
        this.name.setValue(name);
        this.age.setValue(age);
        this.score.setValue(score);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public int getAge() {
        return age.getValue();
    }

    public void setAge(int age) {
        this.age.setValue(age);
    }

    public double getScore() {
        return score.getValue();
    }

    public void setScore(double score) {
        this.score.setValue(score);
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }
}

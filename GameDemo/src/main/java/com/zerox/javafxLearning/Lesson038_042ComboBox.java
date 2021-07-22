package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * @Author: zhuxi
 * @Time: 2021/7/21 17:19
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson038_042ComboBox extends Application {
    private ObservableList<Student> obsAll;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane an = new AnchorPane();

        Student s1 = new Student("lil A", 18, 90);
        Student s2 = new Student("lil B", 12, 100);
        Student s3 = new Student("lil C", 40, 50);
        Student s4 = new Student("lil D", 20, 60.5);
        Student s5 = new Student("lil E", 90, 250);

        Button bu = new Button("button");

        ComboBox<Student> cbb = new ComboBox<>();
        cbb.getItems().addAll(s1, s2, s3, s4, s5);
        cbb.setValue(s1);
        cbb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                if (object == null) {
                    return "null";
                }
                String value = object.getName() + " - " + object.getAge() + " - " + object.getScore();
                System.out.println("toSting invoked on [" + value + "]");
                return value;
            }

            @Override
            public Student fromString(String string) {
                if ("" .equals(string)) {
                    return null;
                }
                System.out.println("fromString(" + string + ")");
                Student s = new Student(string, -1, -1);
                // FIXME: 有bug，会插入两次
                cbb.getItems().add(s);
                return s;
            }
        });

        cbb.setEditable(true);
        cbb.setPromptText("请输入");
        // 占位符在cbb.getItems()为空时显示
        cbb.setPlaceholder(new Button("占位符"));
        cbb.setVisibleRowCount(3);

        ComboBox<Student> cbb2 = new ComboBox<>();
        cbb2.getItems().addAll(s1, s2, s3, s4, s5);
        obsAll = cbb2.getItems();
        cbb2.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                if (object == null) {
                    return "null";
                }
                String value = object.getName() + " - " + object.getAge() + " - " + object.getScore();
                System.out.println("toSting invoked on [" + value + "]");
                return value;
            }

            @Override
            public Student fromString(String string) {
                return null;
            }
        });
        cbb2.setEditable(true);
        TextField tf = cbb2.editorProperty().get();

        ComboBox<String> cbb3 = new ComboBox<>();
        cbb3.getItems().addAll("str1", "str2", "str3");
        cbb3.setCellFactory(param -> {
            ListCell<String> listCell = new My_ListCell<>();
            return listCell;
        });

        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);
        box.setMaxWidth(150);
        box.setMaxHeight(150);
        box.setStyle("-fx-background-color: #FF82AB");
        box.getChildren().addAll(new Button("button1"), new Button("button2"));

        Label l1 = new Label();
        l1.setStyle("-fx-background-color: #ffff55");
        l1.setPrefWidth(200);
        l1.setPrefHeight(200);
        l1.setAlignment(Pos.CENTER);
        l1.setContentDisplay(ContentDisplay.CENTER);
        l1.setGraphic(box);

        MyListCell<String> myListCell = new MyListCell<>();
        myListCell.updateItem("hello", true);

        AnchorPane.setTopAnchor(cbb, 100.0);
        AnchorPane.setTopAnchor(cbb2, 200.0);
        AnchorPane.setTopAnchor(cbb3, 300.0);
        AnchorPane.setTopAnchor(l1, 100.0);
        AnchorPane.setLeftAnchor(l1, 300.0);
        AnchorPane.setTopAnchor(myListCell, 100.0);
        AnchorPane.setLeftAnchor(myListCell, 550.0);
        // jdk8 向AnchorPane添加ListCell不可行，报空指针异常
//        an.getChildren().addAll(cbb, bu, cbb2, cbb3, l1, myListCell);
        an.getChildren().addAll(cbb, bu, cbb2, cbb3, l1);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ComboBox");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        cbb.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> System.out.println(nv));
        cbb.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> System.out.println(nv.intValue()));
        cbb.setOnAction(event -> System.out.println("cbb.setOnAction"));

        tf.textProperty().addListener((o, ov, nv) -> {
            if (nv == null) {
                cbb2.setItems(null);
                cbb2.setPlaceholder(new Label("没有找到"));
                return;
            }
            FilteredList<Student> newList = obsAll.filtered(t -> t.getName().contains(nv));
            if (newList.isEmpty()) {
                cbb2.setItems(null);
                cbb2.setPlaceholder(new Label("没有找到"));
            } else {
                cbb2.setItems(newList);
                cbb2.hide();
                cbb2.show();
            }
        });
    }
}

class MyListCell<T> extends ListCell<T> {
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);
        box.setMaxWidth(150);
        box.setMaxHeight(150);
        box.setStyle("-fx-background-color: #FF82AB");
        box.getChildren().addAll(new Button("button1"), new Button("button2"));

        this.setStyle("-fx-background-color: #ffff55");
        this.setPrefWidth(200);
        this.setPrefHeight(200);
        this.setAlignment(Pos.CENTER);
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setGraphic(box);
    }
}

class My_ListCell<T> extends ListCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            System.out.println("empty = " + empty + " item = " + item);

            HBox box = new HBox(10);
            box.setAlignment(Pos.CENTER);
            box.setStyle("-fx-background-color: #FF82AB");
            box.getChildren().addAll(new Button(item), new Button(item));

            this.setStyle("-fx-background-color: #ffff55");
            this.setAlignment(Pos.CENTER);
            this.setContentDisplay(ContentDisplay.CENTER);
            this.setGraphic(box);
        }
    }
}
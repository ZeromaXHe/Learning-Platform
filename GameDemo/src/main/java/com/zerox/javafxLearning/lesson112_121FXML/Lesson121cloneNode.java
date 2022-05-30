package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 16:32
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson121cloneNode extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        obsList.addAll("A", "B", "C");
        MyListView list1 = new MyListView(obsList);

        MyListView list2 = list1.clone();

        MyButton myButton = new MyButton("button1");
        myButton.setPrefWidth(200);
        myButton.setPrefHeight(200);
        myButton.setStyle("-fx-background-color: #ff55ff");
        myButton.setOnAction(event -> {
            System.out.println("button");
            // 验证浅克隆
            list1.getItems().add("New");
        });

        MyButton node = (MyButton) myButton.clone();
        // 验证浅克隆，两个按钮文字都会变成 "button text"
        node.setText("button text");

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(myButton, node, list1, list2);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    class MyButton extends Button implements Cloneable {
        public MyButton() {
            super();
        }

        public MyButton(String text) {
            super(text);
        }

        public MyButton(String text, Node graphic) {
            super(text, graphic);
        }

        @Override
        protected Node clone() throws CloneNotSupportedException {
            return (Node) super.clone();
        }
    }

    class MyListView extends ListView<String> implements Cloneable {
        public MyListView() {
        }

        public MyListView(ObservableList<String> items) {
            super(items);
        }

        @Override
        protected MyListView clone() throws CloneNotSupportedException {
            return (MyListView) super.clone();
        }
    }
}

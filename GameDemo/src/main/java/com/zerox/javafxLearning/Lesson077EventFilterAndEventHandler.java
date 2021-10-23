package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/23 11:13
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson077EventFilterAndEventHandler extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefWidth(400);
        hBox.setPrefHeight(400);
        hBox.setStyle("-fx-background-color: #ffff55");

        Button bu = new Button("button");

        Label label = new Label("hello world");
        label.setFont(new Font(20));
        label.setStyle("-fx-background-color: #87CEFA");

        hBox.getChildren().addAll(bu, label);

        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #FF7256");
        root.getChildren().addAll(hBox);

        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        // Filter 先于 Handler 执行
        // Filter 事件是从父组件往子组件下传
//        bu.addEventFilter(MouseEvent.ANY, event -> System.out.println(event.getEventType()));
        bu.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
                System.out.println("bu.addEventFilter - " + event.getSource() + " - " + event.getTarget()));
        hBox.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
                System.out.println("hBox.addEventFilter - " + event.getSource() + " - " + event.getTarget()));
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("root.addEventFilter - " + event.getSource() + " - " + event.getTarget());
//            event.consume();
        });
        // Handler 事件是从子组件往父组件上传，setOnMouseClicked() 这些其实就是 Handler
        // bu 天生会阻止事件继续传
        bu.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("bu.addEventHandler - " + event.getSource() + " - " + event.getTarget());
            // 主动让事件继续传, 会重新走一遍 root -> hBox 的 Filter 和 hBox -> root 的 Handler
            Event.fireEvent(hBox, event);
        });
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("label.addEventHandler - " + event.getSource() + " - " + event.getTarget());
            // 阻止事件继续传
            event.consume();
        });
        hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                System.out.println("hBox.addEventHandler - " + event.getSource() + " - " + event.getTarget()));
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                System.out.println("root.addEventHandler - " + event.getSource() + " - " + event.getTarget()));
    }
}

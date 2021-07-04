package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 12:40
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class Lesson013AnchorPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("button1");
        // 在AnchorPane.setXXXAnchor存在的情况下，本设置无效
        b1.setLayoutX(100);
        b1.setLayoutY(100);
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        // 使b3失去管理
        b3.setManaged(false);
        b3.setVisible(true);
        b3.setOpacity(0.5);
        Button b4 = new Button("button4");
        // ap -> g1 -> b4, 这里设置的是b4相对于g1的位置，实际位置需要加上g1相对于ap的位置
        b4.setLayoutX(100);

        Group g1 = new Group();
        g1.getChildren().addAll(b3, b4);

        AnchorPane ap = new AnchorPane();
        ap.setPadding(new Insets(10));

        AnchorPane.setTopAnchor(b1, 100.0);
        AnchorPane.setLeftAnchor(b1, 100.0);
        AnchorPane.setRightAnchor(b1, 100.0);
        AnchorPane.setBottomAnchor(b1, 100.0);

        AnchorPane.setTopAnchor(b2, 100.0);
        AnchorPane.setLeftAnchor(b2, 100.0);

        AnchorPane.setTopAnchor(g1, 200.0);
        AnchorPane.setLeftAnchor(g1, 100.0);

        AnchorPane ap2 = new AnchorPane();
        // 直接设置AnchorPane的宽高
        ap2.setPrefWidth(100);
        ap2.setPrefHeight(100);
        ap2.setStyle("-fx-background-color: #98CD9B");

        ap.getChildren().addAll(ap2, b1, b2, g1);
        ap.setStyle("-fx-background-color: #FF3E96;");
        ap.setOnMouseClicked(event -> System.out.println("mouse click"));

        Group root = new Group();
        // Group 配置以下无效果，因为其不是布局
        root.setStyle("-fx-background-color: #FF3E96;");
        root.setOnMouseClicked(event -> System.out.println("mouse click"));

//        Scene scene = new Scene(root);
        Scene scene = new Scene(ap);
        primaryStage.setScene(scene);
        primaryStage.setTitle("anchor pane test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        // stage.show()后有宽高时，ap.getWidth()等获取宽高才获取得到。放show前面会获取不到
        AnchorPane.setTopAnchor(ap2, 0.0);
        AnchorPane.setLeftAnchor(ap2, 0.0);
        AnchorPane.setRightAnchor(ap2, ap.getWidth() / 2);
        AnchorPane.setBottomAnchor(ap2, ap.getHeight() / 2);
        System.out.println("width:" + ap.getWidth());
        System.out.println("height:" + ap.getHeight());

        // 之后讲了绑定可以用其他方式实现
        ap.heightProperty().addListener((o, ov, nv) ->
                AnchorPane.setBottomAnchor(ap2, nv.doubleValue() / 2));
        ap.widthProperty().addListener((o, ov, nv) ->
                AnchorPane.setRightAnchor(ap2, nv.doubleValue() / 2));
//        primaryStage.heightProperty().addListener((o, ov, nv) ->
//                AnchorPane.setBottomAnchor(ap2, ap.getHeight() / 2));
//        primaryStage.widthProperty().addListener((o, ov, nv) ->
//                AnchorPane.setRightAnchor(ap2, ap.getWidth() / 2));
    }
}

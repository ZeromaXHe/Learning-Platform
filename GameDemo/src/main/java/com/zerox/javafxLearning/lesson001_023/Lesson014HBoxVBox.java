package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 13:19
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class Lesson014HBoxVBox extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        Button b4 = new Button("button4");
        Button b5 = new Button("button5");
        Button b6 = new Button("button6");
        Button b7 = new Button("button7");

        AnchorPane ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #AEEEEE;");

        // VBox完全类似
//        VBox box = new VBox();
        HBox box = new HBox();
        box.setStyle("-fx-background-color: #AE66FF;");
        box.setPrefWidth(400);
        box.setPrefHeight(400);

        box.setPadding(new Insets(10));
        box.setSpacing(10);
        HBox.setMargin(b1, new Insets(10));
        box.setAlignment(Pos.BOTTOM_CENTER);

        // 数量多的情况下不会换行而是缩小每个按钮的宽度
//        box.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7);
        box.getChildren().addAll(b1, b2, b3);
        ap.getChildren().add(box);

        Scene scene = new Scene(ap);
        primaryStage.setScene(scene);
        primaryStage.setTitle("hBox vBox test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

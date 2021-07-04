package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 20:26
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson016BorderPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b = new Button("Button");

        AnchorPane a1 = new AnchorPane();
        a1.setStyle("-fx-background-color: #EE6AA7");
        a1.setPrefWidth(100);
        a1.setPrefHeight(100);

        AnchorPane a2 = new AnchorPane();
        a2.setStyle("-fx-background-color: #98FB98");
        a2.setPrefWidth(100);
        a2.setPrefHeight(100);

        AnchorPane a3 = new AnchorPane();
        a3.setStyle("-fx-background-color: #A0522D");
        a3.setPrefWidth(100);
        a3.setPrefHeight(100);

        AnchorPane a4 = new AnchorPane();
        a4.setStyle("-fx-background-color: #1E90FF");
        a4.setPrefWidth(100);
        a4.setPrefHeight(100);

        AnchorPane a5 = new AnchorPane();
        a5.setStyle("-fx-background-color: #EEEE00");
        // 虽然只有100，但会占掉剩余空间
        a5.setPrefWidth(100);
        a5.setPrefHeight(100);

        BorderPane bor = new BorderPane();
        bor.setStyle("-fx-background-color: #B23AEE");

        bor.setTop(b);
//        bor.setTop(a1);
        bor.setBottom(a2);
        bor.setLeft(a3);
        bor.setRight(a4);
        bor.setCenter(a5);

        bor.setPadding(new Insets(10));
        BorderPane.setMargin(a1, new Insets(10));
        BorderPane.setAlignment(a1, Pos.BOTTOM_LEFT);

        Button bu = (Button) bor.getTop();
        System.out.println(bu.getText());

        Scene scene = new Scene(bor);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BorderPane test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

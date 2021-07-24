package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/7/24 11:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson047SplitPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        Button b4 = new Button("button4");

        SplitPane sp = new SplitPane();
        sp.setPrefWidth(800);
        sp.setPrefHeight(800);
        sp.setOrientation(Orientation.VERTICAL);

        StackPane sp1 = new StackPane();
        sp1.setMinHeight(100);
        sp1.setMaxHeight(300);
        sp1.getChildren().addAll(b1);
        StackPane sp2 = new StackPane();
        sp2.setStyle("-fx-background-color: #ff0000");
        sp2.getChildren().addAll(b2);
        StackPane sp3 = new StackPane();
        sp3.getChildren().addAll(b3);
        StackPane sp4 = new StackPane();
        sp4.getChildren().addAll(b4);

        sp.getItems().addAll(sp1, sp2, sp3, sp4);
        sp.setDividerPosition(0, 0.25);
        sp.setDividerPosition(1, 0.5);
        sp.setDividerPosition(2, 0.75);
        sp.setDividerPosition(3, 1);

        an.getChildren().addAll(sp);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SplitPane");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

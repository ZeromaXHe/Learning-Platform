package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 17:54
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class LessonTranformTemplate extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button b1 = new Button("button1");
        b1.setPrefWidth(200);
        b1.setPrefHeight(200);

        Button b2 = new Button("button2");
        b2.setStyle("-fx-background-color: #FF55FF");
        b2.setPrefWidth(200);
        b2.setPrefHeight(200);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(b1, b2);
        AnchorPane.setTopAnchor(b1, 100.0);
        AnchorPane.setLeftAnchor(b1, 100.0);
        AnchorPane.setTopAnchor(b2, 100.0);
        AnchorPane.setLeftAnchor(b2, 100.0);

        customizeNode(b1, b2, an);

        transform(b1, b2, an);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        output(b1, b2, an);
    }

    protected abstract void customizeNode(Button b1, Button b2, AnchorPane an);

    protected abstract void transform(Button b1, Button b2, AnchorPane an);

    protected abstract void output(Button b1, Button b2, AnchorPane an);
}

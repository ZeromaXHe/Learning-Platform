package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 18:05
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson168Shape_Arc extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Arc a1 = new Arc();
        a1.setRadiusX(100);
        a1.setRadiusY(100);
        a1.setLength(90);
        a1.setStartAngle(30);
        a1.setFill(Color.RED);
        a1.setType(ArcType.ROUND);

        Arc a2 = new Arc();
        a2.setRadiusX(100);
        a2.setRadiusY(100);
        a2.setLength(90);
        a2.setStartAngle(60);
        a2.setFill(Color.RED);
        a2.setType(ArcType.ROUND);

        Arc a3 = new Arc();
        a3.setRadiusX(100);
        a3.setRadiusY(200);
        a3.setLength(90);
        a3.setStartAngle(0);
        a3.setFill(Color.RED);
        a3.setType(ArcType.CHORD);
        a3.setStrokeWidth(3);
        a3.setStroke(Color.BLACK);

        Arc a4 = new Arc();
        a4.setRadiusX(100);
        a4.setRadiusY(200);
        a4.setLength(90);
        a4.setStartAngle(0);
        a4.setFill(Color.RED);
        a4.setType(ArcType.OPEN);
        a4.setStrokeWidth(3);
        a4.setStroke(Color.BLACK);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(a1, a2, a3, a4);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

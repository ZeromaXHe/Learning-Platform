package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 18:18
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson170Shape_QuadCurvAndCubicCurv extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane1 = new AnchorPane();
        pane1.setPrefWidth(200);
        pane1.setPrefHeight(200);
        pane1.setStyle("-fx-background-color: #A9A9A9");

        QuadCurve q1 = new QuadCurve();
        q1.setStartX(0);
        q1.setStartY(100);
        q1.setEndX(200);
        q1.setEndY(100);
        q1.setControlX(100);
        q1.setControlY(0);
        q1.setFill(Color.RED);
        q1.setStrokeWidth(1);
        q1.setStroke(Color.BLUE);
        q1.getStrokeDashArray().addAll(5.0, 5.0);

        pane1.getChildren().addAll(q1);

        AnchorPane pane2 = new AnchorPane();
        pane2.setPrefWidth(200);
        pane2.setPrefHeight(200);
        pane2.setStyle("-fx-background-color: #A9A9A9");

        QuadCurve q2 = new QuadCurve();
        q2.setStartX(0);
        q2.setStartY(100);
        q2.setEndX(200);
        q2.setEndY(100);
        q2.setControlX(50);
        q2.setControlY(150);
        q2.setFill(Color.RED);
        q2.setStrokeWidth(1);
        q2.setStroke(Color.BLUE);
        q2.getStrokeDashArray().addAll(5.0, 5.0);

        pane2.getChildren().addAll(q2);

        AnchorPane pane3 = new AnchorPane();
        pane3.setPrefWidth(200);
        pane3.setPrefHeight(200);
        pane3.setStyle("-fx-background-color: #A9A9A9");

        CubicCurve c1 = new CubicCurve();
        c1.setStartX(0);
        c1.setStartY(100);
        c1.setEndX(200);
        c1.setEndY(100);
        c1.setControlX1(50);
        c1.setControlY1(0);
        c1.setControlX2(150);
        c1.setControlY2(200);

        pane3.getChildren().addAll(c1);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(pane1, pane2, pane3);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 50.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

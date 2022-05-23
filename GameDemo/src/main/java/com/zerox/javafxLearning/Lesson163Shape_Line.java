package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 19:15
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson163Shape_Line extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();

        Line l1 = new Line(0, 0, 100, 100);
        l1.setStroke(Color.valueOf("#CD0000"));
        l1.setStrokeWidth(10);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);

        Line l2 = new Line(0, 0, 100, 0);
        l2.setStroke(Color.valueOf("#B452CD"));
        l2.setStrokeWidth(5);

        Line l3 = new Line(0, 80, 70, 10);
        l3.setStroke(Color.valueOf("#B452CD"));
        l3.setStrokeWidth(5);

        AnchorPane ap = new AnchorPane();

        Line l4 = new Line(0, 0, 100, 100);
        l4.setStroke(Color.valueOf("#B452CD"));
        l4.setStrokeWidth(15);
        l4.setStrokeLineCap(StrokeLineCap.BUTT);

        Line l5 = new Line(100, 100, 300, 100);
        l5.setStroke(Color.valueOf("#B452CD"));
        l5.setStrokeWidth(15);
        l5.setStrokeLineCap(StrokeLineCap.BUTT);

        ap.getChildren().addAll(l4, l5);

        Line l6 = new Line(0, 0, 300, 300);
        l6.setStroke(Color.valueOf("#87CEFA"));
        l6.setStrokeWidth(5);
        l6.setSmooth(true);
        l6.setStrokeLineCap(StrokeLineCap.BUTT);
        l6.getStrokeDashArray().addAll(5.0, 10.0, 10.0, 20.0);
        l6.setStrokeDashOffset(10);

        hBox.getChildren().addAll(l1, l2, l3, ap, l6);

        AnchorPane an = new AnchorPane();
        an.getChildren().add(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

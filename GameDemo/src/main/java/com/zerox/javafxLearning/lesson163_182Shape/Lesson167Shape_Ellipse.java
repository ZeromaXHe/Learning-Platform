package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 18:00
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson167Shape_Ellipse extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Ellipse e = new Ellipse();
        e.setCenterX(100);
        e.setCenterY(400);
        e.setFill(Color.PINK);
        e.setRadiusX(160);
        e.setRadiusY(80);

        Ellipse e1 = new Ellipse();
        e1.setFill(Color.RED);
        e1.setRadiusX(80);
        e1.setRadiusY(150);

        Ellipse e2 = new Ellipse();
        e2.setFill(Color.AQUA);
        e2.setRadiusX(150);
        e2.setRadiusY(80);
        e2.setStrokeWidth(3);
        e2.setStroke(Color.BLACK);
        e2.setSmooth(true);

        Ellipse e3 = new Ellipse();
        e3.setFill(Color.AQUA);
        e3.setRadiusX(150);
        e3.setRadiusY(80);
        e3.setStrokeWidth(3);
        e3.setStroke(Color.BLACK);
        e3.getStrokeDashArray().addAll(10.0, 10.0);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(e1, e2, e3);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox, e);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        System.out.println(e.getLayoutBounds());
        System.out.println(e.getBoundsInLocal());
        System.out.println(e.getBoundsInParent());

        System.out.println(e.toString());
    }
}

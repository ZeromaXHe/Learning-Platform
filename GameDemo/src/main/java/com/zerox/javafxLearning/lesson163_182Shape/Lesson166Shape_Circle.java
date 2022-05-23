package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 15:41
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson166Shape_Circle extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle c = new Circle();
        c.setCenterX(100);
        c.setCenterY(400);
        c.setFill(Color.RED);
        c.setRadius(100);

        Circle c1 = new Circle();
        c1.setFill(Color.RED);
        c1.setRadius(100);

        Circle c2 = new Circle();
        c2.setFill(Color.AQUA);
        c2.setRadius(100);
        c2.setStrokeWidth(3);
        c2.setStroke(Color.BLACK);
        c2.setSmooth(true);

        Circle c3 = new Circle();
        c3.setFill(Color.AQUA);
        c3.setRadius(100);
        c3.setStrokeWidth(3);
        c3.setStroke(Color.BLACK);
        c3.getStrokeDashArray().addAll(10.0, 10.0);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(c1, c2, c3);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox, c);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        System.out.println(c.getLayoutBounds());
        System.out.println(c.getBoundsInLocal());
        System.out.println(c.getBoundsInParent());

        System.out.println(c.toString());
    }
}

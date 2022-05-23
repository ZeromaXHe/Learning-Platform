package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/23 7:39
 * @Description: 第 89 课讲过了 Text
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson173Shape_intersectSubstractUnionClip extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageView iv = new ImageView("pic/picTest.jpeg");
        iv.setFitWidth(800);
        iv.setPreserveRatio(true);
        iv.setX(-300);
        Circle c = new Circle(100);
        c.setCenterX(100);
        c.setCenterY(100);
        iv.setClip(c);

        Circle circle = new Circle(100);
        circle.setFill(Color.RED);
        circle.setCenterX(300);
        circle.setCenterY(100);
        circle.setStroke(Color.GREEN);
        circle.setStrokeWidth(3);
        Rectangle rec = new Rectangle(200, 100);
        rec.setFill(Color.BLUE);
        rec.setX(300);
        rec.setY(50);

        Shape s1 = Shape.intersect(circle, rec);
        s1.setLayoutY(200);
        s1.setFill(Color.BLUEVIOLET);

        Shape s2 = Shape.subtract(circle, rec);
        s2.setLayoutX(300);
        s2.setLayoutY(200);

        Shape s3 = Shape.subtract(rec, circle);
        s3.setLayoutX(200);

        Shape s4 = Shape.union(circle, rec);
        s4.setLayoutX(600);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(iv, circle, rec, s1, s2, s3, s4);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

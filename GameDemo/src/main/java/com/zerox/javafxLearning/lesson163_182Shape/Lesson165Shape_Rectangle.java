package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 19:46
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson165Shape_Rectangle extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rec1 = new Rectangle();
        // hBox 内设置 x、y 无效
        rec1.setX(100);
        rec1.setY(100);
        rec1.setWidth(200);
        rec1.setHeight(100);
        rec1.setFill(Color.BLUE);

        Rectangle rec2 = new Rectangle();
        rec2.setWidth(200);
        rec2.setHeight(100);
        rec2.setFill(Color.BLUE);
        rec2.setArcWidth(30);
        rec2.setArcHeight(10);

        Rectangle rec3 = new Rectangle();
        rec3.setWidth(200);
        rec3.setHeight(100);
        rec3.setFill(Color.BLUE);
        rec3.setArcWidth(30);
        rec3.setArcHeight(10);
        rec3.setStrokeWidth(20);
        rec3.setStroke(Color.RED);
        rec3.setStrokeType(StrokeType.OUTSIDE);

        Rectangle rec4 = new Rectangle();
        rec4.setWidth(200);
        rec4.setHeight(100);
        rec4.setFill(Color.BLUE);
        rec4.setArcWidth(30);
        rec4.setArcHeight(10);
        rec4.setStrokeWidth(10);
        rec4.setStroke(Color.RED);
        rec4.setStrokeType(StrokeType.OUTSIDE);
        rec4.getStrokeDashArray().addAll(10.0, 30.0);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(rec1, rec2, rec3, rec4);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);
        primaryStage.show();

        System.out.println(rec2.getLayoutBounds());
        System.out.println(rec2.getBoundsInLocal());
        System.out.println(rec2.getBoundsInParent());

        System.out.println(rec2.toString());
    }
}

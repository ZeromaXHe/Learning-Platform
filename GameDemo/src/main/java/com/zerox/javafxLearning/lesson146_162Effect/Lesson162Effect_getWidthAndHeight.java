package com.zerox.javafxLearning.lesson146_162Effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:59
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson162Effect_getWidthAndHeight extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rec = new Rectangle();
        rec.setFill(Color.BLUE);
        rec.setWidth(100);
        rec.setHeight(100);

        DropShadow ds = new DropShadow();
        ds.setRadius(20);
        GaussianBlur gb = new GaussianBlur();
        gb.setRadius(3);
        ds.setInput(gb);

        rec.setEffect(ds);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(rec);
        AnchorPane.setTopAnchor(rec, 100.0);
        AnchorPane.setLeftAnchor(rec, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        System.out.println("rec: " + rec.getWidth() + " - " + rec.getHeight());
        System.out.println("layoutBounds: " + rec.getLayoutBounds());
        System.out.println("boundsInLocal: " + rec.getBoundsInLocal());
        System.out.println("boundsInParent: " + rec.getBoundsInParent());
    }
}

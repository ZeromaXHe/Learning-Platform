package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 19:31
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson164Shape_Polyline extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();

        Polyline pl = new Polyline(0, 0, 100, 100, 200, 100);
        pl.setStrokeWidth(5);
        pl.setStroke(Color.valueOf("#B03060"));
        pl.setFill(Color.CYAN);

        Polyline pl2 = new Polyline();
        pl2.getPoints().addAll(0.0, 0.0, 100.0, 100.0, 200.0, 100.0, 0.0, 0.0);
        pl2.setStrokeWidth(5);
        pl2.setStroke(Color.valueOf("#B03060"));
        pl2.setFill(Color.CYAN);
        pl2.setStrokeMiterLimit(0);

        double[] doubles = new double[]{0, 0, 100, 0, 0, 100, 100, 100, 0, 0};
        Polyline pl3 = new Polyline(doubles);
        pl3.setStrokeWidth(5);
        pl3.setStroke(Color.valueOf("#B03060"));
        pl3.setFill(Color.CYAN);

        Polyline pl4 = new Polyline(doubles);
        pl4.setStrokeWidth(5);
        pl4.setStroke(Color.valueOf("#B03060"));
        pl4.setFill(Color.CYAN);
        pl4.setStrokeLineJoin(StrokeLineJoin.ROUND);
        pl4.setStrokeType(StrokeType.OUTSIDE);
        pl4.getStrokeDashArray().addAll(10.0, 20.0);

        pl4.setOnMouseClicked(event -> System.out.println("click pl4"));

        hBox.getChildren().addAll(pl, pl2, pl3, pl4);

        AnchorPane an = new AnchorPane();
        an.getChildren().add(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 50.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

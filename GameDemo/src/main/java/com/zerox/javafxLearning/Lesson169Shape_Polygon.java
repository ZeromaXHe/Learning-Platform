package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 18:13
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson169Shape_Polygon extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        double[] d1 = new double[]{0, 0, 100, 0, 100, 100};
        Polygon p1 = new Polygon(d1);

        Polygon p2 = new Polygon();
        p2.getPoints().addAll(0.0, 0.0, 100.0, 0.0, 100.0, 100.0);

        double[] d3 = new double[]{0, 0, 100, 0, 100, 100, 200, 150, 100, 200};
        Polygon p3 = new Polygon(d3);

        double[] d4 = new double[]{0, 0, 100, 0, 0, 100, 100, 100};
        Polygon p4 = new Polygon(d4);
        p4.setFill(Color.RED);
        p4.setStrokeWidth(3);
        p4.setStroke(Color.BLUE);
        p4.getStrokeDashArray().addAll(10.0, 10.0);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(p1, p2, p3, p4);

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

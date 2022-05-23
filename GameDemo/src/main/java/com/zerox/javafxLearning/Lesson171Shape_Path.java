package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 20:36
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson171Shape_Path extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Path path = new Path();
        MoveTo mt = new MoveTo(0, 0);
        LineTo lt = new LineTo(100, 0);

        QuadCurveTo qc = new QuadCurveTo(0, 50, 100, 100);
        qc.setAbsolute(false);

        HLineTo ht = new HLineTo(100);
        ht.setAbsolute(false);

        CubicCurveTo cc = new CubicCurveTo(50, -50, 150, 50, 200, 0);
        cc.setAbsolute(false);

        ArcTo at = new ArcTo(100, 100, 0, 100, 100, true, true);
        at.setAbsolute(false);

        VLineTo vt = new VLineTo(100);
        vt.setAbsolute(false);

        ClosePath close = new ClosePath();

        path.getElements().addAll(mt, lt, qc, ht, cc, at, vt, close);
        path.setFill(Color.BLUE);
        path.setStroke(Color.RED);
        path.setStrokeWidth(5);
        path.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path.getStrokeDashArray().addAll(10.0, 10.0);

        Path path2 = new Path();

        MoveTo mt2 = new MoveTo(0, 0);
        HLineTo ht2 = new HLineTo(100);
        ht2.setAbsolute(false);
        VLineTo vt2 = new VLineTo(100);
        vt2.setAbsolute(false);
        HLineTo ht2_2 = new HLineTo(-100);
        ht2_2.setAbsolute(false);
        VLineTo vt2_2 = new VLineTo(-100);
        vt2_2.setAbsolute(false);

        MoveTo mt2_2 = new MoveTo(50, 50);

        // PathElement 可以重复使用
        path2.getElements().addAll(mt2, ht2, vt2, ht2_2, vt2_2, mt2_2, ht2, vt2, ht2_2, vt2_2);
        path2.setFill(Color.RED);
        path2.setFillRule(FillRule.EVEN_ODD);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(path, path2);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 50.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

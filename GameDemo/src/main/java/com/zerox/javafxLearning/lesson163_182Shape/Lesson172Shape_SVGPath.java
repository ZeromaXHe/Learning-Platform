package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/23 7:21
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson172Shape_SVGPath extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SVGPath path = new SVGPath();
        // 和 Path 有对应关系
        // m = moveTo, l = lineTo, v = vLineTo, h = hLineTo, z = close
        // 小写用相对坐标，大写用绝对坐标
        path.setContent("m0,0 l100,100 v100 h-100 z");
        path.setStroke(Color.RED);
        path.setStrokeWidth(2);
        path.setFill(Color.BLUE);

        SVGPath path2 = new SVGPath();
        // c = cubicCurveTo
        path2.setContent("m0,0 c50,-50,150,50,200,0 z");
        path2.setStroke(Color.RED);
        path2.setStrokeWidth(2);
        path2.setFill(Color.BLUE);

        SVGPath path3 = new SVGPath();
        // s = 简化的贝塞尔曲线
        path3.setContent("m0,0 s100,50,200,0");
        path3.setStroke(Color.RED);
        path3.setStrokeWidth(2);
        path3.setFill(Color.BLUE);

        SVGPath path4 = new SVGPath();
        // q = quadCurveTo, t = 简化的q
        path4.setContent("m0,0 q100,50,200,0 t200,0");
        path4.setStroke(Color.RED);
        path4.setStrokeWidth(2);
        path4.setFill(Color.BLUE);

        SVGPath path5 = new SVGPath();
        // a = arc
        path5.setContent("m0,0 a100,100,0,1,1,100,100");
        path5.setStroke(Color.RED);
        path5.setStrokeWidth(2);
        path5.setFill(Color.BLUE);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(path, path2, path3, path4, path5);

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

package com.zerox.javafxLearning.Lesson024_050;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/7/24 15:46
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson050WidthHeight extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        Button bu = new Button("button");
        bu.setPrefWidth(100);
        bu.setEffect(new DropShadow());
        bu.setRotate(30);
        /// 默认情况就是这个
//        bu.setPrefWidth(Button.USE_COMPUTED_SIZE);
        /// 最小的大小
//        bu.setPrefWidth(Button.USE_PREF_SIZE);

        // 2D图形不会默认给宽高
        Rectangle rec = new Rectangle();
        rec.setHeight(100);
        rec.setWidth(100);

        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #ffff00");
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefWidth(300);
        hBox.setPrefHeight(300);
        hBox.getChildren().addAll(bu, rec);

        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        System.out.println("bu.isResizable(): " + bu.isResizable());
        System.out.println("rec.isResizable(): " + rec.isResizable());

        System.out.println("bu.getPrefWidth(): " + bu.getPrefWidth());
        System.out.println("bu.getWidth()" + bu.getWidth());

        // 不设置这个setWrapText的话，bu.getContentBias() = null
        // 设置后bu.getContentBias() = HORIZONTAL，有水平偏差
        bu.setWrapText(true);
        System.out.println(bu.getContentBias());

        // 相对于hBox的坐标
        System.out.println("bu.getLayoutX()" + bu.getLayoutX());
        System.out.println("bu.getLayoutY()" + bu.getLayoutY());
        Bounds bounds = bu.getLayoutBounds();
        System.out.println("左x: " + bounds.getMinX());
        System.out.println("上y: " + bounds.getMinY());
        System.out.println("右x: " + bounds.getMaxX());
        System.out.println("下y: " + bounds.getMaxY());
        System.out.println("宽度: " + bounds.getWidth());
        System.out.println("高度: " + bounds.getHeight());

        Point2D point1 = bu.localToParent(bounds.getMinX(), bounds.getMinY());
        System.out.println("父组件bBox中的坐标: [" + point1.getX() + ", " + point1.getY() + "]");
        Point2D point2 = bu.localToScene(bounds.getMinX(), bounds.getMinY());
        System.out.println("在场景scene中的坐标: [" + point2.getX() + ", " + point2.getY() + "]");
        Point2D point3 = bu.localToScreen(bounds.getMinX(), bounds.getMinY());
        System.out.println("在屏幕中的坐标: [" + point3.getX() + ", " + point3.getY() + "]");

        Point2D point4 = bu.parentToLocal(point1.getX(), point1.getY());
        System.out.println("父组件转换为本地的坐标: [" + point4.getX() + ", " + point4.getY() + "]");
        Point2D point5 = bu.sceneToLocal(point2.getX(), point2.getY());
        System.out.println("场景scene转换为本地的坐标: [" + point5.getX() + ", " + point5.getY() + "]");
        Point2D point6 = bu.screenToLocal(point3.getX(), point3.getY());
        System.out.println("屏幕转换为本地的坐标: [" + point6.getX() + ", " + point6.getY() + "]");

        System.out.println(bounds.contains(10, 10));

        // 获取不带任何效果的边界框
        System.out.println(bu.getLayoutBounds());
        // 获取带效果的边界框
        System.out.println(bu.getBoundsInLocal());
        System.out.println(bu.getBoundsInParent());
    }
}

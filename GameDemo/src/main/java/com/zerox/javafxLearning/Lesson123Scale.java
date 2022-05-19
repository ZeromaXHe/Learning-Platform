package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 17:17
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson123Scale extends LessonTranformTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void customizeNode(Button b1, Button b2, AnchorPane an) {
        // 无操作
    }

    @Override
    protected void transform(Button b1, Button b2, AnchorPane an) {
        // 默认以图形中心为 pivot 缩放
        b1.setScaleX(1.2);

        Scale scale = new Scale(0.6, 0.9, 100, 100);
        Scale scale2 = new Scale(0.8, 0.9, 100, 100);
        b2.getTransforms().addAll(scale, scale2);

        try {
            Scale inverse = scale.createInverse();
            System.out.println("inverse: " + inverse.getX() + " - " + inverse.getY() + " - " + inverse.getPivotX() + " - " + inverse.getPivotY());
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
        Point2D transform = scale.transform(200, 200);
        System.out.println("transform: " + transform.getX() + " - " + transform.getY());
    }

    @Override
    protected void output(Button b1, Button b2, AnchorPane an) {
        // prefWidth 和 prefHeight 是不会变的，变的是 layoutBounds
        System.out.println("b2.prefWidth: " + b2.getPrefWidth() + ", b2.prefHeight: " + b2.getPrefHeight());
        Bounds bounds = b2.localToParent(b2.getLayoutBounds());
        System.out.println("bounds.width: " + bounds.getWidth() + ", bounds.height: " + bounds.getHeight());
        Bounds boundsInParent = b2.getBoundsInParent();
        System.out.println("boundsInParent.width: " + boundsInParent.getWidth() + ", boundsInParent.height: " + boundsInParent.getHeight());
    }
}

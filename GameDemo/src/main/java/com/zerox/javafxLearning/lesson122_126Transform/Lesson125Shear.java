package com.zerox.javafxLearning.lesson122_126Transform;

import com.zerox.javafxLearning.LessonTranformTemplate;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 17:53
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson125Shear extends LessonTranformTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void customizeNode(Button b1, Button b2, AnchorPane an) {
        b1.setPrefHeight(100);

        b2.setPrefHeight(100);
        // 方便观察
        b2.setOpacity(0.5);

        // 作为参照物
        Button b3 = new Button("button3");
        b3.setPrefWidth(200);
        b3.setPrefHeight(100);
        an.getChildren().add(b3);

        AnchorPane.setTopAnchor(b3, 300.0);
        AnchorPane.setLeftAnchor(b3, 350.0);
    }

    @Override
    protected void transform(Button b1, Button b2, AnchorPane an) {
        Shear shear = new Shear(0.5, 0.5, 0, 0);
        b2.getTransforms().add(shear);

        Point2D transform = shear.transform(200, 100);
        System.out.println("transform: " + transform.getX() + " - " + transform.getY());
    }

    @Override
    protected void output(Button b1, Button b2, AnchorPane an) {
        System.out.println("LocalToParentTransform: " + b2.getLocalToParentTransform().getTx() + " - " + b2.getLocalToParentTransform().getTy());
        // 其实是包裹整个图像的外边框
        Bounds boundsInParent = b2.getBoundsInParent();
        System.out.println("boundsInParent-minXy: " + boundsInParent.getMinX() + " - " + boundsInParent.getMinY());
        System.out.println("boundsInParent-maxXy: " + boundsInParent.getMaxX() + " - " + boundsInParent.getMaxY());
        System.out.println("boundsInParent-wh: " + boundsInParent.getWidth() + " - " + boundsInParent.getHeight());
    }
}

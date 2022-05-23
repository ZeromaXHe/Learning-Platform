package com.zerox.javafxLearning.lesson122_126Transform;

import com.zerox.javafxLearning.LessonTranformTemplate;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 17:40
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson124Rotate extends LessonTranformTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void customizeNode(Button b1, Button b2, AnchorPane an) {
        // 无操作
    }

    @Override
    protected void transform(Button b1, Button b2, AnchorPane an) {
        Rotate rotate = new Rotate(45, 0, 0);
        b2.getTransforms().addAll(rotate);

        // 子组件会叠加父组件的 Transform
        an.setRotate(45);
    }

    @Override
    protected void output(Button b1, Button b2, AnchorPane an) {
        System.out.println("LocalToParentTransform: " + b2.getLocalToParentTransform().getTx() + " - " + b2.getLocalToParentTransform().getTy());
        // 其实是包裹整个图像的外边框
        Bounds boundsInParent = b2.getBoundsInParent();
        System.out.println("boundsInParent-xy: " + boundsInParent.getMinX() + " - " + boundsInParent.getMinY());
        System.out.println("boundsInParent-wh: " + boundsInParent.getWidth() + " - " + boundsInParent.getHeight());
    }
}

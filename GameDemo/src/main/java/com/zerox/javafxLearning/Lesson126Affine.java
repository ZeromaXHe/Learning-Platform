package com.zerox.javafxLearning;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 18:27
 * @Description: 仿射变换
 * @ModifiedBy: zhuxi
 */
public class Lesson126Affine extends LessonTranformTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void customizeNode(Button b1, Button b2, AnchorPane an) {
        b1.setOpacity(0.5);

        b2.setOpacity(0.5);
        b2.setStyle("");
        AnchorPane.setTopAnchor(b2, 100.0);
        AnchorPane.setLeftAnchor(b2, 300.0);

        // 作为参照物
        Button b3 = new Button("button3");
        b3.setPrefWidth(200);
        b3.setPrefHeight(200);
        an.getChildren().add(b3);

        Button b4 = new Button("button4");
        b4.setPrefWidth(200);
        b4.setPrefHeight(200);
        an.getChildren().add(b4);

        AnchorPane.setTopAnchor(b3, 100.0);
        AnchorPane.setLeftAnchor(b3, 100.0);
        AnchorPane.setTopAnchor(b4, 100.0);
        AnchorPane.setLeftAnchor(b4, 300.0);
    }

    @Override
    protected void transform(Button b1, Button b2, AnchorPane an) {
        // 组合成反射效果
        Translate translate = new Translate(0, -200);
        Scale scale = new Scale(1, -1, 100, 100);
        b1.getTransforms().addAll(scale, translate);

        // 直接使用仿射变换的反射效果
        Affine affine = new Affine(1, 0, 0, 0, -1, 400);
        b2.getTransforms().addAll(affine);
    }

    @Override
    protected void output(Button b1, Button b2, AnchorPane an) {

    }
}

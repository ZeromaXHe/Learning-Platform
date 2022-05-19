package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 16:45
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson122Translate extends LessonTranformTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");

        AnchorPane an = new AnchorPane();
        an.setPrefWidth(600);
        an.setPrefHeight(600);
        an.setStyle("-fx-background-color: #FFB5C5");

        an.getChildren().addAll(b1, b2);

        AnchorPane root = new AnchorPane();
        root.getChildren().add(an);
        AnchorPane.setTopAnchor(an, 100.0);
        AnchorPane.setLeftAnchor(an, 100.0);

        transform(b1, b2, an);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(1000);
        primaryStage.show();

        output(b1, b2, root);
    }

    @Override
    protected void customizeNode(Button b1, Button b2, AnchorPane an) {
        b1.setPrefWidth(100);
        b1.setPrefHeight(100);

        b2.setPrefWidth(100);
        b2.setPrefHeight(100);
    }

    @Override
    protected void transform(Button b1, Button b2, AnchorPane an) {
        b1.setLayoutX(100);
        b1.setLayoutY(100);
        b1.setLayoutX(100);
        b1.setLayoutY(100);

        b1.setTranslateX(-150);

        Translate tl1 = new Translate(100, 100);
        Translate tl2 = Transform.translate(100, 100);
        Translate clone = tl1.clone();
        System.out.println("clone: " + clone.getTx() + " - " + clone.getTy());
        Translate inverse = tl1.createInverse();
        System.out.println("inverse: " + inverse.getTx() + " - " + inverse.getTy());
        Point2D p = tl1.transform(150, 150);
        System.out.println("tl1.transform: " + p.getX() + " - " + p.getY());
        Point2D p2 = tl1.deltaTransform(150, 150);
        System.out.println("tl1.deltaTransform: " + p2.getX() + " - " + p2.getY());
        Point2D p3 = tl1.inverseTransform(150, 150);
        System.out.println("tl1.inverseTransform: " + p3.getX() + " - " + p3.getY());
        Point2D p4 = tl1.inverseDeltaTransform(150, 150);
        System.out.println("tl1.inverseDeltaTransform: " + p4.getX() + " - " + p4.getY());
        b2.getTransforms().addAll(tl1, tl2);
    }

    @Override
    protected void output(Button b1, Button b2, AnchorPane an) {
        // 坐标相关可以参考 050 课
        System.out.println("layout:" + b2.getLayoutX() + " - " + b2.getLayoutY());
        System.out.println("localToParentTransform:" + b2.getLocalToParentTransform().getTx() + " - " + b2.getLocalToParentTransform().getTy());
        System.out.println("localToSceneTransform:" + b2.getLocalToSceneTransform().getTx() + " - " + b2.getLocalToSceneTransform().getTy());
        Bounds bd = b2.getLayoutBounds();
        Bounds bd2 = b2.localToParent(bd);
        System.out.println("localToParentBounds:" + bd2.getMinX() + " - " + bd2.getMinY());
        Bounds bd3 = b2.localToScene(bd);
        System.out.println("localToSceneBounds:" + bd3.getMinX() + " - " + bd3.getMinY());
    }
}

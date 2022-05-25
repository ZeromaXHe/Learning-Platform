package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/25 23:49
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson180SubScene extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(200);
        hBox.setAlignment(Pos.CENTER);

        Box box = new Box(150, 150, 150);

        Cylinder cylinder = new Cylinder(50, 200, 64);

        Point3D p = new Point3D(1, 0, 0);
        cylinder.setRotationAxis(p);
        cylinder.setRotate(60);

        Sphere sphere = new Sphere(100, 64);

        hBox.getChildren().addAll(box, cylinder, sphere);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 300.0);

        SubScene subScene = new SubScene(an, 1000, 800, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(new PerspectiveCamera());
        HBox h = new HBox(100);
        Button button = new Button("button");
        TextField textField = new TextField("javaFX 3D");
        h.getChildren().addAll(button, textField, subScene);
        Scene scene = new Scene(h);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1500);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

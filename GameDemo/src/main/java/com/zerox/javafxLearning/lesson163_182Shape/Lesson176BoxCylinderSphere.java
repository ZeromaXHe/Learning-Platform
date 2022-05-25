package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/25 22:38
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson176BoxCylinderSphere extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(50);
        HBox hBox2 = new HBox(50);
        HBox hBox3 = new HBox(50);

        ObservableList<Node> list = hBox.getChildren();
        ObservableList<Node> list2 = hBox2.getChildren();
        ObservableList<Node> list3 = hBox3.getChildren();
        for (int i = 0; i < 5; i++) {
            Box box = new Box(100, 100, 200);
            list.add(box);
            Cylinder cylinder = new Cylinder(100, 200);
            list2.add(cylinder);
            Sphere sphere = new Sphere(100);
            list3.add(sphere);
        }

        Box box1 = (Box) list.get(0);
        box1.setTranslateZ(100);
        box1.setRotationAxis(new Point3D(1, 0, 0));
        box1.setRotate(30);

        Box box2 = (Box) list.get(1);
        box2.setRotationAxis(new Point3D(0, 1, 0));
        box2.setRotate(30);
        // depthBuffer == false 时才点得了
        box2.setOnMouseClicked(event -> System.out.println("box2 被点击"));

        Box box3 = (Box) list.get(2);
        box3.setRotate(30);
        box3.setDrawMode(DrawMode.LINE);
        box3.setCullFace(CullFace.NONE);

        Cylinder cylinder1 = (Cylinder) list2.get(0);
        cylinder1.setTranslateZ(100);
        cylinder1.setRotationAxis(new Point3D(1, 0, 0));
        cylinder1.setRotate(30);

        Cylinder cylinder2 = (Cylinder) list2.get(1);
        cylinder2.setRotationAxis(new Point3D(0, 1, 0));
        cylinder2.setRotate(30);
        // depthBuffer == false 时才点得了
        cylinder2.setOnMouseClicked(event -> System.out.println("cylinder2 被点击"));

        Cylinder cylinder3 = (Cylinder) list2.get(2);
        cylinder3.setRotate(30);
        cylinder3.setDrawMode(DrawMode.LINE);
        cylinder3.setCullFace(CullFace.NONE);

        Sphere sphere3 = (Sphere) list3.get(2);
        sphere3.setDrawMode(DrawMode.LINE);


        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox, hBox2, hBox3);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);
        AnchorPane.setTopAnchor(hBox2, 300.0);
        AnchorPane.setLeftAnchor(hBox2, 100.0);
        AnchorPane.setTopAnchor(hBox3, 500.0);
        AnchorPane.setLeftAnchor(hBox3, 100.0);

        Scene scene = new Scene(an, 1500, 800, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.show();
    }
}

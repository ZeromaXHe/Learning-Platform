package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/25 23:01
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson177Material extends Application {
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

        hBox.getChildren().forEach(node -> {
            Shape3D shape = (Shape3D) node;
            PhongMaterial pm = new PhongMaterial();
//            pm.setDiffuseColor(Color.DARKSEAGREEN);
            pm.setSpecularColor(Color.WHITE);
            pm.setSpecularPower(32);
            pm.setBumpMap(new Image("pic/brickWallNormalMap.png"));
            pm.setDiffuseMap(new Image("pic/brickWallAlbedoMap.png"));
//            pm.setSelfIlluminationMap(new Image("pic/brickWallHeightMap.png"));

            shape.setMaterial(pm);
        });

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 300.0);

        Scene scene = new Scene(an, 1500, 800, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.show();
    }
}

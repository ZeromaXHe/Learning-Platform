package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/25 23:29
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson178Camera extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(300);

        Box box1 = new Box(150, 150, 150);
        Box box2 = new Box(150, 150, 150);
        Box box3 = new Box(150, 150, 150);

        hBox.getChildren().addAll(box1, box2, box3);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 300.0);

        // 构造方法传 true 的话，为默认在 0，0 的固定相机
        PerspectiveCamera camera = new PerspectiveCamera();

        Scene scene = new Scene(an, 1500, 800, true, SceneAntialiasing.BALANCED);
        scene.setCamera(camera);

        camera.setRotationAxis(Rotate.Y_AXIS);
        camera.setRotate(60);

        camera.setFieldOfView(30);
        camera.setNearClip(1.2);
        camera.setFarClip(1.6);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1500);
        primaryStage.setHeight(800);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}

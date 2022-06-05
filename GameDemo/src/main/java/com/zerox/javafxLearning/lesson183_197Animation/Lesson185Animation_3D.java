package com.zerox.javafxLearning.lesson183_197Animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 7:39
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson185Animation_3D extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button bu = new Button("播放");

        AnchorPane subAp = new AnchorPane();
        subAp.setPrefWidth(900);
        subAp.setPrefHeight(900);

        Box box = new Box(100, 100, 100);
        subAp.getChildren().add(box);
        AnchorPane.setTopAnchor(box, 200.0);
        AnchorPane.setLeftAnchor(box, 200.0);

        SubScene subScene = new SubScene(subAp, 900, 900, true, SceneAntialiasing.BALANCED);
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setNearClip(0);
        camera.setFarClip(100);
        subScene.setCamera(camera);

        Rotate rotate = new Rotate(0, -50, -50, -50);
        rotate.setAxis(new Point3D(1, 0, 0));
        Scale scale = new Scale(1, 1, 1, 0, 0, 0);
        box.getTransforms().addAll(rotate, scale);

        KeyValue kv1 = new KeyValue(rotate.angleProperty(), 0);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0), kv1);
        KeyValue kv2 = new KeyValue(rotate.angleProperty(), 360);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(2), kv2);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(kf1, kf2);
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(bu, subScene);
        AnchorPane.setTopAnchor(subScene, 200.0);
        AnchorPane.setLeftAnchor(subScene, 300.0);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1500);
        primaryStage.setHeight(900);
        primaryStage.show();

        bu.setOnAction(event -> timeline.play());
    }
}

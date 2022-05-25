package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.DepthTest;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/25 22:26
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson175threeDimensions extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(Platform.isSupported(ConditionalFeature.SCENE3D));

        Button b1 = new Button("button1");
        b1.setTranslateZ(0);
        b1.setDepthTest(DepthTest.ENABLE);

        Button b2 = new Button("button2");
//        b2.setTranslateX(100);
        b2.setTranslateZ(300);
        b2.setDepthTest(DepthTest.ENABLE);

        Button b3 = new Button("button3");
//        b3.setTranslateX(200);
        b3.setTranslateZ(600);
        b3.setDepthTest(DepthTest.ENABLE);

        AnchorPane an = new AnchorPane();
//        an.setDepthTest(DepthTest.DISABLE);
        an.setStyle("-fx-background-color: #ffffff00");
        an.getChildren().addAll(b1, b2, b3);

        Scene scene = new Scene(an, 1500, 800, true);

        scene.setCamera(new PerspectiveCamera());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.show();
    }
}

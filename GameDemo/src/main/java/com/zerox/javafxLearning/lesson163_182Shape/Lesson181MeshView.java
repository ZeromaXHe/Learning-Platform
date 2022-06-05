package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 7:21
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson181MeshView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(200);
        hBox.setAlignment(Pos.CENTER);

        float[] points = {
                0, 0, 0,
                100, 0, 0,
                100, 100, 0,
                0, 100, 0,

                100, 0, 100,
                100, 100, 100,

                0, 0, 100
        };
        float[] texCoords = {
                0, 0,
                0.5f, 0,
                0.5f, 0.5f,
                0, 0.5f,

                1, 0,
                1, 0.5f,

                0, -0.5f
        };
        int[] faces = {
                0, 0, 3, 3, 1, 1,
                0, 0, 1, 1, 3, 3,
                1, 1, 3, 3, 2, 2,
                1, 1, 2, 2, 3, 3,

                1, 1, 5, 5, 4, 4,
                1, 1, 4, 4, 5, 5,
                2, 2, 5, 5, 1, 1,
                2, 2, 1, 1, 5, 5,

                6, 6, 1, 1, 4, 4,
                6, 6, 4, 4, 1, 1,
                6, 6, 0, 0, 1, 1,
                6, 6, 1, 1, 0, 1
        };

        TriangleMesh tm = new TriangleMesh();
        tm.getPoints().addAll(points);
        tm.getTexCoords().addAll(texCoords);
        tm.getFaces().addAll(faces);

        MeshView mv = new MeshView(tm);
        mv.setRotationAxis(new Point3D(1, 1, 0));
        mv.setRotate(-60);

        hBox.getChildren().addAll(mv);

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

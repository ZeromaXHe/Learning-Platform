package com.zerox.javafxLearning.lesson183_197Animation;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 15:43
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson194AnimationTimer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button bu = new Button("播放");

        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(1));
        tt.setNode(bu);
        tt.setFromX(0);
        tt.setToX(600);

        AnimationTimer at = new AnimationTimer() {
            int i = 0;

            @Override
            public void handle(long now) {
                // now 是纳秒单位
                Duration duration = new Duration(now / 1_000_000);
                System.out.println(now / 1_000_000_000);
                System.out.println(duration.toSeconds());
                System.out.println(Thread.currentThread().getName());
                System.out.println("i = " + i++);

                bu.setRotate(bu.getRotate() + 6);
            }
        };

        tt.setOnFinished(event -> at.stop());

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(bu);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu.setOnAction(event -> {
            at.start();
            tt.play();
        });
    }
}

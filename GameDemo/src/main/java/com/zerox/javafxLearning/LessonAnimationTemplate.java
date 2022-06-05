package com.zerox.javafxLearning;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 13:53
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public abstract class LessonAnimationTemplate extends Application {

    protected Button bu;
    protected Rectangle rec;
    protected AnchorPane an;

    @Override
    public void start(Stage primaryStage) throws Exception {
        bu = new Button("播放");
        rec = new Rectangle(100, 100, Color.RED);
        an = new AnchorPane();

        Transition transition = getTransition();

        an.getChildren().addAll(bu, rec);
        AnchorPane.setTopAnchor(rec, 200.0);
        AnchorPane.setLeftAnchor(rec, 200.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu.setOnAction(event -> transition.play());
    }

    protected abstract Transition getTransition();
}

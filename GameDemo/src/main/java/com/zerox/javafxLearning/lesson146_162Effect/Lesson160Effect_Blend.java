package com.zerox.javafxLearning.lesson146_162Effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:40
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson160Effect_Blend extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox1 = new HBox(30);
        Button b1 = new Button("Button");
        b1.setPrefWidth(200);
        b1.setPrefHeight(200);
        ImageView iv1 = new ImageView("pic/picTest.jpeg");
        iv1.setFitWidth(800);
        iv1.setPreserveRatio(true);
        hBox1.getChildren().addAll(b1,iv1);

        HBox hBox2 = new HBox(30);
        Button b2 = new Button("Button");
        b2.setPrefWidth(200);
        b2.setPrefHeight(200);
        ImageView iv2 = new ImageView("pic/picTest.jpeg");
        iv2.setFitWidth(800);
        iv2.setPreserveRatio(true);
        hBox2.getChildren().addAll(b2,iv2);

        hBox2.setEffect(getEffect());

        StackPane sp = new StackPane();
        sp.getChildren().addAll(hBox1, hBox2);

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(sp);
        AnchorPane.setLeftAnchor(sp, 100.0);
        AnchorPane.setTopAnchor(sp, 100.0);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1500);
        primaryStage.setHeight(900);
        primaryStage.show();
    }

    public Effect getEffect() {
        Blend blend = new Blend();
        blend.setMode(BlendMode.COLOR_BURN);
        blend.setOpacity(0.5);
        return blend;
    }
}

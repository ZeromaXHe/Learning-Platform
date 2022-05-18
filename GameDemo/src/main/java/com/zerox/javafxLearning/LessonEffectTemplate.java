package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:17
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public abstract class LessonEffectTemplate extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(30);
        hBox.setAlignment(Pos.CENTER);

        Button bu = new Button("Button");

        Text text = new Text("this is Text 你好");
        text.setFont(new Font(50));
        text.setFill(Paint.valueOf("#87CEEB"));

        Rectangle rec = new Rectangle(100, 100, Paint.valueOf("#8470FF"));

        Circle circle = new Circle(50, Paint.valueOf("#FF6984"));

        ImageView iv = new ImageView("pic/picTest.jpeg");
        iv.setPreserveRatio(true);
        iv.setFitWidth(400);

        hBox.getChildren().addAll(bu, text, rec, circle, iv);

        hBox.getChildren().forEach(node -> node.setEffect(getEffect()));

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 50.0);
        AnchorPane.setLeftAnchor(hBox, 10.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(400);
        primaryStage.show();
    }

    public abstract Effect getEffect();
}

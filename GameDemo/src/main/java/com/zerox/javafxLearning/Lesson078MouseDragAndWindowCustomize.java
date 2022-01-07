package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Author: zhuxi
 * @Time: 2022/1/7 10:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson078MouseDragAndWindowCustomize extends Application {
    private double a;
    private double b;

    private double c;
    private double d;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button bu = new Button("button");

        AnchorPane an = new AnchorPane();

        Background bg = new Background(new BackgroundFill(Paint.valueOf("#999999"), new CornerRadii(30), new Insets(0)));
        an.setBackground(bg);

        an.setBorder(new Border(new BorderStroke(Color.valueOf("#333333"), BorderStrokeStyle.DASHED,
                new CornerRadii(30), new BorderWidths(2), new Insets(0))));

        an.getChildren().add(bu);

        AnchorPane.setTopAnchor(bu, 100.0);
        AnchorPane.setLeftAnchor(bu, 100.0);

        Scene scene = new Scene(an);

        // 让圆角外围透明
        scene.setFill(Paint.valueOf("#ffffff00"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu.setOnMousePressed(event -> {
            a = event.getX();
            b = event.getY();
        });

        bu.setOnMouseDragged(event -> {
            AnchorPane.setTopAnchor(bu, event.getSceneY() - b);
            AnchorPane.setLeftAnchor(bu, event.getSceneX() - a);
        });

        scene.setOnMousePressed(event -> {
            c = event.getScreenX() - primaryStage.getX();
            d = event.getScreenY() - primaryStage.getY();
        });

        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - c);
            primaryStage.setY(event.getScreenY() - d);
        });
    }
}

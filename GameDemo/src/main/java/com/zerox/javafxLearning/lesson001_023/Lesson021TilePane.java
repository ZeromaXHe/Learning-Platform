package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 22:50
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson021TilePane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("button1");
        b1.setPrefHeight(200);
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        Button b4 = new Button("button4");
        Button b5 = new Button("button5");
        Button b6 = new Button("button6");
        Button b7 = new Button("button7");
        Button b8 = new Button("button8");

        TilePane tile = new TilePane();
        tile.setStyle("-fx-background-color: #EE6AA7");

        tile.setHgap(10);
        tile.setVgap(10);
        tile.setOrientation(Orientation.VERTICAL);
        tile.setPadding(new Insets(10));
        tile.setAlignment(Pos.TOP_CENTER);
        TilePane.setAlignment(b2, Pos.BOTTOM_RIGHT);
        TilePane.setMargin(b1, new Insets(50));

        tile.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8);

        Scene scene = new Scene(tile);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TilePane test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

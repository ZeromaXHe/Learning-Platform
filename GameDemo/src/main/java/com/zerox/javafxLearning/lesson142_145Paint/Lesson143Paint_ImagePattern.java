package com.zerox.javafxLearning.lesson142_145Paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 16:44
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson143Paint_ImagePattern extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("pic/funny.png");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i < 5; i++) {
            Rectangle rec = new Rectangle(300, 300);
            rec.setFill(Paint.valueOf("#EDEDED"));
            grid.add(rec, i, 0);
        }

        HBox hBox = new HBox(10);
        hBox.getChildren().add(new Circle(150));
        hBox.getChildren().add(new Polygon(140, 0, 0, 300, 300, 300));

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(grid, hBox);
        AnchorPane.setTopAnchor(hBox, 400.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);
        primaryStage.show();

        Rectangle rec1 = (Rectangle) grid.getChildren().get(0);
        rec1.setFill(new ImagePattern(image));

        Rectangle rec2 = (Rectangle) grid.getChildren().get(1);
        rec2.setFill(new ImagePattern(image, 0, 0, 0.5, 0.5, true));

        Rectangle rec3 = (Rectangle) grid.getChildren().get(2);
        rec3.setFill(new ImagePattern(image, 0, 0, 100, 100, false));

        Rectangle rec4 = (Rectangle) grid.getChildren().get(3);
        rec4.setFill(new ImagePattern(image, 50, 50, 100, 100, false));

        Rectangle rec5 = (Rectangle) grid.getChildren().get(4);
        rec5.setFill(new ImagePattern(image, 150, 150, 1, 1, true));

        Circle circle = (Circle) hBox.getChildren().get(0);
        circle.setFill(new ImagePattern(image, 0, 0, 100, 100, false));

        Polygon polygon = (Polygon) hBox.getChildren().get(1);
        polygon.setFill(new ImagePattern(image, 0, 0, 100, 100, false));
    }
}

package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 17:17
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson145Paint_RadialGradient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                Rectangle rec = new Rectangle(200, 200);
                rec.setFill(Paint.valueOf("#EDEDED"));
                grid.add(rec, j, i);
            }
        }

        ArrayList<Paint> list = new ArrayList<>();

        Stop[] stops = new Stop[]{
                new Stop(0, Color.valueOf("#de1e1e")),
                new Stop(0.5, Color.valueOf("#23bfd6")),
                new Stop(1, Color.valueOf("#3567da"))};
        RadialGradient rg1 = new RadialGradient(0, 0, 0, 0, 100,
                false, CycleMethod.NO_CYCLE, stops);
        list.add(rg1);

        RadialGradient rg2 = new RadialGradient(0, 0, 100, 100, 100,
                false, CycleMethod.NO_CYCLE, stops);
        list.add(rg2);

        RadialGradient rg3 = new RadialGradient(0, -1, 100, 100, 100,
                false, CycleMethod.NO_CYCLE, stops);
        list.add(rg3);

        RadialGradient rg4 = new RadialGradient(0, 1, 100, 100, 100,
                false, CycleMethod.NO_CYCLE, stops);
        list.add(rg4);

        RadialGradient rg5 = new RadialGradient(45, 1, 100, 100, 100,
                false, CycleMethod.NO_CYCLE, stops);
        list.add(rg5);

        RadialGradient rg6 = new RadialGradient(45, -1, 100, 100, 100,
                false, CycleMethod.NO_CYCLE, stops);
        list.add(rg6);

        RadialGradient rg7 = new RadialGradient(45, -0.5, 100, 100, 100,
                false, CycleMethod.NO_CYCLE, stops);
        list.add(rg7);

        RadialGradient rg8 = new RadialGradient(45, -0.5, 0.5, 0.5, 0.5,
                true, CycleMethod.NO_CYCLE, stops);
        list.add(rg8);

        Stop[] stops9 = new Stop[]{
                new Stop(0, Color.valueOf("#de1e1e")),
                new Stop(0.5, Color.valueOf("#23bfd6")),
                new Stop(0.99, Color.valueOf("#3567da")),
                new Stop(1, Color.valueOf("#3567da00"))};
        RadialGradient rg9 = new RadialGradient(0, 0, 0.5, 0.5, 0.5,
                true, CycleMethod.NO_CYCLE, stops9);
        list.add(rg9);

        for (int i = 0; i < list.size(); i++) {
            Rectangle rec = (Rectangle) grid.getChildren().get(i);
            rec.setFill(list.get(i));
        }

        HBox hBox = new HBox(20);
        hBox.getChildren().add(new Circle(100));
        hBox.getChildren().add(new Circle(100));
        hBox.getChildren().add(new Circle(100));
        hBox.getChildren().add(new Circle(100));
        hBox.getChildren().add(new Circle(100));
        hBox.getChildren().add(new Polygon(100, 0, 200, 200, 0, 200));


        RadialGradient rg10 = new RadialGradient(0, 0, 0.5, 0.5, 0.7,
                true, CycleMethod.NO_CYCLE, stops);
        Circle circle1 = (Circle) hBox.getChildren().get(0);
        circle1.setFill(rg10);

        RadialGradient rg11 = new RadialGradient(0, 0, 0, 0, 100,
                false, CycleMethod.NO_CYCLE, stops);
        Circle circle2 = (Circle) hBox.getChildren().get(1);
        circle2.setFill(rg11);

        RadialGradient rg12 = new RadialGradient(0, 0, 0, 0, 1,
                true, CycleMethod.NO_CYCLE, stops);
        Circle circle3 = (Circle) hBox.getChildren().get(2);
        circle3.setFill(rg12);

        RadialGradient rg13 = new RadialGradient(0, 0, 0, 0, 50,
                false, CycleMethod.REFLECT, stops);
        Circle circle4 = (Circle) hBox.getChildren().get(3);
        circle4.setFill(rg13);

        RadialGradient rg14 = new RadialGradient(0, 0, 0, 0, 50,
                false, CycleMethod.REPEAT, stops);
        Circle circle5 = (Circle) hBox.getChildren().get(4);
        circle5.setFill(rg14);

        RadialGradient rg15 = new RadialGradient(0, 0, 0.5, 0.5, 0.5,
                true, CycleMethod.NO_CYCLE, stops);
        Polygon polygon = (Polygon) hBox.getChildren().get(5);
        polygon.setFill(rg15);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(grid, hBox);
        AnchorPane.setTopAnchor(hBox, 500.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1400);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 16:59
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson144Paint_LinearGradient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                Rectangle rec = new Rectangle(200, 200);
                rec.setFill(Paint.valueOf("#EDEDED"));
                grid.add(rec, j, i);
            }
        }

        ArrayList<Paint> list = new ArrayList<>();

        Stop[] stops = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.5, Color.valueOf("#e7225e")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg1 = new LinearGradient(0, 0, 200, 200, false, CycleMethod.NO_CYCLE, stops);
        list.add(lg1);

        Stop[] stops2 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.3, Color.valueOf("#e7225e")),
                new Stop(0.6, Color.valueOf("#23c7d6")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg2 = new LinearGradient(0, 0, 200, 200, false, CycleMethod.NO_CYCLE, stops2);
        list.add(lg2);

        Stop[] stops3 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.1, Color.valueOf("#e7225e")),
                new Stop(0.6, Color.valueOf("#23c7d6")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg3 = new LinearGradient(0, 100, 200, 100, false, CycleMethod.NO_CYCLE, stops3);
        list.add(lg3);

        Stop[] stops4 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.1, Color.valueOf("#e7225e")),
                new Stop(0.6, Color.valueOf("#23c7d6")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg4 = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE, stops4);
        list.add(lg4);

        Stop[] stops5 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.5, Color.valueOf("#23c7d6")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg5 = new LinearGradient(0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, stops5);
        list.add(lg5);

        Stop[] stops6 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.5, Color.valueOf("#23c7d6")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg6 = new LinearGradient(0, 0.5, 0.5, 0.5, true, CycleMethod.REFLECT, stops6);
        list.add(lg6);

        Stop[] stops7 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.5, Color.valueOf("#23c7d6")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg7 = new LinearGradient(0, 0.5, 0.5, 0.5, true, CycleMethod.REPEAT, stops7);
        list.add(lg7);

        Stop[] stops8 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.5, Color.valueOf("#23c7d600")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg8 = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE, stops8);
        list.add(lg8);

        Stop[] stops9 = new Stop[]{
                new Stop(0, Color.valueOf("#3238b0")),
                new Stop(0.5, Color.valueOf("#23c7d600")),
                new Stop(1, Color.valueOf("#35da63"))};
        LinearGradient lg9 = new LinearGradient(1, 0.5, 0, 0.5, true, CycleMethod.NO_CYCLE, stops9);
        list.add(lg9);

        Label label = new Label("abcdefghijklmn测试用例");
        label.setFont(new Font(40));
        label.setTextFill(lg4);

        for (int i = 0; i < list.size(); i++) {
            Rectangle rec = (Rectangle) grid.getChildren().get(i);
            rec.setFill(list.get(i));
        }

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(grid, label);

        AnchorPane.setTopAnchor(label, 600.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1400);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

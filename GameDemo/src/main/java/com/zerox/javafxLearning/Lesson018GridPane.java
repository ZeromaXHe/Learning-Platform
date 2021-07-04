package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 20:50
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson018GridPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        Button b4 = new Button("button4");
        Button b5 = new Button("button5");
        Button b6 = new Button("button6");
        Button b7 = new Button("button7");
        Button b8 = new Button("button8");

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #EE6AA7");

        // 设置组件在GridPane里的第几列第几行
//        grid.add(b1, 0, 0);
//        grid.add(b2, 1, 0);
        grid.add(b3, 2, 0);
        grid.add(b4, 3, 0);
        grid.add(b5, 0, 1);
        grid.add(b6, 1, 1);
        grid.add(b7, 2, 1);
        grid.add(b8, 3, 1);

        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(10));
        GridPane.setMargin(b1, new Insets(10));
        grid.setAlignment(Pos.CENTER);

        GridPane.setConstraints(b1, 0, 0);
        grid.getChildren().add(b1);

        GridPane.setRowIndex(b2, 0);
        GridPane.setColumnIndex(b2, 1);
        grid.getChildren().add(b2);

        grid.getColumnConstraints().addAll(new ColumnConstraints(100), new ColumnConstraints(100));
        grid.getRowConstraints().add(new RowConstraints(200));

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GridPane test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

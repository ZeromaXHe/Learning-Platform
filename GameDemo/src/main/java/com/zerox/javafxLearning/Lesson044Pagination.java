package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/7/22 11:42
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson044Pagination extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();

        Pagination pa = new Pagination();
        pa.setStyle("-fx-background-color: #ffff55");
        pa.setPrefWidth(200);
        pa.setPrefHeight(200);

        pa.setLayoutX(100);
        pa.setLayoutY(100);

        /// 无限页数
//        pa.setPageCount(Pagination.INDETERMINATE);
        pa.setPageCount(10);
        pa.setMaxPageIndicatorCount(5);
        pa.setCurrentPageIndex(3);
        /// 样式
//        pa.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

        pa.setPageFactory(param -> {
            System.out.println("page = " + param);
            if (param == 0) {
                HBox box = new HBox();
                box.setMaxWidth(100);
                box.setMaxHeight(100);
                box.setAlignment(Pos.BOTTOM_CENTER);
                box.getChildren().add(new Button("boxButton"));
                box.setStyle("-fx-background-color: #FA8072");
                return box;
            } else if (param == 1) {
                HBox box = new HBox();
                box.setAlignment(Pos.TOP_CENTER);
                box.getChildren().add(new Button("box2Button"));
                box.setStyle("-fx-background-color: #87CEEB");
                return box;
            }
            return new Button("button" + param);
        });

        root.getChildren().addAll(pa);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        pa.currentPageIndexProperty().addListener((o, ov, nv) -> {
            System.out.println(nv.intValue());
        });
    }
}

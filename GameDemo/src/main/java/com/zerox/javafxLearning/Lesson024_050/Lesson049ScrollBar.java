package com.zerox.javafxLearning.Lesson024_050;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/7/24 14:32
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson049ScrollBar extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Button button = new Button("scroll down");

        HBox hBox = new HBox(10);
        for (int i = 0; i < 10; i++) {
            hBox.getChildren().add(new Button("button" + i));
        }

        HBox hBox2 = new HBox();
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Separator sep = new Separator(Orientation.VERTICAL);
        sep.setPrefWidth(300);
//        sep.setOrientation(Orientation.VERTICAL);
        sep.setHalignment(HPos.CENTER);
//        sep.setValignment(VPos.CENTER);
        hBox2.getChildren().addAll(b1, sep, b2);

        VBox vBox = new VBox(10);
        for (int i = 0; i < 10; i++) {
            vBox.getChildren().add(new Button("button" + i));
        }

        ScrollPane scp = new ScrollPane();
        scp.setPrefWidth(300);
        scp.setContent(hBox);

        ScrollBar sc = new ScrollBar();
        sc.setOrientation(Orientation.VERTICAL);
        sc.setVisibleAmount(50);
        sc.setValue(20);

        sc.valueProperty().addListener((o, ov, nv) -> {
            System.out.println(nv);
            vBox.setLayoutY(-nv.doubleValue());
        });

        AnchorPane.setLeftAnchor(sc, 100.0);
        AnchorPane.setLeftAnchor(button, 200.0);
        AnchorPane.setTopAnchor(scp, 350.0);
        AnchorPane.setTopAnchor(hBox2, 450.0);
        an.getChildren().addAll(sc, vBox, button, scp, hBox2);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ScrollBar");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        sc.setPrefHeight(vBox.getHeight());
        sc.setMax(vBox.getHeight());
        sc.setUnitIncrement(10);
        sc.setBlockIncrement(50);

        button.setOnAction(event -> {
            sc.increment();
        });

        scp.hvalueProperty().addListener((o, ov, nv) -> System.out.println(nv));
    }
}

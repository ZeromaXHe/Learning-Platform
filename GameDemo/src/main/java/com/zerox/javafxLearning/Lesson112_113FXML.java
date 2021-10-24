package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Objects;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/24 9:13
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson112_113FXML extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fx = new FXMLLoader();
        URL url = fx.getClassLoader().getResource("fxml/Lesson112_113FXML.fxml");
        fx.setLocation(url);
        AnchorPane root = fx.load();

        FXMLLoader fx2 = new FXMLLoader();
        AnchorPane root2 = fx2.load(new FileInputStream(new File("./GameDemo/src/main/resources/fxml/Lesson112_113FXML.fxml")));

        // 等效fxml的代码
        AnchorPane an = new AnchorPane();
        Button button = new Button();
        button.setId("b1");
        button.setPrefWidth(100);
        button.setPrefHeight(100);
        button.setText("button");
        an.getChildren().add(button);
        AnchorPane.setTopAnchor(button, 100.0);
        AnchorPane.setLeftAnchor(button, 100.0);

        Button lookup = (Button) an.lookup("#b1");
        System.out.println(Objects.equals(button, lookup));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

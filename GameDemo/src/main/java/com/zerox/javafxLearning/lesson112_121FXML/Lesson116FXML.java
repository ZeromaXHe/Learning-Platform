package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 16:11
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson116FXML  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(fl.getClassLoader().getResource("fxml/Lesson116FXML.fxml"));
        AnchorPane an = fl.load();

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

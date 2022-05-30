package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 13:53
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson114FXML extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(fl.getClassLoader().getResource("fxml/Lesson114FXML.fxml"));
        AnchorPane an = fl.load();

//        Lesson114FXMLController controller = fl.getController();

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        // 点击按钮替换 AnchorPane
//        controller.getButton().setOnAction(event -> {
//            try {
//                FXMLLoader fl2 = new FXMLLoader();
//                fl2.setLocation(fl2.getClassLoader().getResource("fxml/Lesson114FXMLreplace.fxml"));
//                AnchorPane an2 = fl2.load();
//                scene.setRoot(an2);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}

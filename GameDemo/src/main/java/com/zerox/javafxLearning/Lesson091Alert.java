package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 11:12
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson091Alert extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        Button button = new Button("点击弹出 Alert");

        AnchorPane.setTopAnchor(button, 10.0);
        AnchorPane.setLeftAnchor(button, 10.0);
        an.getChildren().addAll(button);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        button.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Button close = (Button) alert.getDialogPane().lookupButton(ButtonType.CLOSE);
            close.setOnAction(event1 -> System.out.println("close"));

            Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            ok.setOnAction(event1 -> System.out.println("ok"));
            Button cancel = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
            ok.setOnAction(event1 -> System.out.println("cancel"));

//            alert.getDialogPane().getButtonTypes().remove(1);

            // 默认是 APPLICATION_MODAL，可以参考第 4 课
            alert.initModality(Modality.WINDOW_MODAL);

            alert.setTitle("title");
            alert.setHeaderText("header text");
            alert.setContentText("content text");
            alert.show();
        });
    }
}

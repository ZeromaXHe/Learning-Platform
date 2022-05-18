package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 10:44
 * @Description: DialogPane 在 22 课
 * @ModifiedBy: zhuxi
 */
public class Lesson090Dialog extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        Button button = new Button("点击弹出 Dialog");

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
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Button close = (Button) dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
            Button ok = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);

            close.setOnAction(event1 -> System.out.println("close"));
            ok.setOnAction(event1 -> System.out.println("ok"));

            dialog.setOnCloseRequest(event1 -> System.out.println("关闭监听"));
            dialog.setResultConverter(param -> {
                System.out.println(param);
                return param;
            });

            // 窗口出现位置
            dialog.setX(100);
            dialog.setY(100);
            // 窗口大小
            dialog.getDialogPane().setPrefWidth(300);
            dialog.getDialogPane().setPrefHeight(300);

            dialog.setGraphic(new Button("Graphic"));
            dialog.setHeaderText("Dialog Header");
            dialog.setContentText("this is a dialog content text");
            dialog.setTitle("dialog title");

            // 二次弹出
            Optional<ButtonType> optional = dialog.showAndWait();
            optional.ifPresent(buttonType -> {
                if(buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    dialog.setContentText("pressed ok");
                }else{
                    dialog.setContentText("pressed others");
                }
            });

            dialog.show();
        });
    }
}

package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 11:25
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson092ChoiceDialogAndTextInputDialog extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        Button button1 = new Button("点击弹出 ChoiceDialog");
        Button button2 = new Button("点击弹出 TextInputDialog");
        Button button3 = new Button("点击弹出自定义弹窗");

        AnchorPane.setTopAnchor(button1, 10.0);
        AnchorPane.setLeftAnchor(button1, 100.0);
        AnchorPane.setTopAnchor(button2, 100.0);
        AnchorPane.setLeftAnchor(button2, 100.0);
        AnchorPane.setTopAnchor(button3, 200.0);
        AnchorPane.setLeftAnchor(button3, 100.0);
        an.getChildren().addAll(button1, button2, button3);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        button1.setOnAction(event -> {
            ObservableList<String> list = FXCollections.observableArrayList();
            list.addAll("data1", "data2", "data3", "data4");
            ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("data1", list);

            choiceDialog.selectedItemProperty().addListener((o, ov, nv) -> System.out.println(nv));

            choiceDialog.show();
        });

        button2.setOnAction(event -> {
            TextInputDialog textInputDialog = new TextInputDialog("defaultValue");
            Button ok = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.OK);
            ok.setOnAction(event1 -> System.out.println(textInputDialog.getEditor().getText()));
            textInputDialog.show();
        });

        button3.setOnAction(event -> {
            Button bu = new Button("button");

            AnchorPane an1 = new AnchorPane();
            an.getChildren().add(bu);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setAlwaysOnTop(true);

            Scene scene1 = new Scene(an1);
            stage.setScene(scene1);

            stage.setTitle("自定义弹窗");
            stage.setWidth(300);
            stage.setHeight(300);
            stage.show();
        });
    }
}

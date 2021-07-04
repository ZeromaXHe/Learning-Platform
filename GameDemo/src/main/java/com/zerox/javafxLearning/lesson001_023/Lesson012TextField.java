package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 10:46
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class Lesson012TextField extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("input test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);

        Label label = new Label("我是标签");
        label.setLayoutX(30);
        label.setLayoutY(100);
        label.setFont(Font.font(14));
        label.setTextFill(Paint.valueOf("red"));

        label.setOnMouseClicked(event -> System.out.println("点击label"));

        PasswordField ptext = new PasswordField();
        ptext.setLayoutX(300);
        ptext.setLayoutY(100);
        ptext.setFont(Font.font(14));

        TextField text = new TextField();
        text.setText("这是文本");

        text.setLayoutX(100);
        text.setLayoutY(100);
        text.setFont(Font.font(14));

        // 放在文本框一段时间后显示提示
        Tooltip tip = new Tooltip("这是提示");
        tip.setFont(Font.font(30));
        text.setTooltip(tip);

        text.setPromptText("请输入7个字以下");

        text.setFocusTraversable(false);

        text.textProperty().addListener((o, ov, nv) -> {
            System.out.println("文本框中文字：" + nv);
            if (nv.length() > 7) {
                text.setText(ov);
            }
        });

        // 监听选中的文字
        text.selectedTextProperty().addListener((o, ov, nv) -> System.out.println("选中的文字：" + nv));

        // setOnAction好像没有用
//        text.setOnAction(event -> System.out.println("单击"));
        text.setOnMouseClicked(event -> System.out.println("单击text"));

        root.getChildren().add(text);
        root.getChildren().add(ptext);
        root.getChildren().add(label);

        primaryStage.show();
    }
}

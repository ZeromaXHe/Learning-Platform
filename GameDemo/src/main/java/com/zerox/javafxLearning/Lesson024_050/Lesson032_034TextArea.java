package com.zerox.javafxLearning.Lesson024_050;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Arrays;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/18 11:21
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson032_034TextArea extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);

        TextField tf = new TextField();
        tf.setTextFormatter(new TextFormatter<String>(change -> {
            String value = change.getText();
            System.out.println(value);
            if (value.matches("[a-z]*")) {
                return change;
            }
            return null;
        }));

        TextArea ta = new TextArea();
        ta.setText("请输入任意文字");
        ta.setFont(Font.font(16));

        HBox hBox = new HBox(10);
        TextField find_tf = new TextField();
        Button find_bu = new Button("查找");
        Button sort_bu = new Button("排序");
        hBox.getChildren().addAll(find_tf, find_bu, sort_bu);
        hBox.setAlignment(Pos.CENTER);

        TextArea find_ta = new TextArea();
        find_ta.setFont(Font.font(16));
        find_ta.setText("find_ta, text for find, find_ta, aha");

        box.getChildren().addAll(tf, ta, hBox, find_ta);


        /// 自动换行
//        ta.setWrapText(false);

//        ta.setEditable(false);

//        ta.setPrefRowCount(10);
//        ta.setPrefColumnCount(10);
//        ta.setPrefWidth(100.0);
//        ta.setPrefHeight(100.0);

//        ta.appendText("abcdefg");
//        ta.deleteText(0, 3);
//        ta.insertText(3, ":");
//        ta.replaceText(0, 3, "please type ");

//        ta.selectAll();
//        ta.selectForward();
//        ta.selectPositionCaret(4);
//        ta.getLength();
//        ta.selectRange(3, 5);
//        ta.home();
//        ta.positionCaret(4);
//        ta.selectEnd();
//        ta.selectHome();

//        ta.clear();
//        ta.setText("");

        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        ta.requestFocus();

        ta.textProperty().addListener((o, ov, nv) -> {
            ta.setTextFormatter(new TextFormatter<>(new StringConverter<String>() {
                @Override
                public String toString(String object) {
                    System.out.println("obj = " + object);
                    return object;
                }

                @Override
                public String fromString(String string) {
                    System.out.println("str = " + string);
                    if (string.contains("5")) {
                        return string.replace("5", "五");
                    }
                    return string;
                }
            }));
            ta.commitValue();
        });

        find_bu.setOnAction(event -> {
            find_ta.getParagraphs().forEach(t -> {
                String value = t.toString();
                String find_value = find_tf.getText();
                int end = find_ta.getSelection().getEnd();
                int i = value.indexOf(find_value, (end + 1) % value.length());
                if (i >= 0) {
                    find_ta.requestFocus();
                    find_ta.selectRange(i, i + find_value.length());
                }
            });
        });

        sort_bu.setOnAction(event -> {
            char[] chars = find_ta.getText().toCharArray();
            Arrays.sort(chars);
            find_ta.setText(String.valueOf(chars));
        });
    }
}

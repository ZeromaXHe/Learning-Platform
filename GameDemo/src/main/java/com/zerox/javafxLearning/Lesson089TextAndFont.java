package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 9:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson089TextAndFont extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text1 = new Text("hello world");
        Font font1 = new Font("Courier", 25);
        text1.setFont(font1);

        Text text2 = new Text("hello world");
        Font font2 = new Font("Impact", 25);
        text2.setFont(font2);

        Text text3 = new Text("hello world");
        Font font3 = Font.loadFont("file:C:/Windows/Fonts/Inkfree.ttf", 25);
        text3.setFont(font3);

        Text text4 = new Text("hello world 你好 世界");
        // windows 10 华文行楷
        Font font4 = Font.loadFont("file:C:/Windows/Fonts/STXINGKA.TTF", 68);
        Font.getFamilies().forEach(System.out::println);
        System.out.println("==============================");
        Font.getFontNames().forEach(System.out::println);
        System.out.println("==============================");
        System.out.println(font4.getName());
        text4.setFont(font4);

        Text text5 = new Text("hello world");
        Font font5 = Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25);
        text5.setFont(font5);

        Text text6 = new Text("hello world");
        Font font6 = new Font("Courier", 60);
        text6.setFont(font6);
        // 填充颜色
        text6.setFill(Paint.valueOf("#20B2AA"));
        // 描边颜色
        text6.setStroke(Paint.valueOf("#CD5C5C"));
        // 描边宽度
        text6.setStrokeWidth(2);
        // 是否抗锯齿
        text6.setSmooth(true);
        // 下划线
        text6.setUnderline(true);
        // 中间线
        text6.setStrikethrough(true);
        // 字体平滑
        text6.setFontSmoothingType(FontSmoothingType.LCD);

        Text text7 = new Text("hello world\ntest1 test2 test3 test4 test5 test6 test7 test8 test9 test0");
        Font font7 = new Font("Courier", 25);
        text7.setFont(font7);
        // 多行对齐方式
        text7.setTextAlignment(TextAlignment.LEFT);
        // 行间距
        text7.setLineSpacing(10);
        // 用像素限制宽度
        text7.setWrappingWidth(200);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text1, text2, text3, text4, text5, text6, text7);

        // ============================== TextOrigin ====================================

        Text text = new Text("hello world");
        text.setFont(Font.font(20));
        text.setX(500);
        text.setY(300);
        text.setTextOrigin(VPos.BOTTOM);

        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #CD5C3C");
        hBox.setPrefWidth(200);
        hBox.setPrefHeight(200);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(vBox, hBox, text);

        AnchorPane.setTopAnchor(hBox, 300.0);
        AnchorPane.setLeftAnchor(hBox, 500.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

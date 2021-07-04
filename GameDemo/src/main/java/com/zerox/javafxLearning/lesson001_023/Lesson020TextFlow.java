package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 21:13
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson020TextFlow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane an = new AnchorPane();

        Text t1 = new Text("这是一个TextFlow测试");
        Text t2 = new Text("随便打点字来试试");
        Text t3 = new Text("Hello, TextFlow");

        t1.setFont(Font.font(20));
        t1.setFill(Paint.valueOf("#FF82AB"));

        t3.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40));

        TextFlow textFlow = new TextFlow();
        textFlow.setStyle("-fx-background-color: #EECFA1");

        textFlow.setPadding(new Insets(10));
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setLineSpacing(30);

        textFlow.getChildren().addAll(t1, t2, t3);

        Text t4 = new Text("random type random random random");

        TextFlow textFlow2 = new TextFlow();
        textFlow2.getChildren().addAll(t4);

        AnchorPane.setTopAnchor(textFlow, 100.0);
        AnchorPane.setLeftAnchor(textFlow, 100.0);
        an.setPadding(new Insets(10));

        an.getChildren().addAll(textFlow, textFlow2);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TextFlow test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        primaryStage.widthProperty().addListener((o, ov, nv) -> {
            System.out.println("这是AnchorPane的宽度=" + an.getWidth());
            System.out.println("这是textFlow的宽度=" + textFlow.getWidth());
        });

        // 效果不怎么好
        an.widthProperty().addListener((o, ov, nv) -> {
            textFlow.setPrefWidth(nv.doubleValue() - AnchorPane.getLeftAnchor(textFlow));
        });
    }
}

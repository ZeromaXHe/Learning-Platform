package com.zerox.javafxLearning.lesson065_081;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/17 23:22
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson072KeyBoardEvent extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(20);

        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        TextField tf = new TextField();

        Rectangle rec = new Rectangle(100, 100);
        rec.setFill(Color.RED);

        hBox.getChildren().addAll(b1, b2, tf, rec);

        b1.setOnKeyPressed(event -> {
            System.out.println("字符：" + event.getCharacter());
            System.out.println("代码：" + event.getCode());
            System.out.println("事件类型：" + event.getEventType());
            System.out.println("事件源：" + event.getSource());
            System.out.println("事件目标：" + event.getTarget());
            System.out.println("文本：" + event.getText());
            System.out.println(event.isControlDown());

            if (KeyCode.A.getName().equals(event.getCode().getName())) {
                System.out.println("按了A键");
            }

            System.out.println("-------------------------");
        });

        b1.setOnKeyReleased(event -> System.out.println("释放了" + event.getCode().getName()));

        // 有输入焦点才行, 且必须getCharacter，否则使用getCode().getName()是undefined
        tf.setOnKeyTyped(event -> System.out.println(event.getCharacter()));

        rec.setOnKeyPressed(event -> System.out.println(event.getCode().getName()));

        rec.setOnMouseClicked(event -> rec.requestFocus());

        AnchorPane an = new AnchorPane();
        an.getChildren().add(hBox);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

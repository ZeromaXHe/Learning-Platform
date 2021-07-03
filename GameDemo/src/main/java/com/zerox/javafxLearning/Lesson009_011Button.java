package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/3 20:32
 * @Description: 009 Button; 010 KeyCode、MouseButton; 011 快捷键
 * @Modified By: ZeromaXHe
 */
public class Lesson009_011Button extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button();
        b1.setText("B1字体");
        b1.setLayoutX(0);
        b1.setLayoutY(100);

        b1.setPrefWidth(400);
        b1.setPrefHeight(200);

        Button b2 = new Button();
        b2.setText("B2字体");
        b2.setLayoutX(400);
        b2.setLayoutY(100);

        b2.setPrefWidth(400);
        b2.setPrefHeight(200);

        b1.setFont(Font.font("sans-serif", 40));
        // java
        b1.setTextFill(Paint.valueOf("#CD0000"));

        BackgroundFill bgf = new BackgroundFill(
                Paint.valueOf("#8FBC8F"),
                new CornerRadii(20),
                new Insets(10,5,20,5)
//                new Insets(10)
        );
        Background bg = new Background(bgf);
        b1.setBackground(bg);

        BorderStroke bos = new BorderStroke(
                Paint.valueOf("#8A2BE2"),
                BorderStrokeStyle.SOLID,
//                BorderStrokeStyle.DASHED,
//                BorderStrokeStyle.DOTTED,
                new CornerRadii(20),
                new BorderWidths(5)
        );
        Border bo = new Border(bos);
        b1.setBorder(bo);

        // css
        b2.setStyle("-fx-background-color: #7CCD7C;" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: #5CACEE;");

        b1.setOnAction(event -> {
            Button bu = (Button) event.getSource();
            System.out.println("b1的文本:" + bu.getText());
        });

        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("鼠标按键：" + event.getButton().name());
            System.out.println("鼠标点击次数:" + event.getClickCount());

            if (event.getClickCount() == 2
                    && MouseButton.PRIMARY.name().equals(event.getButton().name())) {
                System.out.println("【===左键双击事件===】");
            }
        });

        b1.setOnKeyPressed(event -> {
            System.out.println("按下" + event.getCode().getName());
            if (KeyCode.A.getName().equals(event.getCode().getName())) {
                System.out.println("【===按下A事件===】");
            }
        });
        b1.setOnKeyReleased(event -> {
            System.out.println("释放" + event.getCode().getName());
            if (KeyCode.A.getName().equals(event.getCode().getName())) {
                System.out.println("【===释放A事件===】");
            }
        });

        Group root = new Group();
        root.getChildren().add(b1);
        root.getChildren().add(b2);

        Scene scene = new Scene(root);

        // 快捷键 第一种
        KeyCombination kc1 = new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN, KeyCombination.CONTROL_DOWN);
        Mnemonic mnemonic1 = new Mnemonic(b1, kc1);
        scene.addMnemonic(mnemonic1);

        // 快捷键 第二种
        KeyCombination kc2 = new KeyCharacterCombination("O", KeyCombination.ALT_DOWN);
        Mnemonic mnemonic2 = new Mnemonic(b1, kc2);
        scene.addMnemonic(mnemonic2);

        // 快捷键 第三种
        KeyCombination kc3 = new KeyCodeCombination(KeyCode.K, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN, KeyCombination.META_DOWN, KeyCombination.SHORTCUT_DOWN);
        Mnemonic mnemonic3 = new Mnemonic(b1, kc3);
        scene.addMnemonic(mnemonic3);

        // 快捷键 第四种
        // SHORTCUT 意思是 CTRL on win, META on mac
        KeyCombination kccb = new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kccb, () -> {
            // 并不是真正的多线程
            System.out.println("Runnable run()方法 " + Thread.currentThread().getName());
            b1.fire();
        });

        primaryStage.setScene(scene);

        primaryStage.setTitle("button test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();
    }
}

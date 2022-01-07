package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/7 11:19
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson079Clipboard extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Clipboard clipboard = Clipboard.getSystemClipboard();

        AnchorPane an = new AnchorPane();

        Label label = new Label("等待粘贴内容");
        an.getChildren().add(label);

        Button button = new Button("hello world");
        an.getChildren().add(button);

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(300);
        an.getChildren().add(iv);

        AnchorPane.setTopAnchor(label, 100.0);
        AnchorPane.setLeftAnchor(label, 100.0);
        AnchorPane.setTopAnchor(button, 100.0);
        AnchorPane.setLeftAnchor(button, 200.0);
        AnchorPane.setTopAnchor(iv, 150.0);
        AnchorPane.setLeftAnchor(iv, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        button.setOnAction(event -> {
            // 编辑剪切板的内容
            ClipboardContent content = new ClipboardContent();
            content.put(DataFormat.PLAIN_TEXT, "hello world");
            clipboard.setContent(content);
        });

        KeyCodeCombination kc = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kc, () -> {
            System.out.println(clipboard.getContentTypes());

            if (clipboard.hasString()) {
                label.setText(clipboard.getString());
            } else if (clipboard.hasFiles()) {
                // hasImage()方法可能判断失败（如Mac粘贴的是字符串）
                List<File> files = clipboard.getFiles();
                try {
                    Image image = new Image(new FileInputStream(files.get(0)));
                    iv.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /// 不知道为什么这里注释的实现拿不到图片
//                System.out.println("file");
//                Object o = clipboard.getContent(DataFormat.IMAGE);
//                System.out.println(o);
////                Image image = (Image) o;
//                Image image = clipboard.getImage();
//                System.out.println(clipboard.hasImage() + ":" + image);
//                iv.setImage(image);
            }
        });
    }
}

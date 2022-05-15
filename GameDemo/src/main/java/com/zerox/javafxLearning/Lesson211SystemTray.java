package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/15 17:25
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson211SystemTray extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 窗口关闭后，程序仍在后台运行
        Platform.setImplicitExit(false);

        URL url = this.getClass().getClassLoader().getResource("mp3/radio_noise.mp3");
        Media media = new Media(url.toExternalForm());
        MediaPlayer mp = new MediaPlayer(media);
        mp.setVolume(0.5);

        Button music = new Button("播放");
        Button bu = new Button("最小化到系统托盘");

        bu.setOnAction(event -> {
            SystemTray tray = SystemTray.getSystemTray();

            // awt 图片好像直接是原图大小，有点坑……
            Image image = Toolkit.getDefaultToolkit().getImage(Lesson211SystemTray.class.getResource("/icon/icon.jpeg"));
            String info = "JavaFX 系统托盘";
            PopupMenu menu = new PopupMenu();
            // 中文乱码需要在虚拟机设置里加上 -Dfile.encoding=GB18030
            MenuItem item1 = new MenuItem("显示show");
            MenuItem item2 = new MenuItem("退出quit");
            menu.add(item1);
            menu.add(item2);
            TrayIcon icon = new TrayIcon(image, info, menu);
            try {
                tray.add(icon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            primaryStage.hide();

            item1.addActionListener(e -> {
                Platform.runLater(primaryStage::show);
                tray.remove(icon);
            });

            item2.addActionListener(e -> {
                Platform.setImplicitExit(true);
                Platform.runLater(primaryStage::close);
                tray.remove(icon);
                Platform.exit();
            });
        });

        music.setOnAction(event -> mp.play());

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(music, bu);
        AnchorPane.setLeftAnchor(music, 100.0);
        AnchorPane.setLeftAnchor(bu, 200.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        // 可以修改为最小到托盘的逻辑？
        primaryStage.setOnCloseRequest(event -> Platform.setImplicitExit(true));
    }
}

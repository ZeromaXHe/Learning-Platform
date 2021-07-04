package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/28 22:39
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class Lesson004StageStyle extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage s1 = new Stage();
        s1.setTitle("s1");
        // 默认的样式
        s1.initStyle(StageStyle.DECORATED);
        s1.show();

        Stage s2 = new Stage();
        s2.setTitle("s2");
        // 透明样式
        s2.initStyle(StageStyle.TRANSPARENT);
        s2.show();

        Stage s3 = new Stage();
        s3.setTitle("s3");
        // 也是透明
        s3.initStyle(StageStyle.UNDECORATED);
        s3.show();

        Stage s4 = new Stage();
        s4.setTitle("s4");
        // 界面统一颜色样式
        s4.initStyle(StageStyle.UNIFIED);
        s4.show();

        Stage s5 = new Stage();
        s5.setTitle("s5");
        // 没有最小化和最大化的样式
        s5.initStyle(StageStyle.UTILITY);
        s5.show();

        // 关闭全部窗口
        Platform.exit();
    }
}

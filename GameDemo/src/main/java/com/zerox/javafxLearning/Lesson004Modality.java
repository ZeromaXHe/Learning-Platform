package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/28 22:52
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class Lesson004Modality extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage s1 = new Stage();
        s1.setTitle("s1");
        s1.show();

        Stage s2 = new Stage();
        s2.setTitle("s2");
        // initOwner关联窗口, WINDOW模态才生效
        s2.initOwner(s1);
        s2.initModality(Modality.WINDOW_MODAL);
        s2.show();

        Stage s3 = new Stage();
        // APPLICATION是全局模态
        s3.initModality(Modality.APPLICATION_MODAL);
        s3.setTitle("s3");
        s3.show();
    }
}

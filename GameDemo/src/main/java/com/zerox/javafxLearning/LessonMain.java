package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/22 21:56
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class LessonMain extends Application {
    public static void main(String[] args) {
        // Main = main
        System.out.println("Main = " + Thread.currentThread().getName());

        launch(args);
        /// 方式二: 也可以指定另一个class
//        Application.launch(Main.class, args);
    }

    @Override
    public void init() throws Exception {
        // Init = JavaFX-Launcher
        System.out.println("Init = " + Thread.currentThread().getName());
    }

    /**
     * start是必须实现的，init和stop可选
     * <p>
     * 生命周期顺序：
     * Main = main
     * Init = JavaFX-Launcher
     * Start = JavaFX Application Thread
     * Stop = JavaFX Application Thread
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Start = JavaFX Application Thread
        System.out.println("Start = " + Thread.currentThread().getName());
        // 标题
        primaryStage.setTitle("Test");
        // 图标
        // IDEA显示不出，需要参考下面StackOverflow问题，将icon放在resources文件夹下：
        // https://stackoverflow.com/questions/44420610/javafx-imageview-is-being-displayed-only-when-i-use-an-absolute-path-why
        primaryStage.getIcons().add(new Image("/icon/icon.jpeg"));
        // 最小化
//        primaryStage.setIconified(true);
        // 最大化
//        primaryStage.setMaximized(true);
        // 设置宽高
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        // 设置不可改变窗口大小
//        primaryStage.setResizable(false);
        // 设置最大最小宽高
//        primaryStage.setMaxWidth(800);
//        primaryStage.setMaxHeight(800);
//        primaryStage.setMinWidth(300);
//        primaryStage.setMinHeight(300);

        // 如果没有设置宽高的话，需要在show之后调用getWidth/Height。因为这个时候才会初始化，否则为NaN
        System.out.println("width: " + primaryStage.getWidth());
        System.out.println("height: " + primaryStage.getHeight());
        primaryStage.heightProperty().addListener((o, ov, nv) -> {
            System.out.println("current height: " + nv.doubleValue());
        });
        primaryStage.widthProperty().addListener((o, ov, nv) -> {
            System.out.println("current width: " + nv.doubleValue());
        });

        // 全屏(会受到最大宽高控制)
//        primaryStage.setFullScreen(true);
//        // 必须有Scene才能全屏
//        primaryStage.setScene(new Scene(new Group()));

        // 透明度
        primaryStage.setOpacity(0.8);
        // 置顶
        primaryStage.setAlwaysOnTop(true);

        // 初始坐标
        primaryStage.setX(100);
        primaryStage.setY(100);
        primaryStage.xProperty().addListener((o, nv, ov) -> {
            System.out.println("x coordinate: " + nv);
        });
        primaryStage.yProperty().addListener((o, nv, ov) -> {
            System.out.println("y coordinate: " + nv);
        });

        primaryStage.show();
        // 关闭（和show相对）
//        primaryStage.close();
    }

    @Override
    public void stop() throws Exception {
        // Stop = JavaFX Application Thread
        System.out.println("Stop = " + Thread.currentThread().getName());
    }
}

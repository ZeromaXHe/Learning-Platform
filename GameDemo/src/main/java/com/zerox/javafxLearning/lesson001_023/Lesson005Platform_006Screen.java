package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Lesson005Platform_006Screen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Platform.runLater可以推迟到start最后运行
        Platform.runLater(() -> {
            System.out.println("test");
            // runLater() in: JavaFX Application Thread
            System.out.println("runLater() in: " + Thread.currentThread().getName());
        });
        // 将在test前打印出来
        // beforeTest in: JavaFX Application Thread
        System.out.println("beforeTest in: " + Thread.currentThread().getName());

        // Platform.setImplicitExit为false，窗口关闭后程序继续运行
        // 默认为true
        Platform.setImplicitExit(false);
        primaryStage.show();

        System.out.println("SCENE3D:" + Platform.isSupported(ConditionalFeature.SCENE3D));
        System.out.println("FXML:" + Platform.isSupported(ConditionalFeature.FXML));


        // 第006课 Screen类：
        // 全部屏幕
        Screen screen = Screen.getPrimary();
        System.out.println("dpi:" + screen.getDpi());
        Rectangle2D rec1 = screen.getBounds();
        System.out.println("rec1.minX = " + rec1.getMinX() + " rec1.minY = " + rec1.getMinY());
        System.out.println("rec1.maxX = " + rec1.getMaxX() + " rec1.maxY = " + rec1.getMaxY());
        System.out.println("rec1.width = " + rec1.getWidth() + " rec1.height = " + rec1.getHeight());

        // 减去任务栏的屏幕
        Rectangle2D rec2 = screen.getVisualBounds();
        System.out.println("rec2.minX = " + rec2.getMinX() + " rec2.minY = " + rec2.getMinY());
        System.out.println("rec2.maxX = " + rec2.getMaxX() + " rec2.maxY = " + rec2.getMaxY());
        System.out.println("rec2.width = " + rec2.getWidth() + " rec2.height = " + rec2.getHeight());

        System.out.println("========================================================");

        // 多屏幕
        ObservableList<Screen> screens = Screen.getScreens();
        System.out.println(screens.size());
        for (Screen screen1 : screens) {
            System.out.println(screen1);
        }
    }
}

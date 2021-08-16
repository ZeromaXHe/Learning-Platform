package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/16 21:46
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson067Image extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileInputStream fis1 = new FileInputStream(new File("./GameDemo/src/main/resources/icon/icon.jpeg"));
        Image im1 = new Image(fis1);

        System.out.println("im1.getRequestedWidth():" + im1.getRequestedWidth());
        System.out.println("im1.getRequestedHeight():" + im1.getRequestedHeight());
        System.out.println("im1.getWidth():" + im1.getWidth());
        System.out.println("im1.getHeight():" + im1.getHeight());

        FileInputStream fis2 = new FileInputStream(new File("./GameDemo/src/main/resources/icon/icon.jpeg"));
        Image im2 = new Image(fis2, 600, 600, false, true);

        System.out.println("im2.getRequestedWidth():" + im2.getRequestedWidth());
        System.out.println("im2.getRequestedHeight():" + im2.getRequestedHeight());
        System.out.println("im2.getWidth():" + im2.getWidth());
        System.out.println("im2.getHeight():" + im2.getHeight());

        String path = "file:./GameDemo/src/main/resources/icon/icon.jpeg";
        Image im3 = new Image(path, 600, 600, true, true, true);
        System.out.println("im3.getRequestedWidth():" + im3.getRequestedWidth());
        System.out.println("im3.getRequestedHeight():" + im3.getRequestedHeight());
        System.out.println("im3.getWidth():" + im3.getWidth());
        System.out.println("im3.getHeight():" + im3.getHeight());

        URL url = getClass().getClassLoader().getResource("icon/icon.jpeg");
        String urlPath = url.toExternalForm();
        Image im4 = new Image(urlPath, 600, 600, true, true, true);

        im4.errorProperty().addListener((o, ov, nv) -> System.out.println("是否错误 = " + nv));
        im4.exceptionProperty().addListener((o, ov, nv) -> System.out.println("异常：" + nv.getMessage()));
        im4.progressProperty().addListener((o, ov, nv) -> System.out.println(nv.doubleValue()));

        ImageView iv = new ImageView(im4);
        AnchorPane an = new AnchorPane();

        an.getChildren().add(iv);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

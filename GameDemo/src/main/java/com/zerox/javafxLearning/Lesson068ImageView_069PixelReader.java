package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/16 22:36
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson068ImageView_069PixelReader extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = "file:./GameDemo/src/main/resources/icon/icon.jpeg";
        // 如果这里宽高太小，后面放大也会模糊
        Image image = new Image(path, 100, 100, true, true);

        PixelReader pr = image.getPixelReader();
        int value = pr.getArgb(40, 40);
        System.out.println(value);
        int alpha = (value >> 24) & 0xff;
        int red = (value >> 16) & 0xff;
        int green = (value >> 8) & 0xff;
        int blue = value & 0xff;
        System.out.println("alpha = " + alpha);
        System.out.println("red = " + red);
        System.out.println("green = " + green);
        System.out.println("blue = " + blue);

        Color color = pr.getColor(40, 40);
        System.out.println(color.toString());
        System.out.println("alpha = " + color.getOpacity());
        System.out.println("red = " + color.getRed());
        System.out.println("green = " + color.getGreen());
        System.out.println("blue = " + color.getBlue());

        WritablePixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraPreInstance();

        byte[] by = new byte[3 * 3 * 4];
        pr.getPixels(39, 39, 3, 3, pixelFormat, by, 0, 3 * 4);
        for (int i = 0; i < by.length; i += 4) {
            int b = by[i] & 0xff;
            int g = by[i + 1] & 0xff;
            int r = by[i + 2] & 0xff;
            int a = by[i + 3] & 0xff;
            System.out.println("A = " + a + " R = " + r + " G = " + g + " B = " + b);
        }

        System.out.println("---------------------------------");

        WritablePixelFormat<IntBuffer> pixelFormat2 = PixelFormat.getIntArgbPreInstance();
        int[] ints = new int[3 * 3];
        pr.getPixels(39, 39, 3, 3, pixelFormat2, ints, 0, 3);
        for (int i = 0; i < ints.length; i++) {
            int a = (ints[i] >> 24) & 0xff;
            int r = (ints[i] >> 16) & 0xff;
            int g = (ints[i] >> 8) & 0xff;
            int b = ints[i] & 0xff;
            System.out.println("A = " + a + " R = " + r + " G = " + g + " B = " + b);
        }

        ImageView iv = new ImageView(image);
        iv.setFitWidth(600);
        iv.setFitHeight(600);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);

        Rectangle rec = new Rectangle(600, 600);
        rec.setArcWidth(600);
        rec.setArcHeight(600);
        iv.setClip(rec);
        iv.setX(100);

        // 前两个值控制左上角坐标，后两个代表宽高
        Rectangle2D rec2d = new Rectangle2D(20, 20, 200, 200);
        iv.setViewport(rec2d);

        iv.setOnMouseDragged(event -> {
            Rectangle2D rec2d2 = new Rectangle2D(event.getSceneX(), event.getSceneY(), 200, 200);
            iv.setViewport(rec2d2);
        });

        System.out.println("iv.getFitWidth():" + iv.getFitWidth());
        System.out.println("iv.getFitHeight():" + iv.getFitHeight());
        System.out.println("iv.prefWidth(-1):" + iv.prefWidth(-1));
        System.out.println("iv.prefHeight(-1):" + iv.prefHeight(-1));

        ScrollPane pane = new ScrollPane();
        pane.setPrefSize(300, 300);
        pane.setContent(iv);

        AnchorPane an = new AnchorPane();
        an.getChildren().add(pane);
        an.setStyle("-fx-background-color: #" + Integer.toHexString(red) + Integer.toHexString(green)
                + Integer.toHexString(blue) + Integer.toHexString(alpha));

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

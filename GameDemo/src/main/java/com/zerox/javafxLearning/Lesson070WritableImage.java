package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/16 23:59
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson070WritableImage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WritableImage wi = new WritableImage(100, 100);
        PixelWriter pw = wi.getPixelWriter();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                pw.setColor(i, j, Color.valueOf("#FFD700"));
            }
        }

        for (int i = 0; i < 100; i++) {
            pw.setColor(i, i, Color.valueOf("#FF0000"));
        }

        System.out.println("--------------------");

        Image image = new Image("file:./GameDemo/src/main/resources/icon/icon.jpeg");
        // 宽高不能大于原图大小
        WritableImage wi2 = new WritableImage(image.getPixelReader(), 120, 120);

        PixelWriter pw2 = wi2.getPixelWriter();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                pw2.setColor(i, j, Color.valueOf("#FFD700"));
            }
        }

        System.out.println("--------------------");

        Image image2 = new Image("file:./GameDemo/src/main/resources/icon/icon.jpeg", 600, 600, false, true);
        Image data = new Image("file:./GameDemo/src/main/resources/icon/icon.jpeg", 200, 200, false, true);
        // 宽高不能大于原图大小
        WritableImage wi3 = new WritableImage(image2.getPixelReader(), 0, 0, 600, 600);

        PixelWriter pw3 = wi3.getPixelWriter();

        PixelReader pr = data.getPixelReader();

        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                int x = pr.getArgb(i, j);
                pw3.setArgb(i, j, x);
            }
        }

        System.out.println("--------------------");

        Image image3 = new Image("file:./GameDemo/src/main/resources/icon/icon.jpeg", 600, 600, false, true);
        Image data2 = new Image("file:./GameDemo/src/main/resources/icon/icon.jpeg", 600, 600, false, true);
        // 宽高不能大于原图大小
        WritableImage wi4 = new WritableImage(image3.getPixelReader(), 0, 0, 600, 600);

        PixelWriter pw4 = wi4.getPixelWriter();

        PixelReader pr2 = data2.getPixelReader();

//        WritablePixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
//        int[] in = new int[600 / 2 * 600];
//        pr2.getPixels(600/2, 0, 600/2, 600, pixelFormat, in, 0, 600/2);
//        pw4.setPixels(0, 0, 600/2, 600, pixelFormat, in, 0, 600/2);
        pw4.setPixels(0, 0, 600 / 2, 600, pr2, 600 / 2, 0);

        System.out.println("--------------------");

        boolean savePic = false;

        /// 保存图片
        if (savePic) {
            BufferedImage bi = SwingFXUtils.fromFXImage(wi4, null);
            // jpg的话颜色会有问题
            ImageIO.write(bi, "png", new File("D:/newimg.png"));
        }

        System.out.println("--------------------");

        ImageView iv = new ImageView(wi4);

        AnchorPane an = new AnchorPane();
        an.getChildren().add(iv);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        // 还可以把iv换成其他组件进行截图，例如Button、HBox之类的。但这些组件必须在show之后截图
        if (savePic) {
            WritableImage wbi = iv.snapshot(null, null);
            BufferedImage buff = SwingFXUtils.fromFXImage(wbi, null);
            ImageIO.write(buff, "png", new File("D:/newimg.png"));
        }
    }
}

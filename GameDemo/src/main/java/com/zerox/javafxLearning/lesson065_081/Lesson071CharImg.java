package com.zerox.javafxLearning.lesson065_081;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/17 22:55
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson071CharImg extends Application {
    String[] strs = new String[]{"@", "&", "#", "*", ".", " "};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = "file:./GameDemo/src/main/resources/icon/icon.jpeg";
        Image image = new Image(path);

        PixelReader pr = image.getPixelReader();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = pr.getColor(j, i);
                int value = (int) (color.getRed() * 255);
                String data = getString(value);
                sb.append(data);
            }
            sb.append("\r\n");
        }
        write(sb);

        ImageView iv = new ImageView(image);

        AnchorPane an = new AnchorPane();
        an.getChildren().add(iv);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    private void write(StringBuilder sb) {
        File file = new File("D:/strimg.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getString(int value) {
        return strs[value * 6 / 256];
    }
}

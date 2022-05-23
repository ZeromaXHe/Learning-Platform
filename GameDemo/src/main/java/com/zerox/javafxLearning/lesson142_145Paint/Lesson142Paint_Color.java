package com.zerox.javafxLearning.lesson142_145Paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 16:12
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson142Paint_Color extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Rectangle rec = new Rectangle(100, 100);
                rec.setFill(Paint.valueOf("#EDEDED"));
                grid.add(rec, j, i);
            }
        }

        ArrayList<Color> list = new ArrayList<>();
        list.add(Color.valueOf("#EE6AA7"));
        list.add(Color.valueOf("0xEE6AA7"));
        list.add(Color.valueOf("#EE6AA788"));
        list.add(Color.CADETBLUE);
        list.add(Color.MEDIUMSEAGREEN);

        list.add(new Color(140.0 / 255, 39.0 / 255, 218.0 / 255, 1));
        list.add(new Color(140.0 / 255, 39.0 / 255, 218.0 / 255, 0.5));
        list.add(new Color(140.0 / 255, 39.0 / 255, 218.0 / 255, 0));
        list.add(Color.rgb(30, 175, 217));
        list.add(Color.hsb(180, 1, 1));

        list.add(Color.rgb(34, 212, 143)
                .deriveColor(-100, 1, 1, 1));
        list.add(Color.web("rgb(255,50%,50%)"));
        list.add(Color.gray(0.5, 1));
        list.add(Color.grayRgb(128, 1));
        list.add(Color.rgb(54, 105, 198, 1));

        list.add(Color.rgb(54, 105, 198, 1).brighter());
        list.add(Color.rgb(54, 105, 198, 1).darker());
        list.add(Color.rgb(54, 105, 198, 1).saturate());
        list.add(Color.rgb(54, 105, 198, 1).desaturate());
        list.add(Color.rgb(54, 105, 198, 1).grayscale());

        list.add(Color.rgb(54, 105, 198, 1).invert());

        Color color = Color.rgb(54, 105, 198, 1);
        System.out.println("color.getRed: "+color.getRed() * 255);
        System.out.println("color.getGreen: "+color.getGreen() * 255);
        System.out.println("color.getBlue: "+color.getBlue() * 255);
        System.out.println("color.getOpacity: "+color.getOpacity());
        System.out.println("color.getHue: "+color.getHue());
        System.out.println("color.getSaturation: "+color.getSaturation());
        System.out.println("color.getBrightness: "+color.getBrightness());
        System.out.println("color.isOpaque: "+color.isOpaque());

        for (int i = 0; i < list.size(); i++) {
            Rectangle rec = (Rectangle) grid.getChildren().get(i);
            rec.setFill(list.get(i));
        }

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(grid);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

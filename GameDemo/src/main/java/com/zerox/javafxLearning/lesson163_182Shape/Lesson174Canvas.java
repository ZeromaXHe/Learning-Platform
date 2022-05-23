package com.zerox.javafxLearning.lesson163_182Shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 15:48
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson174Canvas extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        // 保存画笔配置
        gc.save();
        gc.setLineWidth(4);
        gc.fillRect(100, 100, 100, 100);
        gc.strokeRect(200, 200, 100, 100);

        gc.setStroke(Color.CORAL);
        gc.strokeLine(400, 100, 400, 200);
        gc.strokeLine(400, 200, 500, 200);

        gc.setFill(Color.BLUEVIOLET);
        gc.fillPolygon(new double[]{100, 100, 300}, new double[]{300, 500, 400}, 3);

        gc.setFill(Color.VIOLET);
        gc.setFont(Font.font(30));
        gc.fillText("hello world", 100, 500);

        gc.setFont(Font.font(50));
        gc.setStroke(Color.CHARTREUSE);
        gc.setLineWidth(2);
        gc.strokeText("hello world", 100, 600);

        gc.rotate(25);
        gc.clearRect(250, 300, 50, 50);
        gc.rotate(-25);

        // 恢复画笔配置
        gc.restore();
        gc.beginPath();
        gc.moveTo(400, 100);
        gc.appendSVGPath("c50,-50,150,50,200,0");
        gc.closePath();
        gc.setLineJoin(StrokeLineJoin.ROUND);
        gc.stroke();
        gc.fill();

        gc.moveTo(400, 300);
        gc.quadraticCurveTo(500, 400, 700, 300);
        gc.stroke();
        gc.fill();

        gc.drawImage(new Image("pic/picTest.jpeg"), 0, 0, 500, 500, 400, 400, 300, 300);

        PixelWriter pw = gc.getPixelWriter();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                pw.setColor(i, j, Color.GREENYELLOW);
            }
        }

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(canvas);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

package com.zerox.snake.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class SnakeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AtomicBoolean circle = new AtomicBoolean(true);
        primaryStage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawGraphics(gc);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                circle.set(!circle.get());
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        new GameLoop(circle, gc, 100).start();
    }

    class GameLoop extends AnimationTimer {
        private AtomicBoolean circle;
        private GraphicsContext gc;
        private long deltaMilliSecond;
        private long milliSecond;

        public GameLoop(AtomicBoolean circle, GraphicsContext gc, long deltaMilliSecond) {
            this.circle = circle;
            this.gc = gc;
            this.deltaMilliSecond = deltaMilliSecond;
            this.milliSecond = System.currentTimeMillis();
        }

        @Override
        public void handle(long now) {
            if (circle.get() && System.currentTimeMillis() - milliSecond > deltaMilliSecond) {
                drawGraphics(gc);
                milliSecond = System.currentTimeMillis();
            }
        }
    }

    private void drawGraphics(GraphicsContext gc) {
        gc.setFill(new Color(Math.random(), Math.random(), Math.random(), Math.random()));
        gc.fillRect(Math.random() * 800, Math.random() * 600, 20 + Math.random() * 100, 20 + Math.random() * 100);
    }
}

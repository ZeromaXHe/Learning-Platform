package com.zerox.snake.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SnakeApplication extends Application {
    private final static int SQUARE_WIDTH = 20;
    private final static int AREA_WIDTH = 40;
    private final static int AREA_HEIGHT = 30;
    private final static int TURN_MILLISECOND = 100;

    private AtomicInteger direction = new AtomicInteger(4);
    private AtomicBoolean gameEnd = new AtomicBoolean(false);
    private int snakeHeadX = 20;
    private int snakeHeadY = 15;
    private int fruitX = 10;
    private int fruitY = 20;
    private Random random = new Random();
    private int[][] gameArea = new int[AREA_WIDTH][AREA_HEIGHT];
    private LinkedList<int[]> snake = new LinkedList<>();
    private int youLoseCount = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initData();

        primaryStage.setTitle("Snake Game");
        Group root = new Group();
        Canvas canvas = new Canvas(SQUARE_WIDTH * AREA_WIDTH, SQUARE_WIDTH * AREA_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        initGraphics(gc);

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                if (direction.get() != 3) {
                    direction.set(1);
                }
            } else if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                if (direction.get() != 4) {
                    direction.set(2);
                }
            } else if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                if (direction.get() != 1) {
                    direction.set(3);
                }
            } else if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                if (direction.get() != 2) {
                    direction.set(4);
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        new GameLoop(gc, TURN_MILLISECOND).start();
    }

    private void initData() {
        snake.addFirst(new int[]{snakeHeadX, snakeHeadY});
        gameArea[snakeHeadX][snakeHeadY] = 1;
        refreshFruitData();
    }

    class GameLoop extends AnimationTimer {
        private GraphicsContext gc;
        private long deltaMilliSecond;
        private long milliSecond;

        public GameLoop(GraphicsContext gc, long deltaMilliSecond) {
            this.gc = gc;
            this.deltaMilliSecond = deltaMilliSecond;
            this.milliSecond = System.currentTimeMillis();
        }

        @Override
        public void handle(long now) {
            if (gameEnd.get()) {
                printGameOver(gc);
            } else if (System.currentTimeMillis() - milliSecond > deltaMilliSecond) {
                updateGraphics(gc);
                milliSecond = System.currentTimeMillis();
            }
        }

    }

    private void printGameOver(GraphicsContext gc) {
        if (youLoseCount < 100) {
            gc.setFill(new Color(Math.random(), Math.random(), Math.random(), Math.random()));
            gc.setFont(new Font(10 + random.nextInt(40)));
            gc.fillText("YOU LOSE", random.nextInt(SQUARE_WIDTH * AREA_WIDTH), random.nextInt(SQUARE_WIDTH * AREA_HEIGHT));
            youLoseCount++;
        }
    }

    private void initGraphics(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        fillAreaSquare(gc, snakeHeadX, snakeHeadY);
        gc.setFill(Color.GRAY);
        for (int i = 0; i <= 800; i += SQUARE_WIDTH) {
            gc.strokeLine(i, 0, i, 600);
            if (i <= 600) {
                gc.strokeLine(0, i, 800, i);
            }
        }
        gc.setFill(Color.ORANGE);
        fillAreaSquare(gc, fruitX, fruitY);
    }

    private void updateGraphics(GraphicsContext gc) {
        int[] first = snake.peekFirst();
        int nextX = first[0];
        int nextY = first[1];
        switch (direction.get()) {
            case 1:
                nextY -= 1;
                if (nextY < 0) {
                    gameOver(gc);
                    return;
                }
                break;
            case 2:
                nextX -= 1;
                if (nextX < 0) {
                    gameOver(gc);
                    return;
                }
                break;
            case 3:
                nextY += 1;
                if (nextY >= AREA_HEIGHT) {
                    gameOver(gc);
                    return;
                }
                break;
            case 4:
                nextX += 1;
                if (nextX >= AREA_WIDTH) {
                    gameOver(gc);
                    return;
                }
                break;
            default:
                throw new IllegalArgumentException("direction must be 1~4");
        }
        if (nextX == fruitX && nextY == fruitY) {
            gameArea[nextX][nextY] = 0;
            refreshFruitData();
            gc.setFill(Color.ORANGE);
            fillAreaSquare(gc, fruitX, fruitY);
        } else {
            int[] last = snake.pollLast();
            clearAreaSquare(gc, last[0], last[1]);
            gameArea[last[0]][last[1]] = 0;
        }
        if (gameArea[nextX][nextY] != 0) {
            gameOver(gc);
            return;
        }
        snake.addFirst(new int[]{nextX, nextY});
        gameArea[nextX][nextY] = 1;
        gc.setFill(Color.GREEN);
        fillAreaSquare(gc, nextX, nextY);
    }

    private void refreshFruitData() {
        do {
            fruitX = random.nextInt(AREA_WIDTH);
            fruitY = random.nextInt(AREA_HEIGHT);
        } while (gameArea[fruitX][fruitY] != 0);
        gameArea[fruitX][fruitY] = -1;
    }

    private void gameOver(GraphicsContext gc) {
        gameEnd.set(true);
        gc.clearRect(0, 0, SQUARE_WIDTH * AREA_WIDTH, SQUARE_WIDTH * AREA_HEIGHT);
    }

    private void fillAreaSquare(GraphicsContext gc, int x, int y) {
        gc.fillRect(SQUARE_WIDTH * x, SQUARE_WIDTH * y, SQUARE_WIDTH, SQUARE_WIDTH);
    }

    private void clearAreaSquare(GraphicsContext gc, int x, int y) {
        gc.clearRect(SQUARE_WIDTH * x, SQUARE_WIDTH * y, SQUARE_WIDTH, SQUARE_WIDTH);
        gc.setFill(Color.GRAY);
        gc.strokeLine(SQUARE_WIDTH * x, SQUARE_WIDTH * y, SQUARE_WIDTH * (x + 1), SQUARE_WIDTH * y);
        gc.strokeLine(SQUARE_WIDTH * x, SQUARE_WIDTH * y + SQUARE_WIDTH, SQUARE_WIDTH * (x + 1), SQUARE_WIDTH * (y + 1));
        gc.strokeLine(SQUARE_WIDTH * x, SQUARE_WIDTH * y, SQUARE_WIDTH * x, SQUARE_WIDTH * (y + 1));
        gc.strokeLine(SQUARE_WIDTH * (x + 1), SQUARE_WIDTH * y, SQUARE_WIDTH * (x + 1), SQUARE_WIDTH * (y + 1));
    }
}

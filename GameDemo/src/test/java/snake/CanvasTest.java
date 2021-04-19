package snake;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CanvasTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CanvasTest");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawGraphics(gc);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                System.out.println("pressed enter");
                drawGraphics(gc);
                primaryStage.show();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawGraphics(GraphicsContext gc) {
        gc.setFill(new Color(Math.random(), Math.random(), Math.random(), Math.random()));
        gc.fillRect(Math.random() * 800, Math.random() * 600, 20+Math.random() * 400, 20+Math.random() * 300);
    }
}

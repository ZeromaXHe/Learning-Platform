package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 20:59
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson019StackPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        Button b4 = new Button("button4");
        Button b5 = new Button("button5");

        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-color: #EE6AA7");

        stack.setPadding(new Insets(10));
        stack.setAlignment(Pos.BOTTOM_LEFT);
        StackPane.setMargin(b1, new Insets(100));
        StackPane.setAlignment(b1, Pos.CENTER);
        StackPane.setAlignment(b2, Pos.CENTER);

        stack.getChildren().addAll(b1, b2, b3, b4, b5);

        stack.getChildren().forEach(t -> {
            Button bu = (Button) t;
            System.out.println(bu.getText());
        });

        Scene scene = new Scene(stack);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GridPane test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

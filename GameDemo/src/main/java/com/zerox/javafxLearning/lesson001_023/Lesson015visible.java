package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 13:32
 * @Description: setManaged(false); setVisible(false); setOpacity(0); 的区别
 * @Modified By: ZeromaXHe
 */
public class Lesson015visible extends Application {
    static boolean isManaged = false;
    static boolean isVisible = false;
    static double opacityValue = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        Button b4 = new Button("button4");

        Button b5 = new Button("b3.setManaged(false);");
        Button b6 = new Button("b3.setVisible(false);");
        Button b7 = new Button("b3.setOpacity(0);");

//        b3.setManaged(false);
//        b3.setVisible(false);
//        b3.setOpacity(0);

        AnchorPane ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #ffffff");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(b1, b2, b3, b4);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(b5, b6, b7);

        AnchorPane.setTopAnchor(vBox, 100.0);
        AnchorPane.setLeftAnchor(vBox, 20.0);

        ap.getChildren().add(hBox);
        ap.getChildren().add(vBox);

        Scene scene = new Scene(ap);
        primaryStage.setScene(scene);
        primaryStage.setTitle("setManaged etc test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        b5.setOnAction(event -> {
            b3.setManaged(isManaged);
            new Print(hBox);
            isManaged = !isManaged;
            b5.setText("b3.setManaged(" + isManaged + ")");
        });

        b6.setOnAction(event -> {
            b3.setVisible(isVisible);
            new Print(hBox);
            isVisible = !isVisible;
            b6.setText("b3.setVisible(" + isVisible + ")");
        });

        b7.setOnAction(event -> {
            b3.setOpacity(opacityValue);
            new Print(hBox);
            if (opacityValue == 0) {
                opacityValue = 1;
            } else {
                opacityValue = 0;
            }
            b7.setText("b3.setOpacity(" + opacityValue + ")");
        });
    }

    class Print {
        Print(HBox hBox) {
            System.out.println("当前HBox里子组件数量=" + hBox.getChildren().size());
        }
    }
}

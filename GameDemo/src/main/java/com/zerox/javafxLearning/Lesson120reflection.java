package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.lang.reflect.Method;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 18:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson120reflection extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Node node = new Button("button");

        String name = node.getClass().getName();
        System.out.println(name);

//        Class<?> aClass = Class.forName(name);
//        Node node2 = (Node) aClass.getConstructor().newInstance();

//        Node node2 = Button.class.getConstructor(new Class[] {String.class}).newInstance("button2");

        Node node2 = node.getClass().getConstructor(String.class).newInstance("button2");
        Method method = node.getClass().getMethod("setText", String.class);
        method.invoke(node2, "BUTTON_2");

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(node, node2);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

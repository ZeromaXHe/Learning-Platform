package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/17 23:41
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson073_074MouseEvent extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(20);

        Button b1 = new Button("button1");
        Button b2 = new Button("button2");

        hBox.getChildren().addAll(b1, b2);

        b1.setOnMouseClicked(event -> {
            System.out.println("x = " + event.getX());
            System.out.println("y = " + event.getY());
            System.out.println("sceneX = " + event.getSceneX());
            System.out.println("sceneY = " + event.getSceneY());
            System.out.println("screenX = " + event.getScreenX());
            System.out.println("screenY = " + event.getScreenY());

            System.out.println("source: " + event.getSource());
            // 点击button的文本，target会是TEXT
            System.out.println("target: " + event.getTarget());
            System.out.println("eventType: " + event.getEventType());
            System.out.println("button: " + event.getButton());
            System.out.println("ctrlDown: " + event.isControlDown());
            System.out.println("clickCount: " + event.getClickCount());
            System.out.println("secondaryButtonDown: " + event.isSecondaryButtonDown());
        });

        b1.setOnMousePressed(event -> System.out.println("鼠标按下"));
        b1.setOnMouseReleased(event -> System.out.println("鼠标释放"));
        b1.setOnMouseEntered(event -> System.out.println("鼠标进入"));
        b1.setOnMouseExited(event -> System.out.println("鼠标退出"));

        b2.setOnMouseMoved(event -> System.out.println("鼠标移动"));
        b2.setOnMouseDragged(event -> System.out.println("鼠标拖拽"));
        b2.setOnMouseDragOver(event -> System.out.println("dragOver"));
        b2.setOnDragDetected(event -> {
            // 有这个代码，dragOver、dragEntered、dragExited、dragReleased这些才生效
            b2.startFullDrag();
            System.out.println("dragDetected");
        });
        // 还有个setOnDragEntered，不知道是怎么拖拽
        b2.setOnMouseDragEntered(event -> System.out.println("鼠标拖拽进入"));
        b2.setOnMouseDragExited(event -> System.out.println("鼠标拖拽退出"));
        b2.setOnMouseDragReleased(event -> {
            System.out.println("target:" + event.getTarget());
            System.out.println("source:" + event.getSource());
            System.out.println("gestureSource:" + event.getGestureSource());
            System.out.println("鼠标拖拽释放");
        });

        AnchorPane an = new AnchorPane();
        an.getChildren().add(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

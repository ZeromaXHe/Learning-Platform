package com.zerox.javafxLearning.lesson065_081;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: zhuxi
 * @Time: 2022/1/7 14:23
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson080DragEvent extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #999999");
        hBox.setPrefWidth(300);
        hBox.setPrefHeight(300);

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(300);
        hBox.getChildren().add(iv);

        TextField tf = new TextField();

        Label label = new Label("hello world");

        an.getChildren().addAll(label, tf, hBox);
        AnchorPane.setTopAnchor(label, 100.0);
        AnchorPane.setLeftAnchor(label, 10.0);
        AnchorPane.setTopAnchor(tf, 100.0);
        AnchorPane.setLeftAnchor(tf, 200.0);
        AnchorPane.setTopAnchor(hBox, 200.0);
        AnchorPane.setLeftAnchor(hBox, 10.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        hBox.setOnDragEntered(event -> {
            hBox.setBorder(new Border(new BorderStroke(Paint.valueOf("#333333"),
                    BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
            System.out.println("hBox.setOnDragEntered: " + event.getTransferMode());
        });

        hBox.setOnDragExited(event -> {
//            hBox.setBorder(new Border(new BorderStroke(Paint.valueOf("#33333300"),
//                    BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
            hBox.setBorder(null);
        });

        hBox.setOnDragOver(event -> event.acceptTransferModes(TransferMode.COPY));

        hBox.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                // 本地图片
                Image image = null;
                try {
                    image = new Image(new FileInputStream(db.getFiles().get(0)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /// 还是获取不到
//                Object obj = db.getContent(DataFormat.IMAGE);
//                System.out.println(obj);
//                Image image = (Image) obj;
                iv.setImage(image);
            } else if (db.hasUrl()) {
                // 网络图片
                Image im = new Image(db.getUrl());
                iv.setImage(im);
            }
        });

        label.setOnDragDetected(event -> {
            Dragboard db = label.startDragAndDrop(TransferMode.MOVE);

            Text text = new Text(label.getText());
            WritableImage wi = new WritableImage((int) label.getWidth(), (int) label.getHeight());
            text.snapshot(new SnapshotParameters(), wi);
            db.setDragView(wi, 30, 10);

            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText());
            db.setContent(content);
        });

        tf.setOnDragOver(event -> event.acceptTransferModes(TransferMode.MOVE));

        tf.setOnDragDropped(event -> {
            tf.setText(event.getDragboard().getString());
            // 让 drag 动作完成，这样 dragDone 的监听才生效
            event.setDropCompleted(true);
        });

        label.setOnDragDone(event -> {
            if (TransferMode.MOVE.equals(event.getAcceptedTransferMode())) {
                label.setText("");
            }
        });
    }
}

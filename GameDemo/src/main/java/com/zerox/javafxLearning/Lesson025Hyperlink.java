package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/11 22:20
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson025Hyperlink extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox box = new VBox();
        Hyperlink link = new Hyperlink("www.baidu.com", new Button("百度"));
        link.setOnAction(event -> {
            HostServices host = Lesson025Hyperlink.this.getHostServices();
            host.showDocument(link.getText());
        });
        box.getChildren().add(link);

        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hyperlink");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

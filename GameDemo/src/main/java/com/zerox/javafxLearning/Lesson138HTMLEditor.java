package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 15:33
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson138HTMLEditor extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HTMLEditor html = new HTMLEditor();
        html.setPrefSize(600, 400);

        Button bu1 = new Button("button1");
        Button bu2 = new Button("button2");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(bu1, bu2);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox, html);

        AnchorPane.setTopAnchor(html, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu1.setOnAction(event -> html.setHtmlText("<font size=\"20\" color=\"blue\">Hello world.</font>"));
        bu2.setOnAction(event -> System.out.println(html.getHtmlText()));
    }
}

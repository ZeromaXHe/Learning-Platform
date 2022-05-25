package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 17:52
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson119CSS extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
//        Application.setUserAgentStylesheet(STYLESHEET_MODENA);

        FXMLLoader fl = new FXMLLoader();
        URL url = fl.getClassLoader().getResource("fxml/Lesson119CSS.fxml");
        fl.setLocation(url);

        VBox vBox = fl.load();

        AnchorPane an = getView();
        an.setId("root");
        // JavaFX css 优先级顺序： 默认 caspian.css < API设置 < 用户的 Scene css < 用户的 Parent css < .setStyle()
//        an.setStyle("-fx-background-color: linear-gradient(to bottom right, darkcyan, darkgoldenrod);" +
//                "-fx-border-color: #CD0000;" +
//                "-fx-border-width: 10 5 0 20;");
        an.getChildren().addAll(vBox);
        AnchorPane.setTopAnchor(vBox, 50.0);
        AnchorPane.setLeftAnchor(vBox, 300.0);

        Scene scene = new Scene(an);
        URL cssUrl = this.getClass().getClassLoader().getResource("css/Lesson119CSS.css");
        scene.getStylesheets().add(cssUrl.toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    private AnchorPane getView() {
        Button bu = new Button("button");
        bu.setId("button");
        Label label = new Label("label");
        label.getStyleClass().add("my_css");
        TextField tf = new TextField("textField");

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(bu, label, tf);

        AnchorPane.setTopAnchor(bu, 50.0);
        AnchorPane.setTopAnchor(label, 100.0);
        AnchorPane.setTopAnchor(tf, 150.0);

        AnchorPane.setLeftAnchor(bu, 50.0);
        AnchorPane.setLeftAnchor(label, 50.0);
        AnchorPane.setLeftAnchor(tf, 50.0);

        return an;
    }
}

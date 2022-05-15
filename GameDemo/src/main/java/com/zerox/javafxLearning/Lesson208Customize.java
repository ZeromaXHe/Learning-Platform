package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/15 16:15
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson208Customize extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MyNode view = new MyNode();
        for (int i = 0; i < 10; i++) {
            view.getChildren().add(new Button("button" + i));
        }

        Node view1 = getView();

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(view, view1);

        AnchorPane.setTopAnchor(view, 100.0);
        AnchorPane.setLeftAnchor(view, 100.0);
        AnchorPane.setTopAnchor(view1, 100.0);
        AnchorPane.setLeftAnchor(view1, 200.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    public Node getView() {
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        VBox vBox = new VBox(10);
        vBox.setStyle("-fx-background-color: #FF82AB");
        vBox.getChildren().addAll(b1, b2);
        return vBox;
    }

    class MyNode extends VBox {
        @Override
        protected void layoutChildren() {
            super.layoutChildren();

            for (int i = 0; i < this.getChildren().size(); i++) {
                Button bu = (Button) this.getChildren().get(i);
                if (i % 2 == 0) {
                    bu.setTranslateX(20);
                } else {
                    bu.setTranslateX(-20);
                }
                bu.setOnAction(event -> System.out.println(bu.getText()));
            }
        }
    }

}

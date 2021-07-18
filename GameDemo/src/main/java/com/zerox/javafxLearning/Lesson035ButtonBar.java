package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/18 15:56
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson035ButtonBar extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        ButtonBar buttonBar = new ButtonBar();

        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");
        b1.setPrefWidth(200);

        ButtonBar.setButtonData(b1, ButtonBar.ButtonData.APPLY);
        ButtonBar.setButtonData(b2, ButtonBar.ButtonData.FINISH);
        ButtonBar.setButtonData(b3, ButtonBar.ButtonData.NO);

        buttonBar.getButtons().addAll(b1, b2, b3);
        buttonBar.setButtonOrder(ButtonBar.BUTTON_ORDER_LINUX);
        ButtonBar.setButtonUniformSize(b1, false);
        an.getChildren().add(buttonBar);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        EventHandler<ActionEvent> actionEventEventHandler = event -> {
            Button btn = (Button) event.getSource();
            if (ButtonBar.getButtonData(btn) == ButtonBar.ButtonData.APPLY) {
                System.out.println("APPLY: " + btn.getText());
            } else if (ButtonBar.getButtonData(btn) == ButtonBar.ButtonData.NO) {
                System.out.println("NO: " + btn.getText());
            } else if (ButtonBar.getButtonData(btn) == ButtonBar.ButtonData.FINISH) {
                System.out.println("FINISH: " + btn.getText());
            }
        };
        b1.setOnAction(actionEventEventHandler);
        b2.setOnAction(actionEventEventHandler);
        b3.setOnAction(actionEventEventHandler);
    }
}

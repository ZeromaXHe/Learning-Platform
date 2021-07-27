package com.zerox.javafxLearning.Lesson024_050;

import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/17 17:51
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson029TitledPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Accordion acc = new Accordion();

        TitledPane tp1 = new TitledPane("TitledPane1", new Button("button1"));
        tp1.setAnimated(false);
        tp1.setExpanded(false);

        TitledPane tp2 = new TitledPane();
        tp2.setText("TitledPane2");
        tp2.setContent(new Button("button2"));
        AnchorPane.setTopAnchor(tp2, 100.0);
//        tp2.setCollapsible(false);

        TitledPane tp3 = new TitledPane();
        tp3.setText("TitledPane3");
        HBox box = new HBox();
        box.setStyle("-fx-background-color: #FFB5C5");
        box.getChildren().addAll(new Button("button3"), new Button("button4"), new Button("button5"));
        tp3.setContent(box);
        AnchorPane.setTopAnchor(tp3, 200.0);
        tp3.setGraphic(new Button("image"));
        tp3.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        acc.getPanes().addAll(tp1, tp2, tp3);
        an.getChildren().addAll(acc);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        tp1.expandedProperty().addListener((o, ov, nv) -> System.out.println(nv));
        acc.expandedPaneProperty().addListener((o, ov, nv) -> {
            if (nv == null) {
                System.out.println(ov.getText() + "折叠");
            } else {
                System.out.println(nv.getText() + "展开");
            }
        });
    }
}

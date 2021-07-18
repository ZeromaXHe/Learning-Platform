package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/18 10:42
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson031RadioButton_CheckBox extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");


        HBox box = new HBox(10);
        ToggleGroup tg = new ToggleGroup();
        RadioButton r1 = new RadioButton("r1");
        RadioButton r2 = new RadioButton("r2");
        RadioButton r3 = new RadioButton("r3");
        RadioButton r4 = new RadioButton("r4");

//        r1.setToggleGroup(tg);
//        r2.setToggleGroup(tg);
//        r3.setToggleGroup(tg);
//        r4.setToggleGroup(tg);
        tg.getToggles().addAll(r1, r2, r3, r4);

//        r2.setSelected(true);
        /// 必须放在设置toggleGroup的后面，否则无效
        tg.selectToggle(r3);

        box.getChildren().addAll(r1, r2, r3, r4);

        AnchorPane.setTopAnchor(box, 100.0);
        AnchorPane.setLeftAnchor(box, 100.0);
        an.getChildren().add(box);

        HBox box2 = new HBox(10);
        CheckBox c1 = new CheckBox("c1");
        CheckBox c2 = new CheckBox("c2");
        CheckBox c3 = new CheckBox("c3");
        CheckBox c4 = new CheckBox("c4");
        c1.setSelected(true);
        c2.setIndeterminate(true);
        c3.setAllowIndeterminate(true);
        box2.getChildren().addAll(c1, c2, c3, c4);

        AnchorPane.setTopAnchor(box2, 200.0);
        AnchorPane.setLeftAnchor(box2, 100.0);
        an.getChildren().add(box2);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        r1.selectedProperty().addListener((o, ov, nv) -> System.out.println(nv));

        tg.selectedToggleProperty().addListener((o, ov, nv) -> {
            RadioButton r = (RadioButton) nv;
            System.out.println(r.getText() + " - " + r.isSelected());
        });

        c1.selectedProperty().addListener((o, ov, nv) -> System.out.println(nv));

        an.setOnMouseClicked(event -> {
            box2.getChildren().forEach(o -> {
                CheckBox c = (CheckBox) o;
                System.out.println(c.getText() + "的选择状态是: " + c.isSelected() + " 不确定状态是: " + c.isIndeterminate());
            });
        });
    }
}

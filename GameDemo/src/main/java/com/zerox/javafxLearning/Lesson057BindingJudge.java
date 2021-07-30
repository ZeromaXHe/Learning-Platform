package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/7/30 9:53
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson057BindingJudge extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static void test() {
        SimpleIntegerProperty a = new SimpleIntegerProperty(10);
        SimpleIntegerProperty b = new SimpleIntegerProperty(10);

        // 8.0
        DoubleBinding n = a.add(10).subtract(10).divide(5.0).multiply(4);
        System.out.println(n.get());

        System.out.println(a.greaterThan(b).get());
        System.out.println(a.lessThan(b).get());
        System.out.println(a.isEqualTo(b).get());
        System.out.println(a.isEqualTo(9, 1).get());
        System.out.println(a.isNotEqualTo(b).get());
        System.out.println(a.isNotEqualTo(9, 1).get());
        System.out.println(a.greaterThanOrEqualTo(b).get());
        System.out.println(a.lessThanOrEqualTo(b).get());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        test();

        SimpleIntegerProperty a = new SimpleIntegerProperty(0);
        SimpleIntegerProperty b = new SimpleIntegerProperty(0);

        BooleanBinding boo = a.greaterThan(b);

        AnchorPane an = new AnchorPane();

        TextField t1 = new TextField();
        TextField t2 = new TextField();

        Button bu = new Button("判断");

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(t1, t2, bu);

        an.getChildren().add(hBox);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu.setOnAction(event -> {
            int valueA = Integer.parseInt(t1.getText());
            int valueB = Integer.parseInt(t2.getText());
            a.set(valueA);
            b.set(valueB);

            if(boo.get()){
                an.setStyle("-fx-background-color: #007777");
            }else{
                an.setStyle("-fx-background-color: #990099");
            }
        });
    }
}

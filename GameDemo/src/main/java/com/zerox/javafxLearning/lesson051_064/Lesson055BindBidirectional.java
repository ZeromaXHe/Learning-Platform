package com.zerox.javafxLearning.lesson051_064;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/28 22:45
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson055BindBidirectional extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static void test() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(5);

        // 单向绑定
        x.bind(y);

        // java.lang.RuntimeException: A bound value cannot be set.
//        x.set(8);
        y.set(10);

        System.out.println(x.isBound());
        System.out.println(y.isBound());
        System.out.println(x.get());
        System.out.println(y.get());

        x.unbind();
        x.set(8);
        y.set(100);

        System.out.println(x.isBound());
        System.out.println(y.isBound());
        System.out.println(x.get());
        System.out.println(y.get());

        System.out.println("------------------");

        SimpleIntegerProperty a = new SimpleIntegerProperty(3);
        SimpleIntegerProperty b = new SimpleIntegerProperty(6);

        // 双向绑定
        a.bindBidirectional(b);

        a.set(100);
        b.set(200);

        System.out.println();

        System.out.println(a.isBound());
        System.out.println(b.isBound());
        System.out.println(a.get());
        System.out.println(b.get());

        a.unbindBidirectional(b);
        // 都可以解绑
//        b.unbindBidirectional(a);

        a.set(30);
        b.set(40);

        System.out.println(a.isBound());
        System.out.println(b.isBound());
        System.out.println(a.get());
        System.out.println(b.get());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        test();

        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        Button bu = new Button("button");

        TextField t1 = new TextField();
        TextField t2 = new TextField();

        Slider slider = new Slider(0, 500, 0);
        slider.setPrefWidth(500);
        Button bu2 = new Button("button2");

        AnchorPane.setTopAnchor(t1, 100.0);
        AnchorPane.setTopAnchor(t2, 100.0);
        AnchorPane.setLeftAnchor(t1, 10.0);
        AnchorPane.setLeftAnchor(t2, 200.0);

        AnchorPane.setTopAnchor(slider, 200.0);
        AnchorPane.setTopAnchor(bu2, 150.0);
        an.getChildren().addAll(bu, t1, t2, slider, bu2);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        /// 原来的实现方式
//        bu.setPrefWidth(an.getWidth());
//
//        an.widthProperty().addListener((o, ov,nv)->{
//            bu.setPrefWidth(nv.doubleValue());
//        });

        bu.prefWidthProperty().bind(an.widthProperty());

        t1.textProperty().bindBidirectional(t2.textProperty(), new StringConverter<String>() {
            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                if (string.contains("5")) {
                    return string.replace("5", "五");
                }
                return string;
            }
        });

        // java.lang.RuntimeException: Button.layoutX : A bound value cannot be set.
//        bu2.layoutXProperty().bind(slider.valueProperty());
        bu2.translateXProperty().bind(slider.valueProperty());
    }
}

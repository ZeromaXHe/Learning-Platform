package com.zerox.javafxLearning.lesson051_064;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/28 23:17
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson056BindingCalculation extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static void test() {
        SimpleIntegerProperty a = new SimpleIntegerProperty(1);
        SimpleIntegerProperty b = new SimpleIntegerProperty(1);

        IntegerBinding ib = a.add(6);
        System.out.println("a + 6 = " + ib.get());
        System.out.println("a = " + a.get());

        NumberBinding nb = a.add(b);
        System.out.println("a + b = " + nb.intValue());

        System.out.println("a - 1 = " + a.subtract(1).get());
        System.out.println("a / 2.0 = " + a.divide(2.0).get());
        System.out.println("a * 2 = " + a.multiply(2).get());

        System.out.println("-------------");

        a.set(10);
        b.set(8);
        System.out.println(ib.get());
        System.out.println(nb.intValue());

        System.out.println("-------------");

        SimpleIntegerProperty x = new SimpleIntegerProperty(-6);

        System.out.println(x.negate().get());

        SimpleBooleanProperty boo = new SimpleBooleanProperty(true);

        System.out.println(boo.not().get());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        Button bu = new Button("button");

        TextField t = new TextField();

        an.getChildren().addAll(bu, t);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        SimpleIntegerProperty x = new SimpleIntegerProperty(2);
        DoubleBinding dbw = an.widthProperty().divide(x);
        DoubleBinding dbh = an.heightProperty().divide(x);

        bu.prefWidthProperty().bind(dbw);
        bu.prefHeightProperty().bind(dbh);

        t.textProperty().addListener((o, ov, nv) -> {
            try {
                int value = Integer.parseInt(nv);
                x.set(value);
            } catch (NumberFormatException e) {
                System.out.println("输入不是integer");
                x.set(2);
            }
        });
    }
}

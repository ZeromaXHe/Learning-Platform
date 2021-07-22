package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * @Author: zhuxi
 * @Time: 2021/7/22 11:11
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson043ColorPicker_DatePicker extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        ColorPicker cp = new ColorPicker(Color.valueOf("#ffff55"));
        cp.setPrefWidth(200);
        cp.valueProperty().addListener((o, ov, nv) -> {
            System.out.println("red = " + nv.getRed());
            System.out.println("green = " + nv.getGreen());
            System.out.println("blue = " + nv.getBlue());
            System.out.println("opacity = " + nv.getOpacity());
            String value = nv.toString().substring(2);
            System.out.println(value);
            an.setStyle("-fx-background-color: #" + value);
        });

        DatePicker date = new DatePicker(LocalDate.now());
        date.setEditable(false);

        date.setDayCellFactory(param -> {
            DateCell cell = new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
//                    this.setGraphic(new Label(item.now().toString()));
                    this.setGraphic(new Button("test"));
                }
            };
            return cell;
        });

        date.valueProperty().addListener((o, ov, nv) -> {
            int year = nv.getYear();
            int month = nv.getMonthValue();
            int day = nv.getDayOfMonth();
            int week = nv.getDayOfWeek().getValue();
            int number = nv.getDayOfYear();
            System.out.println(year + "年" + month + "月" + day + "日 星期" + week + " : 一年中的第" + number + "天");
        });

        AnchorPane.setTopAnchor(date, 100.0);
        an.getChildren().addAll(cp, date);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Picker");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @Author: zhuxi
 * @Time: 2022/5/20 14:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson130LineChart extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LineChart<Number, Number> lineChart1 = getLineChart1();
        LineChart<String, Number> lineChart2 = getLineChart2();

        Button bu = new Button("button");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(bu, lineChart1, lineChart2);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        // 加载 css
        // JavaFX css 优先级顺序： 默认 caspian.css < API设置 < 用户的 Scene css < 用户的 Parent css < .setStyle()
        URL url = this.getClass().getClassLoader().getResource("css/Lesson130LineChart.css");
        scene.getStylesheets().add(url.toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    private LineChart<Number, Number> getLineChart1() {
        NumberAxis x = new NumberAxis("x轴", 0, 100, 10);
        NumberAxis y = new NumberAxis("y轴", 0, 100, 10);
        XYChart.Series<Number, Number> xy1 = new XYChart.Series<>();
        xy1.setName("xy1");
        XYChart.Series<Number, Number> xy2 = new XYChart.Series<>();
        xy2.setName("xy2");
        XYChart.Data<Number, Number> d1 = new XYChart.Data<>(10, 10);
        XYChart.Data<Number, Number> d2 = new XYChart.Data<>(20, 22);
        XYChart.Data<Number, Number> d3 = new XYChart.Data<>(30, 28);
        XYChart.Data<Number, Number> d4 = new XYChart.Data<>(40, 40);

        XYChart.Data<Number, Number> d5 = new XYChart.Data<>(90, 10);
        XYChart.Data<Number, Number> d6 = new XYChart.Data<>(70, 33);
        XYChart.Data<Number, Number> d7 = new XYChart.Data<>(50, 47);
        XYChart.Data<Number, Number> d8 = new XYChart.Data<>(30, 70);

        xy1.getData().addAll(d1, d2, d3, d4);
        xy2.getData().addAll(d5, d6, d7, d8);

        LineChart<Number, Number> lineChart = new LineChart<>(x, y);
        lineChart.setId("linechart");
        lineChart.getData().addAll(xy1, xy2);
        lineChart.setPrefWidth(300);
        lineChart.setPrefHeight(300);

        lineChart.setCreateSymbols(true);
        return lineChart;
    }

    private LineChart<String, Number> getLineChart2() {
        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis("y轴", 0, 100, 10);
        XYChart.Series<String, Number> xy1 = new XYChart.Series<>();
        xy1.setName("xy1");
        XYChart.Series<String, Number> xy2 = new XYChart.Series<>();
        xy2.setName("xy2");
        XYChart.Data<String, Number> d1 = new XYChart.Data<>("一", 10);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("二", 22);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("三", 28);
        XYChart.Data<String, Number> d4 = new XYChart.Data<>("四", 40);

        XYChart.Data<String, Number> d5 = new XYChart.Data<>("一", 10);
        XYChart.Data<String, Number> d6 = new XYChart.Data<>("二", 33);
        XYChart.Data<String, Number> d7 = new XYChart.Data<>("三", 47);
        XYChart.Data<String, Number> d8 = new XYChart.Data<>("四", 70);

        xy1.getData().addAll(d1, d2, d3, d4);
        xy2.getData().addAll(d5, d6, d7, d8);

        LineChart<String, Number> lineChart = new LineChart<>(x, y);
        lineChart.getData().addAll(xy1, xy2);

        // 必须在加入 lineChart 后才有效？
        xy1.getData().forEach(t -> {
            Tooltip tip = new Tooltip(t.getXValue() + " - " + t.getYValue());
            Tooltip.install(t.getNode(), tip);
            t.getNode().setOnMouseClicked(event -> System.out.println(t.getXValue() + " - " + t.getYValue()));
        });

        lineChart.setPrefWidth(300);
        lineChart.setPrefHeight(300);

        lineChart.setCreateSymbols(true);
        return lineChart;
    }
}

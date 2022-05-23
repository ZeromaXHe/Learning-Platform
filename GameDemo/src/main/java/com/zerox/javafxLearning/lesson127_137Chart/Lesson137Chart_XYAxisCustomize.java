package com.zerox.javafxLearning.lesson127_137Chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 15:21
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson137Chart_XYAxisCustomize extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // caspian.css 自带网格背景颜色
//        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);

        LineChart<Number, Number> lineChart1 = getLineChart1();

        Button bu = new Button("button");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(bu, lineChart1);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        // 加载 css
        // JavaFX css 优先级顺序： 默认 caspian.css < API设置 < 用户的 Scene css < 用户的 Parent css < .setStyle()
        URL url = this.getClass().getClassLoader().getResource("css/Lesson137Chart_XYAxisCustomize.css");
        scene.getStylesheets().add(url.toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    private LineChart<Number, Number> getLineChart1() {
        NumberAxis x = new NumberAxis("x轴", -20, 80, 10);
        NumberAxis y = new NumberAxis("y轴", -20, 80, 10);

        y.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return object + "%";
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

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
        lineChart.setPrefWidth(600);
        lineChart.setPrefHeight(600);

        lineChart.setCreateSymbols(true);

        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setVerticalGridLinesVisible(false);

        lineChart.setVerticalZeroLineVisible(true);
        lineChart.setHorizontalZeroLineVisible(true);

        lineChart.setAlternativeRowFillVisible(true);
        lineChart.setAlternativeColumnFillVisible(true);
        return lineChart;
    }
}

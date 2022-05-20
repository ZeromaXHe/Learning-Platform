package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @Author: zhuxi
 * @Time: 2022/5/20 17:09
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson134AreaChart extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AreaChart<Number, Number> areaChart = getBubbleChart();

        Button bu = new Button("button");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(bu, areaChart);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox);

        Scene scene = new Scene(an);
        // 加载 css
        // JavaFX css 优先级顺序： 默认 caspian.css < API设置 < 用户的 Scene css < 用户的 Parent css < .setStyle()
        URL url = this.getClass().getClassLoader().getResource("css/Lesson134AreaChart.css");
        scene.getStylesheets().add(url.toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    private AreaChart<Number, Number> getBubbleChart() {
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

        AreaChart<Number, Number> areaChart = new AreaChart<>(x, y);
        areaChart.getData().addAll(xy1, xy2);
        areaChart.setTitle("areaChart");
        return areaChart;
    }
}

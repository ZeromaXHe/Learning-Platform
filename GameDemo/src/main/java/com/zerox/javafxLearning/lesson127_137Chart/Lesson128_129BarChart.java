package com.zerox.javafxLearning.lesson127_137Chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @Author: zhuxi
 * @Time: 2022/5/20 10:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson128_129BarChart extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BarChart barChart1 = getBarChart1();
        BarChart barChart2 = getBarChart2();
        BarChart barChart3 = getBarChart3();
        BarChart barChart4 = getBarChart4();

        HBox hBox = new HBox(0);
        hBox.getChildren().addAll(barChart1, barChart2, barChart3, barChart4);

        AnchorPane an = new AnchorPane();
        an.getChildren().add(hBox);

        Scene scene = new Scene(an);
        // 加载 css
        // JavaFX css 优先级顺序： 默认 caspian.css < API设置 < 用户的 Scene css < 用户的 Parent css < .setStyle()
        URL url = this.getClass().getClassLoader().getResource("css/Lesson129BarChart.css");
        scene.getStylesheets().add(url.toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    private BarChart getBarChart1() {
        CategoryAxis x = new CategoryAxis();
        x.setLabel("国家");
        x.setId("x");

        x.setStartMargin(10);
        x.setEndMargin(10);
        x.setGapStartAndEnd(true);

        x.setSide(Side.TOP);
        x.setTickLabelFill(Paint.valueOf("#FF55FF"));
        x.setTickLabelFont(new Font(16));
        x.setTickLabelGap(0);
        x.setTickLabelRotation(45);
        x.setTickLabelsVisible(true);
        x.setTickLength(100);
        x.setTickMarkVisible(false);

        NumberAxis y = new NumberAxis(0, 100, 10);
        y.setLabel("生产总值");

        ObservableList<XYChart.Series<String, Number>> seriesList = FXCollections.observableArrayList();
        XYChart.Series<String, Number> xy = new XYChart.Series<>();
        xy.setName("生产总值");
        ObservableList<XYChart.Data<String, Number>> dataList = FXCollections.observableArrayList();
        XYChart.Data<String, Number> d1 = new XYChart.Data<>("中国", 80);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("美国", 98);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("日本", 25);
        dataList.addAll(d1, d2, d3);
        xy.setData(dataList);
        seriesList.add(xy);

        BarChart<String, Number> barChart = new BarChart<>(x, y, seriesList);
        barChart.setPrefWidth(300);
        barChart.setPrefHeight(300);
        barChart.setTitle("第一种方式");

        barChart.setAnimated(true);
        barChart.setLegendSide(Side.TOP);
        barChart.setLegendVisible(false);
        barChart.setTitleSide(Side.BOTTOM);

        barChart.setBarGap(1);
        barChart.setCategoryGap(2);
        barChart.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        return barChart;
    }

    private BarChart getBarChart2() {
        CategoryAxis x = new CategoryAxis();
        x.setLabel("国家");
        NumberAxis y = new NumberAxis(0, 100, 10);
        y.setLabel("生产总值");

        XYChart.Series<String, Number> xy1 = new XYChart.Series<>();
        xy1.setName("中国");
        XYChart.Series<String, Number> xy2 = new XYChart.Series<>();
        xy2.setName("美国");
        XYChart.Series<String, Number> xy3 = new XYChart.Series<>();
        xy3.setName("日本");

        XYChart.Data<String, Number> d1 = new XYChart.Data<>("GDP", 80);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("GDP", 98);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("GDP", 25);
        XYChart.Data<String, Number> d21 = new XYChart.Data<>("GNP", 90);
        XYChart.Data<String, Number> d22 = new XYChart.Data<>("GNP", 88);
        XYChart.Data<String, Number> d23 = new XYChart.Data<>("GNP", 34);
        xy1.getData().addAll(d1, d21);
        xy2.getData().addAll(d2, d22);
        xy3.getData().addAll(d3, d23);

        BarChart<String, Number> barChart = new BarChart<>(x, y);
        barChart.getData().addAll(xy1, xy2, xy3);
        barChart.setPrefWidth(300);
        barChart.setPrefHeight(300);
        barChart.setTitle("第二种方式");
        return barChart;
    }

    private BarChart getBarChart3() {
        CategoryAxis x = new CategoryAxis();
        x.setLabel("国家");
        NumberAxis y = new NumberAxis(0, 100, 10);
        y.setLabel("生产总值");

        XYChart.Series<String, Number> xy1 = new XYChart.Series<>();
        xy1.setName("GDP");
        XYChart.Series<String, Number> xy2 = new XYChart.Series<>();
        xy2.setName("GNP");

        XYChart.Data<String, Number> d1 = new XYChart.Data<>("中国", 80);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("美国", 98);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("日本", 25);

        // 可以传递其他数据
        d1.setExtraValue(13000);

        XYChart.Data<String, Number> d21 = new XYChart.Data<>("中国", 90);
        XYChart.Data<String, Number> d22 = new XYChart.Data<>("美国", 88);
        XYChart.Data<String, Number> d23 = new XYChart.Data<>("日本", 34);
        xy1.getData().addAll(d1, d2, d3);
        xy2.getData().addAll(d21, d22, d23);

        xy1.getData().forEach(stringNumberData -> {
            HBox h1 = new HBox();
            h1.setAlignment(Pos.CENTER);
            h1.setStyle("-fx-background-color: #FFFF55");
            h1.getChildren().add(new Label(String.valueOf(stringNumberData.getYValue())));
            h1.setOnMouseClicked(event -> System.out.println(stringNumberData.getXValue()));
            // 可以自定义 node
            stringNumberData.setNode(h1);
        });

        BarChart<String, Number> barChart = new BarChart<>(x, y);
        barChart.getData().addAll(xy1, xy2);
        barChart.setPrefWidth(300);
        barChart.setPrefHeight(300);
        barChart.setTitle("第三种方式");
        return barChart;
    }

    private BarChart getBarChart4() {
        NumberAxis x = new NumberAxis(0, 100, 10);
        x.setLabel("生产总值");
        CategoryAxis y = new CategoryAxis();
        y.setLabel("国家");

        XYChart.Series<Number, String> xy = new XYChart.Series<>();
        xy.setName("生产总值");
        XYChart.Data<Number, String> d1 = new XYChart.Data<>(80, "中国");
        XYChart.Data<Number, String> d2 = new XYChart.Data<>(98, "美国");
        XYChart.Data<Number, String> d3 = new XYChart.Data<>(25, "日本");
        xy.getData().addAll(d1, d2, d3);

        BarChart<Number, String> barChart = new BarChart<>(x, y);
        barChart.getData().addAll(xy);
        barChart.setPrefWidth(300);
        barChart.setPrefHeight(300);
        barChart.setTitle("第四种方式");
        return barChart;
    }
}

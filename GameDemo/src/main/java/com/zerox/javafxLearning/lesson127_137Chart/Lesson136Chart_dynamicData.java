package com.zerox.javafxLearning.lesson127_137Chart;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 14:25
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson136Chart_dynamicData extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        NumberAxis x = new NumberAxis("x轴", 0, 20, 1);
        NumberAxis y = new NumberAxis("y轴", 0, 100, 10);

        LineChart<Number, Number> lineChart = new LineChart<>(x, y);
        lineChart.setAnimated(true);
        lineChart.setPrefWidth(800);
        lineChart.setPrefHeight(500);

        XYChart.Series<Number, Number> xy1 = new XYChart.Series<>();
        xy1.setName("xy1");
        XYChart.Series<Number, Number> xy2 = new XYChart.Series<>();
        xy2.setName("xy2");

        lineChart.getData().add(xy1);
        lineChart.getData().add(xy2);

        Button play = new Button("监控");
        Button stop = new Button("停止");

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(play, stop);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox, lineChart);

        AnchorPane.setTopAnchor(hBox, 50.0);
        AnchorPane.setLeftAnchor(hBox, 50.0);
        AnchorPane.setTopAnchor(lineChart, 100.0);
        AnchorPane.setLeftAnchor(lineChart, 50.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(900);
        primaryStage.setHeight(800);
        primaryStage.show();

        DataTask task = new DataTask();
        task.setDelay(Duration.seconds(0));
        task.setPeriod(Duration.seconds(1));
        task.valueProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                double value1 = nv.get(0);
                double value2 = nv.get(1);

                if (xy1.getData().size() == 20) {
                    xy1.getData().remove(0);
                    xy2.getData().remove(0);
                    x.setUpperBound(x.getUpperBound() + 1);
                    x.setLowerBound(x.getLowerBound() + 1);
                }

                XYChart.Data<Number, Number> data1 = new XYChart.Data<>(x.getLowerBound() + xy1.getData().size(), value1);
                XYChart.Data<Number, Number> data2 = new XYChart.Data<>(x.getLowerBound() + xy2.getData().size(), value2);
                xy1.getData().add(data1);
                xy2.getData().add(data2);
            }
        });

        play.setOnAction(event -> {
            if (!task.isRunning()) {
                task.start();
            }
        });

        stop.setOnAction(event -> {
            task.cancel();
            task.reset();
            xy1.getData().clear();
            xy2.getData().clear();
            x.setLowerBound(0);
            x.setUpperBound(20);
        });
    }

    class DataTask extends ScheduledService<ArrayList<Double>> {
        // 定义在 Task 里面的话，call 中的逻辑就修改不了变量，很奇怪
        private double pre1 = 50;
        private double pre2 = 50;
        private double modifier1 = 5;
        private double modifier2 = 5;

        private final static double MULTI = 20;
        private final static double MOTION = 0.5;

        @Override
        protected Task<ArrayList<Double>> createTask() {

            Task<ArrayList<Double>> task = new Task<ArrayList<Double>>() {

                @Override
                protected ArrayList<Double> call() throws Exception {
                    double value1 = pre1 + MULTI * Math.random() - modifier1;
                    double value2 = pre2 + MULTI * Math.random() - modifier2;
                    System.out.printf("first: %.2f - %.2f - %.2f; second: %.2f - %.2f - %.2f\n", pre1, value1, modifier1, pre2, value2, modifier2);

                    if (value1 > 100) {
                        modifier1 = MULTI / 2 - (100 - pre1) * MOTION / 2;
                        value1 = 100;
                    } else if (value1 < 0) {
                        modifier1 = MULTI / 2 + pre1 * MOTION / 2;
                        value1 = 0;
                    } else {
                        modifier1 = MULTI / 2 - (value1 - pre1) * MOTION / 2;
                    }
                    pre1 = value1;

                    if (value2 > 100) {
                        modifier2 = MULTI / 2 - (100 - pre2) * MOTION / 2;
                        value2 = 100;
                    } else if (value2 < 0) {
                        modifier2 = MULTI / 2 + pre2 * MOTION / 2;
                        value2 = 0;
                    } else {
                        modifier2 = MULTI / 2 - (value2 - pre2) * MOTION / 2;
                    }
                    pre2 = value2;
                    System.out.printf("first: %.2f - %.2f; second: %.2f - %.2f\n", pre1, modifier1, pre2, modifier2);

                    ArrayList<Double> arrayList = new ArrayList<>();
                    arrayList.add(value1);
                    arrayList.add(value2);
                    return arrayList;
                }
            };
            return task;
        }
    }
}

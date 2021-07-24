package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: zhuxi
 * @Time: 2021/7/24 11:42
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson046ProgressBar extends Application {
    private ScheduledService<Double> service;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        ProgressIndicator pi = new ProgressIndicator();
        /// 默认的就是这个状态
//        pi.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        pi.setPrefWidth(100);
        pi.setPrefHeight(100);

        ProgressBar pb = new ProgressBar(0.5);
//        pb.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        pb.setProgress(0.5);
        pb.setPrefWidth(600);
        pb.setPrefHeight(20);

        pb.progressProperty().addListener((o, ov, nv) -> System.out.println(nv));

        AnchorPane.setTopAnchor(pb, 100.0);
        an.getChildren().addAll(pb);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ProgressBar");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        service = new ScheduledService<Double>() {
            double i = 0;

            @Override
            protected Task<Double> createTask() {
                return new Task<Double>() {
                    @Override
                    protected Double call() throws Exception {
                        return i += 0.1;
                    }

                    @Override
                    protected void updateValue(Double value) {
                        pb.setProgress(value);
                        pi.setProgress(value);
                        if (value >= 1) {
                            service.cancel();
                            System.out.println("task cancelled");
                        }
                    }
                };
            }
        };

        service.setDelay(Duration.millis(0));
        service.setPeriod(Duration.seconds(1));
        service.start();
    }
}

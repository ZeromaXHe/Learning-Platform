package com.zerox.javafxLearning.lesson082_111;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/25 0:23
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson111ScheduledService extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        ProgressBar pb = new ProgressBar(0);
        pb.setPrefWidth(200);

        Button cancel = new Button("取消");
        Button start = new Button("开始");
        Button restart = new Button("重启");
        Button reset = new Button("重置");

        Label l1 = new Label("state");
        Label l2 = new Label("value");
        Label l3 = new Label("title");
        Label l4 = new Label("message");

        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        hBox.getChildren().addAll(start, cancel, restart, reset, pb, l1, l2, l3, l4);

        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        MyScheduledService mss = new MyScheduledService();
        mss.setDelay(Duration.seconds(2));
        mss.setPeriod(Duration.seconds(1));
        mss.setRestartOnFailure(true);
        mss.setMaximumFailureCount(3);
        mss.setBackoffStrategy(ScheduledService.LINEAR_BACKOFF_STRATEGY);

        start.setOnAction(event -> mss.start());
        cancel.setOnAction(event -> mss.cancel());
        restart.setOnAction(event -> mss.restart());
        reset.setOnAction(event -> mss.reset());

        mss.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                l2.setText(String.valueOf(newValue));
            }
        });

        mss.lastValueProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null) {
                System.out.println("lastValue = " + newValue.intValue());
            }
        });
    }

    class MyScheduledService extends ScheduledService<Number> {
        int sum = 0;

        @Override
        protected Task<Number> createTask() {
            System.out.println("createTask");

            Task<Number> task = new Task<Number>() {
                @Override
                protected void updateValue(Number value) {
                    super.updateValue(value);

                    System.out.println("updateValue");
                    if (value.intValue() == 10) {
                        MyScheduledService.this.cancel();
                        System.out.println("任务取消");
                    }
                }

                @Override
                protected Number call() throws Exception {
                    sum += 1;
                    System.out.println("call " + sum);
                    return sum;
                }
            };
            return task;
        }
    }
}

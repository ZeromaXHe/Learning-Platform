package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 * @Author: zhuxi
 * @Time: 2021/7/22 11:54
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson045Slider extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        Slider slider = new Slider(0, 100, 50);
        slider.setPrefWidth(300);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(10);
        slider.setValue(20);
        slider.setOrientation(Orientation.VERTICAL);

        slider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                if (object == 0) {
                    return "零";
                } else if (object == 20) {
                    return "二十";
                } else if (object == 100) {
                    return "一百";
                }
                return "未知";
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });

        slider.setOnMouseClicked(event -> System.out.println("setOnMouseClicked"));

        slider.valueProperty().addListener((o, ov, nv) -> System.out.println("valueProperty:" + nv));
        slider.valueChangingProperty().addListener((o, ov, nv) -> System.out.println("valueChangingProperty:" + nv));

        MyScheduledService m = new MyScheduledService(slider);
        m.setDelay(Duration.millis(0));
        m.setPeriod(Duration.seconds(1));
        m.start();

        m.valueProperty().addListener((o, ov, nv) -> {
            System.out.println("[" + Thread.currentThread().getName() + "]ov:" + ov + ", nv:" + nv);
            // MyScheduledService更新时会发送两个消息，一个含oldValue，一个含newValue
            if (nv != null) {
                slider.setValue(nv);
            }
        });

        AnchorPane.setTopAnchor(slider, 100.0);
        AnchorPane.setLeftAnchor(slider, 100.0);
        an.getChildren().addAll(slider);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

/**
 * 多线程递增进度条
 */
class MyScheduledService extends ScheduledService<Integer> {
    int i = 0;
    Slider s;

    public MyScheduledService(Slider s) {
        this.s = s;
    }

    @Override
    protected Task<Integer> createTask() {
        Task task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return i += 1;
            }

            /// 注释后会调用父类方法修改MyScheduledService实例的value
//            @Override
//            protected void updateValue(Integer value) {
////                super.updateValue(value);
//                s.setValue(value);
//            }
        };
        return task;
    }
}
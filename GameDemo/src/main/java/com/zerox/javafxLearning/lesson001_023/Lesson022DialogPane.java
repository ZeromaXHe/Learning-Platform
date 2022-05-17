package com.zerox.javafxLearning.lesson001_023;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 23:03
 * @Description: DialogPane 和 ScheduledService
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson022DialogPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("点击显示窗口");

        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        AnchorPane.setTopAnchor(button, 100.0);
        AnchorPane.setLeftAnchor(button, 100.0);

        an.getChildren().addAll(button);

        button.setOnAction(event -> {
            Stage stage = new Stage();

            DialogPane dialogPane = new DialogPane();
            dialogPane.setHeaderText("HeaderText");
            dialogPane.setContentText("ContentText");
            dialogPane.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.OK, ButtonType.CANCEL,
                    ButtonType.APPLY, ButtonType.PREVIOUS, ButtonType.NEXT, ButtonType.FINISH, ButtonType.CLOSE);
            Button close = (Button) dialogPane.lookupButton(ButtonType.CLOSE);
            Button apply = (Button) dialogPane.lookupButton(ButtonType.APPLY);

            ImageView imageView = new ImageView("/icon/icon.jpeg");
            dialogPane.setGraphic(imageView);

            dialogPane.setExpandableContent(new Text("this is ExpandableContent"));
            dialogPane.setExpanded(true);

            Scene sc = new Scene(dialogPane);
            stage.setScene(sc);

            stage.initOwner(primaryStage);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("这是弹出的窗口");
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);
            stage.show();

            apply.setOnAction(event1 -> System.out.println("apply"));
            close.setOnAction(event1 -> stage.close());

            MyScheduledService my = new MyScheduledService(dialogPane, stage);
            my.setDelay(Duration.ZERO);
            my.setPeriod(Duration.seconds(1));
            my.start();
        });

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DialogPane test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    class MyScheduledService extends ScheduledService {
        private int i = 0;

        private DialogPane dialogPane;
        private Stage stage;

        public MyScheduledService(DialogPane dialogPane, Stage stage) {
            this.dialogPane = dialogPane;
            this.stage = stage;
        }

        @Override
        protected Task createTask() {
            return new Task<Integer>() {
                /**
                 * 必须实现的
                 * @return
                 * @throws Exception
                 */
                @Override
                protected Integer call() throws Exception {
                    // call Thread-5 (数字递增)
                    System.out.println("call " + Thread.currentThread().getName());
                    return i++;
                }


                /**
                 * 用来修改UI线程上的值
                 * @param value
                 */
                @Override
                protected void updateValue(Integer value) {
                    // updateValue JavaFX Application Thread
                    System.out.println("updateValue " + Thread.currentThread().getName());
                    System.out.println("updateValue的值：" + value);

                    if (value <= 10) {
                        dialogPane.setContentText(String.valueOf(value));
                    } else {
                        stage.close();
                        // 必须使用类名加this来取消外部类的任务，直接使用this的话是Task的
                        MyScheduledService.this.cancel();
                    }
                }
            };
        }
    }
}

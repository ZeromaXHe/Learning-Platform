package com.zerox.javafxLearning.lesson082_111;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/24 23:40
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson109Task extends Application {
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

        Label l1 = new Label("state");
        Label l2 = new Label("value");
        Label l3 = new Label("title");
        Label l4 = new Label("message");

        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        hBox.getChildren().addAll(start, cancel, pb, l1, l2, l3, l4);

        an.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);

        start.setOnAction(event -> thread.start());
        cancel.setOnAction(event -> myTask.cancel());
//        myTask.progressProperty().addListener((observable, oldValue, newValue) -> pb.setProgress(newValue.doubleValue()));
        pb.progressProperty().bind(myTask.progressProperty());
        myTask.titleProperty().addListener((observable, oldValue, newValue) -> l3.setText(newValue));
        myTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue()==1){
                l2.setText("完成");
            }
        });
        myTask.messageProperty().addListener((observable, oldValue, newValue) -> l4.setText(newValue));
        myTask.stateProperty().addListener((observable, oldValue, newValue) -> l1.setText(newValue.toString()));

        myTask.exceptionProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue.getMessage()));
    }

    class MyTask extends Task<Number>{
        @Override
        protected void updateValue(Number value) {
            super.updateValue(value);
            System.out.println("updateValue():Platform.isFxApplicationThread(): " + Platform.isFxApplicationThread());
            System.out.println(value.intValue());
        }

        @Override
        protected Number call() throws Exception {
            System.out.println("call():Platform.isFxApplicationThread(): " + Platform.isFxApplicationThread()
                    + ", threadName: " + Thread.currentThread().getName());
            this.updateTitle("count100");
            for (int i = 0; i < 101; i++) {
                updateProgress(i, 100);
                double progress = i/100.0;
                if(progress<0.5){
                    updateMessage("请耐心等待");
                }else if(progress<0.8) {
                    updateMessage("马上完成");
                }else if(progress<1){
                    updateMessage("最后阶段");
                }else{
                    updateMessage("搞定了");
                }
                Thread.sleep(50);
            }

            return 1;
        }
    }
}

package com.zerox.javafxLearning.lesson183_197Animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/22 16:28
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson183_184Animation_TimeLineAndToggleButton extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ToggleButton tb1 = new ToggleButton("播放");
        ToggleButton tb2 = new ToggleButton("暂停");
        ToggleButton tb3 = new ToggleButton("停止");
        ToggleButton tb4 = new ToggleButton("自定义按键");
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
        tb3.setToggleGroup(group);
        tb4.setToggleGroup(group);

        HBox hBox = new HBox(30);
        hBox.getChildren().addAll(tb1, tb2, tb3, tb4);

        Scale scale = new Scale(1, 1, -100, -100);
        // 先公转再自转
        Rotate rotate = new Rotate(0, 200, 50);
        Rotate rotate2 = new Rotate(0, 50, 50);

        Rectangle rec = new Rectangle(100, 100);
        rec.setFill(Color.RED);
        rec.getTransforms().addAll(scale, rotate, rotate2);

        Timeline timeline_rec = new Timeline();
        KeyValue kv1_rec = new KeyValue(rec.translateXProperty(), 0);
//        KeyValue kv1R_rec = new KeyValue(rec.rotateProperty(), 0);
//        KeyValue kv1SX_rec = new KeyValue(rec.scaleXProperty(), 1);
//        KeyValue kv1SY_rec = new KeyValue(rec.scaleYProperty(), 1);
        KeyValue kv1R_rec = new KeyValue(rotate.angleProperty(), 0);
        KeyValue kv1R2_rec = new KeyValue(rotate2.angleProperty(), 0);
        KeyValue kv1SX_rec = new KeyValue(scale.xProperty(), 1);
        KeyValue kv1SY_rec = new KeyValue(scale.yProperty(), 1);
        KeyValue kv1O_rec = new KeyValue(rec.opacityProperty(), 1);
        KeyFrame kf1_rec = new KeyFrame(Duration.seconds(0), kv1_rec, kv1R_rec, kv1R2_rec, kv1SX_rec, kv1SY_rec, kv1O_rec);
        KeyValue kv2_rec = new KeyValue(rec.translateXProperty(), 300);
//        KeyValue kv2R_rec = new KeyValue(rec.rotateProperty(), 360);
//        KeyValue kv2SX_rec = new KeyValue(rec.scaleXProperty(), 2);
//        KeyValue kv2SY_rec = new KeyValue(rec.scaleYProperty(), 2);
        KeyValue kv2R_rec = new KeyValue(rotate.angleProperty(), 360);
        KeyValue kv2R2_rec = new KeyValue(rotate2.angleProperty(), 360);
        KeyValue kv2SX_rec = new KeyValue(scale.xProperty(), 2);
        KeyValue kv2SY_rec = new KeyValue(scale.yProperty(), 2);
        KeyValue kv2O_rec = new KeyValue(rec.opacityProperty(), 0.2);
        KeyFrame kf2_rec = new KeyFrame(Duration.seconds(3), kv2_rec, kv2R_rec, kv2R2_rec, kv2SX_rec, kv2SY_rec, kv2O_rec);
        timeline_rec.getKeyFrames().addAll(kf1_rec, kf2_rec);
        timeline_rec.setAutoReverse(true);
        timeline_rec.setCycleCount(Timeline.INDEFINITE);

        Button bu = new Button("button");

        // 构造参数第一个参数指定帧率, 默认 60
        Timeline timeline = new Timeline(30);
        KeyValue kv1 = new KeyValue(bu.translateXProperty(), 0);
        KeyValue kv1_2 = new KeyValue(bu.translateYProperty(), 0);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0), "kf1", event -> System.out.println("kf1"), kv1, kv1_2);

        KeyValue kv2 = new KeyValue(bu.translateXProperty(), 300, Interpolator.EASE_OUT);
        KeyValue kv2_2 = new KeyValue(bu.translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(2), "kf2", event -> System.out.println("kf2"), kv2, kv2_2);

        KeyValue kv3 = new KeyValue(bu.translateXProperty(), 600, Interpolator.EASE_IN);
        KeyValue kv3_2 = new KeyValue(bu.translateYProperty(), 300, Interpolator.EASE_IN);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(3), "kf3", event -> System.out.println("kf3"), kv3, kv3_2);
        timeline.getKeyFrames().addAll(kf1, kf2, kf3);
        timeline.setDelay(Duration.seconds(0.5));
//        timeline.setCycleCount(2);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        // 播放速率
        timeline.setRate(-1);

        System.out.println(timeline.getTargetFramerate());
        System.out.println(timeline.getTotalDuration().toSeconds());
        timeline.setOnFinished(event -> System.out.println("结束"));
        timeline.statusProperty().addListener(
                (observable, oldValue, newValue) -> System.out.println("状态 = " + newValue.toString()));
        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(hBox, rec, bu);
        AnchorPane.setTopAnchor(bu, 200.0);
        AnchorPane.setLeftAnchor(bu, 100.0);
        AnchorPane.setTopAnchor(rec, 200.0);
        AnchorPane.setLeftAnchor(rec, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        tb1.setOnAction(event -> {
            group.getToggles().forEach(System.out::println);
            timeline.play();
            timeline_rec.play();
        });
        tb2.setOnAction(event -> {
            timeline.pause();
            timeline_rec.pause();
        });
        tb3.setOnAction(event -> {
            timeline.stop();
            timeline_rec.stop();
        });
        tb4.setOnAction(event -> {
//            timeline.jumpTo(Duration.seconds(2));
            timeline.jumpTo("kf2");

            // 重新播放
//            timeline.playFromStart();

            // 设置可用于跳转的点，使用 timeline.jumpTo/playFrom("aa") 之类方法使用 cuePoint 即可
//            timeline.getCuePoints().put("aa", Duration.seconds(3));
//            timeline.playFrom("aa");
        });
    }
}

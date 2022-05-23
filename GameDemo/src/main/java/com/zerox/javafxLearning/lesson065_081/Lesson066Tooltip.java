package com.zerox.javafxLearning.lesson065_081;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/14 17:20
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson066Tooltip extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Tooltip tooltip = new Tooltip("这是提示的内容:这是随便打的提示的内容，测试一下换行效果");
        tooltip.setFont(new Font(20));
        tooltip.setPrefWidth(200);
        tooltip.setPrefHeight(200);
        tooltip.setWrapText(true);
        /// 如果不自动换行，配置裁切方式
//        tooltip.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_TOP_LEFT);
        tooltip.setOpacity(0.7);
        tooltip.setStyle("-fx-background-color: #0000DD");

        VBox vBox = new VBox();
        vBox.setPrefWidth(100);
        vBox.setPrefHeight(100);
        vBox.setStyle("-fx-background-color: #00AAAA");
        tooltip.setGraphic(vBox);

        /// show的时候相关的配置
//        tooltip.setX(100);
//        tooltip.setY(100);
//        tooltip.setAnchorX(100);
//        tooltip.setAnchorY(100);
//        tooltip.setAutoHide(false);

        tooltip.setOnShown(event -> System.out.println("显示"));
        tooltip.setOnShowing(event -> System.out.println("正在显示"));
        tooltip.setOnHidden(event -> System.out.println("隐藏"));

        Button b1 = new Button("button");
        b1.setTooltip(tooltip);
//        Tooltip.install(b1, tooltip);
//        Tooltip.uninstall(b1, tooltip);

        an.getChildren().addAll(b1);
        AnchorPane.setTopAnchor(b1, 50.0);
        AnchorPane.setLeftAnchor(b1, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

//        tooltip.show(primaryStage);
    }
}

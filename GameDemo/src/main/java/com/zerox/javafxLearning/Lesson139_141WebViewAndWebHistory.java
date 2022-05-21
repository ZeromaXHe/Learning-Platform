package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 15:43
 * @Description: 141 课提到 JSObject 类，没有具体讲
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson139_141WebViewAndWebHistory extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView web = new WebView();
        WebEngine engine = web.getEngine();

        engine.loadContent("<html>" +
                "<head><title>javafx webview</title></head>" +
                "<body>" +
                "<h1>hello world</h1>" +
                "<font size=\"20\" color=\"red\">hello world</font><br/>" +
                "<button type=\"button\">点我</button>" +
                "</body>" +
                "</html>");

        engine.load("https://www.baidu.com");
        web.setFontScale(1.5);
        web.setZoom(1.5);

        WebHistory history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        // 禁用右键菜单
//        web.setContextMenuEnabled(false);

        HBox hBox = new HBox(20);
        Button b1 = new Button("前进");
        Button b2 = new Button("后退");
        Button b3 = new Button("信息");
        hBox.getChildren().addAll(b1, b2, b3);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(web, hBox);
        AnchorPane.setTopAnchor(web, 30.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        web.prefWidthProperty().bind(an.widthProperty());
        web.prefHeightProperty().bind(an.heightProperty().subtract(30));

        b1.setOnAction(event -> {
            if (history.getCurrentIndex() < entries.size() - 1) {
                history.go(1);
            } else {
                System.out.println("处于最后一页，无法前进");
            }
        });

        b2.setOnAction(event -> {
            if (history.getCurrentIndex() > 0) {
                history.go(-1);
            } else {
                System.out.println("处于第一页，无法后退");
            }
        });

        b3.setOnAction(event -> {
            System.out.println("当前页面索引：" + history.getCurrentIndex());
            System.out.println("最大存储数量：" + history.getMaxSize());
            System.out.println("当前存储数量：" + entries.size());
            entries.forEach(entry -> System.out.println(
                    entry.getTitle() + " - " + entry.getUrl() + " - " + entry.getLastVisitedDate()));
            System.out.println("------------------------");
        });

        primaryStage.setOnCloseRequest(event -> {
            // 保证后台进程关闭
            engine.load(null);
            Platform.exit();
        });
    }
}

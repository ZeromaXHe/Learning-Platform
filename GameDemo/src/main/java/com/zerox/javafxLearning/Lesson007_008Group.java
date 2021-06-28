package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ListChangeListener;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/28 23:25
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class Lesson007_008Group extends Application {
    int newBtnCount = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 打开网页
//        HostServices host = getHostServices();
//        host.showDocument("www.baidu.com");

        // 路径写成"./icon/icon.jpeg"也可以
        URL url = getClass().getClassLoader().getResource("icon/icon.jpeg");
        String path = url.toExternalForm();

        Button button = new Button("按钮");
        button.setCursor(Cursor.CROSSHAIR);
        // 如果直接放在Scene里面作为根节点，下面宽高设置无效
        button.setPrefWidth(200);
        button.setPrefHeight(200);

        Button b1 = new Button("b1");
        b1.setLayoutX(200);
        b1.setLayoutY(0);
        b1.setPrefWidth(200);
        b1.setPrefHeight(100);
        Button b2 = new Button("b2");
        b2.setLayoutX(200);
        b2.setLayoutY(100);
        b2.setPrefWidth(200);
        b2.setPrefHeight(100);
        Button b3 = new Button("b3");
        b3.setLayoutX(200);
        b3.setLayoutY(200);
        b3.setPrefWidth(200);
        b3.setPrefHeight(100);

        Group group = new Group();
        group.getChildren().addListener(
                (ListChangeListener<Node>) (c -> System.out.println("list size: " + c.getList().size()))
        );
        b1.setOnAction(event -> {
            Button newBtn = new Button("newBtn"+newBtnCount);
            newBtn.setLayoutX(0);
            newBtn.setLayoutY(200 + newBtnCount * 20);
            newBtnCount++;
            group.getChildren().add(newBtn);
        });
        group.getChildren().add(button);
        // 不指定layout的话，会重叠在左上角
        group.getChildren().addAll(b1, b2, b3);

        // 只检查组件左上角位置是否在x,y上
        System.out.println(group.contains(200, 0));
        System.out.println(group.contains(300, 10));
        group.setOpacity(0.8);
        // group.setAutoSizeChildren为false的话，没有指定宽高的子节点就宽高为0了
//        group.setAutoSizeChildren(false);
        // 清空子节点
//        group.getChildren().clear();
        Object[] objects = group.getChildren().toArray();
        System.out.println(objects.length);
        for (Object o : objects) {
            if (o instanceof Button) {
                Button btn = (Button) o;
                btn.setPrefWidth(100);
                btn.setPrefHeight(100);
            }
        }

        Scene scene = new Scene(group);
        scene.setCursor(Cursor.cursor(path));

        primaryStage.setScene(scene);

        primaryStage.setTitle("test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

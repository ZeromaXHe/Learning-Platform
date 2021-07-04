package com.zerox.javafxLearning;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/4 23:52
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson024LogInGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label l_name = new Label("账号");
        l_name.setFont(Font.font(14));
        Label l_pwd = new Label("密码：");
        l_pwd.setFont(Font.font(14));

        TextField t_name = new TextField();
        t_name.setTooltip(new Tooltip("请输入用户名"));
        PasswordField p_pwd = new PasswordField();
        p_pwd.setTooltip(new Tooltip("请输入密码"));

        t_name.setUserData("admin");
        p_pwd.setUserData("12345");

        Button login = new Button("登录");
        Button clear = new Button("清除");

        GridPane gr = new GridPane();
        gr.setStyle("-fx-background-color: #FFF5EE");

        gr.add(l_name, 0, 0);
        gr.add(t_name, 1, 0);
        gr.add(l_pwd, 0, 1);
        gr.add(p_pwd, 1, 1);
        gr.add(clear, 0, 2);
        gr.add(login, 1, 2);

        GridPane.setMargin(login, new Insets(0, 0, 0, 120));

        gr.setHgap(5);
        gr.setVgap(17);
        gr.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gr);
        primaryStage.setScene(scene);
        primaryStage.setTitle("登录");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.setResizable(false);
        primaryStage.show();

        clear.setOnAction(event -> {
            t_name.setText("");
            p_pwd.setText("");
        });

        login.setOnAction(event -> {
            String name = t_name.getText();
            String pwd = p_pwd.getText();
            if (t_name.getUserData().equals(name) && p_pwd.getUserData().equals(pwd)) {
                System.out.println("登录成功");
                primaryStage.close();
                new MyWindow(name, pwd);
            } else {
                System.out.println("登陆失败");
                l_name.setTextFill(Color.RED);
                l_pwd.setTextFill(Color.RED);
                primaryStage.setTitle("账号或密码错误");

                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.seconds(0.3));
                fade.setNode(gr);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.play();
            }
        });
    }
}

class MyWindow {
    private final Stage stage = new Stage();

    public MyWindow(String name, String pwd) {
        Text text = new Text("用户名：" + name + " 密码：" + pwd);

        BorderPane bor = new BorderPane();
        bor.setStyle("-fx-background-color: #FFC0CB");
        bor.setCenter(text);

        Scene scene = new Scene(bor);
        stage.setTitle("登陆成功");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }
}
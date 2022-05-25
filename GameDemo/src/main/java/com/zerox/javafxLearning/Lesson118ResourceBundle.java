package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 17:10
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson118ResourceBundle extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = Locale.getDefault();
        System.out.println(locale.getLanguage());
        System.out.println(locale.getCountry());
//        System.out.println(Arrays.toString(Locale.getAvailableLocales()));
//        System.out.println(Arrays.toString(Locale.getISOCountries()));
//        System.out.println(Arrays.toString(Locale.getISOLanguages()));

        // 修改本地化
        Locale.setDefault(new Locale("en", "US"));

        FXMLLoader fl = new FXMLLoader();
        URL url = fl.getClassLoader().getResource("fxml/Lesson118ResourceBundle.fxml");
        fl.setLocation(url);
        /**
         * 关于乱码问题：请确保 Properties 文件（包括 ResourceBundle）编码格式为 ISO-8859-1。
         * Java 的标准假定 Properties 文件编码格式为 ISO-8859-1。用其它格式会比较麻烦。
         * IDEA 上的 PropertiesFiles 默认可能不是 ISO-8859-1，因此很可能造成乱码问题。
         * 在 IDEA -> File -> Settings -> Editor -> FileEncodings -> Properties Files 设为 ISO-8859-1，
         * 勾选 Transparent native-to-ascii conversion。
         * 在更换默认编码的之前，备份 Properties 文件（似乎需要重新建立文件才能生效）
         */
        ResourceBundle rb = ResourceBundle.getBundle("property/Lesson118ResourceBundle");
        fl.setResources(rb);
        AnchorPane an = fl.load();

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

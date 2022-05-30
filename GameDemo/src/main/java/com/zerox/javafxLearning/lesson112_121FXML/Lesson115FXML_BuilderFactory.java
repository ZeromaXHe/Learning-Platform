package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 14:49
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson115FXML_BuilderFactory extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(fl.getClassLoader().getResource("fxml/Lesson115FXML_BuilderFactory.fxml"));
        fl.setBuilderFactory(new Lesson115PersonBuilderFactoryMap());
//        fl.setBuilderFactory(new Lesson115PersonBuilderFactory());
        ArrayList<Lesson115Person> persons = fl.load();
        for (Lesson115Person person : persons) {
            System.out.println("person: " + person.getName() + " - " + person.getAge());
        }

        AnchorPane an = new AnchorPane();

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

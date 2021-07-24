package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * @Author: zhuxi
 * @Time: 2021/7/24 13:49
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson048Spinner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("A", "B", "C", "D");

        Student s1 = new Student("lil A", 18, 90);
        Student s2 = new Student("lil B", 12, 100);
        Student s3 = new Student("lil C", 40, 50);
        ObservableList<Student> list2 = FXCollections.observableArrayList();
        list2.addAll(s1, s2, s3);

        Spinner spinner = new Spinner(0, 10, 5);
        Spinner spinner2 = new Spinner(0, 10, 0, 2);

        Spinner<String> spinner3 = new Spinner<>(list);
        spinner3.valueProperty().addListener((o, ov, nv) -> System.out.println(nv));
        spinner3.setEditable(true);
        spinner3.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_LEFT_HORIZONTAL);

        Spinner<Student> spinner4 = new Spinner<>();
        spinner4.setEditable(true);
        SVF svf = new SVF(list2);
        svf.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                return object.getName() + " - " + object.getAge() + " - " + object.getScore();
            }

            @Override
            public Student fromString(String string) {
                System.out.println(string);
                Student s = new Student(string, 70, 0);
                // 未添加到数据源里，不会显示出来
                return s;
            }
        });
        svf.setValue(s1);
        spinner4.setValueFactory(svf);

//        SpinnerValueFactory.ListSpinnerValueFactory<Student> lsvf = new SpinnerValueFactory.ListSpinnerValueFactory<>(list2);
//        lsvf.setConverter(new StringConverter<Student>() {
//            @Override
//            public String toString(Student object) {
//                if(object == null){
//                    return "";
//                }
//                return object.getName() + " - " + object.getAge() + " - " + object.getScore();
//            }
//
//            @Override
//            public Student fromString(String string) {
//                return null;
//            }
//        });
//        spinner4.setValueFactory(lsvf);

        AnchorPane.setTopAnchor(spinner, 10.0);
        AnchorPane.setTopAnchor(spinner2, 110.0);
        AnchorPane.setTopAnchor(spinner3, 210.0);
        AnchorPane.setTopAnchor(spinner4, 310.0);
        an.getChildren().addAll(spinner, spinner2, spinner3, spinner4);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Spinner");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

class SVF extends SpinnerValueFactory<Student> {
    private ObservableList<Student> list;
    private int index = 0;

    public SVF(ObservableList<Student> list) {
        this.list = list;
    }

    @Override
    public void decrement(int steps) {
        System.out.println("decrement = " + steps);
        index = (list.size() + index - steps) % list.size();
        System.out.println("index = " + index);
        this.setValue(list.get(index));
    }

    @Override
    public void increment(int steps) {
        System.out.println("increment = " + steps);
        index = (index + steps) % list.size();
        System.out.println("index = " + index);
        this.setValue(list.get(index));
    }
}

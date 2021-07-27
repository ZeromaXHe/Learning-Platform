package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.beans.PropertyChangeSupport;

/**
 * @Author: zhuxi
 * @Time: 2021/7/27 10:07
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson051_052Binding extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button bu = new Button("修改数据Student");

        Student s = new Student("A", 19);

        SimpleIntegerProperty sip = new SimpleIntegerProperty(5);
        sip.addListener((o, ov, nv) -> {
            System.out.println("old = " + ov);
            System.out.println("new = " + nv);
        });
        System.out.println(sip.get());
        sip.set(10);
        System.out.println(sip.get());

        ReadOnlyDoubleWrapper rodw = new ReadOnlyDoubleWrapper(3);
        ReadOnlyDoubleProperty only = rodw.getReadOnlyProperty();
        System.out.println(only.get());
        rodw.set(5);
        System.out.println(only.get());

        AnchorPane an = new AnchorPane();

        Data data = new Data("A", 18);
        data.nameProperty().addListener((o, ov, nv) -> {
            System.out.println("observable = " + o);
            SimpleStringProperty name = (SimpleStringProperty) o;
            System.out.println(name.getName());
            System.out.println(name.getBean());
            System.out.println("oldValue = " + ov);
            System.out.println("newValue = " + nv);
        });

        Button bu2 = new Button("修改数据Data");

        AnchorPane.setTopAnchor(bu2, 100.0);
        an.getChildren().addAll(bu, bu2);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        s.psc.addPropertyChangeListener(evt -> {
            System.out.println("old = " + evt.getOldValue());
            System.out.println("new = " + evt.getNewValue());
        });

        s.psc.addPropertyChangeListener("setName_pro", evt -> {
            System.out.println("name old = " + evt.getOldValue());
            System.out.println("name new = " + evt.getNewValue());

            Student stu = (Student) evt.getSource();
        });

        s.psc.addPropertyChangeListener("setAge_pro", evt -> {
            System.out.println("age old = " + evt.getOldValue());
            System.out.println("age new = " + evt.getNewValue());
        });

        bu.setOnAction(event -> s.setName("B"));

        bu2.setOnAction(event -> data.setName("B"));
    }
}

class Student {
    private String name;
    private int age;

    public PropertyChangeSupport psc = new PropertyChangeSupport(this);

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        psc.firePropertyChange("setName_pro", oldValue, this.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        int oldValue = this.age;
        this.age = age;
        psc.firePropertyChange("setAge_pro", oldValue, this.age);
    }
}

class Data {
    private SimpleIntegerProperty age = new SimpleIntegerProperty(this, "age");
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");

    public Data(String name, int age) {
        this.age.set(age);
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}


class Data2 {
    private String name;
    private SimpleStringProperty nameProp = null;

    public Data2(String name) {
        this.name = name;
    }

    public StringProperty nameProperty() {
        if (nameProp == null) {
            nameProp = new SimpleStringProperty(this, "name", name);
        }
        return nameProp;
    }

    public String getName() {
        if (nameProp == null) {
            return this.name;
        } else {
            return this.nameProp.get();
        }
    }

    public void setName(String name) {
        if (nameProp == null) {
            this.name = name;
        } else {
            this.nameProp.set(name);
        }
    }
}
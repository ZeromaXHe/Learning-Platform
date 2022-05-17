package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Comparator;

/**
 * @Author: zhuxi
 * @Time: 2022/5/17 18:06
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson087ListView_sortAndSearch extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataPro dp1 = new DataPro("A", "10");
        DataPro dp2 = new DataPro("B", "20");
        DataPro dp3 = new DataPro("C", "30");
        DataPro dp4 = new DataPro("D", "40");
        DataPro dp5 = new DataPro("Dark", "140");
        DataPro dp6 = new DataPro("Duke", "40");
        DataPro dp7 = new DataPro("Peter", "26");
        DataPro dp8 = new DataPro("Lora", "35");
        DataPro dp9 = new DataPro("Hammer", "44");
        DataPro dp10 = new DataPro("Justin", "70");
        DataPro dp11 = new DataPro("Zeus", "80");
        DataPro dp12 = new DataPro("Obmar", "60");

        ObservableList<DataPro> list = FXCollections.observableArrayList(param ->
                new SimpleStringProperty[]{param.ageProperty(), param.nameProperty()});

        ListView<DataPro> listView = new ListView<>(list);

        listView.setCellFactory(TextFieldListCell.forListView(new StringConverter<DataPro>() {
            @Override
            public String toString(DataPro object) {
                return object.getName() + " - " + object.getAge();
            }

            @Override
            public DataPro fromString(String string) {
                return null;
            }
        }));

        ObservableList<DataPro> obsList = listView.getItems();
        obsList.addAll(dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8, dp9, dp10, dp11, dp12);

        obsList.addListener((Observable o) -> {
            ObservableList<DataPro> data = (ObservableList) o;
            data.forEach(t -> System.out.println(t.getName() + " - " + t.getAge()));
        });

        obsList.addListener((ListChangeListener.Change<? extends DataPro> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("添加动作");
                }
                if (c.wasRemoved()) {
                    System.out.println("删除动作");
                }
                if (c.wasReplaced()) {
                    System.out.println("替换动作");
                }
                if (c.wasUpdated()) {
                    System.out.println("更新动作");
                }
                if (c.wasPermutated()) {
                    System.out.println("排序动作");
                }
            }
        });

        listView.setPrefWidth(400);
        listView.setPrefHeight(500);

        TextField tf = new TextField();
        Button bu = new Button("sort");

        AnchorPane an = new AnchorPane();

        an.getChildren().addAll(listView, bu, tf);

        AnchorPane.setTopAnchor(listView, 100.0);
        AnchorPane.setLeftAnchor(listView, 100.0);

        AnchorPane.setTopAnchor(bu, 50.0);
        AnchorPane.setLeftAnchor(bu, 300.0);

        AnchorPane.setTopAnchor(tf, 50.0);
        AnchorPane.setLeftAnchor(tf, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        tf.textProperty().addListener((o, ov, nv) -> {
            FilteredList<DataPro> fl = obsList.filtered(dataPro -> dataPro.getName().contains(nv));
            listView.setItems(fl);
        });

        bu.setOnAction(event -> {
            SortedList<DataPro> sl = obsList.sorted(Comparator.comparingInt(o -> Integer.parseInt(o.getAge())));
            listView.setItems(sl);
        });

        an.setOnMouseClicked(event -> listView.setItems(obsList));
    }

    class DataPro {
        private SimpleStringProperty name = new SimpleStringProperty();
        private SimpleStringProperty age = new SimpleStringProperty();

        public DataPro(String name, String age) {
            this.name.set(name);
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

        public String getAge() {
            return age.get();
        }

        public SimpleStringProperty ageProperty() {
            return age;
        }

        public void setAge(String age) {
            this.age.set(age);
        }
    }
}

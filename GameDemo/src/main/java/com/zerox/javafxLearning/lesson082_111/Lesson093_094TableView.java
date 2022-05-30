package com.zerox.javafxLearning.lesson082_111;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 11:56
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson093_094TableView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Data d1 = new Data("A", 15, 80, false);
        Data d2 = new Data("B", 25, 85, true);
        Data d3 = new Data("C", 17, 60, false);
        Data d4 = new Data("D", 19, 53, true);

        ObservableList<Data> list = FXCollections.observableArrayList();
        list.addAll(d1, d2, d3, d4);

        TableView<Data> tableView = new TableView<>(list);

        TableColumn<Data, String> tc_name = new TableColumn<>("姓名");
        tc_name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        // PropertyValueFactory 源码可以看到使用反射实现，而内部类无法正常获取。将 Data 设为独立的 public 类即可
//        tc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().add(tc_name);

        TableColumn<Data, Number> tc_age = new TableColumn<>("年龄");
        tc_age.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAge()));
        tableView.getColumns().add(tc_age);

        TableColumn<Data, Number> tc_score = new TableColumn<>("分数");
        tc_score.setCellValueFactory(param -> new SimpleDoubleProperty(param.getValue().getScore()));
        tableView.getColumns().add(tc_score);

        TableColumn<Data, Boolean> tc_bool = new TableColumn<>("布尔");
        tc_bool.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue().isBool()));
        // PropertyValueFactory 源码可以看到使用反射实现，而内部类无法正常获取。将 Data 设为独立的 public 类即可
//        tc_bool.setCellValueFactory(new PropertyValueFactory<>("bool"));
        tableView.getColumns().add(tc_bool);

        Button button = new Button("button");

        // ======================================= Property =======================================

        Data2 d2_1 = new Data2("A", 15, 80, false);
        Data2 d2_2 = new Data2("B", 25, 85, true);
        Data2 d2_3 = new Data2("C", 17, 60, false);
        Data2 d2_4 = new Data2("D", 19, 53, true);

        // 加了 callback
        ObservableList<Data2> list2 = FXCollections.observableArrayList(param ->
                new Observable[]{param.nameProperty(), param.ageProperty(), param.scoreProperty(), param.boolProperty()});
        list2.addAll(d2_1, d2_2, d2_3, d2_4);

        TableView<Data2> tableView2 = new TableView<>(list2);

        TableColumn<Data2, String> tc_name2 = new TableColumn<>("姓名");
        tc_name2.setCellValueFactory(param -> param.getValue().nameProperty());
        // PropertyValueFactory 源码可以看到使用反射实现，而内部类无法正常获取。将 Data 设为独立的 public 类即可
        // 这种情况下，修改 name 将不会立即更新界面
//        tc_name2.setCellValueFactory(new PropertyValueFactory<>("name"));
        // group 里面合并了
//        tableView2.getColumns().add(tc_name2);

        TableColumn<Data2, Number> tc_age2 = new TableColumn<>("年龄");
        tc_age2.setCellValueFactory(param -> param.getValue().ageProperty());
        // group 里面合并了
//        tableView2.getColumns().add(tc_age2);

        TableColumn<Data2, Number> tc_score2 = new TableColumn<>("分数");
        tc_score2.setCellValueFactory(param -> param.getValue().scoreProperty());
        // groupMain 里面合并了
//        tableView2.getColumns().add(tc_score2);

        TableColumn<Data2, Boolean> tc_bool2 = new TableColumn<>("布尔");
        tc_bool2.setCellValueFactory(param -> param.getValue().boolProperty());
        tableView2.getColumns().add(tc_bool2);

        TableColumn<Data2, Object> group = new TableColumn<>("信息");
        group.getColumns().add(tc_name2);
        group.getColumns().add(tc_age2);
        TableColumn<Data2, Object> groupMain = new TableColumn<>("总信息");
        groupMain.getColumns().add(group);
        groupMain.getColumns().add(tc_score2);
        tableView2.getColumns().add(groupMain);

        Button button2 = new Button("button");

        // ======================================= Map =======================================

        HashMap<String, Observable> map1 = new HashMap<>();
        map1.put("name", new SimpleStringProperty("A"));
        map1.put("age", new SimpleIntegerProperty(18));
        map1.put("bool", new SimpleBooleanProperty(true));
        HashMap<String, Observable> map2 = new HashMap<>();
        map2.put("name", new SimpleStringProperty("B"));
        map2.put("age", new SimpleIntegerProperty(20));
        map2.put("bool", new SimpleBooleanProperty(false));

        // 不使用 MapValueFactory 的话，需要使用类似以下范型来实现, 且 Map 需使用 HashMap<String, SimpleStringProperty>
        // 即 age、bool 也需要按照原视频代码设置为 SimpleStringProperty
//        ObservableList<HashMap<String, Observable>> list3 = FXCollections.observableArrayList();
//        list3.addAll(map1, map2);
//
//        TableView<HashMap<String, Observable>> tableView3 = new TableView<>(list3);
//
//        TableColumn<HashMap<String, Observable>, String> tc_name3 = new TableColumn<>("姓名");
//        tc_name3.setCellValueFactory(param -> param.getValue().get("name"));
//        tableView3.getColumns().add(tc_name3);

        ObservableList<Map> list3 = FXCollections.observableArrayList();
        list3.addAll(map1, map2);

        TableView<Map> tableView3 = new TableView<>(list3);

        TableColumn<Map, String> tc_name3 = new TableColumn<>("姓名");
        // 使用上面注释里的范型 HashMap<String, SimpleStringProperty> 的话，则 new MapValueFactory() 不能带范型尖括号
        // (原视频里面和这里代码不同，就是采用无范型的 MapValueFactory)
        tc_name3.setCellValueFactory(new MapValueFactory<>("name"));
        tableView3.getColumns().add(tc_name3);

        TableColumn<Map, String> tc_age3 = new TableColumn<>("年龄");
        tc_age3.setCellValueFactory(new MapValueFactory<>("age"));
        tableView3.getColumns().add(tc_age3);

        TableColumn<Map, String> tc_bool3 = new TableColumn<>("布尔");
        tc_bool3.setCellValueFactory(new MapValueFactory<>("bool"));
        tableView3.getColumns().add(tc_bool3);

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(button, button2, tableView, tableView2, tableView3);

        AnchorPane.setTopAnchor(tableView, 100.0);
        AnchorPane.setLeftAnchor(tableView, 100.0);
        AnchorPane.setTopAnchor(tableView2, 100.0);
        AnchorPane.setLeftAnchor(tableView2, 450.0);
        AnchorPane.setTopAnchor(tableView3, 100.0);
        AnchorPane.setLeftAnchor(tableView3, 800.0);
        AnchorPane.setTopAnchor(button, 50.0);
        AnchorPane.setLeftAnchor(button, 100.0);
        AnchorPane.setTopAnchor(button2, 50.0);
        AnchorPane.setLeftAnchor(button2, 450.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();

        list2.addListener((ListChangeListener.Change<? extends Data2> c) -> {
            while (c.next()) {
                // list2 需要加 callback，否则这一行不会有显示
                System.out.println("update=" + c.wasUpdated());
            }
        });

        button.setOnAction(event -> {
//            tableView.getItems().add(new Data("E", 13, 99.5, true));
            d1.setAge(100);
            // 还是需要刷新
            tableView.refresh();
        });

        button2.setOnAction(event -> {
            d2_1.setAge(100);
            // 不需要刷新了
        });
    }

    class Data {
        private String name;
        private int age;
        private double score;
        private boolean bool;

        public Data(String name, int age, double score, boolean bool) {
            this.name = name;
            this.age = age;
            this.score = score;
            this.bool = bool;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public boolean isBool() {
            return bool;
        }

        public void setBool(boolean bool) {
            this.bool = bool;
        }
    }

    class Data2 {
        private SimpleStringProperty name = new SimpleStringProperty();
        private SimpleIntegerProperty age = new SimpleIntegerProperty();
        private SimpleDoubleProperty score = new SimpleDoubleProperty();
        private SimpleBooleanProperty bool = new SimpleBooleanProperty();

        public Data2(String name, int age, double score, boolean bool) {
            this.name.set(name);
            this.age.set(age);
            this.score.set(score);
            this.bool.set(bool);
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

        public int getAge() {
            return age.get();
        }

        public SimpleIntegerProperty ageProperty() {
            return age;
        }

        public void setAge(int age) {
            this.age.set(age);
        }

        public double getScore() {
            return score.get();
        }

        public SimpleDoubleProperty scoreProperty() {
            return score;
        }

        public void setScore(double score) {
            this.score.set(score);
        }

        public boolean isBool() {
            return bool.get();
        }

        public SimpleBooleanProperty boolProperty() {
            return bool;
        }

        public void setBool(boolean bool) {
            this.bool.set(bool);
        }
    }
}

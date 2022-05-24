package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTablePosition;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2022/5/24 14:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson106TreeTableView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Data d1 = new Data("A", 12, true);
        Data d2 = new Data("B", 18, false);
        Data d3 = new Data("C", 15, true);
        Data d4 = new Data("D", 20, false);
        Data d5 = new Data("E", 21, true);

        TreeTableView<Data> ttv = new TreeTableView<>();
        ttv.setPrefWidth(300);
        ttv.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        TreeItem<Data> root = new TreeItem<>(d1);
        ttv.setRoot(root);

        TreeItem<Data> ti1 = new TreeItem<>(d2);
        TreeItem<Data> ti2 = new TreeItem<>(d3);
        TreeItem<Data> ti3 = new TreeItem<>(d4);
        TreeItem<Data> ti2_1 = new TreeItem<>(d5);
        root.getChildren().addAll(ti1, ti2, ti3);
        ti2.getChildren().addAll(ti2_1);
        root.setExpanded(true);
        ti2.setExpanded(true);

        TreeTableColumn<Data, String> name = new TreeTableColumn<>("姓名");
        TreeTableColumn<Data, Number> age = new TreeTableColumn<>("年龄");
        TreeTableColumn<Data, Boolean> bool = new TreeTableColumn<>("布尔");

        ttv.getColumns().addAll(name, age, bool);

        // 还是内部类就会报错
//        name.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        name.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        // 想用 TreeTableColumn<Data, Integer> age 的话，这里使用 param.getValue().getValue().ageProperty()
        age.setCellValueFactory(param -> param.getValue().getValue().ageProperty());
        bool.setCellValueFactory(param -> param.getValue().getValue().boolProperty());

        Button bu = new Button("button");
        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(bu, ttv);
        AnchorPane.setTopAnchor(ttv, 100.0);
        AnchorPane.setLeftAnchor(ttv, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu.setOnAction(event -> {
            d1.setName("test");
        });

        ttv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ttv.getSelectionModel().setCellSelectionEnabled(true);
        ttv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.getValue().getName());
        });
        ttv.getSelectionModel().getSelectedItems().addListener((InvalidationListener) System.out::println);
        ttv.getSelectionModel().getSelectedCells().addListener((Observable observable) -> {
            ObservableList<TreeTablePosition<Data, ?>> list = (ObservableList<TreeTablePosition<Data, ?>>) observable;
            for (TreeTablePosition<Data, ?> ttp : list) {
                Object obj = ttp.getTableColumn().getCellData(ttp.getRow());
                System.out.println("行 = " + ttp.getRow() + ", 列 = " + ttp.getColumn() + ", 内容 = " + obj.toString());
            }
        });
    }

    class Data {
        private SimpleStringProperty name = new SimpleStringProperty();
        private SimpleIntegerProperty age = new SimpleIntegerProperty();
        private SimpleBooleanProperty bool = new SimpleBooleanProperty();

        public Data(String name, int age, boolean bool) {
            this.name.set(name);
            this.age.set(age);
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

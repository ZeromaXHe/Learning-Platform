package com.zerox.javafxLearning.lesson082_111;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @Author: zhuxi
 * @Time: 2022/5/17 17:24
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson086ListView_customizeListCell extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataPro dp1 = new DataPro("A", "10");
        DataPro dp2 = new DataPro("B", "20");
        DataPro dp3 = new DataPro("C", "30");
        DataPro dp4 = new DataPro("D", "40");

        ObservableList<DataPro> list = FXCollections.observableArrayList(param ->
                new SimpleStringProperty[]{param.ageProperty(), param.nameProperty()});

        ListView<DataPro> listView = new ListView<>(list);

        ObservableList<DataPro> obsList = listView.getItems();
        obsList.add(dp1);
        obsList.add(dp2);
        obsList.add(dp3);
        obsList.add(dp4);

        listView.setEditable(true);

        Callback<ListView<DataPro>, ListCell<DataPro>> callback = new Callback<ListView<DataPro>, ListCell<DataPro>>() {
            int index = 0;
            DataPro temp = null;

            @Override
            public ListCell<DataPro> call(ListView<DataPro> param) {

                param.setOnEditStart(event -> {
                    index = event.getIndex();
                    temp = param.getItems().get(index);
                });

                ListCell<DataPro> listCell = new ListCell<DataPro>() {
                    @Override
                    public void updateSelected(boolean selected) {
                        System.out.println("updateSelected");
                        super.updateSelected(selected);
                    }

                    @Override
                    protected boolean isItemChanged(DataPro oldItem, DataPro newItem) {
                        System.out.println("isItemChanged");
                        return super.isItemChanged(oldItem, newItem);
                    }

                    @Override
                    protected void updateItem(DataPro item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            HBox hBox = new HBox(10);
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            ImageView iv = new ImageView("icon/icon.jpeg");
                            iv.setPreserveRatio(true);
                            iv.setFitHeight(30);
                            Button button = new Button(item.getName() + " button");
                            Label name = new Label(item.getName());
                            Label age = new Label(item.getAge());
                            hBox.getChildren().addAll(iv, button, name, age);
                            this.setGraphic(hBox);

                            button.setOnAction(event -> System.out.println(item.getName() + " - " + item.getAge()));
                        }
                    }

                    @Override
                    public void startEdit() {
                        super.startEdit();

                        HBox hBox = new HBox(10);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        ImageView iv = new ImageView("icon/icon.jpeg");
                        iv.setPreserveRatio(true);
                        iv.setFitHeight(30);
                        Button button = new Button(temp.getName() + " button");
                        TextField name = new TextField(temp.getName());
                        name.setPrefWidth(40);
                        TextField age = new TextField(temp.getAge());
                        age.setPrefWidth(40);
                        hBox.getChildren().addAll(iv, button, name, age);
                        this.setGraphic(hBox);

                        name.setOnKeyPressed(event -> {
                            if (event.getCode() == KeyCode.ENTER) {
                                if (!"".equals(name.getText())) {
                                    temp.setName(name.getText());
                                }
                                if (!"".equals(age.getText())) {
                                    temp.setAge(age.getText());
                                }
                                commitEdit(temp);
                            }
                        });
                        age.setOnKeyPressed(event -> {
                            if (event.getCode() == KeyCode.ENTER) {
                                if (!"".equals(name.getText())) {
                                    temp.setName(name.getText());
                                }
                                if (!"".equals(age.getText())) {
                                    temp.setAge(age.getText());
                                }
                                commitEdit(temp);
                            }
                        });
                    }

                    @Override
                    public void cancelEdit() {
                        super.cancelEdit();

                        HBox hBox = new HBox(10);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        ImageView iv = new ImageView("icon/icon.jpeg");
                        iv.setPreserveRatio(true);
                        iv.setFitHeight(30);
                        Button button = new Button(temp.getName() + " button");
                        Label name = new Label(temp.getName());
                        Label age = new Label(temp.getAge());
                        hBox.getChildren().addAll(iv, button, name, age);
                        this.setGraphic(hBox);

                        button.setOnAction(event -> System.out.println(temp.getName() + " - " + temp.getAge()));
                    }

                    @Override
                    public void commitEdit(DataPro newValue) {
                        System.out.println("提交编辑");
                        super.commitEdit(newValue);
                    }
                };
                return listCell;
            }
        };
        listView.setCellFactory(callback);

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

        listView.setPrefWidth(300);
        listView.setPrefHeight(300);
        Button bu = new Button("button4");

        AnchorPane an = new AnchorPane();

        an.getChildren().addAll(listView, bu);

        AnchorPane.setTopAnchor(listView, 100.0);
        AnchorPane.setLeftAnchor(listView, 100.0);

        AnchorPane.setTopAnchor(bu, 50.0);
        AnchorPane.setLeftAnchor(bu, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
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

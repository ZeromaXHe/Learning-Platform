package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * @Author: zhuxi
 * @Time: 2022/1/7 15:37
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson082_084ListView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<Data> obsList = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            obsList.add(new Data("data - " + (char) ('a' + i)));
        }
        // obsList 也可以从 new 出来的 listView.getItems() 获取

        ListView<Data> listView = new ListView<>(obsList);
        listView.setPlaceholder(new Label("没有数据"));
        listView.setPrefWidth(300);
        listView.setPrefHeight(300);
        // 单元格大小
        listView.setFixedCellSize(50);
        // 排列方向
        listView.setOrientation(Orientation.VERTICAL);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getSelectionModel().select(1);
        listView.getSelectionModel().select(2);
        listView.getFocusModel().focus(3);

        listView.getFocusModel().focusedItemProperty().addListener((o, ov, nv) -> System.out.println("focusedItem: " + nv));

        listView.setEditable(true);
        // 还有 ComboBoxListCell.forListView，修改时是下拉列表
        // 还有 ChoiceBoxListCell.forListView，是单选框
        // 还有 CheckBoxListCell.forListView，是多选框，具体使用可以参考第84课
        Callback<ListView<Data>, ListCell<Data>> cell = TextFieldListCell.forListView(new StringConverter<Data>() {
            @Override
            public String toString(Data object) {
                return object.getName();
            }

            @Override
            public Data fromString(String string) {
                return new Data(string + " - edited");
            }
        });
        listView.setCellFactory(cell);

        listView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) ->
                System.out.println("selectedItem: " + nv));
        listView.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) ->
                System.out.println("selectedIndex: " + nv.intValue()));
        // 仅在代码使用scrollTo时有效
        listView.setOnScrollTo(event -> System.out.println("listView.setOnScrollTo: " + event.getScrollTarget()));

        Button bu = new Button("button");
        // 无法获得焦点
        bu.setFocusTraversable(false);
        Button bu2 = new Button("edit");

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(listView, bu, bu2);
        AnchorPane.setTopAnchor(listView, 100.0);
        AnchorPane.setLeftAnchor(listView, 100.0);
        AnchorPane.setLeftAnchor(bu2, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        // 焦点
        listView.requestFocus();

        bu.setOnAction(event -> {
            obsList.set(1, new Data("testSet"));
            obsList.add(new Data("testAdd"));
            obsList.remove(0, 1);

            listView.scrollTo(listView.getItems().size() - 1);
            // clearSelection可以不传参清除全部，也可以传参指定索引
            listView.getSelectionModel().clearSelection();
            listView.getSelectionModel().selectFirst();
            listView.getSelectionModel().selectLast();
            System.out.println(listView.getSelectionModel().getSelectedIndices());
        });

        bu2.setOnAction(event -> {
            listView.edit(2);
        });

        listView.setOnEditStart(event -> {
            System.out.println("listView.setOnEditStart: " + event.getIndex() + " - " + event.getNewValue());
        });

        listView.setOnEditCancel(event -> System.out.println("取消编辑"));
        listView.setOnEditCommit(event -> {
            System.out.println("提交编辑: " + event.getIndex() + " - " + event.getNewValue().getName());
            // 这个监听好像会打断提交修改的事件，导致修改失效。所以这里主动设置一下值
            obsList.set(event.getIndex(), event.getNewValue());
        });
    }

    class Data {
        private String name;

        Data(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}

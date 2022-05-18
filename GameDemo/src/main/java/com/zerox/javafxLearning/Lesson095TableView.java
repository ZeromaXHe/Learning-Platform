package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 17:49
 * @Description: TableView 各种配置以及选择相关的内容
 * @ModifiedBy: zhuxi
 */
public class Lesson095TableView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Data2 d2_1 = new Data2("A", 15, 80, false);
        Data2 d2_2 = new Data2("B", 25, 85, true);
        Data2 d2_3 = new Data2("C", 17, 60, false);
        Data2 d2_4 = new Data2("D", 19, 53, true);

        // 加了 callback
        ObservableList<Data2> list2 = FXCollections.observableArrayList(param ->
                new Observable[]{param.nameProperty(), param.ageProperty(), param.scoreProperty(), param.boolProperty()});
        list2.addAll(d2_1, d2_2, d2_3, d2_4);
        list2.addAll(new Data2("Test1", 15, 80, false),
                new Data2("Test2", 15, 80, false),
                new Data2("Test3", 15, 80, false),
                new Data2("Test4", 15, 80, false),
                new Data2("Test5", 15, 80, false),
                new Data2("Test6", 15, 80, false),
                new Data2("Test7", 15, 80, false),
                new Data2("Test8", 15, 80, false),
                new Data2("Test9", 15, 80, false));

        TableView<Data2> tableView2 = new TableView<>(list2);

        TableColumn<Data2, String> tc_name2 = new TableColumn<>("姓名");
        tc_name2.setCellValueFactory(param -> param.getValue().nameProperty());
        tableView2.getColumns().add(tc_name2);

        TableColumn<Data2, Number> tc_age2 = new TableColumn<>("年龄");
        tc_age2.setCellValueFactory(param -> param.getValue().ageProperty());
        tableView2.getColumns().add(tc_age2);

        TableColumn<Data2, Number> tc_score2 = new TableColumn<>("分数");
        tc_score2.setCellValueFactory(param -> param.getValue().scoreProperty());
        tableView2.getColumns().add(tc_score2);

        TableColumn<Data2, Boolean> tc_bool2 = new TableColumn<>("布尔");
        tc_bool2.setCellValueFactory(param -> param.getValue().boolProperty());
        tableView2.getColumns().add(tc_bool2);

        // 占位符
        tableView2.setPlaceholder(new Label("没有数据"));
        tableView2.setPrefWidth(500);
        tableView2.setPrefHeight(300);

        // 每列平分宽度
        double width = tableView2.getPrefWidth() / tableView2.getColumns().size();
        tc_name2.setPrefWidth(width);
        tc_age2.setPrefWidth(width);
        tc_score2.setPrefWidth(width);
        tc_bool2.setPrefWidth(width);

        // 默认隐藏 bool 列
        tc_bool2.setVisible(false);
        // 是否使用选择显示列的按钮
        tableView2.setTableMenuButtonVisible(true);


        // 滚动到指定行列（这个滚动貌似不会特别精确的滚到指定位置？好像保证指定位置在显示范围内就可以了）
        tableView2.scrollTo(2);
        tableView2.scrollToColumnIndex(2);

        // 会影响 cell 的高，会不会影响宽不太清楚，没观察到
        tableView2.setFixedCellSize(30);

        // 设置可编辑
        tableView2.setEditable(true);
        // 对应的列需要配置可编辑的工厂方法
        tc_name2.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_age2.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Number>() {
            // 数字类型需要实现 StringConverter
            @Override
            public String toString(Number object) {
                return String.valueOf(object.intValue());
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Integer.parseInt(string);
                } catch (Exception e) {
                    return 0;
                }
            }
        }));

        // 选择模式（默认单选）
        tableView2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView2.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            // 在不同行间选择才会触发该监听
            if (ov != null) {
                System.out.println("ov=" + ov.getName());
            }
            // 不加判空的话，反选的时候，最后一次取消所有选项时，会有一个ov != null 而 nv == null 的情况，会空指针
            if (nv != null) {
                System.out.println("nv=" + nv.getName());
            }
            System.out.println("selected indices:" + tableView2.getSelectionModel().getSelectedIndices());
        });

        // 选择的是单元格（默认选择一行）
        tableView2.getSelectionModel().setCellSelectionEnabled(true);

        tableView2.getSelectionModel().getSelectedCells().addListener((Observable o) -> {
            ObservableList<TablePosition> obs = (ObservableList<TablePosition>) o;
            for (TablePosition tp : obs) {
                // 获取所在行列的对象
                Object obj = tp.getTableColumn().getCellData(tp.getRow());
                System.out.println("row = " + tp.getRow() + ", col = " + tp.getColumn() + ", obj = " + obj.toString());
            }
        });

        Button button2 = new Button("button");

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(button2, tableView2);

        AnchorPane.setTopAnchor(tableView2, 100.0);
        AnchorPane.setLeftAnchor(tableView2, 100.0);
        AnchorPane.setTopAnchor(button2, 50.0);
        AnchorPane.setLeftAnchor(button2, 100.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        button2.setOnAction(event -> {
            // 选择指定行列位置的单元格
//            tableView2.getSelectionModel().select(2, tc_age2);
            // 聚焦指定行列位置的单元格
//            tableView2.getFocusModel().focus(2, tc_age2);
            tableView2.getSelectionModel().selectLeftCell();
            tableView2.getSelectionModel().selectAboveCell();
            // 找到可换行的前一个
            tableView2.getSelectionModel().selectPrevious();
            tableView2.requestFocus();
        });
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

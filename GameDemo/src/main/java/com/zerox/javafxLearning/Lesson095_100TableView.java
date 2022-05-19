package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 17:49
 * @Description: 095 ~ 100 课的内容
 * 095 课：TableView 各种配置以及选择相关的内容
 * 096 课：TableView ColumnResizePolicy 和排序相关的一些内容
 * 097 课：五种 TableCell 默认实现
 * TextFieldTableCell、ChoiceBoxTableCell、CheckBoxTableCell、ComboBoxTableCell、ProgressBarTableCell
 * 098 课：自定义 TableCell 实现显示功能
 * 099 课：自定义 TableCell 实现编辑功能
 * 100 课：TableRow 相关
 * @ModifiedBy: zhuxi
 */
public class Lesson095_100TableView extends Application {
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
        list2.addAll(new Data2("Test1", 22, 76, false),
                new Data2("Test2", 19, 62, false),
                new Data2("Test3", 15, 80, true),
                new Data2("Test4", 25, 89, true),
                new Data2("Test5", 15, 55, false),
                new Data2("Test6", 14, 10, true),
                new Data2("Test7", 18, 73, false),
                new Data2("Test8", 5, 20, false),
                new Data2("Test9", 35, 2, true));

        TableView<Data2> tableView2 = new TableView<>(list2);

        TableColumn<Data2, String> tc_name2 = new TableColumn<>("姓名");
        tc_name2.setCellValueFactory(param -> param.getValue().nameProperty());
        tableView2.getColumns().add(tc_name2);

        TableColumn<Data2, Number> tc_age2 = new TableColumn<>("年龄");
        tc_age2.setCellValueFactory(param -> param.getValue().ageProperty());
        tableView2.getColumns().add(tc_age2);

        // 如果要使用 ProgressBarTableCell，这里就只能使用 TableColumn<Data2, Double> 和 PropertyValueFactory
        TableColumn<Data2, Double> tc_score2 = new TableColumn<>("分数");
        // 内部类还是用不了
//        tc_score2.setCellValueFactory(new PropertyValueFactory<>("score"));
        // 加上 asObject() 可以返回 Observable<Double>（具体实现类为 ObjectProperty<Double>）
        // divide(100) 是为了让 ProgressBarTableCell 获取的是 0~1 的值
        tc_score2.setCellValueFactory(param -> param.getValue().scoreProperty().divide(100).asObject());
        tableView2.getColumns().add(tc_score2);

        TableColumn<Data2, Boolean> tc_bool2 = new TableColumn<>("布尔");
        tc_bool2.setCellValueFactory(param -> param.getValue().boolProperty());
        tableView2.getColumns().add(tc_bool2);

        // 自定义 TableRow（实际没什么作用，一般用不到。JavaFX API 文档——即 TableRow 注释——里面也是这么说的）
        tableView2.setRowFactory(param -> new TableRow<Data2>() {
            @Override
            protected void updateItem(Data2 item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty && item != null) {
                    if (item.getAge() < 15) {
                        // 好像有 bug，多滚几次滚动条会把其他的行也涂上颜色
                        this.setStyle("-fx-background-color: #009900");
                    }
                    this.setBorder(new Border(new BorderStroke(
                            Paint.valueOf("#FFB5C5"), BorderStrokeStyle.DOTTED,
                            new CornerRadii(3), new BorderWidths(2))));
                    this.setTooltip(new Tooltip(item.getName() + "-" + item.getAge()));
                }
            }
        });

        // 占位符
        tableView2.setPlaceholder(new Label("没有数据"));
        tableView2.setPrefWidth(500);
        tableView2.setPrefHeight(300);

        // 每列平分宽度
//        double width = tableView2.getPrefWidth() / tableView2.getColumns().size();
//        tc_name2.setPrefWidth(width);
//        tc_age2.setPrefWidth(width);
//        tc_score2.setPrefWidth(width);
//        tc_bool2.setPrefWidth(width);
        // 也可以达到默认平分宽度效果，不过该设置也会使得总宽度限定，不再有横向滚动条
        tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // 自定义
//        tableView2.setColumnResizePolicy(param -> {
//            System.out.println(param.getDelta());
//            if (param.getColumn() != null) {
//                double w = param.getColumn().getPrefWidth();
//                param.getColumn().setPrefWidth(w + param.getDelta() / 10);
//            }
//            // true 的话就无法拖动了
//            return false;
//        });

        // 排列方向
        tableView2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);


        // 点击列名是否可以排序（默认 true）
        tc_name2.setSortable(true);

        // 指定排序优先级（类似按住 shift 依次点击列名的效果）
        tableView2.getSortOrder().addAll(tc_name2, tc_age2);

        // 自定义排序规则，此规则下，只有姓名排序有效
//        tableView2.setSortPolicy(param -> {
//            param.getColumns().forEach(tableColumn -> {
//                if ("姓名".equals(tableColumn.getText())) {
//                    if (tableColumn.getSortType() == TableColumn.SortType.ASCENDING) {
//                        // 自定义排序图标
//                        tableColumn.setSortNode(new Label("升"));
//                        param.getItems().sort(Comparator.comparing(Data2::getName));
//                    } else if (tableColumn.getSortType() == TableColumn.SortType.DESCENDING) {
//                        tableColumn.setSortNode(new Label("降"));
//                        param.getItems().sort(((o1, o2) -> o2.getName().compareTo(o1.getName())));
//                    }
//                }
//            });
//            // 返回 false 则禁用排序
//            return true;
//        });

        // 默认隐藏 bool 列
        tc_bool2.setVisible(false);
        // 是否使用选择显示列的按钮
        tableView2.setTableMenuButtonVisible(true);


        // 滚动到指定行列（这个滚动貌似不会特别精确的滚到指定位置？好像保证指定位置在显示范围内就可以了）
        tableView2.scrollTo(2);
        tableView2.scrollToColumnIndex(2);

        // 会影响 cell 的高，会不会影响宽不太清楚，没观察到
        tableView2.setFixedCellSize(50);

        setCellFactory(tableView2, tc_name2, tc_age2, tc_score2, tc_bool2);
        setSelection(tableView2);

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

    private void setCellFactory(TableView<Data2> tableView2, TableColumn<Data2, String> tc_name2, TableColumn<Data2, Number> tc_age2, TableColumn<Data2, Double> tc_score2, TableColumn<Data2, Boolean> tc_bool2) {
        // 设置可编辑
        tableView2.setEditable(true);
        // 对应的列需要配置可编辑的工厂方法
        tc_name2.setCellFactory(TextFieldTableCell.forTableColumn());
        // 自定义 TableCell, 类似 086 课
        tc_name2.setCellFactory(param -> {
            TableCell<Data2, String> cell = new TableCell<Data2, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // 直接使用自带的 userData 传递值
                    this.setUserData(item);

                    if (!empty && item != null) {
                        HBox hBox = new HBox();
                        hBox.setStyle("-fx-background-color: #ffff55");
                        hBox.setAlignment(Pos.CENTER);
                        Label label = new Label(item);
                        hBox.getChildren().addAll(label);
                        this.setGraphic(hBox);
                    }
                }

                @Override
                public void startEdit() {
                    super.startEdit();

                    HBox hBox = new HBox();
                    hBox.setStyle("-fx-background-color: #ffff55");
                    hBox.setAlignment(Pos.CENTER);
                    TextField tf = new TextField(String.valueOf(this.getUserData()));
                    tf.setAlignment(Pos.CENTER);
                    hBox.getChildren().addAll(tf);
                    this.setGraphic(hBox);

                    tf.setOnKeyPressed(event -> {
                        if (KeyCode.ENTER == event.getCode()) {
                            String nv = tf.getText();
                            this.commitEdit(nv);
                        }
                    });
                }

                @Override
                public void cancelEdit() {
                    super.cancelEdit();

                    HBox hBox = new HBox();
                    hBox.setStyle("-fx-background-color: #ffff55");
                    hBox.setAlignment(Pos.CENTER);
                    Data2 data = (Data2) this.getTableRow().getItem();
                    Label label = new Label(data.getName());
//                    Label label = new Label(String.valueOf(this.getUserData()));
                    hBox.getChildren().addAll(label);
                    this.setGraphic(hBox);
                }

                @Override
                public void commitEdit(String newValue) {
                    super.commitEdit(newValue);
                }
            };
            return cell;
        });

        // 自己实现的简化版 NumberStringConverter
        StringConverter<Number> numberStringConverter = new StringConverter<Number>() {
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
        };
        tc_age2.setCellFactory(TextFieldTableCell.forTableColumn(numberStringConverter));
        tc_age2.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

        // 自己实现的简化版 BooleanStringConverter
        StringConverter<Boolean> booleanStringConverter = new StringConverter<Boolean>() {
            @Override
            public String toString(Boolean object) {
                return String.valueOf(object);
            }

            @Override
            public Boolean fromString(String string) {
                return Boolean.valueOf(string);
            }
        };
        tc_bool2.setCellFactory(ChoiceBoxTableCell.forTableColumn(booleanStringConverter, true, false));
        tc_bool2.setCellFactory(ChoiceBoxTableCell.forTableColumn(new BooleanStringConverter(), true, false));
        // 这个生效
        tc_bool2.setCellFactory(CheckBoxTableCell.forTableColumn(tc_bool2));

        // 进度条单元格 ProgressBarTableCell
        tc_score2.setCellFactory(ProgressBarTableCell.forTableColumn());
        // 自定义 TableCell, 类似 086 课
        tc_score2.setCellFactory(param -> {
            TableCell<Data2, Double> cell = new TableCell<Data2, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);

                    if (!empty && item != null) {
                        HBox hBox = new HBox();
                        hBox.setStyle("-fx-background-color: #55ff55");
                        hBox.setAlignment(Pos.CENTER);

                        ProgressIndicator pi = new ProgressIndicator(item);
                        pi.setPrefWidth(50);
                        pi.setPrefHeight(50);

                        hBox.getChildren().addAll(pi);
                        this.setGraphic(hBox);
                    }
                }
            };
            return cell;
        });
    }

    private void setSelection(TableView<Data2> tableView2) {
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

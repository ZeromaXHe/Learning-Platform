package com.zerox.javafxLearning.lesson082_111;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.ChoiceBoxTreeCell;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * @Author: zhuxi
 * @Time: 2022/5/23 10:58
 * @Description: 第 101 ~ 103 课
 * 101 课： 基本 TreeView
 * 102 课： 各种事件
 * 103 课： 默认的几种 TreeCell
 * 104 课： 自定义 TreeCell
 * 105 课： 节点拖拽
 * @ModifiedBy: zhuxi
 */
public class Lesson101_105TreeView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TreeView<Data> treeViewData = getTreeViewData();

        TreeView<String> treeView = new TreeView<>();
        TreeItem<String> root = new TreeItem<>("中国");
        root.setGraphic(new Button("graphic"));
        TreeItem<String> hlj = new TreeItem<>("黑龙江");
        TreeItem<String> city1 = new TreeItem<>("哈尔滨");
        TreeItem<String> city2 = new TreeItem<>("佳木斯");
        TreeItem<String> city3 = new TreeItem<>("大庆");
        TreeItem<String> gd = new TreeItem<>("广东省");
        TreeItem<String> city4 = new TreeItem<>("广州");
        TreeItem<String> city5 = new TreeItem<>("深圳");
        TreeItem<String> tw = new TreeItem<>("台湾省");
        TreeItem<String> city7 = new TreeItem<>("台北");
        TreeItem<String> city8 = new TreeItem<>("新竹");
        TreeItem<String> city9 = new TreeItem<>("高雄");

        treeView.setRoot(root);
        root.getChildren().addAll(hlj, gd, tw);
        hlj.getChildren().addAll(city1, city2, city3);
        gd.getChildren().addAll(city4, city5);
        tw.getChildren().addAll(city7, city8, city9);

        treeView.setPrefWidth(300);
        treeView.setPrefHeight(300);
        root.setExpanded(true);
        hlj.setExpanded(true);
        gd.setExpanded(true);
        tw.setExpanded(true);
        treeView.scrollTo(9);
        treeView.setFixedCellSize(40);

        root.setValue("CHN");
        treeView.setShowRoot(true);

        System.out.println("root.isLeaf: " + root.isLeaf());
        System.out.println("city1.isLeaf: " + city1.isLeaf());
        System.out.println("city2.previousSibling: " + city2.previousSibling());
        System.out.println("city2.previousSibling(city3): " + city2.previousSibling(city3));
        System.out.println("city2.getParent: " + city2.getParent());

        Button button = new Button("button");
        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(button, treeView, treeViewData);
        AnchorPane.setTopAnchor(treeView, 100.0);
        AnchorPane.setLeftAnchor(treeView, 100.0);
        AnchorPane.setTopAnchor(treeViewData, 100.0);
        AnchorPane.setLeftAnchor(treeViewData, 400.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.getValue() + "选中");

        });

        treeView.getFocusModel().focus(3);
        treeView.requestFocus();

        System.out.println("treeView.getExpandedItemCount:" + treeView.getExpandedItemCount());

        treeView.setEditable(true);
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());
        treeView.setCellFactory(ComboBoxTreeCell.forTreeView("A", "B"));
        treeView.setCellFactory(ChoiceBoxTreeCell.forTreeView("A", "B"));
        treeView.setCellFactory(CheckBoxTreeCell.forTreeView(param -> {
            SimpleBooleanProperty bool = new SimpleBooleanProperty();
            if (treeView.getTreeItemLevel(param) == 1) {
                bool.set(true);
            } else {
                bool.set(false);
            }
            return bool;
        }));
        // 前面的 setCellFactory 设置都不会生效，这里才生效
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            private TreeCell<String> temp;

            @Override
            public TreeCell<String> call(TreeView<String> param) {
                // 自定义 TreeCell
                TreeCell<String> treeCell = new TreeCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            HBox hBox = new HBox();
                            hBox.setStyle("-fx-background-color: #ffff55");
                            Label label = new Label(item);
                            hBox.getChildren().addAll(label);
                            if (this.getTreeItem().isExpanded()) {
                                ImageView iv = new ImageView("icon/icon.jpeg");
                                iv.setPreserveRatio(true);
                                iv.setFitWidth(25);
                                this.setDisclosureNode(iv);
                            } else {
                                ImageView iv = new ImageView("pic/funny.png");
                                iv.setPreserveRatio(true);
                                iv.setFitWidth(25);
                                this.setDisclosureNode(iv);
                            }
                            this.setGraphic(hBox);
                        } else {
                            this.setGraphic(null);
                        }
                    }

                    @Override
                    public void startEdit() {
                        super.startEdit();

                        HBox hBox = new HBox();
                        hBox.setStyle("-fx-background-color: #ffff55");
                        TextField tf = new TextField(this.getItem());
                        hBox.getChildren().addAll(tf);

                        this.setGraphic(hBox);
                        tf.requestFocus();

                        tf.setOnKeyPressed(event -> {
                            if (KeyCode.ENTER == event.getCode()) {
                                commitEdit(tf.getText());
                            }
                        });
                    }

                    @Override
                    public void cancelEdit() {
                        super.cancelEdit();

                        HBox hBox = new HBox();
                        hBox.setStyle("-fx-background-color: #ffff55");
                        Label label = new Label(this.getItem());
                        hBox.getChildren().addAll(label);

                        this.setGraphic(hBox);
                    }
                };

                // 拖拽效果是半成品
                treeCell.setOnDragDetected(event -> {
                    Dragboard db = treeCell.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(treeCell.getItem());

                    Text text = new Text(treeCell.getItem());
                    text.setFont(new Font(15));
                    WritableImage wi = new WritableImage((int) treeCell.getWidth() - 200, 20);
                    text.snapshot(new SnapshotParameters(), wi);

                    db.setDragView(wi);
                    db.setContent(content);
                });
                treeCell.setOnDragOver(event -> {
                    event.acceptTransferModes(TransferMode.COPY);
                    if (temp != null) {
                        temp.setBorder(null);
                    }
                    temp = treeCell;

                    if (event.getY() >= 0 && event.getY() <= treeCell.getHeight() - 10) {
//                        treeCell.setBackground(new Background(new BackgroundFill(Paint.valueOf("#71C671"), new CornerRadii(0),)));
//                        treeCell.setBackground(new Background(new BackgroundImage()));
                    } else if (event.getY() > treeCell.getHeight() - 10 && event.getY() <= treeCell.getHeight()) {
                        BorderStroke bs = new BorderStroke(null, null, Paint.valueOf("#71C671"),
                                null, BorderStrokeStyle.SOLID, null, null, null,
                                null, new BorderWidths(0, 0, 2, 0), null);
                        Border border = new Border(bs);
                        treeCell.setBorder(border);
                    }
                });
                treeCell.setOnDragDropped(event -> {
                    String value = event.getDragboard().getString();
                    if (treeCell.getTreeItem().getParent() != null) {
                        int index = treeCell.getTreeItem().getParent().getChildren().indexOf(treeCell.getTreeItem());
                        treeCell.getTreeItem().getParent().getChildren().add(index + 1, new TreeItem<>(value));
                    }
                    treeCell.setBorder(null);
                });


                return treeCell;
            }
        });

        // 节点会向上一级监听通知，所以直接在 root 上配置会监听到下面所有子节点
//        root.addEventHandler(TreeItem.<String>valueChangedEvent(), event -> {
//            System.out.println(event.getNewValue() + "值修改");
//            System.out.println(event.getTreeItem().getValue() + "值修改");
//        });
//        root.addEventHandler(TreeItem.<String>graphicChangedEvent(),
//                event -> System.out.println(event.getTreeItem().getValue() + "图像修改"));
//        root.addEventHandler(TreeItem.<String>branchCollapsedEvent(),
//                event -> System.out.println(event.getTreeItem().getValue() + "收起"));
//        root.addEventHandler(TreeItem.<String>branchCollapsedEvent(),
//                event -> System.out.println(event.getTreeItem().getValue() + "展开"));
//        root.addEventHandler(TreeItem.<String>childrenModificationEvent(),
//                event -> System.out.println(event.getTreeItem().getValue() + "子节点变化"));
        root.addEventHandler(TreeItem.<String>treeNotificationEvent(),
                event -> {
                    if (event.wasPermutated()) {
                        System.out.println(event.getTreeItem().getValue() + "排序");
                    } else if (event.wasAdded()) {
                        System.out.println(event.getTreeItem().getValue() + "新增");
                    } else if (event.wasRemoved()) {
                        System.out.println(event.getTreeItem().getValue() + "移除");
                    } else if (event.wasExpanded()) {
                        System.out.println(event.getTreeItem().getValue() + "展开");
                    } else if (event.wasCollapsed()) {
                        System.out.println(event.getTreeItem().getValue() + "收起");
                    }
                });
    }

    private TreeView<Data> getTreeViewData() {
        TreeView<Data> treeView = new TreeView<>();
        TreeItem<Data> root = new TreeItem<>(new Data(false, "中国"));
        root.setGraphic(new Button("graphic"));
        TreeItem<Data> hlj = new TreeItem<>(new Data(false, "黑龙江"));
        TreeItem<Data> city1 = new TreeItem<>(new Data(false, "哈尔滨"));
        TreeItem<Data> city2 = new TreeItem<>(new Data(false, "佳木斯"));
        TreeItem<Data> gd = new TreeItem<>(new Data(false, "广东省"));
        TreeItem<Data> city3 = new TreeItem<>(new Data(false, "广州"));
        TreeItem<Data> city4 = new TreeItem<>(new Data(false, "深圳"));

        treeView.setRoot(root);
        root.getChildren().addAll(hlj, gd);
        hlj.getChildren().addAll(city1, city2);
        gd.getChildren().addAll(city3, city4);

        treeView.setPrefWidth(300);
        treeView.setPrefHeight(300);
        root.setExpanded(true);
        hlj.setExpanded(true);
        gd.setExpanded(true);

        treeView.setCellFactory(CheckBoxTreeCell.forTreeView(
                param -> param.getValue().boolProperty(),
                new StringConverter<TreeItem<Data>>() {
                    @Override
                    public String toString(TreeItem<Data> object) {
                        return object.getValue().getDataValue();
                    }

                    @Override
                    public TreeItem<Data> fromString(String string) {
                        return null;
                    }
                }));

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                System.out.println(newValue.getValue().getDataValue() + " - " + newValue.getValue().isBool()));
        return treeView;
    }

    class Data {
        private SimpleBooleanProperty bool = new SimpleBooleanProperty();
        private SimpleStringProperty dataValue = new SimpleStringProperty();

        public Data(boolean bool, String dataValue) {
            this.bool.set(bool);
            this.dataValue.set(dataValue);
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

        public String getDataValue() {
            return dataValue.get();
        }

        public SimpleStringProperty dataValueProperty() {
            return dataValue;
        }

        public void setDataValue(String dataValue) {
            this.dataValue.set(dataValue);
        }
    }
}

package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @Author: zhuxi
 * @Time: 2022/5/17 18:35
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson088ListView_zoomAndDrag extends Application {
    int index = 0;
    String data = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView<String> listViewZoom = getListViewZoom();
        ListView<String> listViewDrag = getListViewDrag();

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(listViewZoom, listViewDrag);

        AnchorPane.setTopAnchor(listViewZoom, 100.0);
        AnchorPane.setLeftAnchor(listViewZoom, 100.0);
        AnchorPane.setTopAnchor(listViewDrag, 100.0);
        AnchorPane.setLeftAnchor(listViewDrag, 450.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

//        listView.setOnScroll(event -> System.out.println(event.getDeltaY()));
    }

    private ListView<String> getListViewZoom() {
        ListView<String> listView = new ListView<>();
        ObservableList<String> obsList = listView.getItems();
        obsList.addAll("数据 A", "数据 B", "数据 C", "数据 D", "数据 E");

        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            int position = 0;

            @Override
            public ListCell<String> call(ListView<String> param) {
                Label label = new Label();
                label.setPrefHeight(20);
                label.setFont(new Font(15));

                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty && item != null) {
                            label.setText(item);
                            this.setGraphic(label);
                        }
                    }
                };

                cell.hoverProperty().addListener((o, ov, nv) -> {
                    if (nv && !"".equals(label.getText())) {
                        position = param.getItems().indexOf(label.getText());
                        label.setPrefHeight(40);
                        label.setFont(new Font(22));
                        // 不加这一行，就不会聚焦到放大的那个 cell（没有 focus 框）
                        param.getFocusModel().focus(position);
                        System.out.println(position + " " + label.getText());
                        // label 比 cell 小
//                        cell.setStyle("-fx-background-color: #ffff55");
                        label.setStyle("-fx-background-color: #ffff55");
                    } else {
                        label.setPrefHeight(20);
                        label.setFont(new Font(15));
                        label.setStyle("-fx-background-color: #ffff5500");
                    }
                });

                return cell;
            }
        });
        listView.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        });

        listView.setOnDragDropped(event -> {
            String value = event.getDragboard().getString();
            listView.getItems().add(value);
        });

        listView.setPrefWidth(300);
        listView.setPrefHeight(300);
        return listView;
    }

    private ListView<String> getListViewDrag() {
        ListView<String> listView = new ListView<>();
        ObservableList<String> obsList = listView.getItems();
        obsList.addAll("数据 A", "数据 B", "数据 C", "数据 D", "数据 E");

        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            int position = 0;
            String positionStr = "";

            @Override
            public ListCell<String> call(ListView<String> param) {
                Label label = new Label();
                label.setPrefHeight(20);
                label.setFont(new Font(15));

                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty && item != null) {
                            label.setText(item);
                            this.setGraphic(label);
                        }
                    }
                };

                cell.setOnDragDetected(event -> {
                    Dragboard db = cell.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    ClipboardContent content = new ClipboardContent();

                    Text text = new Text(data);
                    WritableImage wi = new WritableImage((int) param.getPrefWidth() - 40, 20);
                    text.snapshot(new SnapshotParameters(), wi);

                    content.putString(data);
                    db.setContent(content);
                    db.setDragView(wi);
                });

                cell.setOnDragEntered(event -> {
                    position = param.getItems().indexOf(label.getText());
                    positionStr = label.getText();
                    param.getFocusModel().focus(position);
                    System.out.println(position + " " + label.getText());
                });

                cell.setOnDragOver(event -> {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                });

                cell.setOnDragDropped(event -> {
                    if (position == -1) {
                        position = param.getItems().size() - 1;
                    }
                    String value = event.getDragboard().getString();
                    param.getItems().remove(index);
                    param.getItems().add(position, value);
                    param.getSelectionModel().select(position);
                });
                return cell;
            }
        });

        listView.setPrefWidth(300);
        listView.setPrefHeight(300);

        listView.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            index = nv.intValue();
        });
        listView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            data = nv;
        });
        return listView;
    }
}

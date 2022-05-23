package com.zerox.javafxLearning.lesson065_081;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.Serializable;

/**
 * @Author: zhuxi
 * @Time: 2022/1/7 11:58
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson081Dragboard extends Application {
    private DataFormat person_format = new DataFormat("data/person");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Person person = new Person("小A", "18", "/icon/icon.jpeg");

        VBox view = getDataPane();
        TextField tf1 = (TextField) view.getChildren().get(1);
        TextField tf2 = (TextField) view.getChildren().get(2);
        ImageView imageView = (ImageView) view.getChildren().get(3);
        tf1.setText(person.getName());
        tf2.setText(person.getAge());
        imageView.setImage(new Image(person.getPhoto()));

        AnchorPane an = new AnchorPane();

        Button button = new Button("数据: " + person.getName());

        VBox dataPane = getDataPane();

        an.getChildren().addAll(button, dataPane);
        AnchorPane.setTopAnchor(button, 100.0);
        AnchorPane.setLeftAnchor(button, 10.0);
        AnchorPane.setTopAnchor(dataPane, 100.0);
        AnchorPane.setLeftAnchor(dataPane, 200.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent content = new ClipboardContent();

            // 必须有图像才能截图
            an.getChildren().add(view);

            // 这里获取不到 view 的宽高，因为没有在 show 方法前把 view 添加到 an。
            WritableImage wi = new WritableImage((int) dataPane.getPrefWidth(), (int) dataPane.getPrefHeight());
            view.snapshot(new SnapshotParameters(), wi);
            db.setDragView(wi);

            // 截完图可以去掉
            an.getChildren().remove(an.getChildren().size() - 1);

            content.put(person_format, person);
            db.setContent(content);
        });

        dataPane.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        });

        dataPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            Object obj = db.getContent(person_format);
//            Object obj = db.getContent(DataFormat.lookupMimeType("data/person"));
            Person person1 = (Person) obj;
            TextField t1 = (TextField) dataPane.getChildren().get(1);
            TextField t2 = (TextField) dataPane.getChildren().get(2);
            ImageView iv = (ImageView) dataPane.getChildren().get(3);
            t1.setText(person1.getName());
            t2.setText(person1.getAge());
            iv.setImage(new Image(person1.getPhoto()));
        });
    }

    private VBox getDataPane() {
        VBox dataPane = new VBox(10);
        dataPane.setStyle("-fx-border-color: #ff0000");
        dataPane.setPrefWidth(300);
        dataPane.setPrefHeight(300);

        Button bu = new Button("个人详情");
        bu.prefWidthProperty().bind(dataPane.prefWidthProperty());

        TextField t1 = new TextField();
        t1.setAlignment(Pos.CENTER);
        TextField t2 = new TextField();
        t2.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(dataPane.getPrefWidth());

        dataPane.getChildren().addAll(bu, t1, t2, iv);
        return dataPane;
    }

    class Person implements Serializable {
        private static final long serialVersionUID = 5235074556290483455L;
        private String name;
        private String age;
        private String photo;

        public Person(String name, String age, String photo) {
            this.name = name;
            this.age = age;
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

}

package com.zerox.javafxLearning.Lesson024_050;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/17 21:35
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson030Tab extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        TabPane tabPane = new TabPane();
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#87CEEB"), new CornerRadii(10), new Insets(20)));
        tabPane.setBackground(bg);
        Border bor = new Border(new BorderStroke(Paint.valueOf("#EE2C2C"), BorderStrokeStyle.DOTTED, new CornerRadii(10), new BorderWidths(5)));
        tabPane.setBorder(bor);

//        tabPane.setStyle("-fx-background-color: #FFA07A");
        Tab tab1 = new Tab("tab1");
        Tab tab2 = new Tab("tab2");
        Tab tab3 = new Tab("tab3");

        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #FF6984");
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(new Button("button1"), new Button("button2"), new Button("button3"));
        tab1.setContent(hBox);

        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #B9D3EE");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10.0);
        vBox.getChildren().addAll(new Button("button4"), new Button("button5"));
        tab2.setContent(vBox);

        tabPane.setPrefWidth(300);
        tabPane.setPrefHeight(300);
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        AnchorPane.setTopAnchor(tabPane, 100.0);
        AnchorPane.setLeftAnchor(tabPane, 100.0);
        an.getChildren().add(tabPane);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tab test");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        // 想让图片正立，需要下面到setSide的代码放在show前面
        ImageView img = new ImageView("/icon/icon.jpeg");
        img.setFitWidth(25.0);
        img.setFitHeight(25.0);
        tab1.setGraphic(img);

        tabPane.setSide(Side.RIGHT);

        tab2.setClosable(false);

        tab3.setDisable(true);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        tabPane.getSelectionModel().select(tab2);
//        tabPane.getSelectionModel().selectLast();
//        tabPane.getSelectionModel().selectPrevious();

        tabPane.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> System.out.println(nv.getText()));

        tab1.setOnSelectionChanged(event -> {
            Tab t = (Tab) event.getSource();
            System.out.println("tab1的选择状态是 = " + t.isSelected());
        });

        an.setOnMouseClicked(event -> tabPane.getTabs().add(new Tab("tab" + (tabPane.getTabs().size() + 1))));

        tab1.setOnClosed(event -> System.out.println("tab1.setOnClosed"));
        tab1.setOnCloseRequest(event -> {
            System.out.println("tab1.setOnCloseRequest");
//            event.consume();
        });
    }
}

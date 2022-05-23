package com.zerox.javafxLearning.lesson127_137Chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @Author: zhuxi
 * @Time: 2022/5/20 10:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson127PieChart extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PieChart.Data d1 = new PieChart.Data("data1", 40);
        PieChart.Data d2 = new PieChart.Data("data2", 10);
        PieChart.Data d3 = new PieChart.Data("data3", 30);
        PieChart.Data d4 = new PieChart.Data("data4", 20);
        PieChart.Data d5 = new PieChart.Data("data5", 50);

        ObservableList<PieChart.Data> obsList = FXCollections.observableArrayList();
        obsList.addAll(d1, d2, d3, d4, d5);
        PieChart pc = new PieChart(obsList);
        pc.setLabelLineLength(50);
        pc.setStartAngle(90);
        pc.setLabelsVisible(true);
        pc.setLegendVisible(true);
        pc.setLegendSide(Side.RIGHT);
        pc.setTitle("PieChart");
        pc.setTitleSide(Side.LEFT);
        pc.setAnimated(true);
        pc.setClockwise(true);

        pc.getData().forEach(data -> {
            // 想要自定义颜色的话，可以参考官方 css 文档：https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html
            Node node = data.getNode();
            Tooltip tip = new Tooltip(data.getName() + " - " + data.getPieValue());
            tip.textProperty().bind(data.nameProperty().concat(" - ").concat(data.pieValueProperty()));
            tip.setFont(new Font(20));
            Tooltip.install(node, tip);
        });

        Button bu = new Button("button");

        AnchorPane an = new AnchorPane();
        an.getChildren().addAll(bu, pc);

        AnchorPane.setTopAnchor(pc, 100.0);
        AnchorPane.setLeftAnchor(pc, 100.0);

        Scene scene = new Scene(an);

        // 加载 css
        // JavaFX css 优先级顺序： 默认 caspian.css < API设置 < 用户的 Scene css < 用户的 Parent css < .setStyle()
        URL url = this.getClass().getClassLoader().getResource("css/Lesson127PieChart.css");
        scene.getStylesheets().add(url.toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu.setOnAction(event -> d1.setPieValue(100));
    }
}

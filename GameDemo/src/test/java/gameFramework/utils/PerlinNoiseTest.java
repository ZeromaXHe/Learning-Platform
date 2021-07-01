package gameFramework.utils;

import com.zerox.gameFramework.utils.PerlinNoiseUtils;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: zhuxi
 * @Time: 2021/6/29 11:11
 * @Description:
 * @Modified By: zhuxi
 */
public class PerlinNoiseTest extends Application {
    @Test
    public void perlin1DTest() {
        System.out.println(IntStream.rangeClosed(0, 1000)
                .mapToDouble(i -> (double) i / 1000.0)
                .map(PerlinNoiseUtils::perlin1D)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    @Test
    public void perlin2DTest() {
//        DoubleStream.iterate(0.0, d -> d += 0.1)
//                .limit(100)
//                .forEach(dx -> System.out.println(DoubleStream.iterate(0.0, d -> d += 0.1)
//                        .limit(100)
//                        .map(dy -> PerlinNoiseUtils.perlin2D(dx, dy))
//                        .mapToObj(d -> String.format("%.2f", d))
//                        .collect(Collectors.joining(",\t "))));
        double max = -1, maxX = -1, maxY = -1;
        double min = 1, minX = -1, minY = -1;

        for (double x = 0.0; x < 40.0; x += 0.1) {
            for (double y = 0.0; y < 40.0; y += 0.1) {
                double perlin2D = PerlinNoiseUtils.perlin2D(x, y);
                if (perlin2D > max) {
                    max = perlin2D;
                    maxX = x;
                    maxY = y;
                }
                if (perlin2D < min) {
                    min = perlin2D;
                    minX = x;
                    minY = y;
                }
            }
        }
        System.out.println("max:" + max + ", maxX:" + maxX + ", maxY:" + maxY);
        System.out.println("min:" + min + ", minX:" + minX + ", minY:" + minY);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LineChart<Number, Number> linechart = getPerline1DLineChart();

        Canvas canvas = getPerline2DCanvas();
        canvas.setLayoutX(75);
        canvas.setLayoutY(400);

        Group root = new Group(linechart, canvas);
        Scene scene = new Scene(root, 550, 900);

        primaryStage.setTitle("Perlin noise test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private LineChart<Number, Number> getPerline1DLineChart() {
        int maxX = 20;

        //Defining X axis
        NumberAxis xAxis = new NumberAxis(0, maxX, 0.1);
        xAxis.setLabel("x axis");

        //Defining y axis
        NumberAxis yAxis = new NumberAxis(-200, 200, 2);
        yAxis.setLabel("y axis - Perlin noise");

        LineChart<Number, Number> linechart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Perlin nosie curve");

        IntStream.rangeClosed(0, 10 * maxX)
                .mapToDouble(i -> (double) i / 10.0)
                .forEach(d -> series.getData().add(new XYChart.Data<>(d, PerlinNoiseUtils.perlin1D(d))));
//        series.getData().add(new XYChart.Data<>(0.001, 2.2810133775));
//        series.getData().add(new XYChart.Data<>(0.111, 3.1424870399999993));
//        series.getData().add(new XYChart.Data<>(0.225, 1.868046386203999));
//        series.getData().add(new XYChart.Data<>(0.409, 0));
//        series.getData().add(new XYChart.Data<>(0.689, -1));
//        series.getData().add(new XYChart.Data<>(0.980, -3));

        //Setting the data to Line chart
        linechart.getData().add(series);
        linechart.setTitle("一维柏林噪声折线图");
        return linechart;
    }

    private Canvas getPerline2DCanvas() {
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double maxAbs = getPerlin2DMaxAbs();

        for (double x = 0.0; x < 40.0; x += 0.1) {
            for (double y = 0.0; y < 40.0; y += 0.1) {
                gc.setFill(doubleToColor(PerlinNoiseUtils.perlin2D(x, y) / maxAbs));
                gc.fillRect(10 * x, 10 * y, 1, 1);
            }
        }
        // stream的.limit()中数字太大会导致内存溢出
//        DoubleStream.iterate(0.0, d -> d += 0.1)
//                .limit(400)
//                .forEach(dx -> DoubleStream.iterate(0.0, d -> d += 0.1)
//                        .limit(400)
//                        .forEach(dy -> {
//                            gc.setFill(doubleToColor(PerlinNoiseUtils.perlin2D(dx, dy)));
//                            gc.fillRect(10 * dx, 10 * dy, 1, 1);
//                        }));
        return canvas;
    }

    private double getPerlin2DMaxAbs() {
        double max = 0;
        double min = 0;
        for (double x = 0.0; x < 40.0; x += 0.1) {
            for (double y = 0.0; y < 40.0; y += 0.1) {
                double perlin2D = PerlinNoiseUtils.perlin2D(x, y);
                if (perlin2D > max) {
                    max = perlin2D;
                }
                if (perlin2D < min) {
                    min = perlin2D;
                }
            }
        }
        return Math.max(max, -min);
    }

    private Color doubleToColor(double d) {
        if (d < 0) {
            return Color.rgb(0, 0, Math.min((int) (255 * -d), 255));
        } else {
            return Color.rgb(0, Math.min((int) (255 * d), 255), 0);
        }
    }
}

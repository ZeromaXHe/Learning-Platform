package strategy.gamemap;

import com.zerox.strategy.controller.StrategyGameController;
import com.zerox.strategy.entity.MapSquare;
import com.zerox.strategy.entity.MapSquareGeo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/6/30 10:56
 * @Description:
 * @Modified By: zhuxi
 */
public class CircleGameMapTest extends Application {
    StrategyGameController strategyGameController = StrategyGameController.getSingletonInstance();

    private static final int MAP_X = 400;
    private static final int MAP_Y = 300;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        strategyGameController.initMap(MAP_X, MAP_Y);
        Canvas canvas = getCircleGameMapCanvas();

        Group root = new Group(canvas);
        Scene scene = new Scene(root, MAP_X, MAP_Y);

        primaryStage.setTitle("Perlin noise test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Canvas getCircleGameMapCanvas() {
        Canvas canvas = new Canvas(MAP_X, MAP_Y);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        MapSquare[][] map = strategyGameController.getMap();

        for (int x = 0; x < MAP_X; x++) {
            for (int y = 0; y < MAP_Y; y++) {
                gc.setFill(geoTypeToColor(map[x][y].getGeo()));
                gc.fillRect(x, y, 1, 1);
            }
        }

        return canvas;
    }

    private Color geoTypeToColor(MapSquareGeo geo) {
        switch (geo.getType()){
            case MOUNTAIN:
                return Color.GREY;
            case HILL:
                return Color.GREENYELLOW;
            case PLAIN:
                return Color.GREEN;
            case OCEAN:
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }
}

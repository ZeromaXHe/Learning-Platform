package strategy.gamemap;

import com.zerox.strategy.controller.StrategyGameController;
import com.zerox.strategy.entity.gamemap.GameMapType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import strategy.testutils.GameMapTestUtils;

/**
 * @Author: zhuxi
 * @Time: 2021/7/1 10:59
 * @Description:
 * @Modified By: zhuxi
 */
public class PerlinNoiseGameMapTest extends Application {
    private StrategyGameController strategyGameController = StrategyGameController.getSingletonInstance();

    private static final int MAP_X = 400;
    private static final int MAP_Y = 300;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        strategyGameController.initMap(MAP_X, MAP_Y, GameMapType.PERLIN_NOISE);
        Canvas canvas = GameMapTestUtils.getGameMapCanvas(strategyGameController.getMap());

        Group root = new Group(canvas);
        Scene scene = new Scene(root, MAP_X, MAP_Y);

        primaryStage.setTitle("perlin noise game map test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

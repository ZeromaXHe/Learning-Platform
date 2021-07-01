package strategy.testutils;

import com.zerox.strategy.entity.MapSquare;
import com.zerox.strategy.entity.MapSquareGeo;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @Author: zhuxi
 * @Time: 2021/7/1 10:53
 * @Description:
 * @Modified By: zhuxi
 */
public final class GameMapTestUtils {
    public static Canvas getGameMapCanvas(MapSquare[][] map) {
        int mapX = map.length;
        int mapY = map[0].length;
        Canvas canvas = new Canvas(mapX, mapY);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int x = 0; x < mapX; x++) {
            for (int y = 0; y < mapY; y++) {
                gc.setFill(GameMapTestUtils.geoTypeToColor(map[x][y].getGeo()));
                gc.fillRect(x, y, 1, 1);
            }
        }

        return canvas;
    }

    private static Color geoTypeToColor(MapSquareGeo geo) {
        switch (geo.getType()) {
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

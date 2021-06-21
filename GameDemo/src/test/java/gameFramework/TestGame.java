package gameFramework;

import com.zerox.gameFramework.Framework;
import com.zerox.gameFramework.app.App;
import com.zerox.gameFramework.app.Game;
import gameFramework.view.HomeView;
import gameFramework.view.PlayView;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 21:20
 * @Description: 参考 B站 小白猴LWM JavaFX游戏框架开发
 * @Modified By: ZeromaXHe
 */
public class TestGame extends Game {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onLaunch() {
        App app = Framework.app;
        app.setTitle("Test Game");
        app.setHeight(600);
        app.setWidth(800);

        app.regView("Home", new HomeView());
        app.regView("Play", new PlayView());
        app.gotoView("Home");
    }

    @Override
    public void onFinish() {
        System.out.println("Finish");
    }

    @Override
    public boolean onExit() {
        System.out.println("Exit");
        return true;
    }
}

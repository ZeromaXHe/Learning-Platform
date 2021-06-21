package com.zerox.gameFramework.app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 21:18
 * @Description: 参考 B站 小白猴LWM JavaFX游戏框架开发
 * @Modified By: ZeromaXHe
 */
public abstract class Game extends Application {
    private App app;

    public abstract void onLaunch();

    public void onFinish() {
        // do something in subclass
    }

    public boolean onExit() {
        // do something in subclass
        return true;
    }

    @Override
    public final void start(Stage primaryStage) throws Exception {
        app = new App(primaryStage);
        app.onLaunch = this::onLaunch;
        app.onFinish = this::onFinish;
        app.onExit = this::onExit;

        app.launch();
    }

    @Override
    public final void stop() throws Exception {
        app.finish();
    }
}

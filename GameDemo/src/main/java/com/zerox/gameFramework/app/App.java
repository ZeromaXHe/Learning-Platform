package com.zerox.gameFramework.app;

import com.zerox.gameFramework.Framework;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 21:13
 * @Description: 参考 B站 小白猴LWM JavaFX游戏框架开发
 * @Modified By: ZeromaXHe
 */
public class App {
    private final Stage stage;
    private final Scene scene;
    private final Pane root;

    private final HashMap<String, View> viewMap;
    private final ObjectProperty<View> currentView;

    OnLaunch onLaunch;
    OnFinish onFinish;
    OnExit onExit;

    public App(Stage stage) {
        this.stage = stage;

        this.root = new StackPane();
        this.scene = new Scene(root);
        stage.setScene(scene);

        viewMap = new HashMap<>();
        currentView = new SimpleObjectProperty<>();

        initFramework();
        initApp();
    }

    private void initFramework() {
        Framework.app = this;
    }

    private void initApp() {
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            if (onExit != null && !onExit.handle()) {
                event.consume();
            }
        });

        currentView.addListener((observable, oldVal, newVal) -> {
            if (oldVal != null) {
                oldVal.onLeave();
                root.getChildren().remove(oldVal.getPane());
            }
            if (newVal != null) {
                root.getChildren().add(newVal.getPane());
                newVal.onEnter();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return stage.getTitle();
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public StringProperty titleProperty() {
        return stage.titleProperty();
    }

    public double getWidth() {
        return root.getMinWidth();
    }

    public void setWidth(double width) {
        root.setMinWidth(width);
    }

    public DoubleProperty widthProperty() {
        return root.minWidthProperty();
    }

    public double getHeight() {
        return root.getMinHeight();
    }

    public void setHeight(double height) {
        root.setMinHeight(height);
    }

    public DoubleProperty heightProperty() {
        return root.minHeightProperty();
    }

    public View getView(String name) {
        return viewMap.get(name);
    }

    public void regView(String name, View view) {
        viewMap.put(name, view);
    }

    public void unRegView(String name) {
        viewMap.remove(name);
    }

    public View getCurrentView() {
        return currentView.get();
    }

    public ReadOnlyObjectProperty<View> currentViewProperty() {
        return currentView;
    }

    public void gotoView(String name) {
        View view = viewMap.get(name);

        if (view != null) {
            currentView.set(view);
        }
    }

    void launch() {
        if (onLaunch != null) {
            onLaunch.handle();
        }

        for (View view : viewMap.values()) {
            view.onLaunch();
        }

        stage.requestFocus();
        stage.show();
    }

    void finish() {
        for (View view : viewMap.values()) {
            view.onFinish();
        }

        if (onFinish != null) {
            onFinish.handle();
        }
    }

    public void exit() {
        Platform.exit();
    }

    interface OnLaunch {
        void handle();
    }

    interface OnFinish {
        void handle();
    }

    interface OnExit {
        boolean handle();
    }
}

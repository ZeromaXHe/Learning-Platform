package com.zerox.gameFramework.app;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 21:45
 * @Description:
 * @Modified By: ZeromaXHe
 */
public abstract class View {
    private final Pane pane;

    public View() {
        pane = new StackPane();
        pane.setBackground(Background.EMPTY);
    }

    public Pane getPane() {
        return pane;
    }

    public ObservableList<Node> getChildren() {
        return pane.getChildren();
    }

    public abstract void onLaunch();

    public void onEnter() {
        // do something in subclass
    }

    public void onStart() {
        // do something in subclass
    }

    public void onUpdate(double time) {
        // do something in subclass
    }

    public void onStop() {
        // do something in subclass
    }

    public void onLeave() {
        // do something in subclass
    }

    public void onFinish() {
        // do something in subclass
    }
}

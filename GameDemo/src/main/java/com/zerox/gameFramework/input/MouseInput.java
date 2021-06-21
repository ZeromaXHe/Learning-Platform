package com.zerox.gameFramework.input;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/22 0:01
 * @Description: 【JavaFX游戏框架开发】第06期-鼠标输入
 * https://www.bilibili.com/video/BV1Kb41187R1
 * @Modified By: ZeromaXHe
 */
public class MouseInput {
    private double pointX;
    private double pointY;

    private final class Unit {
        private boolean pressed;
        private boolean released;
        private boolean held;

        private double pressX;
        private double pressY;

        private double releaseX;
        private double releaseY;

        private double dragX;
        private double dragY;
        private double dragMarkX;
        private double dragMarkY;
        private double dragStoreX;
        private double dragStoreY;

        private int clickCount;
        private int clickStore;
        private long clickStamp;

        private static final int CLICK_DURATION = 200;

        public Unit() {

        }

        public void press(double x, double y) {
            if (!held) {
                pressed = true;
                held = true;

                pressX = x;
                pressY = y;

                dragMarkX = x;
                dragMarkX = y;
            }
        }

        public void release(double x, double y) {
            if (held) {
                released = true;
                held = false;

                releaseX = x;
                releaseY = y;

                dragMarkX = 0;
                dragMarkY = 0;

                dragStoreX = 0;
                dragStoreY = 0;

                clickCount = ++clickStore;
                clickStamp = System.currentTimeMillis();
            }
        }

        public void drag(double x, double y) {
            dragX = (dragStoreX += x - dragMarkX);
            dragY = (dragStoreY += y - dragMarkY);

            dragMarkX = x;
            dragMarkY = y;
        }

        public void refresh() {
            pressed = false;
            released = false;

            pressX = 0;
            pressY = 0;

            releaseX = 0;
            releaseY = 0;

            dragX = 0;
            dragY = 0;

            clickCount = 0;

            if (clickStamp > 0) {
                long now = System.currentTimeMillis();
                long stamp = clickStamp;
                if (now - stamp > CLICK_DURATION) {
                    clickStore = 0;
                    clickStamp = 0;
                }
            }
        }

        public void reset() {
            pressed = false;
            released = false;
            held = false;

            pressX = 0;
            pressY = 0;

            releaseX = 0;
            releaseY = 0;

            dragX = 0;
            dragY = 0;
            dragMarkX = 0;
            dragMarkY = 0;
            dragStoreX = 0;
            dragStoreY = 0;

            clickCount = 0;
            clickStore = 0;
            clickStamp = 0;
        }
    }

    private final Unit[] units;

    private double scrollValue;

    public MouseInput() {
        units = new Unit[Mouse.values().length];
        for (int i = 0; i < units.length; i++) {
            units[i] = new Unit();
        }
    }

    public double getPointX() {
        return pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public boolean isPressed(Mouse mouse) {
//        return mouse != null ? units[mouse.ordinal()].pressed : false;
        return mouse != null && units[mouse.ordinal()].pressed;
    }

    public boolean isReleased(Mouse mouse) {
//        return mouse != null ? units[mouse.ordinal()].released : false;
        return mouse != null && units[mouse.ordinal()].released;
    }

    public boolean isHeld(Mouse mouse) {
//        return mouse != null ? units[mouse.ordinal()].held : false;
        return mouse != null && units[mouse.ordinal()].held;
    }

    public double getPressX(Mouse mouse) {
        return mouse != null ? units[mouse.ordinal()].pressX : 0;
    }

    public double getPressY(Mouse mouse) {
        return mouse != null ? units[mouse.ordinal()].pressY : 0;
    }

    public double getReleaseX(Mouse mouse) {
        return mouse != null ? units[mouse.ordinal()].releaseX : 0;
    }

    public double getReleaseY(Mouse mouse) {
        return mouse != null ? units[mouse.ordinal()].releaseY : 0;
    }

    public boolean isDragged(Mouse mouse) {
        return getDragX(mouse) > 0 || getDragY(mouse) > 0;
    }

    public double getDragX(Mouse mouse) {
        return mouse != null ? units[mouse.ordinal()].dragX : 0;
    }

    public double getDragY(Mouse mouse) {
        return mouse != null ? units[mouse.ordinal()].dragY : 0;
    }

    public boolean isClicked(Mouse mouse) {
        return getClickCount(mouse) > 0;
    }

    public int getClickCount(Mouse mouse) {
        return mouse != null ? units[mouse.ordinal()].clickCount : 0;
    }

    public boolean isScrolled(){
        return scrollValue != 0;
    }

    public double getScrollValue() {
        return scrollValue;
    }

    public void install(Stage stage) {
        stage.addEventHandler(MouseEvent.MOUSE_MOVED, this::handleMouseMoved);
        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
        stage.addEventHandler(MouseEvent.MOUSE_RELEASED, this::handleMouseReleased);
        stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDragged);
        stage.addEventHandler(ScrollEvent.SCROLL, this::handleMouseScrolled);

        reset();
    }

    public void uninstall(Stage stage) {
        stage.removeEventHandler(MouseEvent.MOUSE_MOVED, this::handleMouseMoved);
        stage.removeEventHandler(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
        stage.removeEventHandler(MouseEvent.MOUSE_RELEASED, this::handleMouseReleased);
        stage.removeEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDragged);
        stage.removeEventHandler(ScrollEvent.SCROLL, this::handleMouseScrolled);

        reset();
    }

    private void handleMouseMoved(MouseEvent event) {
        point(event.getX(), event.getY());
    }

    private void handleMousePressed(MouseEvent event) {
        press(Mouse.find(event.getButton()), event.getX(), event.getY());
    }

    private void handleMouseReleased(MouseEvent event) {
        release(Mouse.find(event.getButton()), event.getX(), event.getY());
    }

    private void handleMouseDragged(MouseEvent event) {
        point(event.getX(), event.getY());
        drag(Mouse.find(event.getButton()), event.getX(), event.getY());
    }

    private void handleMouseScrolled(ScrollEvent event) {
        scroll(event.getDeltaY());
    }

    public void point(double x, double y) {
        pointX = x;
        pointY = y;
    }

    public void press(Mouse mouse, double x, double y) {
        if (mouse != null) {
            units[mouse.ordinal()].press(x, y);
        }
    }

    public void release(Mouse mouse, double x, double y) {
        if (mouse != null) {
            units[mouse.ordinal()].release(x, y);
        }
    }

    public void drag(Mouse mouse, double x, double y) {
        if (mouse != null) {
            units[mouse.ordinal()].drag(x, y);
        }
    }

    public void scroll(double value) {
        scrollValue += value;
    }

    public void refresh() {
        for (int i = 0; i < units.length; i++) {
            units[i].refresh();
        }
        scrollValue = 0;
    }

    public void reset() {
        pointX = 0;
        pointY = 0;
        for (int i = 0; i < units.length; i++) {
            units[i].reset();
        }
        scrollValue = 0;
    }
}

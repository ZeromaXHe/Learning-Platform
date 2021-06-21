package com.zerox.gameFramework.input;

import javafx.scene.input.MouseButton;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 23:58
 * @Description: 【JavaFX游戏框架开发】第06期-鼠标输入
 * https://www.bilibili.com/video/BV1Kb41187R1
 * @Modified By: ZeromaXHe
 */
public enum Mouse {
    LEFT(MouseButton.PRIMARY),
    MIDDLE(MouseButton.MIDDLE),
    RIGHT(MouseButton.SECONDARY)
    ;

    private final MouseButton button;

    Mouse(MouseButton button) {
        this.button = button;
    }

    public MouseButton getButton() {
        return button;
    }

    public static Mouse find(MouseButton button) {
        for (Mouse m : values()) {
            if (m.button != null && m.button == button) {
                return m;
            }
        }
        return null;
    }
}

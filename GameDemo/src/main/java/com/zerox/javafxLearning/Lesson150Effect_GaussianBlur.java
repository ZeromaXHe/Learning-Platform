package com.zerox.javafxLearning;

import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:15
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson150Effect_GaussianBlur extends LessonEffectTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Effect getEffect() {
        GaussianBlur gb = new GaussianBlur();
        // 最大 63 默认 10
        gb.setRadius(5);
        return gb;
    }
}

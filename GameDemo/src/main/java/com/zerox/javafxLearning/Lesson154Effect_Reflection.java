package com.zerox.javafxLearning;

import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 22:17
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson154Effect_Reflection extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        Reflection reflection = new Reflection();
        reflection.setBottomOpacity(0.2);
        reflection.setTopOpacity(0.7);
        reflection.setFraction(1);
        reflection.setTopOffset(10);
        return reflection;
    }
}

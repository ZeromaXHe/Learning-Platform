package com.zerox.javafxLearning;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:27
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson157Effect_ColorAdjust extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        ColorAdjust ca = new ColorAdjust();
        // 色相
        ca.setHue(1);
        // 饱和度
        ca.setSaturation(0.5);
        // 亮度
        ca.setBrightness(0.4);
        // 对比度
        ca.setContrast(1);
        return ca;
    }
}

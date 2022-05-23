package com.zerox.javafxLearning.lesson146_162Effect;

import com.zerox.javafxLearning.LessonEffectTemplate;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Paint;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:13
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson156Effect_ColorInput extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        ColorInput colorInput = new ColorInput();
        colorInput.setWidth(100);
        colorInput.setHeight(100);
        colorInput.setX(0);
        colorInput.setY(0);
        colorInput.setPaint(Paint.valueOf("#9AFF9A"));
        return colorInput;
    }
}

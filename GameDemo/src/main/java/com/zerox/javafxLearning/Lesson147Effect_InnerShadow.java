package com.zerox.javafxLearning;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:47
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson147Effect_InnerShadow extends LessonEffectTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Effect getEffect() {
        InnerShadow is = new InnerShadow();
        is.setColor(Color.valueOf("#FF0000"));
        is.setWidth(30);
        is.setHeight(30);
        is.setBlurType(BlurType.GAUSSIAN);
        is.setOffsetX(10);
        is.setOffsetY(10);
        is.setRadius(40);
        is.setChoke(0.1);
        return is;
    }
}

package com.zerox.javafxLearning;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:52
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson148Effect_Shadow extends LessonEffectTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Effect getEffect() {
        Shadow s = new Shadow();
        s.setColor(Color.DEEPPINK);
        s.setWidth(100);
        s.setHeight(100);
        s.setBlurType(BlurType.GAUSSIAN);
        s.setRadius(40);
        return s;
    }
}

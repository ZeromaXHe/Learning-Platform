package com.zerox.javafxLearning.lesson146_162Effect;

import com.zerox.javafxLearning.LessonEffectTemplate;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:10
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson151Effect_MotionBlur extends LessonEffectTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Effect getEffect() {
        MotionBlur mb = new MotionBlur();
        mb.setRadius(5);
        mb.setAngle(-45);
        return mb;
    }
}

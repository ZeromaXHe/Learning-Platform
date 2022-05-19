package com.zerox.javafxLearning;

import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 22:14
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson153Effect_SepiaTone extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(1);
        return sepiaTone;
    }
}

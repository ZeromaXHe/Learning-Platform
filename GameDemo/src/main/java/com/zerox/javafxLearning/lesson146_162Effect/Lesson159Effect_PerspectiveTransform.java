package com.zerox.javafxLearning.lesson146_162Effect;

import com.zerox.javafxLearning.LessonEffectTemplate;
import javafx.scene.effect.Effect;
import javafx.scene.effect.PerspectiveTransform;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:36
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson159Effect_PerspectiveTransform extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        PerspectiveTransform pt = new PerspectiveTransform();
        pt.setUlx(0);
        pt.setUly(0);

        pt.setUrx(100);
        pt.setUry(20);

        pt.setLlx(0);
        pt.setLly(100);

        pt.setLrx(100);
        pt.setLry(100);
        return pt;
    }
}

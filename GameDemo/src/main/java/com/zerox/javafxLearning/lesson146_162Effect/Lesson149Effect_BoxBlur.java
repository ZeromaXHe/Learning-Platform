package com.zerox.javafxLearning.lesson146_162Effect;

import com.zerox.javafxLearning.LessonEffectTemplate;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:22
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson149Effect_BoxBlur extends LessonEffectTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Effect getEffect() {
        BoxBlur bb = new BoxBlur();
        //     Min:   0.0
        //     Max: 255.0
        // Default:   5.0
        bb.setWidth(2);
        bb.setHeight(20);
        //     Min:   0
        //     Max:   3
        // Default:   1
        bb.setIterations(1);
        return bb;
    }
}

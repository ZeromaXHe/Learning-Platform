package com.zerox.javafxLearning.lesson146_162Effect;

import com.zerox.javafxLearning.LessonEffectTemplate;
import javafx.scene.effect.Effect;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:33
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson158Effect_ImageInput extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        ImageInput ii = new ImageInput();
        ii.setSource(new Image("icon/icon.jpeg"));
        ii.setX(0);
        ii.setY(0);
        return ii;
    }
}

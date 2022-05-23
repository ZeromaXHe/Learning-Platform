package com.zerox.javafxLearning.lesson146_162Effect;

import com.zerox.javafxLearning.LessonEffectTemplate;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:37
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson146Effect_DropShadow extends LessonEffectTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Effect getEffect() {
        DropShadow ds = new DropShadow();
        ds.setColor(Color.valueOf("#CD2626"));
        //     Min:   0.0
        //     Max: 255.0
        // Default:  21.0
        ds.setWidth(40);
        ds.setHeight(20);

        ds.setOffsetX(10);
        ds.setOffsetY(10);

        ds.setBlurType(BlurType.GAUSSIAN);
//        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setRadius(30);
        ds.setSpread(0.4);
        return ds;
    }
}

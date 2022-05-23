package com.zerox.javafxLearning.lesson146_162Effect;

import com.zerox.javafxLearning.LessonEffectTemplate;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/18 23:57
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson152Effect_BloomAndGlow extends LessonEffectTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Effect getEffect() {
        if (Math.random() >= 0.5) {
            System.out.println("bloom");
            return getBloom();
        } else {
            System.out.println("glow");
            return getGlow();
        }
    }

    private Effect getBloom() {
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.5);
        return bloom;
    }

    private Effect getGlow() {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        return glow;
    }
}

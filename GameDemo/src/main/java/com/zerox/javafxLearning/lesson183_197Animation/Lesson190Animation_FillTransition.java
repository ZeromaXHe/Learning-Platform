package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 14:07
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson190Animation_FillTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        FillTransition ft = new FillTransition();
        ft.setShape(rec);
        ft.setDuration(Duration.seconds(2));
        ft.setFromValue(Color.valueOf("#ADFF2F55"));
        ft.setToValue(Color.BLUE);
        ft.setAutoReverse(true);
        ft.setCycleCount(Animation.INDEFINITE);
        return ft;
    }
}

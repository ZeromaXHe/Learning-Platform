package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 13:52
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson189Animation_FadeTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        FadeTransition ft = new FadeTransition();
        ft.setNode(rec);
        ft.setDuration(Duration.seconds(1));
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setAutoReverse(true);
        ft.setCycleCount(Animation.INDEFINITE);

        return ft;
    }
}

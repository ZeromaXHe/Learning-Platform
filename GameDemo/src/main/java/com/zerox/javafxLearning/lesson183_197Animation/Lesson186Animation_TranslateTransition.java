package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 7:55
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson186Animation_TranslateTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        bu.translateXProperty().bind(rec.translateXProperty());
        bu.translateYProperty().bind(rec.translateYProperty());

        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(2));
        tt.setNode(rec);
        tt.setFromX(0);
        tt.setFromY(0);
        tt.setToX(300);
        tt.setToY(300);
        // 相对位移
        // to 和 by 同时存在的话，使用 to
//        tt.setByX(300);

        tt.setInterpolator(Interpolator.LINEAR);
//        tt.setInterpolator(Interpolator.SPLINE(0.5, 0, 0.5, 1));
        tt.setAutoReverse(true);
        tt.setCycleCount(Animation.INDEFINITE);

        return tt;
    }
}

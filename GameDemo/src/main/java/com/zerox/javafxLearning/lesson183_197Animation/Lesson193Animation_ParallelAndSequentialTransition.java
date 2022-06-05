package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 15:23
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson193Animation_ParallelAndSequentialTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(6));
        tt.setFromX(0);
        tt.setToX(600);

        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(3));
        rt.setFromAngle(0);
        rt.setToAngle(360);

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt, rt);

        FillTransition ft = new FillTransition();
        ft.setDuration(Duration.seconds(3));
        ft.setFromValue(Color.AQUA);
        ft.setToValue(Color.BLUE);

        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(ft, pt);
        st.setNode(rec);
        st.setAutoReverse(true);
        st.setCycleCount(Animation.INDEFINITE);
        return st;
    }
}

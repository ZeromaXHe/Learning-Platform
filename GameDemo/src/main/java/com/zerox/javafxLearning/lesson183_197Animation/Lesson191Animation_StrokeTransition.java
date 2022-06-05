package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.Animation;
import javafx.animation.StrokeTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 14:12
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson191Animation_StrokeTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        rec.setStrokeWidth(10);
        rec.setStrokeType(StrokeType.OUTSIDE);
        rec.setStroke(Color.RED);

        StrokeTransition st = new StrokeTransition();
        // 也可以使用提示点
//        st.getCuePoints().put()
        st.setShape(rec);
        st.setDuration(Duration.seconds(3));
        st.setFromValue(Color.AQUA);
        st.setToValue(Color.YELLOW);
        st.setAutoReverse(true);
        st.setCycleCount(Animation.INDEFINITE);
        return st;
    }
}

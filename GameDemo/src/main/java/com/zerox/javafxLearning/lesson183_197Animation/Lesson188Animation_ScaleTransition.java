package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 13:36
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson188Animation_ScaleTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        Scale scale = new Scale(1, 1, 100, 100);
        // 默认缩放轴也是中心点，偏移 （50, 50） 后即可按左上角缩放
        rec.getTransforms().addAll(scale, new Translate(50, 50));

        ScaleTransition st = new ScaleTransition();
        st.setNode(rec);
        st.setDuration(Duration.seconds(3));
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(2);
        st.setToY(2);
        // to 和 by 同时存在的话，使用 to
        st.setByX(-0.5);

        st.setOnFinished(event -> System.out.println(rec.getBoundsInParent()));

        return st;
    }
}

package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 13:21
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson187Animation_RotateTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        Rotate rotate = new Rotate(0, 100, 100);
        // 加一个位移好像才能偏置旋转轴
        rec.getTransforms().addAll(rotate, new Translate(50, 50));

        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(3));
        rt.setNode(rec);
        rt.setFromAngle(0);
        // to 和 by 同时存在的话，使用 to
        rt.setToAngle(180);
//        rt.setByAngle(180);
        rt.setAxis(new Point3D(0, 0, 1));
        rt.setInterpolator(Interpolator.LINEAR);

        return rt;
    }
}

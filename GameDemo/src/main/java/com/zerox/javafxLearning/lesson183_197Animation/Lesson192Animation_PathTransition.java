package com.zerox.javafxLearning.lesson183_197Animation;

import com.zerox.javafxLearning.LessonAnimationTemplate;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 14:16
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson192Animation_PathTransition extends LessonAnimationTemplate {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected Transition getTransition() {
        // 左上角在 path 上
//        rec.setX(-50);
//        rec.setY(-50);

        StackPane sp = new StackPane();

        Path path = new Path();
        MoveTo mt = new MoveTo(0, 0);
        LineTo lt = new LineTo(100, 0);

        QuadCurveTo qc = new QuadCurveTo(0, 50, 100, 100);
        qc.setAbsolute(false);

        HLineTo ht = new HLineTo(100);
        ht.setAbsolute(false);

        CubicCurveTo cc = new CubicCurveTo(50, -50, 150, 50, 200, 0);
        cc.setAbsolute(false);

        ArcTo at = new ArcTo(100, 100, 0, 100, 100, true, true);
        at.setAbsolute(false);

        VLineTo vt = new VLineTo(100);
        vt.setAbsolute(false);

        ClosePath close = new ClosePath();

        path.getElements().addAll(mt, lt, qc, ht, cc, at, vt, close);
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(5);

        sp.getChildren().add(path);
        an.getChildren().add(sp);

        AnchorPane.setTopAnchor(sp, 200.0);
        AnchorPane.setLeftAnchor(sp, 200.0);

        PathTransition pt = new PathTransition();
        pt.setNode(rec);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setDuration(Duration.seconds(6));
        pt.setPath(path);
        return pt;
    }
}

package com.zerox.javafxLearning;

import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.Effect;
import javafx.scene.effect.FloatMap;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:02
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson155Effect_DisplacementMap extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        int w = 100;
        int h = 100;
        FloatMap fmap = new FloatMap(w, h);
        float value = 0.3F;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                float temp = 0;
                if (j < h / 2) {
                    temp = value;
                } else {
                    temp = -value;
                }
                fmap.setSamples(i, j, temp, 0);
            }
        }

        DisplacementMap map = new DisplacementMap();
        map.setMapData(fmap);
        map.setOffsetX(0);
        map.setOffsetY(0);
        map.setWrap(false);
        map.setScaleX(1);
        map.setScaleY(1);
        return map;
    }
}

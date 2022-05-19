package com.zerox.javafxLearning;

import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/19 23:50
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson161Effect_Lighting extends LessonEffectTemplate {
    @Override
    public Effect getEffect() {
        Lighting lighting = new Lighting();
        lighting.setSurfaceScale(10);
        lighting.setDiffuseConstant(2);
        lighting.setSpecularConstant(1);
        lighting.setSpecularExponent(40);
        Light.Distant dis = new Light.Distant();
        dis.setColor(Color.AQUA);
        dis.setAzimuth(90);
        dis.setElevation(50);
        lighting.setLight(dis);
        lighting.setBumpInput(new GaussianBlur(15));
        lighting.setContentInput(new GaussianBlur(15));
        return lighting;
    }
}

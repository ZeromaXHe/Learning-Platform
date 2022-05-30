package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 15:04
 * @Description: BuilderFactory 貌似也不能是内部类，否则解析过程会报错
 * @ModifiedBy: zhuxi
 */
public class Lesson115PersonBuilderFactory implements BuilderFactory {
    private final JavaFXBuilderFactory JBF = new JavaFXBuilderFactory();

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        if (type == Lesson115Person.class) {
            System.out.println("返回 Builder");
            return new Lesson115PersonBuilder();
        }
        return JBF.getBuilder(type);
    }
}

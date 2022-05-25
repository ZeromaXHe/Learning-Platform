package com.zerox.javafxLearning;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 15:22
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson115PersonBuilderFactoryMap implements BuilderFactory {
    private final JavaFXBuilderFactory JBF = new JavaFXBuilderFactory();

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        if (type == Lesson115Person.class) {
            System.out.println("Lesson115PersonBuilderFactoryMap.getBuilder");
            return new Lesson115PersonBuilderMap();
        }
        return JBF.getBuilder(type);
    }
}

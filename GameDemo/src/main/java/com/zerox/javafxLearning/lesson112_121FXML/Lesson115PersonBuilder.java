package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.util.Builder;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 15:01
 * @Description: Builder 貌似也不能是内部类，否则解析过程会报错
 * @ModifiedBy: zhuxi
 */
public class Lesson115PersonBuilder implements Builder<Lesson115Person> {
    private String name;
    private int age;

    @Override
    public Lesson115Person build() {
        System.out.println("构建中...");
        return new Lesson115Person(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Builder setName");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Builder setAge");
        this.age = age;
    }
}

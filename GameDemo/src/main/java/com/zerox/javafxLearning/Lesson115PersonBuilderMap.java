package com.zerox.javafxLearning;

import javafx.util.Builder;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 15:16
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson115PersonBuilderMap extends HashMap<String, Object> implements Builder<Lesson115Person> {
    private String name;
    private int age;

    @Override
    public Object put(String key, Object value) {
        if("name".equals(key)){
            this.name = String.valueOf(value);
        }else if("age".equals(key)){
            this.age = Integer.parseInt(String.valueOf(value));
        }
        return null;
    }

    @Override
    public Lesson115Person build() {
        System.out.println("Lesson115PersonBuilderMap.build");
        return new Lesson115Person(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

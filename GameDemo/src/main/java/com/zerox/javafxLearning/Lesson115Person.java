package com.zerox.javafxLearning;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 14:58
 * @Description: FXML 只能导入 public 修饰的类
 * 否则报错：Class sun.reflect.misc.ReflectUtil can not access a member of class com.zerox.javafxLearning.Person with modifiers "public"
 * @ModifiedBy: zhuxi
 */
public class Lesson115Person {
    private String name;
    private int age;

//    public Lesson115Person() {
//        System.out.println("无参构造方法");
//    }

    public Lesson115Person(String name, int age) {
        System.out.println("双参构造方法");
        this.name = name;
        this.age = age;
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

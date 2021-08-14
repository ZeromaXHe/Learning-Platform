package com.zerox.javafxLearning.lesson051_064;

import com.zerox.javafxLearning.Stu;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Locale;

/**
 * @Author: zhuxi
 * @Time: 2021/8/4 16:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson062Bindings {
    public static void main(String[] args) {
        SimpleIntegerProperty value = new SimpleIntegerProperty(10);
        StringExpression se = Bindings.concat("value = ", value.asString(Locale.getDefault(), "%s"));
        System.out.println(se.get());

        StringExpression se2 = Bindings.format("value = %s", value);
        System.out.println(se2.get());

        System.out.println("---------------------");

        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);

        System.out.println(Bindings.max(x, y).intValue());
        System.out.println(Bindings.min(x, y).intValue());

        System.out.println("---------------------");

        SimpleIntegerProperty a = new SimpleIntegerProperty(1);
        SimpleIntegerProperty b = new SimpleIntegerProperty(2);

        StringBinding stringBinding = Bindings.createStringBinding(() -> {
//            System.out.println("call()");
            if (a.get() == 1) {
                return "A";
            } else if (a.get() == 2) {
                return "B";
            }
            return "hello";
        }, a, b);

        System.out.println(stringBinding.get());
        System.out.println(a.get());
        a.set(2);
        System.out.println(stringBinding.get());
        System.out.println(a.get());
        a.set(3);
        System.out.println(stringBinding.get());
        System.out.println(a.get());

        System.out.println("---------------------");

        // Stu不能是内部类，否则反射报错
        Stu student = new Stu("A");
        SimpleObjectProperty<Stu> stu = new SimpleObjectProperty<>(student);
        StringBinding strValue = Bindings.selectString(stu, "stuName", "name");
        System.out.println(strValue.get());
        student.setName("B");
        System.out.println(strValue.get());
    }
}

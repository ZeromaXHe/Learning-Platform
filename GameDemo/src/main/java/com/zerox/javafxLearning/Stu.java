package com.zerox.javafxLearning;

import javafx.beans.property.SimpleObjectProperty;

/**
 * @Author: zhuxi
 * @Time: 2021/8/4 16:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Stu {
    private Name name = new Name();
    private SimpleObjectProperty<Name> stuName = new SimpleObjectProperty<>();

    public Stu(String name) {
        this.name.setName(name);
        this.stuName.set(this.name);
    }

    public SimpleObjectProperty<Name> stuNameProperty() {
        System.out.println("stuName");
        return stuName;
    }

    public void setName(String name) {
        this.name.setName(name);
        this.stuName.set(this.name);
    }
}

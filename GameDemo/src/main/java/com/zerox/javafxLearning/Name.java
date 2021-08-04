package com.zerox.javafxLearning;

import javafx.beans.property.SimpleStringProperty;

/**
 * @Author: zhuxi
 * @Time: 2021/8/4 16:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Name {
    private SimpleStringProperty name = new SimpleStringProperty("A");

    public SimpleStringProperty nameProperty() {
        System.out.println("Name");
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

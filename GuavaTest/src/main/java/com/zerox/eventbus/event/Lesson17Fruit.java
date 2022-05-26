package com.zerox.eventbus.event;

import com.google.common.base.MoreObjects;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17Fruit {
    private final String name;

    public Lesson17Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }
}

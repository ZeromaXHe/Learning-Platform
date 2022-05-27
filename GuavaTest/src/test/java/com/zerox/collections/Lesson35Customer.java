package com.zerox.collections;

import com.google.common.base.MoreObjects;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 17:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson35Customer {
    final int type;
    final String name;

    public Lesson35Customer(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("name", name)
                .toString();
    }
}

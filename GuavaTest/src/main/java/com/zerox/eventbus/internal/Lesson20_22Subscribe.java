package com.zerox.eventbus.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 17:13
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lesson20_22Subscribe {
    String topic() default "default-topic";
}

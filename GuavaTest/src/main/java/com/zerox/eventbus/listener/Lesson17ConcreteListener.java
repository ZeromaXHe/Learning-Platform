package com.zerox.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 13:59
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17ConcreteListener extends Lesson17BaseListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson17ConcreteListener.class);

    @Subscribe
    public void concreteTask(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The event [{}] will be handle by {}.concreteTask()", event, this.getClass().getSimpleName());
        }
    }
}

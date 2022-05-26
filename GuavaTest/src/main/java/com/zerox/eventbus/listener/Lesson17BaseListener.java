package com.zerox.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 13:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17BaseListener extends Lesson17AbstractListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson17BaseListener.class);

    @Subscribe
    public void baseTask(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The event [{}] will be handle by {}.baseTask()", event, this.getClass().getSimpleName());
        }
    }
}

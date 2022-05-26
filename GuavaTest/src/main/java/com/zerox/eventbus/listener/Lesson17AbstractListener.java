package com.zerox.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 13:54
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Lesson17AbstractListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson17AbstractListener.class);

    @Subscribe
    public void commonTask(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The event [{}] will be handle by {}.commonTask()", event, this.getClass().getSimpleName());
        }
    }
}

package com.zerox.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 13:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17MultipleEventListeners {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson17MultipleEventListeners.class);

    @Subscribe
    public void task1(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==task1==", event);
        }
    }

    @Subscribe
    public void task2(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==task2==", event);
        }
    }

    /**
     * @param event 不能是 int 类型，会报错
     */
    @Subscribe
    public void intTask(Integer event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==intTask==", event);
        }
    }
}

package com.zerox.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:36
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18ExceptionListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson18ExceptionListener.class);

    @Subscribe
    public void m1(String event) {
        LOGGER.info("m1 receive event: {}", event);
    }
    @Subscribe
    public void m2(String event) {
        LOGGER.info("m2 receive event: {}", event);
        throw new RuntimeException();
    }
    @Subscribe
    public void m3(String event) {
        LOGGER.info("m3 receive event: {}", event);
    }
}

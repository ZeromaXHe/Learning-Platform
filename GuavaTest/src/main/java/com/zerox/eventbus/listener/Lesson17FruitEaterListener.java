package com.zerox.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import com.zerox.eventbus.event.Lesson17Apple;
import com.zerox.eventbus.event.Lesson17Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:05
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17FruitEaterListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson17FruitEaterListener.class);

    @Subscribe
    public void eat(Lesson17Fruit event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Fruit eat [{}]", event);
        }
    }

    @Subscribe
    public void eat(Lesson17Apple event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Apple eat [{}]", event);
        }
    }
}

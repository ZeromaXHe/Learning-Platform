package com.zerox.eventbus.monitor;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:26
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson19FileChangeListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson19FileChangeListener.class);

    @Subscribe
    private void onChange(Lesson19FileChangeEvent event) {
        LOGGER.info("{} - {}", event.getPath(), event.getKind());
    }
}

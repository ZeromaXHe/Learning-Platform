package com.zerox.eventbus.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.zerox.eventbus.event.Lesson18Request;
import com.zerox.eventbus.event.Lesson18Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:58
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18RequestQueryHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson18RequestQueryHandler.class);

    private final EventBus eventBus;

    public Lesson18RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    @Subscribe
    public void doQuery(Lesson18Request request) {
        LOGGER.info("start query the request [{}]", request.toString());
        this.eventBus.post(new Lesson18Response());
    }
}

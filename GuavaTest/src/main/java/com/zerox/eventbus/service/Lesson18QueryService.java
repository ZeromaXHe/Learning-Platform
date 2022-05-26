package com.zerox.eventbus.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.zerox.eventbus.event.Lesson18Request;
import com.zerox.eventbus.event.Lesson18Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:54
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18QueryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson18QueryService.class);

    private final EventBus eventBus;

    public Lesson18QueryService(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    public void query(String orderNo) {
        LOGGER.info("Received the orderNo [{}]", orderNo);
        this.eventBus.post(new Lesson18Request(orderNo));
    }

    @Subscribe
    public void handleResponse(Lesson18Response response) {
        LOGGER.info("response: {}", response);
    }
}

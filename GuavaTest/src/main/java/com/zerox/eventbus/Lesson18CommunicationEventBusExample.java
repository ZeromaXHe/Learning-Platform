package com.zerox.eventbus;

import com.google.common.eventbus.EventBus;
import com.zerox.eventbus.service.Lesson18QueryService;
import com.zerox.eventbus.service.Lesson18RequestQueryHandler;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 15:01
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18CommunicationEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        Lesson18QueryService queryService = new Lesson18QueryService(eventBus);
        Lesson18RequestQueryHandler requestQueryHandler = new Lesson18RequestQueryHandler(eventBus);
        queryService.query("123456");
    }
}

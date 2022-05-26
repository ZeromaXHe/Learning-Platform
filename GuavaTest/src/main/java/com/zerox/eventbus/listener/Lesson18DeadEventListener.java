package com.zerox.eventbus.listener;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 14:46
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson18DeadEventListener {
    @Subscribe
    public void handle(DeadEvent event) {
        System.out.println("source: " + event.getSource());
        System.out.println("event: " + event.getEvent());
    }
}

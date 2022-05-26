package com.zerox.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 13:43
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson17SimpleListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(Lesson17SimpleListener.class);

    /**
     * 订阅者必须满足一下条件：
     * 1. 订阅者对象是普通类（指不是内部类？）
     * 2. 订阅方法必须 public void 修饰
     * 3. 订阅方法必须只有一个参数
     * 4. 订阅方法必须 @Subscribe 注解
     * @param event
     */
    @Subscribe
    public void doAction(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will take a action", event);
        }
    }
//    @Subscribe
//    public void doAction2(final String event) {
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("doAction2 Received event [{}] and will take a action", event);
//        }
//    }
}

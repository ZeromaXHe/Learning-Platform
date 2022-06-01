package com.zerox.log4j2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/6/1 17:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson32_35Slf4jTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(Lesson32_35Slf4jTest.class);

    @Test
    public void testQuick() {
        // 当有多个实现的时候, pom.xml 文件中哪个实现写在前面,就用哪个实现
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        // 使用占位符输出日志信息
        String name = "zerox";
        Integer age = 14;
        LOGGER.info("用户:{},{}", name, age);

        // 将系统的异常信息输出
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            LOGGER.error("出现异常:", e);
        }
    }
}

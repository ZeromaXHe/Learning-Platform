package com.zerox.logback;

import com.zerox.slf4j.Lesson22_24Slf4jTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhuxi
 * @Time: 2022/6/1 16:10
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson26_31LogbackTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(Lesson22_24Slf4jTest.class);

    @Test
    public void testQuick() {
        // 当有多个实现的时候,pom 文件中哪个实现写在前面,就用哪个实现。所以记得把 Logback 的依赖放在最前面
        // 默认 debug 级别
//        for (int i = 0; i < 10000; i++) {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
//        }
    }
}

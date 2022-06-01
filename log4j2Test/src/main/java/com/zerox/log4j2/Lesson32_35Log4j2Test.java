package com.zerox.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/6/1 17:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson32_35Log4j2Test {
    public static final Logger LOGGER = LogManager.getLogger(Lesson32_35Log4j2Test.class);

    @Test
    public void testQuick() {
        LOGGER.fatal("fatal");
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.trace("trace");
    }
}

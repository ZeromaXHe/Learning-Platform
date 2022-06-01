package com.zerox.slf4j;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/6/1 15:54
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson25Log4jTest {
    public static final Logger LOGGER = Logger.getLogger(Lesson25Log4jTest.class);

    /**
     * 测试桥接器。（需要在 pom.xml 中把 log4j 的依赖排在桥接器之后）
     * 注意： log4j 桥接器（log4j-over-slf4j）和适配器（slf4j-log4j12） 不能同时生效，否则会死循环导致栈溢出
     */
    @Test
    public void test() {
        LOGGER.info("test log4j");
    }
}

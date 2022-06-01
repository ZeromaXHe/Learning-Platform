package com.zerox.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;

import java.io.File;

/**
 * @Author: zhuxi
 * @Time: 2022/5/31 11:55
 * @Description: 需要在 pom.xml 中将 log4j 的依赖排在 slf4j 相关依赖（尤其是 log4j 桥接器）前面，这样才是走的 log4j 本身的代码。
 * @ModifiedBy: zhuxi
 */
public class Lesson11_17Log4jTest {
    @Test
    public void testQuick() {
        // Log4j 内置日志记录
        LogLog.setInternalDebugging(true);

        // 不使用配置文件初始化配置信息
//        BasicConfigurator.configure();

        // 使用非 resources 文件夹下的配置文件
        PropertyConfigurator.configure(Lesson11_17Log4jTest.class.getClassLoader().getResource("log4j/log4j.properties"));

        Logger logger = Logger.getLogger(Lesson11_17Log4jTest.class);
        logger.info("hello log4j");

//        for (int i = 0; i < 10000; i++) {
        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
//        }
    }

    @Test
    public void testFilePath() {
        // 默认是 C:/log/log4j.log
        System.out.println(new File("/log/log4j.log").getAbsolutePath());
    }
}

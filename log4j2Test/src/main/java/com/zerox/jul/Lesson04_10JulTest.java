package com.zerox.jul;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @Author: zhuxi
 * @Time: 2022/5/31 10:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson04_10JulTest {
    @Test
    public void testQuick() {
        Logger logger = Logger.getLogger("com.zerox.jul.Lesson04_10JulTest");
        logger.info("hello JUL");

        logger.log(Level.INFO, "info msg");

        String name = "zerox";
        Integer age = 13;
        logger.log(Level.INFO, "用户信息：{0},{1}", new Object[]{name, age});
    }

    @Test
    public void testLogLevel() {
        // 默认为 info 级别
        Logger logger = Logger.getLogger("com.zerox.jul.Lesson04_10JulTest");
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void testLogConfig() throws IOException {
        Logger logger = Logger.getLogger("com.zerox.jul.Lesson04_10JulTest");
        // 关闭系统默认配置
        logger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);

        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        // 相对路径以 log4j2Test 为起点。貌似如果路径里面有文件夹且文件夹未创建的话，会对同名的 .lck 文件报错 NoSuchFileException
        FileHandler fileHandler = new FileHandler("logs/jul.log");
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void testLogParent() {
        Logger logger1 = Logger.getLogger("com.zerox");
        Logger logger2 = Logger.getLogger("com");

        System.out.println(logger1.getParent() == logger2);
        System.out.println("logger2.parent: " + logger2.getParent() + ", name: " + logger2.getParent().getName());

        // 不加这个，info 以上的日志会多打一份
        logger2.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);

        logger2.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        logger1.severe("severe");
        logger1.warning("warning");
        logger1.info("info");
        logger1.config("config");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");
    }

    @Test
    public void testLogProperties() throws IOException {
        // logging.properties 是从 jdk 路径下的 jre/lib 复制的
        InputStream ins = Lesson04_10JulTest.class.getClassLoader().getResourceAsStream("jul/logging.properties");
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(ins);

        Logger logger = Logger.getLogger("com.zerox");

        logger.severe("[testLogProperties] severe");
        logger.warning("[testLogProperties] warning");
        logger.info("[testLogProperties] info");
        logger.config("[testLogProperties] config");
        logger.fine("[testLogProperties] fine");
        logger.finer("[testLogProperties] finer");
        logger.finest("[testLogProperties] finest");

        Logger logger2 = Logger.getLogger("test");

        logger2.severe("[test] severe");
        logger2.warning("[test] warning");
        logger2.info("[test] info");
        logger2.config("[test] config");
        logger2.fine("[test] fine");
        logger2.finer("[test] finer");
        logger2.finest("[test] finest");
    }
}

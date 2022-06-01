package com.zerox.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/6/1 14:30
 * @Description: jcl 已经被淘汰了
 * @ModifiedBy: zhuxi
 */
public class Lesson18_19JCLTest {
    @Test
    public void testQuick() {
        Log log = LogFactory.getLog(Lesson18_19JCLTest.class);
        // 没有依赖的话，默认使用的是 JUL。存在 log4j 依赖时，优先使用 log4j。如需指定，则在 commons-logging.properties 文件中
        // 加入 org.apache.commons.logging.Log = org.apache.commons.logging.impl.Jdk14Logger 即可
        // 详情可以参考 org.apache.commons.logging.impl.LogFactoryImpl.discoverLogImplementation() 方法源码
        log.info("hello JCL");
    }
}

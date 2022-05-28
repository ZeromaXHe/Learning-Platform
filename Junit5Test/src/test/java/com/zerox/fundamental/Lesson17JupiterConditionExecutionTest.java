package com.zerox.fundamental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 14:26
 * @Description:
 * @ModifiedBy: zhuxi
 */
@DisplayName("<Examples of Jupiter builds-in Condition Execution>")
public class Lesson17JupiterConditionExecutionTest {
    @Test
    @DisabledOnJre(value = JRE.JAVA_8, disabledReason = "disabled due to JDK 8")
    void disableWhenJDK8() {

    }

    @Test
    @DisabledOnJre(value = JRE.JAVA_17, disabledReason = "disabled due to JDK 17")
    void disableWhenJDK17() {
        System.out.println(System.getProperty("java.version"));
    }

    @Test
    @EnabledOnJre(value = JRE.JAVA_8)
    void enabledWhenJDK8() {
        System.out.println(System.getProperty("java.version"));
    }

    @DisplayName("only execute on jdk [8-14]")
    @Test
    @EnabledForJreRange(max = JRE.JAVA_14)
    void enabledWhenJDKMatchRange() {
        // @EnabledForJreRange min 默认为 JRE.JAVA_8
        System.out.println(System.getProperty("java.version"));
    }

    @DisplayName("not execute on jdk [10-14]")
    @Test
    @DisabledForJreRange(min = JRE.JAVA_10, max = JRE.JAVA_14)
    void disabledWhenJDKMatchRange() {
        System.out.println(System.getProperty("java.version"));
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void enableOnMac() {

    }

    @Test
    @DisabledOnOs({OS.WINDOWS, OS.MAC})
    void disabledOnMacAndWindows() {

    }

    @Test
    @EnabledIfEnvironmentVariable(named="JAVA_HOME", matches = ".*")
    void enabledIfEnvironmentContainsMavenHome() {

    }

    @Test
    @EnabledIfSystemProperty(named = "env", matches = "dev")
    void enabledIfSystemProperty() {

    }

    @Test
    @EnabledIf("customCondition")
    void customEnabledIf() {

    }

    private boolean customCondition() {
        return false;
    }

    @BeforeEach
    void setUp() {
        System.getProperties().put("env", "sit");
    }
}

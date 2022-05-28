package com.zerox.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 14:57
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Tag("resolver")
public class Lesson19TestReporterParameterResolverTest {
    @BeforeEach
    void setUp(TestInfo testInfo, RepetitionInfo repetitionInfo, TestReporter reporter) {
        reporter.publishEntry("displayName", testInfo.getDisplayName());
        reporter.publishEntry("totalRepetitions", String.valueOf(repetitionInfo.getTotalRepetitions()));
    }

    @DisplayName("simple test for test reporter")
    @RepeatedTest(1)
    void simpleTest(TestReporter reporter) {
        reporter.publishEntry("from simple test");
    }
}

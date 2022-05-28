package com.zerox.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 14:54
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson19RepetitionInfoParameterResolver {
    @DisplayName("prefix--")
    @RepeatedTest(value = 3, name = RepeatedTest.LONG_DISPLAY_NAME)
    void repeatTest(RepetitionInfo repetitionInfo, TestInfo testInfo) {
        System.out.println(repetitionInfo.getCurrentRepetition() + "--" + repetitionInfo.getTotalRepetitions());
        System.out.println(testInfo.getDisplayName());
        System.out.println("----------------------------");
    }
}

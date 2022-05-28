package com.zerox.advanced;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 14:47
 * @Description:
 * @ModifiedBy: zhuxi
 */
@DisplayName("display name of Lesson19TestInfoParameterResolverTest")
public class Lesson19TestInfoParameterResolverTest {
    @DisplayName("display name of init @BeforeAll")
    @BeforeAll
    static void init(TestInfo testInfo) {
        System.out.println("==============================");
        System.out.println("displayName: " + testInfo.getDisplayName());
        System.out.println("testClass: " + testInfo.getTestClass());
        System.out.println("testMethod: " + testInfo.getTestMethod());
        System.out.println("tags: " + testInfo.getTags());
        System.out.println("==============================");
    }

    @DisplayName("display name of setUp @BeforeEach")
    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("------------------------------");
        System.out.println("displayName: " + testInfo.getDisplayName());
        System.out.println("testClass: " + testInfo.getTestClass());
        System.out.println("testMethod: " + testInfo.getTestMethod());
        System.out.println("tags: " + testInfo.getTags());
        System.out.println("------------------------------");
    }

    @DisplayName("display name of simpleTest @Test")
    @Tag("only-test")
    @Test
    void simpleTest(TestInfo testInfo) {
        System.out.println("..............................");
        System.out.println("displayName: " + testInfo.getDisplayName());
        System.out.println("testClass: " + testInfo.getTestClass());
        System.out.println("testMethod: " + testInfo.getTestMethod());
        System.out.println("tags: " + testInfo.getTags());
        System.out.println("..............................");
    }
}

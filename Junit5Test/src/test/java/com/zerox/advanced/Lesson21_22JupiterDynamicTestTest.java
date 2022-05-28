package com.zerox.advanced;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 15:07
 * @Description:
 * @ModifiedBy: zhuxi
 */
@DisplayName("Examples for Dynamic Test")
public class Lesson21_22JupiterDynamicTestTest {
    @TestFactory
    Iterator<DynamicTest> dynamicTestsFromIterator() {
        return Arrays.asList(
                DynamicTest.dynamicTest("1 dynamic test",
                        () -> Assertions.assertTrue(true)),
                DynamicTest.dynamicTest("2 dynamic test",
                        () -> Assertions.assertEquals(10, 2 * 5))
        ).iterator();
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        return Stream.of(
                DynamicTest.dynamicTest("3 dynamic test",
                        () -> Assertions.assertTrue(true)),
                DynamicTest.dynamicTest("4 dynamic test",
                        () -> Assertions.assertEquals(10, 2 * 5))
        );
    }

    @TestFactory
    Stream<DynamicTest> theUserTitleShouldBe3Star() {
        final List<String> userLevel = queryFromDB();
        return Stream.of("JAVA", "SCALA", "GROOVY")
                .map(e -> DynamicTest.dynamicTest(String.format("{%s} in db", e), () -> {
                    Assertions.assertTrue(userLevel.contains(e));
                }));
    }

    private List<String> queryFromDB() {
        return Arrays.asList("JAVA", "SCALA", "GROOVY", "CLOJURE", "JSHEEL");
    }

    // ======================= 【第 22 课内容】 =======================

    @TestFactory
    DynamicNode dynamicTestsWithContainer() {
        return DynamicContainer.dynamicContainer("topic dynamic Container",
                Stream.of(
                        DynamicTest.dynamicTest("first level dynamic test",
                                () -> Assertions.assertTrue(true)),
                        DynamicContainer.dynamicContainer("second dynamic Container",
                                Stream.of(
                                        DynamicTest.dynamicTest("second level <1> dynamic test",
                                                () -> Assertions.assertTrue(true)),
                                        DynamicTest.dynamicTest("second level <2> dynamic test",
                                                () -> Assertions.assertTrue(true)))
                        )
                ));
    }

    @TestFactory
    Stream<DynamicNode> loadDynamicTestFromFiles() throws Exception {
        return Files.walk(Paths.get("src/test/resources/testcase"), 1)
                .filter(path -> path.toString().endsWith(".txt"))
                .map(path -> DynamicTest.dynamicTest("load test file from class uri", path.toUri(), () -> checkFileContent(path)));
    }

    private void checkFileContent(Path path) throws IOException {
        final List<String> contents = Files.readAllLines(path);
        String line8 = contents.get(7);
        Assertions.assertEquals("8", line8);
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        // 对于 @TestFactory，@BeforeEach 会生效一次，内部的动态测试不生效
        System.out.println("setUp @BeforeEach -> " + testInfo);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        // 对于 @TestFactory，@AfterEach 会生效一次，内部的动态测试不生效
        System.out.println("tearDown @AfterEach -> " + testInfo);
    }
}

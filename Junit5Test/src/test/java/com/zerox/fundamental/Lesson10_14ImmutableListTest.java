package com.zerox.fundamental;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.MultipleFailuresError;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 10:39
 * @Description:
 * @ModifiedBy: zhuxi
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.DisplayName.class) // 貌似不加这个注解的话，默认是 class 声明的倒序？
@DisplayName("<The unit test for Java Immutable List Features>")
public class Lesson10_14ImmutableListTest {
    private final List<String> LIST = Arrays.asList("HELLO", "JAVA", "JUNIT", "JUPITER");

    private static String ENV;

    @BeforeAll
    static void setUp() {
        ENV = System.getenv().getOrDefault("env", "N/A");
        System.out.println("----------- init -----------");
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("<setUp " + testInfo.getTestMethod().get() + " >");
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("<tearDown " + testInfo.getTestMethod().get() + " >");
    }

    @AfterAll
    static void destroy() {
        System.out.println("----------- destroy -----------");
    }

    @DisplayName("10. The basic usage of jupiter")
    @Nested
    class NestedBasic {

        @DisplayName("[the list size should be four]")
        @Test
        void listSizeShouldEqualFour() {
            // given
            // when
            int size = LIST.size();
            // then
            Assertions.assertEquals(4, size);
        }

        @DisplayName("[the list contains 'JAVA' element]")
        @Test
        void listContainsJavaElement() {
            // given
            final String java = "JAVA";
            // when
            final boolean existing = LIST.contains(java);
            // then
            Assertions.assertTrue(existing);

            // 效果同上
            Assertions.assertTrue(() -> LIST.contains(java));
        }

        @DisplayName("[the Immutable list only support read operation]")
        @Test
        void immutableListCouldNotUpdate() {
            // given
            int index = 0;
            String expected = "HELLO";
            // when
            Executable executable = () -> {
                String firstElement = LIST.remove(index);
                Assertions.fail();
                Assertions.assertEquals(expected, firstElement);
            };
            // then
            Assertions.assertThrows(UnsupportedOperationException.class, executable);
        }

        @Disabled("disabled due to assertion failure test")
        @DisplayName("[immutable list only support read but update]")
        @Test
        void immutableListCanReadButUpdate() {
//            Assertions.assertThrows(MultipleFailuresError.class, () ->
            Assertions.assertAll("assert read and update mixed operation", Stream.of(
                    () -> Assertions.assertEquals("HELLO", LIST.get(0)),
                    () -> Assertions.assertEquals("HELLO", LIST.remove(0)),
                    () -> Assertions.assertEquals("JAVA", LIST.remove(1))
            ))
//            )
            ;
        }
    }

    @DisplayName("11. repeated feature")
    @Nested
    class NestedRepeated {

        // ==================== 【第 11 课内容】 ========================

        @RepeatedTest(3)
        void repeatTest() {
            Assertions.assertTrue(() -> LIST.contains("JUNIT"));
        }

        @RepeatedTest(4)
        void repeatWithIndex(RepetitionInfo info) {
            String element;
            switch (info.getCurrentRepetition()) {
                case 1:
                    element = "HELLO";
                    break;
                case 2:
                    element = "JAVA";
                    break;
                case 3:
                    element = "JUNIT";
                    break;
                case 4:
                    element = "JUPITER";
                    break;
                default:
                    element = "N/A";
            }
            Assertions.assertEquals(element, LIST.get(info.getCurrentRepetition() - 1));
        }

        @DisplayName("Repeat Assert Immutable list element")
        @RepeatedTest(value = 4, name = "{displayName} ==> {currentRepetition}/{totalRepetitions}")
        void repeatWithDetailsInformation(RepetitionInfo info) {
            repeatWithIndex(info);
        }

        @DisplayName("Long Display Name ...")
        @RepeatedTest(value = 2, name = RepeatedTest.LONG_DISPLAY_NAME)
        void repeatLongDisplayName(TestInfo testInfo, RepetitionInfo repetitionInfo) {
            Assertions.assertEquals(String.format("Long Display Name ... :: repetition %d of %d",
                    repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions()),
                    testInfo.getDisplayName());
        }
    }


    @DisplayName("12. timeout feature")
    @Nested
    class NestedTimeout {

        // ==================== 【第 12 课内容】 ========================

        @Order(2)
        @Disabled
        @Timeout(value = 30) // 默认 unit = TimeUnit.SECONDS
        @DisplayName("Simple test for assertTimeout API of Jupiter")
        @Test
        void timeoutAssertion() {
            final ArrayBlockingQueue<String> blockedQueue = new ArrayBlockingQueue<>(5);
            ;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockedQueue.offer("TEST");
            }).start();
            Assertions.assertTimeout(Duration.ofSeconds(2), () -> {
                Assertions.assertEquals("TEST", blockedQueue.take());
            });
        }

        @Order(1)
        @Disabled
        @DisplayName("Simple test for assertTimeoutPreemptively API of Jupiter")
        @Test
        void timeoutPreemptivelyAssertion() {
            final ArrayBlockingQueue<String> blockedQueue = new ArrayBlockingQueue<>(5);
            Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), blockedQueue::take);
        }
    }

    @DisplayName("13. Assumption feature")
    @Nested
    class NestedAssumption {

        // ==================== 【第 13 课内容】 ========================

        /**
         * System Integration Test 系统集成测试
         */
        @Order(3)
        @Test
        void onlyExecutionAtSitEnv() {
            Assumptions.assumingThat(() -> "sit".equals(ENV), () -> new NestedTimeout().timeoutPreemptivelyAssertion());
            // 等效
            Assumptions.assumeTrue("sit".equals(ENV));
            new NestedTimeout().timeoutPreemptivelyAssertion();
        }
    }
}

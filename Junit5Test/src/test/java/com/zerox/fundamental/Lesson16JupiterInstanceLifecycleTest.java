package com.zerox.fundamental;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 13:55
 * @Description: 使用了 hamcrest 的 assertThat
 * @ModifiedBy: zhuxi
 */
@ExtendWith({
        Lesson16JupiterInstanceLifecycle.MyTestInstancePostProcessor.class,
        Lesson16JupiterInstanceLifecycle.MyBeforeAllCallback.class,
        Lesson16JupiterInstanceLifecycle.MyBeforeEachCallback.class,
        Lesson16JupiterInstanceLifecycle.MyBeforeTestExecutionCallback.class,
        Lesson16JupiterInstanceLifecycle.MyTestExecutionExceptionHandler.class,
        Lesson16JupiterInstanceLifecycle.MyAfterTestExecutionCallback.class,
        Lesson16JupiterInstanceLifecycle.MyAfterEachCallback.class,
        Lesson16JupiterInstanceLifecycle.MyAfterAllCallback.class
})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("jupiter test class instance lifecycle")
public class Lesson16JupiterInstanceLifecycleTest {

    private final List<String> LIST = Arrays.asList("HELLO", "JAVA", "JUNIT", "JUPITER");

    @BeforeAll
    static void setUp() {
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

    @DisplayName("[the list size should be four]")
    @Test
    void listSizeShouldEqualFour() {
        // given
        // when
        int size = LIST.size();
        // then
//        Assertions.assertEquals(4, size);
        MatcherAssert.assertThat(LIST, Matchers.hasSize(4));
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
//        Assertions.assertTrue(() -> LIST.contains(java));
        MatcherAssert.assertThat(LIST, Matchers.contains(java));
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
//            Assertions.assertEquals(expected, firstElement);
            MatcherAssert.assertThat(firstElement, Matchers.equalTo(expected));
        };
        // then
        Assertions.assertThrows(UnsupportedOperationException.class, executable);
    }

    //    @Disabled("disabled due to assertion failure test")
    @DisplayName("[immutable list only support read but update]")
    @Test
    void immutableListCanReadButUpdate() {
//            Assertions.assertThrows(MultipleFailuresError.class, () ->
        Assertions.assertAll("assert read and update mixed operation", Stream.of(
//                () -> Assertions.assertEquals("HELLO", LIST.get(0)),
//                () -> Assertions.assertEquals("HELLO", LIST.remove(0)),
//                () -> Assertions.assertEquals("JAVA", LIST.remove(1))
                () -> MatcherAssert.assertThat(LIST.get(0), Matchers.equalTo("HELLO")),
                () -> MatcherAssert.assertThat(LIST.remove(0), Matchers.equalTo("HELLO")),
                () -> MatcherAssert.assertThat(LIST.remove(1), Matchers.equalTo("JAVA"))
        ))
//            )
        ;
    }
}

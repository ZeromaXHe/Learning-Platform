package com.zerox.fundamental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * @Author: zhuxi
 * @Time: 2022/5/28 11:54
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Tag("dev")
@Tag("sit")
@Tag("uat")
// 等效
// @Tags({@Tag("dev"), @Tag("sit"), @Tag("uat")})
public class Lesson15JupiterTaggingTest {
    private static List<String> LIST = null;

    @BeforeAll
    static void init() {
        LIST = Arrays.asList("HELLO", "JAVA", "JUNIT", "JUPITER");
    }

    @Tag("dev")
    @DisplayName("[the list size should be four]")
    @Test
    void listSizeShouldEqualFour() {
        // given
        // when
        int size = LIST.size();
        // then
        Assertions.assertEquals(4, size);
    }

    @Tags({@Tag("sit")})
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

    @Tag("uat")
    @Tag("prod")
    @DisplayName("[the Immutable list only support read operation]")
    @Test
    void immutableListCouldNotUpdate(TestInfo info) {
        // 方法的 Tag 会合并类上面的 Tag
        System.out.println(info.getTags());
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

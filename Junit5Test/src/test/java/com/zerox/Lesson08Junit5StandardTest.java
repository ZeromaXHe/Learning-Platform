package com.zerox;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/26 22:54
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson08Junit5StandardTest {
    @Test
    public void listShouldContainsElement() {
        // given
        final List<String> list = new ArrayList<>();
        list.add("JAVA");
        list.add("JUNIT");
        list.add("JUPITER");
        final String element = "JAVA";
        // when
        boolean existing = list.contains(element);
        // then
        assertTrue(existing);
        System.out.println("---------------");
    }
}

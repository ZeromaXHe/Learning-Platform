package com.zerox.mockito.lesson03;

import com.zerox.mockito.common.Lesson02Account;
import com.zerox.mockito.common.Lesson02AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 17:21
 * @Description:
 * @ModifiedBy: zhuxi
 */
// 使用 @RunWith(MockitoJUnitRunner.class) 这样也可以开启注解，详情可以看 MockitoJUnitRunner 类注释，注释原文如下：
// Runner is compatible with JUnit 4.4 and higher and adds following behavior:
// Initializes mocks annotated with {@link Mock},
// so that explicit usage of {@link MockitoAnnotations#openMocks(Object)} is not necessary.
// Mocks are initialized before each test method.

// @RunWith(MockitoJUnitRunner.class)
public class Lesson03MockByAnnotationTest {
    @Before
    public void init() {
        // initMocks 方法已废弃
        MockitoAnnotations.openMocks(this);
    }

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private Lesson02AccountDao accountDao;

    @Test
    public void testMock() {
        Lesson02Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}

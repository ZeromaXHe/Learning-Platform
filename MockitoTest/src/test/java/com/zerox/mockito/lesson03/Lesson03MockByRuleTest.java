package com.zerox.mockito.lesson03;

import com.zerox.mockito.common.Lesson02Account;
import com.zerox.mockito.common.Lesson02AccountDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 17:28
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson03MockByRuleTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Lesson02AccountDao accountDao;

    @Test
    public void testMock() {
        // @Mock 或者如下格式均可
//        Lesson02AccountDao accountDao = Mockito.mock(Lesson02AccountDao.class);
        Lesson02Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}

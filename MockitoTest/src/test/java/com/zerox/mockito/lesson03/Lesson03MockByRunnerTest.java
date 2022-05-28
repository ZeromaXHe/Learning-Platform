package com.zerox.mockito.lesson03;

import com.zerox.mockito.common.Lesson02Account;
import com.zerox.mockito.common.Lesson02AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 17:17
 * @Description:
 * @ModifiedBy: zhuxi
 */
@RunWith(MockitoJUnitRunner.class)
public class Lesson03MockByRunnerTest {
    @Test
    public void testMock() {
//        Lesson02AccountDao accountDao = Mockito.mock(Lesson02AccountDao.class);
        Lesson02AccountDao accountDao = Mockito.mock(Lesson02AccountDao.class, Mockito.RETURNS_SMART_NULLS);
        Lesson02Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}

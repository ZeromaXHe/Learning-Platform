package com.zerox.mockito.quickstart;

import com.zerox.mockito.common.Lesson02Account;
import com.zerox.mockito.common.Lesson02AccountDao;
import com.zerox.mockito.common.Lesson02AccountLoginController;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 17:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
@RunWith(MockitoJUnitRunner.class)
public class Lesson02AccountLoginControllerTest {

    private Lesson02AccountDao accountDao;

    private HttpServletRequest request;

    private Lesson02AccountLoginController controller;

    @Before
    public void setUp() {
        this.accountDao = Mockito.mock(Lesson02AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.controller = new Lesson02AccountLoginController(accountDao);
    }

    @Test
    public void testLoginSuccess() {
        Lesson02Account account = new Lesson02Account();
        Mockito.when(request.getParameter("username")).thenReturn("alex");
        Mockito.when(request.getParameter("password")).thenReturn("123456");
        Mockito.when(accountDao.findAccount(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(account);

        String result = controller.login(request);
        MatcherAssert.assertThat(result, CoreMatchers.equalTo("/index"));
    }

    @Test
    public void testLoginFailure() {
        Mockito.when(request.getParameter("username")).thenReturn("alex");
        Mockito.when(request.getParameter("password")).thenReturn("fail");
        Mockito.when(accountDao.findAccount(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(null);

        String result = controller.login(request);
        MatcherAssert.assertThat(result, CoreMatchers.equalTo("/login"));
    }

    @Test
    public void testLogin505() {
        Mockito.when(request.getParameter("username")).thenReturn("alex");
        Mockito.when(request.getParameter("password")).thenReturn("fail");
        Mockito.when(accountDao.findAccount(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenThrow(UnsupportedOperationException.class);

        String result = controller.login(request);
        MatcherAssert.assertThat(result, CoreMatchers.equalTo("/505"));
    }
}

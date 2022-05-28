package com.zerox.mockito.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 16:55
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson02AccountLoginController {
    private final Lesson02AccountDao accountDao;

    public Lesson02AccountLoginController(Lesson02AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest request){
        final String userName = request.getParameter("username");
        final String password = request.getParameter("password");
        try {
            Lesson02Account account = accountDao.findAccount(userName, password);
            if(account==null){
                return "/login";
            }else {
                return "/index";
            }
        } catch (Exception e) {
            return "/505";
        }
    }
}

package com.zerox.mockito.common;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 16:58
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson02AccountDao {
    public Lesson02Account findAccount(String username, String password) {
        // 模拟 DB 不可用
        throw new UnsupportedOperationException();
    }
}

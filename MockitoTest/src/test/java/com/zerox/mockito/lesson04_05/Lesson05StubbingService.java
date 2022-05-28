package com.zerox.mockito.lesson04_05;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 18:04
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson05StubbingService {
    public int getI() {
        return 10;
    }

    public String getS() {
        throw new RuntimeException();
    }
}

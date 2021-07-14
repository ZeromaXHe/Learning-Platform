package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 17:35
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Input {
    Result<Tuple<String, Input>> readString();

    Result<Tuple<Integer, Input>> readInt();

    // 这些方法允许你以参数的形式传递消息，这可能有助于提示用户，但是提供的默认实现会忽略该消息
    default Result<Tuple<String, Input>> readString(String message) {
        return readString();
    }

    default Result<Tuple<Integer, Input>> readInt(String message) {
        return readInt();
    }
}

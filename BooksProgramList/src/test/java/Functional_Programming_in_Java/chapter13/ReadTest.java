package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 17:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ReadTest {
    public static void main(String[] args) {
        Input input = ConsoleReader.consoleReader();
        Result<String> rString = input.readString("Enter your name: ").map(t -> t._1);
        Result<String> result = rString.map(s -> String.format("Hello, %s!", s));
        // 该行代表了该程序的业务部分，它可能是纯函数式的
        result.forEachOrFail(System.out::println).forEach(System.out::println);
    }
}

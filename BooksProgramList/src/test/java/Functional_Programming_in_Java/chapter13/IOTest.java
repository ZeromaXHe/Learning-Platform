package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter10.Nothing;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 19:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class IOTest {
    public static void main(String[] args) {
        IO<Nothing> script = sayHello();
        script.run();
    }

    private static IO<Nothing> sayHello() {
        return Console.printLine("Enter your name: ")
                .flatMap(Console::readLine)
                .map(IOTest::buildMessage)
                .flatMap(Console::printLine);
    }

    private static String buildMessage(String name) {
        return String.format("Hello, %s", name);
    }
}

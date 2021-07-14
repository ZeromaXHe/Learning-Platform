package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter7.Result;
import Functional_Programming_in_Java.chapter9.Stream;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 17:56
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ReadConsole {
    /**
     * 13.2.1 从控制台读取 - 练习13.3 编写一个程序，反复要求用户输入一个整数ID、一个名字和一个姓氏，之后将人员列表显示在控制台上。
     * 一旦用户输入空ID，数据输入就会停止，然后显式输入数据的列表
     *
     * @param args
     */
    public static void main(String[] args) {
        Input input = ConsoleReader.consoleReader();
        Stream<Person> stream = Stream.unfold(input, ReadConsole::person);
        stream.toList().forEach(System.out::println);
    }

    public static Result<Tuple<Person, Input>> person(Input input) {
        return input.readInt("Enter ID:")
                .flatMap(id -> id._2.readString("Enter first name:")
                        .flatMap(firstName -> firstName._2.readString("Enter last name:")
                                .map(lastName -> new Tuple<>(
                                        Person.apply(id._1, firstName._1, lastName._1),
                                        lastName._2))));
    }
}

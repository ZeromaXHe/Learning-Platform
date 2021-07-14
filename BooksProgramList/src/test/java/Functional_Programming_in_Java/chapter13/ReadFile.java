package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter7.Result;
import Functional_Programming_in_Java.chapter9.Stream;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 18:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ReadFile {
    private static String path = "BooksProgramList\\src\\main\\resources\\Functional_Programming_in_Java\\readFile_person.txt";

    /**
     * 13.2.2 从文件中读取 - 练习13.4 编写一段类似于ReadConsole的ReadFile程序，只不过它是从文件中读取，其中的每一行都是一个条目。
     *
     * @param args
     */
    public static void main(String[] args) {
        Result<Input> rInput = FileReader.fileReader(path);
        Result<Stream<Person>> rStream = rInput.map(input -> Stream.unfold(input, ReadFile::person));
        rStream.forEachOrFail(stream -> stream.toList().forEach(System.out::println))
                .forEach(System.out::println);
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

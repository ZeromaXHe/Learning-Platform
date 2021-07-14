package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter7.Result;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 17:45
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ConsoleReader extends AbstractReader {
    protected ConsoleReader(BufferedReader reader) {
        super(reader);
    }

    @Override
    public Result<Tuple<String, Input>> readString(String message) {
        System.out.print(message + " ");
        return readString();
    }

    @Override
    public Result<Tuple<Integer, Input>> readInt(String message) {
        System.out.print(message + " ");
        return readInt();
    }

    public static ConsoleReader consoleReader() {
        return new ConsoleReader(new BufferedReader(new InputStreamReader(System.in)));
    }
}

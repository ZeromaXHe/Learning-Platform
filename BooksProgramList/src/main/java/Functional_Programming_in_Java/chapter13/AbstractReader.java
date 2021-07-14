package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter7.Result;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 17:40
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class AbstractReader implements Input {
    protected final BufferedReader reader;

    protected AbstractReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public Result<Tuple<String, Input>> readString() {
        try {
            String s = reader.readLine();
            return s.length() == 0 ?
                    Result.empty() :
                    Result.success(new Tuple<>(s, this));
        } catch (IOException e) {
            return Result.failure(e);
        }
    }

    @Override
    public Result<Tuple<Integer, Input>> readInt() {
        try {
            String s = reader.readLine();
            return s.length() == 0 ?
                    Result.empty() :
                    Result.success(new Tuple<>(Integer.parseInt(s), this));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}

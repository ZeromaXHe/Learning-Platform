package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter7.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 18:05
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class FileReader extends AbstractReader {
    private FileReader(BufferedReader reader) {
        super(reader);
    }

    public static Result<Input> fileReader(String path) {
        try {
            return Result.success(new FileReader(new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File(path))))));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}

package com.zerox.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 10:50
 * @Description: 学习 汪文君 Google Guava 第 14 讲
 * @ModifiedBy: zhuxi
 */
public class Lesson14CloserTest {
    @Test
    public void testCloser() throws IOException {
        String source = "./src/test/resources/io/source.txt";
        ByteSource byteSource = Files.asByteSource(new File(source));

        Closer closer = Closer.create();
        try {
            InputStream in = closer.register(byteSource.openStream());
        } catch (IOException e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }

    @Test(expected = RuntimeException.class)
    public void testTryCatchFinally() {
        try {
            System.out.println("try");
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("catch");
            throw new RuntimeException();
        } finally {
            System.out.println("finally");
        }
    }

    // 去掉括号以查看异常堆栈
    @Test(expected = RuntimeException.class)
    public void TestTryCatch() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            // 模拟 close 的逻辑中丢出异常
            final RuntimeException runtimeException = new RuntimeException("2");
            runtimeException.addSuppressed(t);
            throw runtimeException;
        }
    }
}

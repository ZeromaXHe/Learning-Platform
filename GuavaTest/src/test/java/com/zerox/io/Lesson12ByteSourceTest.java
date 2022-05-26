package com.zerox.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 10:16
 * @Description: 学习 汪文君 Google Guava 第 12 讲
 * @ModifiedBy: zhuxi
 */
public class Lesson12ByteSourceTest {
    private final String path = "./src/test/resources/io/source.txt";

    @Test
    public void testAsByteSource() throws IOException {
        File file = new File(path);
        ByteSource byteSource = Files.asByteSource(file);
        byte[] readBytes = byteSource.read();
        Assert.assertThat(readBytes, CoreMatchers.is(Files.toByteArray(file)));
    }

    @Test
    public void testSliceByteSource() throws IOException {
        ByteSource byteSource = ByteSource.wrap(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09});
        ByteSource slice = byteSource.slice(5, 5);
        byte[] bytes = slice.read();
        // 5（包含） ~ 9
        for (byte b : bytes) {
            System.out.println(b);
        }
    }
}

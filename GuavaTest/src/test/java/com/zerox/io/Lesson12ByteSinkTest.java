package com.zerox.io;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 10:26
 * @Description: 学习 汪文君 Google Guava 第 12 讲
 * @ModifiedBy: zhuxi
 */
public class Lesson12ByteSinkTest {
    private final String path = "./src/test/resources/io/Lesson12ByteSinkTest.txt";

    @Test
    public void testByteSink() throws IOException {
        File file = new File(path);
        file.deleteOnExit();
        ByteSink byteSink = Files.asByteSink(file);
        byte[] bytes = {0x01, 0x02};
        byteSink.write(bytes);
        byte[] expected = Files.toByteArray(file);
        // CoreMatchers.is 里面其实就是 Is.is
        Assert.assertThat(expected, CoreMatchers.is(bytes));
    }

    @Test
    public void testFromSourceToSink() throws IOException {
        String source = "./src/test/resources/io/source.txt";
        String target = path;
        File sourceFile = new File(source);
        ByteSource byteSource = Files.asByteSource(sourceFile);
        File targetFile = new File(target);
        targetFile.deleteOnExit();
        byteSource.copyTo(Files.asByteSink(targetFile));

        // CoreMatchers.equalTo 里面其实就是 IsEqual.equalTo
        Assert.assertThat(targetFile.exists(), CoreMatchers.equalTo(true));
        Assert.assertThat(Files.asByteSource(sourceFile).hash(Hashing.sha256()).toString(),
                CoreMatchers.equalTo(Files.asByteSource(targetFile).hash(Hashing.sha256()).toString()));
    }
}

package com.zerox.io;

import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/3 19:57
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class Lesson11CharSourceTest {
    @Test
    public void testCharSourceWrap() throws IOException {
        CharSource charSource = CharSource.wrap("i am the CharSource");
        String resultAsRead = charSource.read();
        Assert.assertThat(resultAsRead, IsEqual.equalTo("i am the CharSource"));
        ImmutableList<String> list = charSource.readLines();
        Assert.assertThat(list.size(), IsEqual.equalTo(1));
        Assert.assertThat(charSource.length(), IsEqual.equalTo(19L));
        Assert.assertThat(charSource.isEmpty(), IsEqual.equalTo(false));
        Assert.assertThat(charSource.lengthIfKnown().get(), IsEqual.equalTo(19L));

//        Files.asCharSource();
    }

    @Test
    public void testConcat() throws IOException {
        CharSource charSource = CharSource.concat(
                CharSource.wrap("i am the CharSource1\n"),
                CharSource.wrap("i am the CharSource2")
        );

        Assert.assertThat(charSource.readLines().size(), IsEqual.equalTo(2));
        charSource.lines().forEach(System.out::println);
    }
}

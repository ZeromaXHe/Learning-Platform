package com.zerox.io;

import com.google.common.io.BaseEncoding;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 11:09
 * @Description: 学习 汪文君 Google Guava 第 15 ~ 16 讲
 * @ModifiedBy: zhuxi
 */
public class Lesson15_16BaseEncodingTest {
    @Test
    public void testBase64Encode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        // aGVsbG8=
        System.out.println(baseEncoding.encode("hello".getBytes()));
    }

    @Test
    public void testBase64Decode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        // hello
        System.out.println(new String(baseEncoding.decode("aGVsbG8=")));
    }

    @Test
    public void testMyBase64Encode() {
        System.out.println(Lesson15_16Base64.encode("hello"));
        Assert.assertThat(Lesson15_16Base64.encode("hello"),
                CoreMatchers.equalTo(BaseEncoding.base64().encode("hello".getBytes())));
        Assert.assertThat(Lesson15_16Base64.encode("alex"),
                CoreMatchers.equalTo(BaseEncoding.base64().encode("alex".getBytes())));
        Assert.assertThat(Lesson15_16Base64.encode("scala"),
                CoreMatchers.equalTo(BaseEncoding.base64().encode("scala".getBytes())));
        Assert.assertThat(Lesson15_16Base64.encode("kafka"),
                CoreMatchers.equalTo(BaseEncoding.base64().encode("kafka".getBytes())));
    }

    @Test
    public void testMyBase64Decode() {
        Assert.assertEquals("hello", Lesson15_16Base64.decode("aGVsbG8="));
        Assert.assertEquals("a", Lesson15_16Base64.decode("YQ=="));
        Assert.assertEquals("12a", Lesson15_16Base64.decode("MTJh"));
    }
}

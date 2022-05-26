package com.zerox.utilities;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/20 0:58
 * @Description: 学习 汪文君Google Guava 第05讲
 * @Modified By: ZeromaXHe
 */
public class Lesson05StringsTest {
    @Test
    public void testStringsMethod() {
        Assert.assertThat(Strings.emptyToNull(""), IsNull.nullValue());
        Assert.assertThat(Strings.nullToEmpty(null), IsEqual.equalTo(""));
        Assert.assertThat(Strings.nullToEmpty("hello"), IsEqual.equalTo("hello"));
        Assert.assertThat(Strings.commonPrefix("Hello", "Hit"), IsEqual.equalTo("H"));
        Assert.assertThat(Strings.commonPrefix("Hello", "Bit"), IsEqual.equalTo(""));
        Assert.assertThat(Strings.commonSuffix("Hello", "Echo"), IsEqual.equalTo("o"));
        Assert.assertThat(Strings.repeat("Alex", 3), IsEqual.equalTo("AlexAlexAlex"));
        Assert.assertThat(Strings.isNullOrEmpty(null), IsEqual.equalTo(true));
        Assert.assertThat(Strings.isNullOrEmpty(""), IsEqual.equalTo(true));

        Assert.assertThat(Strings.padStart("Alex", 3, 'H'), IsEqual.equalTo("Alex"));
        Assert.assertThat(Strings.padStart("Alex", 5, 'H'), IsEqual.equalTo("HAlex"));
        Assert.assertThat(Strings.padEnd("Alex", 5, 'H'), IsEqual.equalTo("AlexH"));
    }

    @Test
    public void testCharsets() {
        Charset charset = Charset.forName("UTF-8");
        Assert.assertThat(Charsets.UTF_8, IsEqual.equalTo(charset));
    }

    @Test
    public void testCharMatcher() {
        Assert.assertThat(CharMatcher.javaDigit().matches('5'), IsEqual.equalTo(true));
        Assert.assertThat(CharMatcher.javaDigit().matches('x'), IsEqual.equalTo(false));

        Assert.assertThat(CharMatcher.is('A').countIn("Alex sharing the Google Guava to us"), IsEqual.equalTo(1));
        Assert.assertThat(CharMatcher.breakingWhitespace().collapseFrom("      hello Guava     ", '*'),
                IsEqual.equalTo("*hello*Guava*"));

        Assert.assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"),
                IsEqual.equalTo("helloworld"));
        Assert.assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 234 world"),
                IsEqual.equalTo(" 234 "));
    }
}

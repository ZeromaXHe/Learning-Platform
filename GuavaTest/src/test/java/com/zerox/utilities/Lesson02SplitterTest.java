package com.zerox.utilities;

import com.google.common.base.Splitter;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/19 19:41
 * @Description: 学习 汪文君Google Guava 第02讲
 * @Modified By: ZeromaXHe
 */
public class Lesson02SplitterTest {
    @Test
    public void testSplitOnSplit() {
        List<String> result = Splitter.on("|").splitToList("hello|world");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(2));
        Assert.assertThat(result.get(0), IsEqual.equalTo("hello"));
        Assert.assertThat(result.get(1), IsEqual.equalTo("world"));
    }

    @Test
    public void testSplit_On_Split_OmitEmpty() {
        List<String> result = Splitter.on("|").splitToList("hello|world|||");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(5));

        result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(2));
    }

    @Test
    public void testSplit_On_Split_OmitEmpty_TrimResult() {
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world|||");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(2));
        Assert.assertThat(result.get(0), IsEqual.equalTo("hello "));
        Assert.assertThat(result.get(1), IsEqual.equalTo(" world"));

        result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(2));
        Assert.assertThat(result.get(0), IsEqual.equalTo("hello"));
        Assert.assertThat(result.get(1), IsEqual.equalTo("world"));
    }

    @Test
    public void testSplitFixLength() {
        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(4));
        Assert.assertThat(result.get(0), IsEqual.equalTo("aaaa"));
        Assert.assertThat(result.get(3), IsEqual.equalTo("dddd"));
    }

    @Test
    public void testSplitOnSplitLimit() {
        List<String> result = Splitter.on("#").limit(3).splitToList("hello#world#java#google#scala");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(3));
        Assert.assertThat(result.get(0), IsEqual.equalTo("hello"));
        Assert.assertThat(result.get(1), IsEqual.equalTo("world"));
        Assert.assertThat(result.get(2), IsEqual.equalTo("java#google#scala"));
    }

    @Test
    public void testSplitOnPatternString() {
        List<String> result = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(2));
        Assert.assertThat(result.get(0), IsEqual.equalTo("hello"));
        Assert.assertThat(result.get(1), IsEqual.equalTo("world"));
    }

    @Test
    public void testSplitOnPattern() {
        List<String> result = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings().splitToList("hello | world|||");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(2));
        Assert.assertThat(result.get(0), IsEqual.equalTo("hello"));
        Assert.assertThat(result.get(1), IsEqual.equalTo("world"));
    }

    @Test
    public void testSplitOnSplitToMap() {
        Map<String, String> result = Splitter.on(Pattern.compile("\\|"))
                .trimResults()
                .omitEmptyStrings()
                .withKeyValueSeparator("=")
                .split("hello=HELLO| world=WORLD|||");
        Assert.assertThat(result, IsNull.notNullValue());
        Assert.assertThat(result.size(), IsEqual.equalTo(2));
        Assert.assertThat(result.get("hello"), IsEqual.equalTo("HELLO"));
        Assert.assertThat(result.get("world"), IsEqual.equalTo("WORLD"));
    }
}

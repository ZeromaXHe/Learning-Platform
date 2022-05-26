package com.zerox.utilities;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsSame;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/19 18:36
 * @Description: 学习 汪文君Google Guava 第01讲
 * @Modified By: ZeromaXHe
 */
public class Lesson01JoinerTest {
    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", null
    );

    private final Map<String, String> stringMap = ImmutableMap.of("Hello", "Guava", "Java", "Scala");

    private final String targetFileName = ".\\src\\main\\java\\com\\zerox\\utilities\\Lesson01JoinerTest.txt";

    @Test
    public void testJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        Assert.assertThat(result, IsEqual.equalTo("Google#Guava#Java"));
    }

    @Test(expected = NullPointerException.class)
    public void testJoinOnJoinWithNullValue() {
        String result = Joiner.on("#").join(stringListWithNullValue);
        Assert.assertThat(result, IsEqual.equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoinOnJoinWithNullValueButSkip() {
        String result = Joiner.on("#")
                .skipNulls()
                .join(stringListWithNullValue);
        Assert.assertThat(result, IsEqual.equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoin_On_Join_WithNullValue_ButUseDefaultValue() {
        String result = Joiner.on("#")
                .useForNull("DEFAULT")
                .join(stringListWithNullValue);
        Assert.assertThat(result, IsEqual.equalTo("Google#Guava#Java#DEFAULT"));
    }

    @Test
    public void testJoin_On_Append_To_StringBuilder() {
        final StringBuilder builder = new StringBuilder();
        StringBuilder resultBuilder = Joiner.on("#")
                .useForNull("DEFAULT")
                .appendTo(builder, stringListWithNullValue);
        Assert.assertThat(resultBuilder, IsSame.sameInstance(builder));
        Assert.assertThat(resultBuilder.toString(), IsEqual.equalTo("Google#Guava#Java#DEFAULT"));
        Assert.assertThat(builder.toString(), IsEqual.equalTo("Google#Guava#Java#DEFAULT"));
    }

    @Test
    public void testJoin_On_Append_To_Writer() {
        /// 测试File相对路径从哪里开始，结果是GuavaTest文件夹
//        File f = new File(".");
//        String absolutePath = f.getAbsolutePath();
//        System.out.println(absolutePath)
        try (FileWriter writer = new FileWriter(new File(targetFileName))) {
            Joiner.on("#")
                    .useForNull("DEFAULT")
                    .appendTo(writer, stringListWithNullValue);
            Assert.assertThat(Files.isFile().test(new File(targetFileName)), IsEqual.equalTo(true));
        } catch (IOException e) {
            Assert.fail("appending to the writer occurs fatal error");
        }
    }

    @Test
    public void testJoiningByStreamSkipNullValues() {
        String result = stringListWithNullValue.stream()
                .filter(item -> item != null && !item.isEmpty())
                .collect(Collectors.joining("#"));
        Assert.assertThat(result, IsEqual.equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoiningByStreamWithDefaultValue() {
        String result = stringListWithNullValue.stream()
                .map(this::defaultValue)
                .collect(Collectors.joining("#"));
        Assert.assertThat(result, IsEqual.equalTo("Google#Guava#Java#DEFAULT"));
    }

    private String defaultValue(String item) {
        return item == null || item.isEmpty() ? "DEFAULT" : item;
    }

    @Test
    public void testJoinOnWithMap() {
        Assert.assertThat(Joiner.on('#').withKeyValueSeparator('=').join(stringMap), IsEqual.equalTo("Hello=Guava#Java=Scala"));
    }

    @Test
    public void testJoinOnWithMapToAppendable() {
        try (FileWriter writer = new FileWriter(new File(targetFileName))) {
            Joiner.on("#")
                    .withKeyValueSeparator('=')
                    .appendTo(writer, stringMap);
            Assert.assertThat(Files.isFile().test(new File(targetFileName)), IsEqual.equalTo(true));
        } catch (IOException e) {
            Assert.fail("appending to the writer occurs fatal error");
        }
    }
}

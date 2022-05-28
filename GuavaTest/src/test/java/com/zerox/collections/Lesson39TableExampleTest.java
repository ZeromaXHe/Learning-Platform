package com.zerox.collections;

import com.google.common.collect.HashBasedTable;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 9:44
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson39TableExampleTest {
    @Test
    public void test() {
        HashBasedTable<String, String, String> table = HashBasedTable.create();
        table.put("Language", "Java", "1.8");
        table.put("Language", "Scala", "2.3");
        table.put("Database", "Oracle", "12c");
        table.put("Database", "MySQL", "7.0");
        System.out.println(table);

        Map<String, String> language = table.row("Language");
        Assert.assertThat(language.containsKey("Java"), CoreMatchers.is(true));
        Assert.assertThat(table.row("Language").get("Java"), CoreMatchers.equalTo("1.8"));
        System.out.println(table.column("Java"));
        System.out.println(table.cellSet());
    }
}

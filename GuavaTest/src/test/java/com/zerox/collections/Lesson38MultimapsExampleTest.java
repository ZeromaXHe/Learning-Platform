package com.zerox.collections;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 9:34
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson38MultimapsExampleTest {
    @Test
    public void testBasic() {
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("1","1");
        hashMap.put("1","2");
        Assert.assertThat(hashMap.size(), CoreMatchers.equalTo(1));
        LinkedListMultimap <String, String> multimap = LinkedListMultimap.create();
        multimap.put("1","1");
        multimap.put("1","2");
        Assert.assertThat(multimap.size(), CoreMatchers.equalTo(2));
    }
}

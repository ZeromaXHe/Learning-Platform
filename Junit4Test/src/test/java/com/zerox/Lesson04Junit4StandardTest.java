package com.zerox;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/26 23:23
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson04Junit4StandardTest {
    private static List<String> classLevelList;
    private List<String> testCaseLevelList;

    @BeforeClass
    public static void init() {
        classLevelList = new ArrayList<>();
    }

    @Before
    public void setUp() {
        this.testCaseLevelList = new ArrayList<>();
    }

    /**
     * 此用例不能单独运行，否则 classLevelList size == 1
     */
    @Test
    public void addJUnitIntoTwoList() {
        Assert.assertTrue(classLevelList.add("JUnit"));
        Assert.assertTrue(testCaseLevelList.add("JUnit"));
    }

    @Test
    public void addJavaIntoTwoList() {
        Assert.assertTrue(classLevelList.add("Java"));
        Assert.assertTrue(testCaseLevelList.add("Java"));
    }

    @After
    public void tearDown() {
        Assert.assertEquals(1, testCaseLevelList.size());
        testCaseLevelList.clear();
    }

    @AfterClass
    public static void destroy() {
        Assert.assertEquals(2, classLevelList.size());
        classLevelList.clear();
    }

}

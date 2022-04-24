package com.zerox.javatest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @Author: zhuxi
 * @Time: 2022/4/20 14:23
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class CodeLineCounter {
    static int count = 0;
    static int nioCount = 0;

    public static void main(String[] args) throws IOException {
        //获取所要查询文件夹路径
        String path1 = ".";
//        String path2 = "..\\StrategyGame";
//        String path3 = "..\\GameStore";
        File file1 = new File(path1);
//        File file2 = new File(path2);
//        File file3 = new File(path3);
        myCodeCount(file1);
//        myCodeCount(file2);
//        myCodeCount(file3);
        myCodeCountNio(".");

        System.out.println("总代码行数为" + count);
        System.out.println(file1.getAbsolutePath());
//        System.out.println(file2.getAbsolutePath());
//        System.out.println(file3.getAbsolutePath());

        System.out.println("NIO 检测总代码行数为： " + nioCount);
    }

    public static void myCodeCountNio(String first, String... more) throws IOException {
        // 遍历 g:\public\codes\15 目录下的所有文件和子目录
        Path path = Paths.get(first, more);
        System.out.println(path.toAbsolutePath());
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            // 访问文件时触发该方法
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java")) {
                    int i = readData(file.toFile());
                    nioCount += i;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * 递归统计代码总行数
     *
     * @throws IOException
     */
    public static void myCodeCount(File file) throws IOException {
        //文件是否为普通文件并以.java为后缀结尾
        if (file.isFile() && (file.getName().endsWith(".java"))) {
            int i = readData(file);
            count += i;
        }
        //测试此抽象路径名表示的文件是否为目录
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                myCodeCount(f);
            }
        }
    }

    /**
     * 单个文件中的行数
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static int readData(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        int i = 0;
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (s.length() == 0 || s.startsWith("*") || s.startsWith("/")) {
                continue;
            }
            i++;
        }
        br.close();
        System.out.println(file.getName() + "的有效行数为" + i);
        return i;
    }
}

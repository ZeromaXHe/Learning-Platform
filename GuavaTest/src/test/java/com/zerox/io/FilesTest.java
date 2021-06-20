package com.zerox.io;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/20 22:43
 * @Description: 学习 汪文君Google Guava 第08~10讲
 * @Modified By: ZeromaXHe
 */
public class FilesTest {
    private final String SOURCE_FILE = "./src/test/resources/io/source.txt";
    private final String TARGET_FILE = "./src/test/resources/io/target.txt";

    @Test
    public void testCopyFileWithGuava() throws IOException {
        File targetFile = new File(TARGET_FILE);
        File sourceFile = new File(SOURCE_FILE);
        Files.copy(sourceFile, targetFile);
        Assert.assertThat(targetFile.exists(), IsEqual.equalTo(true));

        HashCode sourceHashCode = Files.asByteSource(sourceFile).hash(Hashing.sha256());
        HashCode targetHashCode = Files.asByteSource(targetFile).hash(Hashing.sha256());
        Assert.assertThat(sourceHashCode.toString(), IsEqual.equalTo(targetHashCode.toString()));
    }

    @Test
    public void testCopyFileWithJDKNio2() throws IOException {
        java.nio.file.Files.copy(
                Paths.get("./src/test/resources", "io", "source.txt"),
                Paths.get("./src/test/resources", "io", "target.txt"),
                StandardCopyOption.REPLACE_EXISTING
        );
        File targetFile = new File(TARGET_FILE);
        Assert.assertThat(targetFile.exists(), IsEqual.equalTo(true));
    }

    @Test
    public void testMoveFile() throws IOException {
        File targetFile = new File(TARGET_FILE);
        File sourceFile = new File(SOURCE_FILE);
        try {
            // TODO: better prepare data.
            Files.move(sourceFile, targetFile);
            Assert.assertThat(targetFile.exists(), IsEqual.equalTo(true));
            Assert.assertThat(sourceFile.exists(), IsEqual.equalTo(false));
        } finally {
            Files.move(targetFile, sourceFile);
        }
    }

    @Test
    public void testToString() throws IOException {
        final String expectedString = "test Guava1\n" +
                "test Guava2\n" +
                "test Guava3\n" +
                "\n" +
                "test Guava4";
        List<String> strings = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        String result = Joiner.on("\n").join(strings);
        Assert.assertThat(result, IsEqual.equalTo(expectedString));
    }

    @Test
    public void testToProcessString() throws IOException {
        LineProcessor<List<Integer>> lineProcessor = new LineProcessor<List<Integer>>() {
            private final List<Integer> lengthList = new ArrayList<>();

            @Override
            public boolean processLine(String line) throws IOException {
//                if(line.length()==0){
//                    return false;
//                }
                lengthList.add(line.length());
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return lengthList;
            }
        };
        List<Integer> result = Files.asCharSource(new File(SOURCE_FILE), Charsets.UTF_8).readLines(lineProcessor);

        System.out.println(result);
    }

    @Test
    public void testFileSha() throws IOException {
        File file = new File(SOURCE_FILE);
        /// deprecated Files.hash()
//        Files.hash(file, Hashing.goodFastHash(128));
        HashCode hashCode = Files.asByteSource(file).hash(Hashing.sha256());
        System.out.println(hashCode);
    }

    @Test
    public void testFileWrite() throws IOException {
        final String TEST_FILE = "./src/test/resources/io/testFileWrite.txt";
        File testFile = new File(TEST_FILE);
        testFile.deleteOnExit();

        String content1 = "content 1";
        /// deprecated
//        Files.write(content1, testFile, Charsets.UTF_8);
        Files.asCharSink(testFile, Charsets.UTF_8).write(content1);
        /// deprecated
//        Files.toString(testFile, Charsets.UTF_8);
        String actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        Assert.assertThat(actually, IsEqual.equalTo(content1));

        String content2 = "content 2";
        Files.asCharSink(testFile, Charsets.UTF_8).write(content2);
        actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        Assert.assertThat(actually, IsEqual.equalTo(content2));
    }

    @Test
    public void testFileAppend() throws IOException {
        final String testPath = "./src/test/resources/io/testFileWrite.txt";
        File testFile = new File(testPath);
        testFile.deleteOnExit();

        String content1 = "content1";
        CharSink charSink = Files.asCharSink(testFile, Charsets.UTF_8, FileWriteMode.APPEND);
        charSink.write(content1);
        String actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        Assert.assertThat(actually, IsEqual.equalTo(content1));

        String content2 = "content2";
        charSink.write(content2);
        actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        Assert.assertThat(actually, IsEqual.equalTo("content1content2"));
    }

    @Test
    public void testTouchFile() throws IOException {
        File touchFile = new File("./src/test/resources/io/touch.txt");
        touchFile.deleteOnExit();
        Files.touch(touchFile);
        Assert.assertThat(touchFile.exists(), IsEqual.equalTo(true));
    }

    @Test
    public void testRecursive() {
        List<File> list = new ArrayList<>();
        recursiveList(new File("./src/main"), list);
        list.forEach(System.out::println);
    }

    private void recursiveList(File root, List<File> fileList) {
        if (root.isHidden()) {
            return;
        }
//        fileList.add(root);
//        if(!root.isFile()){
//            File[] files = root.listFiles();
//            for (File f : files) {
//                recursiveList(f, fileList);
//            }
//        }

        if (root.isFile()) {
            fileList.add(root);
        } else {
            File[] files = root.listFiles();
            for (File f : files) {
                recursiveList(f, fileList);
            }
        }
    }

    @Test
    public void testTreeFilesPreOrderTraversal() {
        File root = new File("./src/main");
        // deleted method:
        // FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal(root);
        // FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal(root).filter(Files::isFile);
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(root);
        files.forEach(System.out::println);
    }

    @Test
    public void testTreeFilesPostOrderTraversal() {
        File root = new File("./src/main");
        // deleted method:
        // FluentIterable<File> files = Files.fileTreeTraverser().postOrderTraversal(root);
        Iterable<File> files = Files.fileTraverser().depthFirstPostOrder(root);
        files.forEach(System.out::println);
    }

    @Test
    public void testTreeFilesbreadthFirstTraversal() {
        File root = new File("./src/main");
        // deleted method:
        // FluentIterable<File> files = Files.fileTreeTraverser().breadthFirstTraversal(root);
        Iterable<File> files = Files.fileTraverser().breadthFirst(root);
        files.forEach(System.out::println);

        // FluentIterable<File> files = Files.fileTreeTraverser().children(root);
    }

    @After
    public void tearDown() {
        File targetFile = new File(TARGET_FILE);
        if (targetFile.exists()) {
            targetFile.delete();
        }
    }
}

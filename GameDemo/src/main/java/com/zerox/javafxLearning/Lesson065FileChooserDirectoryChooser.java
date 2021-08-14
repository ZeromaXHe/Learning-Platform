package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/8/14 16:37
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson065FileChooserDirectoryChooser extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        VBox vBox = new VBox(10);
        Button b1 = new Button("单选文件窗口");
        Button b2 = new Button("多选文件窗口");
        Button b3 = new Button("打开文本");
        Button b4 = new Button("保存文本");

        TextArea ta = new TextArea();
        ta.setWrapText(true);

        Button b5 = new Button("选择文件夹");

        vBox.getChildren().addAll(b1, b2, b3, b4, ta, b5);

        an.getChildren().addAll(vBox);

        AnchorPane.setTopAnchor(vBox, 50.0);
        AnchorPane.setLeftAnchor(vBox, 100.0);

        b1.setOnAction(event -> {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();

            fc.setTitle("单选文件");
            fc.setInitialDirectory(new File("D:" + File.separator));
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("图片", "*.gif", "*.jpg", "*.png"),
                    new FileChooser.ExtensionFilter("文本", "*.txt"),
                    new FileChooser.ExtensionFilter("所有", "*.*"));

            File file = fc.showOpenDialog(stage);
            System.out.println(file == null ? "" : file.getAbsolutePath());
        });

        b2.setOnAction(event -> {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();

            fc.setTitle("多选文件");
            fc.setInitialDirectory(new File("D:" + File.separator));
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("图片", "*.gif", "*.jpg", "*.png"),
                    new FileChooser.ExtensionFilter("文本", "*.txt"),
                    new FileChooser.ExtensionFilter("所有", "*.*"));

            List<File> files = fc.showOpenMultipleDialog(stage);
            if (files == null) {
                return;
            }
            files.forEach(System.out::println);
        });

        b3.setOnAction(event -> {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();

            fc.setTitle("选择文本");
            fc.setInitialDirectory(new File("D:" + File.separator));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("文本", "*.txt"));

            File file = fc.showOpenDialog(stage);
            if (file == null) {
                return;
            }
            System.out.println(file.getAbsolutePath());

            try (
                    /// 如果有编码问题，可以参考这里read替换掉fr
//                    FileInputStream fis = new FileInputStream(file);
//                    InputStreamReader read = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr)
            ) {
                ta.clear();
                String str;
                while ((str = br.readLine()) != null) {
                    ta.appendText(str + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        b4.setOnAction(event -> {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();

            fc.setTitle("保存文本");
            fc.setInitialFileName("默认文件名");
            fc.setInitialDirectory(new File("D:" + File.separator));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("文本", "*.txt"));

            File file = fc.showSaveDialog(stage);
            if (file == null) {
                return;
            }
            System.out.println(file.getAbsolutePath());

            try (
                    FileOutputStream fos = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)
            ) {
//                file.createNewFile();
                osw.write(ta.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        b5.setOnAction(event -> {
            Stage stage = new Stage();
            DirectoryChooser dc = new DirectoryChooser();
            dc.setTitle("文件夹选择器");
            dc.setInitialDirectory(new File("D:" + File.separator));
            File directory = dc.showDialog(stage);
            if (directory == null) {
                return;
            }
            System.out.println(directory.getAbsolutePath());
            File[] files = directory.listFiles();
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
            }
        });

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

package com.zerox.javafxLearning.Lesson024_050;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/7/11 22:25
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson026_028MenuBar extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("menu1");
        Menu menu2 = new Menu("menu2");
        Menu menu3 = new Menu("menu3");
        Menu menu4 = new Menu("menu4");

        // 分割符
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        SeparatorMenuItem separator2 = new SeparatorMenuItem();

        ImageView graphic = new ImageView("/icon/icon.jpeg");
        graphic.setFitWidth(16.0);
        graphic.setFitHeight(16.0);

        MenuItem item1 = new MenuItem("item1", graphic);
        item1.setAccelerator(KeyCombination.valueOf("ctrl+i"));

        MenuItem item2 = new MenuItem("item2");
        MenuItem item3 = new MenuItem("item3");
        MenuItem item4 = new MenuItem("item4");
        MenuItem item5 = new MenuItem("item5");

        // 子菜单
        // Menu extends MenuItem
        Menu subMenu = new Menu("subMenu");
        MenuItem item6 = new MenuItem("item6");
        MenuItem item7 = new MenuItem("item7");

        subMenu.getItems().addAll(item6, item7);

        // 单选菜单
        ToggleGroup tg = new ToggleGroup();
        RadioMenuItem rmi1 = new RadioMenuItem("RadioMenuItem1");
        RadioMenuItem rmi2 = new RadioMenuItem("RadioMenuItem2");
        RadioMenuItem rmi3 = new RadioMenuItem("RadioMenuItem3");
        rmi1.setToggleGroup(tg);
        rmi2.setToggleGroup(tg);
        rmi3.setToggleGroup(tg);
        rmi2.setSelected(true);

        // 多选菜单
        CheckMenuItem cmi1 = new CheckMenuItem("CheckMenuItem1");
        CheckMenuItem cmi2 = new CheckMenuItem("CheckMenuItem2");
        CheckMenuItem cmi3 = new CheckMenuItem("CheckMenuItem3");
        cmi1.setSelected(true);

        // 自定义菜单
        CustomMenuItem customMenuItem1 = new CustomMenuItem();
        Button button = new Button("button");
        customMenuItem1.setContent(button);
        CustomMenuItem customMenuItem2 = new CustomMenuItem();
        ProgressBar bar = new ProgressBar(0.5);
        customMenuItem2.setContent(bar);
        CustomMenuItem customMenuItem3 = new CustomMenuItem();
        HBox box = new HBox();
        box.setPrefHeight(200);
        box.setPrefWidth(200);
        box.setStyle("-fx-background-color: #FF3E96");
        box.getChildren().addAll(new Button("b1"), new Button("b2"), new Button("b3"));
        customMenuItem3.setContent(box);

        // 可展示菜单的按钮
        MenuButton mb = new MenuButton("MenuButton");
        AnchorPane.setTopAnchor(mb, 200.0);
        MenuItem mbItem1 = new MenuItem("mbItem1");
        mbItem1.setAccelerator(KeyCombination.valueOf("ctrl+k"));
        MenuItem mbItem2 = new MenuItem("mbItem2");
        MenuItem mbItem3 = new MenuItem("mbItem3");
        mb.getItems().addAll(mbItem1, mbItem2, mbItem3);
        mb.setMinWidth(200);
        mb.setMaxWidth(200);

        // 左侧可点，右侧可展示菜单的按钮
        SplitMenuButton smb = new SplitMenuButton();
        smb.setText("SplitMenuButton");
        AnchorPane.setTopAnchor(smb, 300.0);
        MenuItem smbItem1 = new MenuItem("smbItem1");
        MenuItem smbItem2 = new MenuItem("smbItem2");
        MenuItem smbItem3 = new MenuItem("smbItem3");
        smb.getItems().addAll(smbItem1, smbItem2, smbItem3);

        // 右键菜单
        ContextMenu cm = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("cmItem1");
        MenuItem cmItem2 = new MenuItem("cmItem2");
        MenuItem cmItem3 = new MenuItem("cmItem3");
        cm.getItems().addAll(cmItem1, cmItem2, cmItem3);
        Button cmButton = new Button("cmButton");
        AnchorPane.setTopAnchor(cmButton, 100.0);
        cmButton.setContextMenu(cm);

        menu1.getItems().addAll(item1, item2, item3, subMenu, separator1, cmi1, cmi2, cmi3);
        menu2.getItems().addAll(item4, item5, separator2, rmi1, rmi2, rmi3);
        menu4.getItems().addAll(customMenuItem1, customMenuItem2, customMenuItem3);

        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);

        menu3.setDisable(true);
        item4.setDisable(true);

        item5.setVisible(false);

        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);

        an.getChildren().addAll(menuBar, mb, smb, cmButton);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("menuBar");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        /// AnchorPane设置左右为0.0后就不需要监听了，可以自适应
//        menuBar.setPrefWidth(primaryStage.getWidth());
//
//        an.widthProperty().addListener((o, ov, nv) -> menuBar.setPrefWidth(primaryStage.getWidth()));

        menu1.setOnShowing(event -> System.out.println("menu1.setOnShowing"));
        menu1.setOnHidden(event -> System.out.println("menu1.setOnHidden"));

        item1.setOnAction(event -> System.out.println("item1.setOnAction"));
        item2.setOnAction(event -> System.out.println("item2.setOnAction"));

        rmi1.setOnAction(event -> {
//            RadioMenuItem r = (RadioMenuItem) event.getSource();
//            System.out.println(r.isSelected());
            System.out.println("rmi1选择状态：" + rmi1.isSelected());
            System.out.println("rmi2选择状态：" + rmi2.isSelected());
            System.out.println("rmi3选择状态：" + rmi3.isSelected());
        });

        cmi1.setOnAction(event -> {
            System.out.println("cmi1选择状态：" + cmi1.isSelected());
            System.out.println("cmi2选择状态：" + cmi2.isSelected());
            System.out.println("cmi3选择状态：" + cmi3.isSelected());
        });

        mbItem1.setOnAction(event -> mb.setText("你好"));
        mbItem1.setOnMenuValidation(event -> System.out.println("mbItem1.setOnMenuValidation"));

        cmButton.setOnContextMenuRequested(event -> System.out.println("cmButton.setOnContextMenuRequested"));
    }
}

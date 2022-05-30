package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 17:44
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson118ResourceBundleController implements Initializable {
    public Lesson118ResourceBundleController() {
        System.out.println("无参构造方法");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        System.out.println("location.getPath: " + location.getPath());
        System.out.println("resources.getBaseBundleName: " + resources.getBaseBundleName());
    }
}

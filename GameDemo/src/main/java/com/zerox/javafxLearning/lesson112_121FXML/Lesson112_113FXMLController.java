package com.zerox.javafxLearning.lesson112_121FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/24 9:44
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson112_113FXMLController {
    @FXML
    private Label fxLbl;
    @FXML
    private Button fxBtn;

    public Lesson112_113FXMLController() {
    }

    @FXML
    private void initialize() {
        System.out.println("controller init");
        System.out.println(fxLbl.getText());
    }

    @FXML
    private void action(){
        System.out.println("button on action");
    }

    public Label getFxLbl() {
        return fxLbl;
    }

    public Button getFxBtn() {
        return fxBtn;
    }
}

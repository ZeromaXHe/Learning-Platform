package com.zerox.javafxLearning;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author: zhuxi
 * @Time: 2022/5/25 14:26
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson114FXMLController {
    @FXML
    private ListView<String> listView;
    @FXML
    private Button button;

    @FXML
    private void initialize() {
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> System.out.println(newValue));

    }

    public Button getButton() {
        return button;
    }

    @FXML
    private void action() {
        try {
            FXMLLoader fl = new FXMLLoader();
            fl.setLocation(fl.getClassLoader().getResource("fxml/Lesson114FXMLreplace.fxml"));
            AnchorPane an = fl.load();

            Scene scene = new Scene(an);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(300);
            stage.setHeight(300);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

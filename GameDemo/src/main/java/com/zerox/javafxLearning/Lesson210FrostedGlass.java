package com.zerox.javafxLearning;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/15 16:30
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Lesson210FrostedGlass extends Application {
    private ImageView iv_blur = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Button bu1 = new Button("button1");
        Button bu2 = new Button("button2");
        ImageView iv = new ImageView("pic/picTest.jpeg");
        iv.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(bu1, 180.0);
        AnchorPane.setLeftAnchor(bu2, 300.0);
        an.getChildren().addAll(iv, bu1, bu2);

        Node node = getView(primaryStage);

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(an, node);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        iv.fitWidthProperty().bind(primaryStage.widthProperty());
        iv.fitHeightProperty().bind(primaryStage.heightProperty());

        TranslateTransition tt = new TranslateTransition(Duration.seconds(1), node);
        tt.setInterpolator(Interpolator.EASE_OUT);

        bu1.setOnAction(event -> {
            if (tt.getNode().getTranslateX() == -200.0) {
                tt.setFromX(-200);
                tt.setToX(0);
                tt.play();
            }
        });

        bu2.setOnAction(event -> {
            if (tt.getNode().getTranslateX() == 0.0) {
                tt.setFromX(0);
                tt.setToX(-200);
                tt.play();
            }
        });

        node.translateXProperty().addListener((o, ov, nv) -> {
            int w = 200 - (-nv.intValue());
            int h = (int) root.getHeight();
            if (w > 0) {
                WritableImage wi = new WritableImage(w, h);
                an.snapshot(new SnapshotParameters(), wi);
                iv_blur.setImage(wi);
            }
        });
    }

    public Node getView(Stage stage) {
        StackPane sp = new StackPane();

        DropShadow ds = new DropShadow();
        ds.setRadius(5);
        ds.setColor(Color.valueOf("#A3A3A399"));
        ds.setOffsetX(3);
        sp.setEffect(ds);

        AnchorPane ap = new AnchorPane();
        iv_blur = new ImageView();
        iv_blur.setEffect(new GaussianBlur());
        AnchorPane.setRightAnchor(iv_blur, 0.0);
        ap.getChildren().add(iv_blur);

        VBox vBox = new VBox(20);
        vBox.setStyle("-fx-background-color: #FFFFFF55");
        vBox.setPrefWidth(200);
        for (int i = 0; i < 5; i++) {
            vBox.getChildren().add(new Text("Hello JavaFX " + i));
        }

        sp.getChildren().addAll(ap, vBox);

        sp.setTranslateX(-200);

        vBox.prefHeightProperty().bind(stage.heightProperty());
        return sp;
    }
}

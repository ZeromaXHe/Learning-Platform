package gameFramework.view;

import com.zerox.gameFramework.Framework;
import com.zerox.gameFramework.app.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 21:58
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class HomeView extends View {
    private Button playBtn;
    private Button exitBtn;

    @Override
    public void onLaunch() {
        playBtn = new Button("Play");
        playBtn.setOnAction(event -> Framework.app.gotoView("Play"));

        exitBtn = new Button("Exit");
        exitBtn.setOnAction(event -> Framework.app.exit());

        VBox box = new VBox(playBtn, exitBtn);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        getChildren().add(box);
    }

    @Override
    public void onStart() {
        System.out.println("Start");
    }

    @Override
    public void onUpdate(double time) {
        System.out.println(time);
    }

    @Override
    public void onStop() {
        System.out.println("Stop");
    }
}

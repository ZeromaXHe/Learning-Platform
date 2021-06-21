package gameFramework.view;

import com.zerox.gameFramework.Framework;
import com.zerox.gameFramework.app.View;
import javafx.scene.control.Button;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 22:03
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class PlayView extends View {
    private Button homeBtn;

    @Override
    public void onLaunch() {
        homeBtn = new Button("Home");
        homeBtn.setOnAction(event -> Framework.app.gotoView("Home"));

        getChildren().add(homeBtn);
    }
}

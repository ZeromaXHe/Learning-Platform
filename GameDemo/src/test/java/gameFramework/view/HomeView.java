package gameFramework.view;

import com.zerox.gameFramework.Framework;
import com.zerox.gameFramework.app.View;
import com.zerox.gameFramework.input.Key;
import com.zerox.gameFramework.input.KeyInput;
import com.zerox.gameFramework.input.Mouse;
import com.zerox.gameFramework.input.MouseInput;
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
        KeyInput keyInput = Framework.keyInput;
        if (keyInput.isPressed(Key.A)) {
            System.out.println("Pressed A");
        }
        if (keyInput.isReleased(Key.A)) {
            System.out.println("Released A");
        }
        if (keyInput.isHeld(Key.B)) {
            System.out.println("Held B");
        }
        if (keyInput.isTyped(Key.C)) {
            System.out.println("Typed C:" + keyInput.getTypeCount(Key.C));
        }

        MouseInput mouseInput = Framework.mouseInput;
        System.out.println("Point: " + mouseInput.getPointX() + "," + mouseInput.getPointY());
        if (mouseInput.isPressed(Mouse.LEFT)) {
            System.out.println("Pressed LEFT");
        }
        if (mouseInput.isReleased(Mouse.LEFT)) {
            System.out.println("Released LEFT");
        }
        if (mouseInput.isHeld(Mouse.RIGHT)) {
            System.out.println("Held RIGHT");
        }
        if (mouseInput.isDragged(Mouse.LEFT)) {
            System.out.println("Dragged LEFT: "
                    + mouseInput.getDragX(Mouse.LEFT) + ","
                    + mouseInput.getDragY(Mouse.LEFT));
        }
        if (mouseInput.isClicked(Mouse.MIDDLE)) {
            System.out.println("Clicked MIDDLE: " + mouseInput.getClickCount(Mouse.MIDDLE));
        }
        if (mouseInput.isScrolled()) {
            System.out.println("Scrolled: " + mouseInput.getScrollValue());
        }
    }

    @Override
    public void onStop() {
        System.out.println("Stop");
    }
}

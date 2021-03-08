package com.zerox.gobang.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: zhuxi
 * @Time: 2021/3/5 14:58
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangFrameManager {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GoBangFrame frame = new GoBangFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}

class GoBangFrame extends JFrame {

    public GoBangFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth / 2, screenHeight / 2);

        setTitle("五子棋");
        setLocationByPlatform(true);

        /// 设置icon
//        Image img = new ImageIcon("icon.gif").getImage();
//        setIconImage(img);

        add(new GoBandHeaderComponent());

        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");

        JPanel panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        add(panel);
    }
}

class GoBandHeaderComponent extends JComponent {
    public static final int MESSAGE_X = 10;
    public static final int MESSAGE_Y = 30;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public void paintComponent(Graphics g) {
        g.drawString("五子棋", MESSAGE_X, MESSAGE_Y);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

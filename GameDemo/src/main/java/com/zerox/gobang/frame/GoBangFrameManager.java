package com.zerox.gobang.frame;

import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.controller.MainController;
import com.zerox.gobang.entity.vo.GoBangRegretResultVO;
import com.zerox.gobang.entity.vo.GoBangStepResultVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton[][] boardButtonRef = new JButton[15][15];

    private MainController goBangController = new MainController();

    private JButton regretButton;
    private JButton restartButton;
    private JTextArea info;
    private RegretAction regretAction;
    private RestartAction restartAction;
    private JPopupMenu popup;

    int[] lastStep;
    int[] regretStep;

    public GoBangFrame() {
        /// 获取电脑屏幕大小
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        int screenWidth = screenSize.width;
//        int screenHeight = screenSize.height;
//        setSize(screenWidth / 2, screenHeight / 2);
        setSize(750, 500);

        setTitle("五子棋");
        setLocationByPlatform(true);

        JMenu optionMenu = new JMenu("游戏选项");
        regretAction = new RegretAction("悔棋");
        regretAction.setEnabled(false);
        JMenuItem regretItem = optionMenu.add(regretAction);
        regretItem.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
        restartAction = new RestartAction("重新开始");
        restartAction.setEnabled(false);
        JMenuItem restartItem = optionMenu.add(restartAction);
        restartItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        optionMenu.addSeparator();

        optionMenu.add(new AbstractAction("退出") {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        // 菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(optionMenu);

        // 右键弹出框
        popup = new JPopupMenu();
        popup.add(regretAction);
        popup.add(restartAction);

        /// 设置icon
//        Image img = new ImageIcon("icon.gif").getImage();
//        setIconImage(img);
        JPanel titlePanel = new JPanel();
        titlePanel.add(new GoBandHeaderComponent());

        regretButton = new JButton("悔棋");
        regretButton.setEnabled(false);
        regretButton.addActionListener(this::regretListenerLambda);
        titlePanel.add(regretButton);

        restartButton = new JButton("重新开始");
        restartButton.setEnabled(false);
        restartButton.addActionListener(this::restartListenerLambda);
        titlePanel.add(restartButton);

        add(titlePanel, BorderLayout.NORTH);

        JPanel boardButtonPanel = new JPanel();
        boardButtonPanel.setLayout(new GridLayout(15, 15));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                JButton button = new JButton("");
                button.addActionListener(new ButtonAction(i, j));
                boardButtonPanel.add(button);
                boardButtonRef[i][j] = button;
            }
        }
        boardButtonPanel.setComponentPopupMenu(popup);
        add(boardButtonPanel, BorderLayout.CENTER);

        info = new JTextArea("【信息框】:欢迎来到五子棋！", 3, 40);
        info.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(info);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void regretListenerLambda(ActionEvent e) {
        GoBangRegretResultVO vo = goBangController.regret();
        if (!vo.isMoreRegret()) {
            regretButton.setEnabled(false);
            regretAction.setEnabled(false);
            restartButton.setEnabled(false);
            restartAction.setEnabled(false);
        }
        if (regretStep != null) {
            boardButtonRef[regretStep[0]][regretStep[1]].setBackground(null);
        }
        regretStep = vo.getRegretStep();
        if (regretStep != null) {
            boardButtonRef[regretStep[0]][regretStep[1]].setText("");
            boardButtonRef[regretStep[0]][regretStep[1]].setEnabled(true);
            boardButtonRef[regretStep[0]][regretStep[1]].setBackground(Color.ORANGE);
            appendInfo("\n【信息框】:悔棋坐标[" + regretStep[0] + "," + regretStep[1] + "], 即橙色格子");
        }

        lastStep = goBangController.getLastStep();
        if (lastStep != null) {
            boardButtonRef[lastStep[0]][lastStep[1]].setBackground(Color.GREEN);
        }
    }

    /**
     * 给信息框添加信息
     *
     * @param s
     */
    private void appendInfo(String s) {
        info.append(s);
        info.setCaretPosition(info.getText().length());
    }

    private void restartListenerLambda(ActionEvent e) {
        goBangController.restart();
        regretButton.setEnabled(false);
        regretAction.setEnabled(false);
        restartButton.setEnabled(false);
        restartAction.setEnabled(false);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardButtonRef[i][j].setEnabled(true);
                boardButtonRef[i][j].setText("");
                boardButtonRef[i][j].setBackground(null);
            }
        }
        lastStep = null;
        regretStep = null;
        appendInfo("\n【信息框】:游戏重新开始");
    }

    class RegretAction extends AbstractAction {
        public RegretAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            regretListenerLambda(e);
        }
    }

    class RestartAction extends AbstractAction {
        public RestartAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            restartListenerLambda(e);
        }
    }

    private class ButtonAction implements ActionListener {
        private int x;
        private int y;

        public ButtonAction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GoBangStepResultVO vo = goBangController.buttonStep(x, y);
            appendInfo("\n【信息框】:" + (vo.getButtonSide() == GoBangEnum.BLACK ? "黑方" : "白方") + "行棋坐标[" + x + "," + y + "], 即绿色格子");
            regretButton.setEnabled(true);
            regretAction.setEnabled(true);
            restartButton.setEnabled(true);
            restartAction.setEnabled(true);

            if (lastStep != null) {
                boardButtonRef[lastStep[0]][lastStep[1]].setBackground(null);
            }

            if (regretStep != null) {
                boardButtonRef[regretStep[0]][regretStep[1]].setBackground(null);
                regretStep = null;
            }

            boardButtonRef[x][y].setText(vo.getButtonSide() == GoBangEnum.BLACK ? "X" : "O");
            boardButtonRef[x][y].setEnabled(false);
            boardButtonRef[x][y].setBackground(Color.GREEN);
            lastStep = new int[]{x, y};
            if (vo.getDominateSide() == GoBangEnum.BLACK || vo.getDominateSide() == GoBangEnum.WHITE) {
                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        boardButtonRef[i][j].setEnabled(false);
                    }
                }
                regretButton.setEnabled(false);
                regretAction.setEnabled(false);
                appendInfo("\n【信息框】:" + (vo.getDominateSide() == GoBangEnum.BLACK ? "黑方" : "白方") + "胜利！");
            } else if (vo.getDominateSide() == GoBangEnum.TIE) {
                regretButton.setEnabled(false);
                regretAction.setEnabled(false);
                appendInfo("\n【信息框】:平局！");
            }
        }
    }
}

class GoBandHeaderComponent extends JComponent {
    public static final int MESSAGE_X = 10;
    public static final int MESSAGE_Y = 30;

    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 60;

    public void paintComponent(Graphics g) {
        g.drawString("五子棋", MESSAGE_X, MESSAGE_Y);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

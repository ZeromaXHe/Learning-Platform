package com.zerox.gobang.frame;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.controller.MainController;
import com.zerox.gobang.entity.vo.GoBangRegretResultVO;
import com.zerox.gobang.entity.vo.GoBangStepResultVO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
    private JButton aiButton;
    private JTextArea info;
    private RegretAction regretAction;
    private RestartAction restartAction;
    private JPopupMenu popup;

    private JProgressBar progressBar;
    private AiWorker aiWorker;
    private JRadioButton randomStrategyButton;
    private JRadioButton firstEmptyStrategyButton;
    private JRadioButton minmaxStrategyButton;

    int[] lastStep;
    int[] regretStep;

    public GoBangFrame() {
        /// 获取电脑屏幕大小
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        int screenWidth = screenSize.width;
//        int screenHeight = screenSize.height;
//        setSize(screenWidth / 2, screenHeight / 2);
        /// 设置icon
//        Image img = new ImageIcon("icon.gif").getImage();
//        setIconImage(img);
        setSize(750, 500);
        setTitle("五子棋");
        setLocationByPlatform(true);

        initMenu();
        initTitlePanel();
        initPopUp();
        initBoardButtonPanel();
        initInfoTextArea();
    }

    private void initInfoTextArea() {
        info = new JTextArea("【信息框】:欢迎来到五子棋！", 3, 40);
        info.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(info);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void initBoardButtonPanel() {
        JPanel boardButtonPanel = new JPanel();
        boardButtonPanel.setLayout(new GridLayout(15, 15));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                JButton button = new JButton("");
                button.addActionListener(new BoardButtonAction(i, j));
                boardButtonPanel.add(button);
                boardButtonRef[i][j] = button;
            }
        }
        boardButtonPanel.setComponentPopupMenu(popup);
        add(boardButtonPanel, BorderLayout.CENTER);
    }

    private void initPopUp() {
        // 右键弹出框
        popup = new JPopupMenu();
        popup.add(regretAction);
        popup.add(restartAction);
    }

    private void initTitlePanel() {
        JPanel titlePanel = new JPanel();
        GridBagLayout titlePanelGridBagLayout = new GridBagLayout();
        titlePanel.setLayout(titlePanelGridBagLayout);
        titlePanel.add(new JLabel("五子棋"), new GBC(0, 0, 2, 1));

        regretButton = new JButton("悔棋");
        regretButton.setEnabled(false);
        regretButton.addActionListener(this::regretListenerLambda);
        titlePanel.add(regretButton, new GBC(0, 1).setInsets(10));

        restartButton = new JButton("重新开始");
        restartButton.setEnabled(false);
        restartButton.addActionListener(this::restartListenerLambda);
        titlePanel.add(restartButton, new GBC(1, 1).setInsets(10));

        JPanel aiPanel = initAiPanel();
        titlePanel.add(aiPanel, new GBC(2, 0, 2, 2));

        add(titlePanel, BorderLayout.NORTH);
    }

    private JPanel initAiPanel() {
        JPanel aiPanel = new JPanel();
        aiPanel.setLayout(new GridBagLayout());

        aiButton = new JButton("AI行棋");
        aiButton.setEnabled(true);
        aiButton.addActionListener(e -> {
            setAllBoardButtonRefEnabledByStatus(false);
            aiButton.setEnabled(false);
            // TODO: 按钮的状态估计得用状态模式重构一下，不然代码就很恶心了。没法继续往下写。接下来的任务就是重构这里，然后再把ai行棋的和棋局上按钮下棋的操作绑定起来
            aiWorker = new AiWorker();
            aiWorker.execute();
        });
        aiPanel.add(aiButton, new GBC(0, 0).setInsets(10));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        aiPanel.add(progressBar, new GBC(1, 0));

        ButtonGroup aiStrategyButtonGroup = new ButtonGroup();
        randomStrategyButton = new JRadioButton("随机策略", true);
        randomStrategyButton.addActionListener(e -> goBangController.setBoardAiStrategy(GoBangAiStrategyEnum.RANDOM));
        aiStrategyButtonGroup.add(randomStrategyButton);
        firstEmptyStrategyButton = new JRadioButton("填充首空策略", false);
        firstEmptyStrategyButton.addActionListener(e -> goBangController.setBoardAiStrategy(GoBangAiStrategyEnum.FIRST_EMPTY));
        aiStrategyButtonGroup.add(firstEmptyStrategyButton);
        minmaxStrategyButton = new JRadioButton("最大最小策略", false);
        minmaxStrategyButton.addActionListener(e -> goBangController.setBoardAiStrategy(GoBangAiStrategyEnum.MINMAX_FROM_WEB));
        aiStrategyButtonGroup.add(minmaxStrategyButton);
        aiPanel.add(randomStrategyButton, new GBC(0, 1));
        aiPanel.add(firstEmptyStrategyButton, new GBC(1, 1));
        aiPanel.add(minmaxStrategyButton, new GBC(2, 1));

        Border etched = BorderFactory.createEtchedBorder();
        Border aiPanelBorder = BorderFactory.createTitledBorder(etched, "AI选项");
        aiPanel.setBorder(aiPanelBorder);
        return aiPanel;
    }

    private void initMenu() {
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
            appendInfo("【信息框】:悔棋坐标[" + regretStep[0] + "," + regretStep[1] + "], 即橙色格子");
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
        info.append("\n" + s);
        info.setCaretPosition(info.getText().length());
    }

    private void restartListenerLambda(ActionEvent e) {
        goBangController.restart();
        regretButton.setEnabled(false);
        regretAction.setEnabled(false);
        restartButton.setEnabled(false);
        restartAction.setEnabled(false);
        aiButton.setEnabled(true);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardButtonRef[i][j].setEnabled(true);
                boardButtonRef[i][j].setText("");
                boardButtonRef[i][j].setBackground(null);
            }
        }
        lastStep = null;
        regretStep = null;
        appendInfo("【信息框】:游戏重新开始");
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

    class AiWorker extends SwingWorker<Void, Integer> {
        FutureTask<int[]> aiFuture;

        public AiWorker() {
        }

        @Override
        protected Void doInBackground() throws Exception {
            try {
                aiFuture = new FutureTask<>(() -> goBangController.getBoardAiNextStep());
                Thread t1 = new Thread(aiFuture);
                t1.start();
                while (!aiFuture.isDone()) {
                    Thread.sleep(100);
                    publish(goBangController.getBoardAiProcess());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 如果SwingWorker通过publish发布了一些数据，那么也应该实现process方法来处理这些中间结果
         *
         * @param chunks
         */
        @Override
        protected void process(List<Integer> chunks) {
            for (Integer chunk : chunks) {
                progressBar.setValue(chunk);
            }
        }

        @Override
        protected void done() {
            aiButton.setEnabled(true);
            progressBar.setValue(100);
            if (goBangController.getBoardDominateSide() == GoBangEnum.EMPTY) {
                setAllBoardButtonRefEnabledByStatus(true);
            }
            try {
                int[] result = aiFuture.get();
                appendInfo("【信息框】aiResult:" + Arrays.toString(result));
                boardButtonRef[result[0]][result[1]].doClick();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private class BoardButtonAction implements ActionListener {
        private int x;
        private int y;

        public BoardButtonAction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GoBangStepResultVO vo = goBangController.buttonStep(x, y);
            appendInfo("【信息框】:" + (vo.getButtonSide() == GoBangEnum.BLACK ? "黑方" : "白方") + "行棋坐标[" + x + "," + y + "], 即绿色格子");
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
                setAllBoardButtonRefEnabledByStatus(false);
                regretButton.setEnabled(false);
                regretAction.setEnabled(false);
                aiButton.setEnabled(false);
                appendInfo("【信息框】:" + (vo.getDominateSide() == GoBangEnum.BLACK ? "黑方" : "白方") + "胜利！");
            } else if (vo.getDominateSide() == GoBangEnum.TIE) {
                regretButton.setEnabled(false);
                regretAction.setEnabled(false);
                aiButton.setEnabled(false);
                appendInfo("【信息框】:平局！");
            }
        }
    }

    private void setAllBoardButtonRefEnabledByStatus(boolean boolVal) {
        if (boolVal) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if ("".equals(boardButtonRef[i][j].getText())) {
                        boardButtonRef[i][j].setEnabled(boolVal);
                    }
                }
            }
        } else {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    boardButtonRef[i][j].setEnabled(false);
                }
            }
        }
    }
}

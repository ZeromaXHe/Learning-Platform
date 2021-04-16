package com.zerox.gobang.service;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.dao.GoBangBoard;
import com.zerox.gobang.entity.ai.MinMaxTreeNode;

import java.util.LinkedList;
import java.util.Random;

/**
 * @Author: zhuxi
 * @Time: 2021/3/12 18:15
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangAiService {
    private int thinkProcess;
    private GoBangAiStrategyEnum strategy = GoBangAiStrategyEnum.RANDOM;

    private final static int MINMAX_DEPTH = 2;

    public int getThinkProcess() {
        return thinkProcess;
    }

    public int[] nextStep() {
        GoBangBoard goBangBoard = GoBangBoard.getInstance();
        int[][] currentBoard = goBangBoard.getBoardCopy();
        GoBangEnum side = goBangBoard.getNowTurn();
        switch (strategy) {
            case RANDOM:
                return randomNextStep(currentBoard, side);
            case FIRST_EMPTY:
                return firstEmptyNextStep(currentBoard, side);
            case MINMAX:
                if (goBangBoard.getStepCount() == 0) {
                    thinkProcess = 100;
                    return new int[]{7, 7};
                }
                return minmaxNextStep(currentBoard, side, goBangBoard.getStepCount(), goBangBoard.getStepStackTop());
            default:
                throw new IllegalArgumentException("策略配置错误");
        }
    }

    private int[] minmaxNextStep(int[][] currentBoard, GoBangEnum side, int stepCount, int[] lastStep) {
        thinkProcess = 0;
        MinMaxTreeNode root = new MinMaxTreeNode(currentBoard, side, stepCount, lastStep[0], lastStep[1]);
        MinMaxTreeNode result = minmax(root, MINMAX_DEPTH);
        // 打印minmax树
        System.out.println(root.toMinmaxTreeString("", new StringBuilder()));
        return new int[]{result.getNextX(), result.getNextY()};
    }

    private MinMaxTreeNode minmax(MinMaxTreeNode node, int depth) {
        // 如果能得到确定的结果或者深度为零，使用评估函数返回局面得分
        if (node.isTerminal() || depth == 0) {
            node.initValue();
            return node;
        }

        node.initChildren();
        if (node.getChildrenCount() == 0) {
            node.initValue();
            return node;
        }

        LinkedList<MinMaxTreeNode> list = new LinkedList<>();

        if (GoBangEnum.WHITE.equals(node.getSide())) {
            // 如果轮到白方走棋，是极小节点，选择一个得分最小的走法
            loop1:
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (node.getChildren()[i][j] != null) {
                        MinMaxTreeNode minmax = minmax(node.getChildren()[i][j], depth - 1);
                        if (minmax.getValue() <= node.getBeta()) {
                            if (minmax.getValue() < node.getBeta()) {
                                list.clear();
                                node.setBeta(minmax.getValue());
                            }
                            list.add(minmax);
                            if (node.getBeta() <= node.getAlpha()) {
                                break loop1;
                            }
                        }
                    }
                }
            }
        } else {
            // 如果轮到黑方走棋，是极大节点，选择一个得分最大的走法
            loop2:
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (node.getChildren()[i][j] != null) {
                        MinMaxTreeNode minmax = minmax(node.getChildren()[i][j], depth - 1);
                        if (minmax.getValue() >= node.getAlpha()) {
                            if (minmax.getValue() > node.getAlpha()) {
                                list.clear();
                                node.setAlpha(minmax.getValue());
                            }
                            list.add(minmax);
                            if (node.getBeta() <= node.getAlpha()) {
                                break loop2;
                            }
                        }
                    }
                }
            }
        }
        // 这种情况应该对应被α-β剪枝了，所以应该不会采用本节点？
        if (list.isEmpty()) {
            int score = GoBangEnum.BLACK.equals(node.getSide()) ? node.getBeta() : node.getAlpha();
            node.setValue(score);
        } else {
            MinMaxTreeNode chosen = list.get((int) (Math.random() * list.size()));
            node.setValue(chosen.getValue());
            node.setNextX(chosen.getLastX());
            node.setNextY(chosen.getLastY());
        }
        return node;
    }

    private int[] firstEmptyNextStep(int[][] currentBoard, GoBangEnum side) {
        thinkProcess = 0;
        int[] result = new int[2];
        loop:
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (currentBoard[i][j] == GoBangEnum.EMPTY.getVal()) {
                    result[0] = i;
                    result[1] = j;
                    break loop;
                }
            }
        }
        // 模拟计算时间
        while (thinkProcess < 100) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thinkProcess++;
        }
        return result;
    }

    /**
     * 随机下一步
     *
     * @param currentBoard
     * @return
     */
    private int[] randomNextStep(int[][] currentBoard, GoBangEnum side) {
        thinkProcess = 0;
        Random random = new Random();
        int[] result = new int[2];
        do {
            result[0] = random.nextInt(currentBoard.length);
            result[1] = random.nextInt(currentBoard[0].length);
        } while (currentBoard[result[0]][result[1]] != GoBangEnum.EMPTY.getVal());
        // 模拟计算时间
        while (thinkProcess < 100) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thinkProcess++;
        }
        return result;
    }

    public void setAiStrategy(GoBangAiStrategyEnum strategy) {
        this.strategy = strategy;
    }
}

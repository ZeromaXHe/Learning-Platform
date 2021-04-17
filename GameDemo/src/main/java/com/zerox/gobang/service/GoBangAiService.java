package com.zerox.gobang.service;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.dao.GoBangBoard;
import com.zerox.gobang.entity.ai.MinMaxTreeNode;

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

    private final static int MINMAX_DEPTH = 3;
    private final static boolean PRINT_MINMAX_TREE = false;

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
        root.minmax(MINMAX_DEPTH);
        // 打印minmax树
        if(PRINT_MINMAX_TREE) {
            System.out.println(root.toMinmaxTreeString("", new StringBuilder()));
        }
        return new int[]{root.getNextX(), root.getNextY()};
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

package com.zerox.gobang.service;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;

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

    public int getThinkProcess() {
        return thinkProcess;
    }

    public int[] nextStep(int[][] currentBoard, GoBangEnum side) {
        switch (strategy) {
            case RANDOM:
                return randomNextStep(currentBoard, side);
            case FIRST_EMPTY:
                return firstEmptyNextStep(currentBoard, side);
            default:
                throw new IllegalArgumentException("策略配置错误");
        }
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

    /**
     * 为棋盘盘面评分
     *
     * @param board
     * @return
     */
    private int value(int[][] board) {
        return 0;
    }

    /**
     * 为棋盘上某一行打分
     *
     * @param line
     * @return
     */
    private int valueLine(int[] line) {
        return 0;
    }

    public void setAiStrategy(GoBangAiStrategyEnum strategy) {
        this.strategy = strategy;
    }
}

package com.zerox.gobang.service;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.dao.GoBangBoard;
import com.zerox.gobang.utils.ScoreMapUtils;

import java.util.HashMap;
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
    private final static HashMap<Integer, Integer> scoreMap = ScoreMapUtils.initScoreMap();

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
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                sum += valueLine(board[i][j], board[i][j + 1], board[i][j + 2], board[i][j + 3], board[i][j + 4]);
                sum += valueLine(board[j][i], board[j + 1][i], board[j + 2][i], board[j + 3][i], board[j + 4][i]);
                if (i < 11) {
                    sum += valueLine(board[i][j], board[i + 1][j + 1], board[i + 2][j + 2], board[i + 3][j + 3], board[i + 4][j + 4]);
                }
                if (i >= 4) {
                    sum += valueLine(board[i][j], board[i - 1][j + 1], board[i - 2][j + 2], board[i - 3][j + 3], board[i - 4][j + 4]);
                }
            }
        }
        return sum;
    }

    /**
     * 为棋盘上某直线上5个位置打分
     *
     * @param pos1
     * @param pos2
     * @param pos3
     * @param pos4
     * @param pos5
     * @return
     */
    private int valueLine(int pos1, int pos2, int pos3, int pos4, int pos5) {
        int key = pos5 + 10 * pos4 + 100 * pos3 + 1000 * pos2 + 10000 * pos1;
        if (scoreMap.containsKey(key)) {
            return scoreMap.get(key);
        } else {
            throw new IllegalArgumentException("valueLine fail, false key:" + key);
        }
    }

    public void setAiStrategy(GoBangAiStrategyEnum strategy) {
        this.strategy = strategy;
    }
}

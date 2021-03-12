package com.zerox.gobang.service;

import com.zerox.gobang.constant.GoBangEnum;

import java.util.Random;

/**
 * @Author: zhuxi
 * @Time: 2021/3/12 18:15
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangAiService {
    public int[] nextStep(int[][] currentBoard) {
        return randomNextStep(currentBoard);
    }

    /**
     * 随机下一步
     *
     * @param currentBoard
     * @return
     */
    private int[] randomNextStep(int[][] currentBoard) {
        Random random = new Random();
        int[] result = new int[2];
        do {
            result[0] = random.nextInt(currentBoard.length);
            result[1] = random.nextInt(currentBoard[0].length);
        } while (currentBoard[result[0]][result[1]] != GoBangEnum.EMPTY.getVal());
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
}

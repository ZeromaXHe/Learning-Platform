package com.zerox.gobang.utils;

import com.zerox.gobang.constant.GoBangEnum;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2021/4/16 11:23
 * @Description:
 * @Modified By: zhuxi
 */
public class ValueUtils {
    private final static HashMap<Integer, Integer> scoreMap = ScoreMapUtils.initScoreMap();

    public static int valueStep(int[][] board, int x, int y, GoBangEnum side) {
        int sumPre = 0;
        int sumAfter = 0;
        int xStartMin = Math.max(0, x - 4);
        int xStartMax = Math.min(10, x);
        int xSlashStartMax = Math.min(14, x + 5);
        int xSlashStartMin = Math.max(4, x);
        int yStartMin = Math.max(0, y - 4);
        int yStartMax = Math.min(10, y);
        for (int i = xStartMin; i <= xStartMax; i++) {
            sumPre += valueColumn(board, i, y);
            sumAfter += valueColumnWithStep(board, i, y, side, x);
        }
        for (int i = yStartMin; i <= yStartMax; i++) {
            sumPre += valueRow(board, x, i);
            sumAfter += valueRowWithStep(board, x, i, side, y);
        }
        for (int i = Math.max(xSlashStartMin - x, y - yStartMax); i <= Math.min(xSlashStartMax - x, y - yStartMin); i++) {
            sumPre += valueSlash(board, x + i, y - i);
            sumAfter += valueSlashWithStep(board, x + i, y - i, side, x);
        }
        for (int i = Math.max(x - xStartMax, y - yStartMax); i <= Math.min(x - xStartMin, y - yStartMin); i++) {
            sumPre += valueBackSlash(board, x - i, y - i);
            sumAfter += valueBackSlashWithStep(board, x - i, y - i, side, x);
        }

        return sumAfter - sumPre;
    }

    /**
     * 为棋盘盘面评分
     *
     * @param board
     * @return
     */
    public static int value(int[][] board) {
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                sum += valueRow(board, i, j);
                sum += valueColumn(board, j, i);
                if (i < 11) {
                    sum += valueBackSlash(board, i, j);
                }
                if (i >= 4) {
                    sum += valueSlash(board, i, j);
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
    private static int valueLine(int pos1, int pos2, int pos3, int pos4, int pos5) {
        int key = pos5 + 10 * pos4 + 100 * pos3 + 1000 * pos2 + 10000 * pos1;
        if (scoreMap.containsKey(key)) {
            return scoreMap.get(key);
        } else {
            throw new IllegalArgumentException("valueLine fail, false key:" + key);
        }
    }

    /**
     * 以[x, y]为起点，向右方计算行方向的valueLine
     *
     * @param board
     * @param x
     * @param y
     * @return
     */
    private static int valueRow(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x][y + 1], board[x][y + 2], board[x][y + 3], board[x][y + 4]);
    }

    private static int valueRowWithStep(int[][] board, int x, int y, GoBangEnum side, int stepY) {
        int pos1 = board[x][y];
        int pos2 = board[x][y + 1];
        int pos3 = board[x][y + 2];
        int pos4 = board[x][y + 3];
        int pos5 = board[x][y + 4];

        if (y == stepY) {
            pos1 = side.getVal();
        } else if (y + 1 == stepY) {
            pos2 = side.getVal();
        } else if (y + 2 == stepY) {
            pos3 = side.getVal();
        } else if (y + 3 == stepY) {
            pos4 = side.getVal();
        } else if (y + 4 == stepY) {
            pos5 = side.getVal();
        }

        return valueLine(pos1, pos2, pos3, pos4, pos5);
    }

    /**
     * 以[x, y]为起点，向下方计算列方向的valueLine
     *
     * @param board
     * @param x
     * @param y
     * @return
     */
    private static int valueColumn(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x + 1][y], board[x + 2][y], board[x + 3][y], board[x + 4][y]);
    }

    private static int valueColumnWithStep(int[][] board, int x, int y, GoBangEnum side, int stepX) {
        int pos1 = board[x][y];
        int pos2 = board[x + 1][y];
        int pos3 = board[x + 2][y];
        int pos4 = board[x + 3][y];
        int pos5 = board[x + 4][y];

        if (x == stepX) {
            pos1 = side.getVal();
        } else if (x + 1 == stepX) {
            pos2 = side.getVal();
        } else if (x + 2 == stepX) {
            pos3 = side.getVal();
        } else if (x + 3 == stepX) {
            pos4 = side.getVal();
        } else if (x + 4 == stepX) {
            pos5 = side.getVal();
        }

        return valueLine(pos1, pos2, pos3, pos4, pos5);
    }

    /**
     * 以[x, y]为起点，向右上方计算斜杠方向（/）的valueLine
     *
     * @param board
     * @param x
     * @param y
     * @return
     */
    private static int valueSlash(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x - 1][y + 1], board[x - 2][y + 2], board[x - 3][y + 3], board[x - 4][y + 4]);
    }

    private static int valueSlashWithStep(int[][] board, int x, int y, GoBangEnum side, int stepX) {
        int pos1 = board[x][y];
        int pos2 = board[x - 1][y + 1];
        int pos3 = board[x - 2][y + 2];
        int pos4 = board[x - 3][y + 3];
        int pos5 = board[x - 4][y + 4];

        if (x == stepX) {
            pos1 = side.getVal();
        } else if (x + 1 == stepX) {
            pos2 = side.getVal();
        } else if (x + 2 == stepX) {
            pos3 = side.getVal();
        } else if (x + 3 == stepX) {
            pos4 = side.getVal();
        } else if (x + 4 == stepX) {
            pos5 = side.getVal();
        }

        return valueLine(pos1, pos2, pos3, pos4, pos5);
    }

    /**
     * 以[x, y]为起点，向右下方计算反斜杠方向（\）的valueLine
     *
     * @param board
     * @param x
     * @param y
     * @return
     */
    private static int valueBackSlash(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x + 1][y + 1], board[x + 2][y + 2], board[x + 3][y + 3], board[x + 4][y + 4]);
    }

    private static int valueBackSlashWithStep(int[][] board, int x, int y, GoBangEnum side, int stepX) {
        int pos1 = board[x][y];
        int pos2 = board[x + 1][y + 1];
        int pos3 = board[x + 2][y + 2];
        int pos4 = board[x + 3][y + 3];
        int pos5 = board[x + 4][y + 4];

        if (x == stepX) {
            pos1 = side.getVal();
        } else if (x + 1 == stepX) {
            pos2 = side.getVal();
        } else if (x + 2 == stepX) {
            pos3 = side.getVal();
        } else if (x + 3 == stepX) {
            pos4 = side.getVal();
        } else if (x + 4 == stepX) {
            pos5 = side.getVal();
        }

        return valueLine(pos1, pos2, pos3, pos4, pos5);
    }
}

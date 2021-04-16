package com.zerox.gobang.utils;

import com.zerox.gobang.constant.GoBangEnum;

/**
 * @Author: zhuxi
 * @Time: 2021/4/15 15:05
 * @Description:
 * @Modified By: zhuxi
 */
public class BoardUtils {
    /**
     * 计算新的获胜状态
     *
     * @param x
     * @param y
     * @return
     */
    public static GoBangEnum calcNewDominateSide(int[][] board, GoBangEnum side, int x, int y, int step) {
        // 判断上下方向
        int count = 1;
        int diff = 1;
        while (x - diff >= 0 && board[x - diff][y] == board[x][y]) {
            count++;
            diff++;
        }
        diff = 1;
        while (x + diff < 15 && board[x + diff][y] == board[x][y]) {
            count++;
            diff++;
        }
        if (count >= 5) {
            return side;
        }

        // 判断左右方向
        count = 1;
        diff = 1;
        while (y - diff >= 0 && board[x][y - diff] == board[x][y]) {
            count++;
            diff++;
        }
        diff = 1;
        while (y + diff < 15 && board[x][y + diff] == board[x][y]) {
            count++;
            diff++;
        }
        if (count >= 5) {
            return side;
        }

        // 判断左上到右下方向
        count = 1;
        diff = 1;
        while (x - diff >= 0 && y - diff >= 0 && board[x - diff][y - diff] == board[x][y]) {
            count++;
            diff++;
        }
        diff = 1;
        while (x + diff < 15 && y + diff < 15 && board[x + diff][y + diff] == board[x][y]) {
            count++;
            diff++;
        }
        if (count >= 5) {
            return side;
        }

        // 判断左下到右上方向
        count = 1;
        diff = 1;
        while (x + diff < 15 && y - diff >= 0 && board[x + diff][y - diff] == board[x][y]) {
            count++;
            diff++;
        }
        diff = 1;
        while (x - diff >= 0 && y + diff < 15 && board[x - diff][y + diff] == board[x][y]) {
            count++;
            diff++;
        }
        if (count >= 5) {
            return side;
        }

        return step == 15 * 15 ? GoBangEnum.TIE : GoBangEnum.EMPTY;
    }

    public static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[15][15];
        for (int i = 0; i < 15; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 15);
        }
        return copy;
    }

    public static String trans2DArrWithChangeLineToString(int[][] arr2D) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr2D.length; i++) {
            if (i != 0) sb.append(",\n");
            sb.append('[');
            for (int j = 0; j < arr2D[0].length; j++) {
                if (j != 0) sb.append(',');
                sb.append(arr2D[i][j]);
            }
            sb.append(']');
        }
        sb.append(']');
        return sb.toString();
    }

    public static String transBoardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int i = 0; i < 15; i++) {
            if (i < 10) {
                sb.append(' ');
            }
            sb.append(i);
            sb.append("  ");
        }
        for (int i = 0; i < 15; i++) {
            sb.append('\n');
            if (i < 10) {
                sb.append(' ');
            }
            sb.append(i).append('|');
            for (int j = 0; j < 15; j++) {
                String fill;
                if (board[i][j] == GoBangEnum.EMPTY.getVal()) {
                    fill = "   ";
                } else if (board[i][j] == GoBangEnum.BLACK.getVal()) {
                    fill = " X ";
                } else if (board[i][j] == GoBangEnum.WHITE.getVal()) {
                    fill = " O ";
                } else {
                    throw new IllegalArgumentException("board contains illegal argument");
                }
                sb.append(fill).append('|');
            }
        }
        return sb.toString();
    }
}

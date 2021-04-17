package com.zerox.gobang.utils;

import com.zerox.gobang.constant.GoBangEnum;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/18 2:18
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class ValueFromWebUtils {
    private final static int N = 15;
    private final static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1}; //flat技术
    private final static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};//（dx,dy）是8个方向向量

    public static int value(int x, int y, int[][] board, GoBangEnum side) {
        int row = x + 1;
        int col = y + 1;
        int[][] paddedBoard = BoardFromWebUtils.transBoardToPaddedBoard(board);
        paddedBoard[row][col] = GoBangEnum.EMPTY.getVal();
        int point = point(row, col, paddedBoard);
        return GoBangEnum.BLACK == side ? point : -point;
    }

    /**
     * 非负分值
     *
     * @param row
     * @param col
     * @param paddedBoard
     * @return
     */
    public static int point(int row, int col, int[][] paddedBoard) {
        if (BoardFromWebUtils.end_(row, col, paddedBoard)) {
            return 10000;
        }
        int ret = live4(row, col, paddedBoard) * 1000 + (chong4(row, col, paddedBoard) + live3(row, col, paddedBoard)) * 100, u;
        for (u = 0; u < 8; u++) {
            if (paddedBoard[row + dx[u]][col + dy[u]] != 0) {
                ret++;//无效点0分
            }
        }
        return ret;
    }

    /**
     * 落子成活4的数量
     *
     * @param row
     * @param col
     * @param paddedBoard
     * @return
     */
    private static int live4(int row, int col, int[][] paddedBoard) {
        int sum = 0, i, u;
        //4个方向，判断每个方向是否落子就成活4
        for (u = 0; u < 4; u++) {
            int sumk = 1;
            for (i = 1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard); i++) {
                sumk++;
            }
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            for (i = -1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard); i--) {
                sumk++;
            }
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            if (sumk == 4) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 成5点的数量
     *
     * @param row
     * @param col
     * @param paddedBoard
     * @return
     */
    private static int cheng5(int row, int col, int[][] paddedBoard) {
        int sum = 0, i, u;
        //8个成五点的方向
        for (u = 0; u < 8; u++) {
            int sumk = 0;
            boolean flag = true;
            for (i = 1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard) || flag; i++) {
                //该方向的第一个不同色的点，超出边界或者对方棋子或空格
                if (!BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard)) {
                    if (paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0) {
                        sumk -= 10;//该方向的第一个不同色的点是对方棋子,没有成五点
                    }
                    flag = false;
                }
                sumk++;
            }
            if (!BoardFromWebUtils.inboard(row + dx[u] * --i, col + dy[u] * i)) {
                continue;//该方向的第一个不同色的点是超出边界,没有成五点
            }
            for (i = -1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard); i--) {
                sumk++;
            }
            if (sumk == 4) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 冲4的数量
     *
     * @param row
     * @param col
     * @param paddedBoard
     * @return
     */
    private static int chong4(int row, int col, int[][] paddedBoard) {
        return cheng5(row, col, paddedBoard) - live4(row, col, paddedBoard) * 2;
    }

    /**
     * 落子成活3的数量
     *
     * @param row
     * @param col
     * @param paddedBoard
     * @return
     */
    private static int live3(int row, int col, int[][] paddedBoard) {
        int key = paddedBoard[row][col], sum = 0, i, u, flag = 2;
        //三连的活三
        for (u = 0; u < 4; u++) {
            int sumk = 1;
            for (i = 1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard); i++) {
                sumk++;
            }
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            i++;
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                flag--;
            }
            for (i = -1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard); i--) {
                sumk++;
            }
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            i--;
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                flag--;
            }
            if (sumk == 3 && flag > 0) {
                sum++;
            }
        }
        //8个方向，每个方向最多1个非三连的活三
        for (u = 0; u < 8; u++) {
            int sumk = 0;
            boolean flagBool = true;
            //成活四点的方向
            for (i = 1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard) || flagBool; i++) {
                if (!BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard)) {
                    if (flagBool && paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0) {
                        sumk -= 10;
                    }
                    flagBool = false;
                }
                sumk++;
            }
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            if (paddedBoard[row + dx[u] * --i][col + dy[u] * i] == 0) {
                continue;
            }
            for (i = 1; BoardFromWebUtils.same(row + dx[u] * i, col + dy[u] * i, paddedBoard[row][col], paddedBoard); i++) {
                sumk++;
            }
            if ((!BoardFromWebUtils.inboard(row + dx[u] * i, col + dy[u] * i) || paddedBoard[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            if (sumk == 3) {
                sum++;
            }
        }
        return sum;
    }
}

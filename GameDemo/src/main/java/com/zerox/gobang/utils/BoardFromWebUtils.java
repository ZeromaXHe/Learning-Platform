package com.zerox.gobang.utils;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/18 3:41
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class BoardFromWebUtils {
    private final static int N = 15;
    private final static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1}; //flat技术
    private final static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};//（dx,dy）是8个方向向量

    /**
     * 将 15*15 的board转化为边缘为 0 的 17*17 的paddedBoard
     *
     * @param board
     * @return
     */
    public static int[][] transBoardToPaddedBoard(int[][] board) {
        int[][] paddedBoard = new int[N + 2][N + 2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                paddedBoard[i + 1][j + 1] = board[i][j];
            }
        }
        return paddedBoard;
    }

    /**
     * 能否落子
     *
     * @param row
     * @param col
     * @return
     */
    public static boolean ok(int row, int col, int[][] p) {
        return inboard(row, col) && (p[row][col] == 0);
    }

    /**
     * 判断(row,col)是否在棋盘内
     *
     * @param row
     * @param col
     * @return
     */
    public static boolean inboard(int row, int col) {
        if (row < 1 || row > N) {
            return false;
        }
        return col >= 1 && col <= N;
    }

    /**
     * 判断2个棋子是否同色
     *
     * @param row
     * @param col
     * @param key
     * @param paddedBoard
     * @return
     */
    static boolean same(int row, int col, int key, int[][] paddedBoard) {
        if (!inboard(row, col)) {
            return false;
        }
        return (paddedBoard[row][col] == key || paddedBoard[row][col] + key == 0);
    }

    /**
     * (row,col)处落子之后是否游戏结束
     *
     * @param row
     * @param col
     * @param paddedBoard
     * @return
     */
    public static boolean end_(int row, int col, int[][] paddedBoard) {
        for (int u = 0; u < 4; u++) {
            if (num(row, col, u, paddedBoard) + num(row, col, u + 4, paddedBoard) >= 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * 坐标（row,col），方向向量u，返回该方向有多少连续同色棋子
     *
     * @param row
     * @param col
     * @param u
     * @param paddedBoard
     * @return
     */
    private static int num(int row, int col, int u, int[][] paddedBoard) {
        int i = row + dx[u], j = col + dy[u], sum = 0, ref = paddedBoard[row][col];
        if (ref == 0) {
            return 0;
        }
        while (BoardFromWebUtils.same(i, j, ref, paddedBoard)) {
            sum++;
            i += dx[u];
            j += dy[u];
        }
        return sum;
    }
}

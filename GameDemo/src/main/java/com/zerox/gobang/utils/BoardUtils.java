package com.zerox.gobang.utils;

/**
 * @Author: zhuxi
 * @Time: 2021/4/15 15:05
 * @Description:
 * @Modified By: zhuxi
 */
public class BoardUtils {
    public static int[][] copyBoard(int[][] board){
        int[][] copy = new int[15][15];
        for (int i = 0; i < 15; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 15);
        }
        return copy;
    }
}

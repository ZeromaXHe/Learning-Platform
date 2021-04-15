package com.zerox.gobang.entity.ai;

import com.zerox.gobang.constant.GoBangEnum;

/**
 * @Author: zhuxi
 * @Time: 2021/4/15 14:27
 * @Description:
 * @Modified By: zhuxi
 */
public class MinMaxTreeNode {
    private int[][] board;
    private MinMaxTreeNode[][] children = new MinMaxTreeNode[15][15];
    private int childrenCount = 0;

    private int lastX = -1;
    private int lastY = -1;

    public MinMaxTreeNode() {
    }

    public void init(int[][] board, int x, int y) {
        init(board);
        this.lastX = x;
        this.lastY = y;
    }

    public void init(int[][] board) {
        this.board = board;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] != GoBangEnum.EMPTY.getVal()) {
                    for (int m = Math.max(0, i - 2); m <= Math.min(14, i + 2); m++) {
                        for (int n = Math.max(0, j - 2); n <= Math.min(14, j + 2); n++) {
                            if (board[m][n] == GoBangEnum.EMPTY.getVal() && children[m][n] == null) {
                                children[m][n] = new MinMaxTreeNode();
                                childrenCount++;
                            }
                        }
                    }
                }
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public MinMaxTreeNode[][] getChildren() {
        return children;
    }

    public void setChildren(MinMaxTreeNode[][] children) {
        this.children = children;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }
}

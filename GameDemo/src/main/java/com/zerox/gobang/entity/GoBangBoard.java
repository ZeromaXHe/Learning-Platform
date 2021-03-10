package com.zerox.gobang.entity;

import com.zerox.gobang.constant.GoBangEnum;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2021/3/5 11:39
 * @Description: 测试类：{@link/ gobang.GoBangBoardTest}
 * @Modified By: zhuxi
 */
public class GoBangBoard {
    /**
     * 当前棋盘状态
     */
    private int[][] board;
    /**
     * 存储每一步的栈
     */
    private LinkedList<int[]> stepStack;
    /**
     * 获胜方
     */
    private GoBangEnum dominateSide;

    public GoBangBoard() {
        this.board = new int[15][15];
        stepStack = new LinkedList<>();
        dominateSide = GoBangEnum.EMPTY;
    }

    /**
     * 获取当前回合的执行方
     *
     * @return
     */
    public GoBangEnum getNowTurn() {
        return stepStack.size() % 2 == 0 ? GoBangEnum.BLACK : GoBangEnum.WHITE;
    }

    /**
     * 获取对手
     *
     * @param side
     * @return
     */
    public GoBangEnum getOpposite(GoBangEnum side) {
        if (side == GoBangEnum.BLACK) {
            return GoBangEnum.WHITE;
        } else if (side == GoBangEnum.WHITE) {
            return GoBangEnum.BLACK;
        } else {
            throw new IllegalArgumentException("Unknown side");
        }
    }

    /**
     * 下一步
     *
     * @param x
     * @param y
     */
    public void step(int x, int y) {
        if (dominateSide != GoBangEnum.EMPTY || x >= 15 || y >= 15 || x < 0 || y < 0 || board[x][y] != GoBangEnum.EMPTY.getVal()) {
            throw new IllegalArgumentException("Illegal movement [x,y]:" + x + "," + y);
        }
        GoBangEnum nowTurn = getNowTurn();
        board[x][y] = nowTurn.getVal();
        dominateSide = calcNewDominateSide(x, y);
        stepStack.push(new int[]{x, y});
    }

    /**
     * 计算新的获胜状态
     *
     * @param x
     * @param y
     * @return
     */
    private GoBangEnum calcNewDominateSide(int x, int y) {
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
            return getNowTurn();
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
            return getNowTurn();
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
            return getNowTurn();
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
            return getNowTurn();
        }

        return stepStack.size() == 15 * 15 ? GoBangEnum.TIE : GoBangEnum.EMPTY;
    }

    /**
     * 悔棋
     */
    public int[] regret() {
        if (stepStack.isEmpty()) {
            throw new RuntimeException("Can't regret at the start");
        }
        int[] pop = stepStack.pop();
        board[pop[0]][pop[1]] = GoBangEnum.EMPTY.getVal();
        return pop;
    }

    /**
     * 输出棋盘
     *
     * @return
     */
    public String getBoardString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < board.length; i++) {
            if (i != 0) sb.append(",\n");
            sb.append('[');
            for (int j = 0; j < board[0].length; j++) {
                if (j != 0) sb.append(',');
                sb.append(board[i][j]);
            }
            sb.append(']');
        }
        sb.append(']');
        return sb.toString();
    }

    public GoBangEnum getDominateSide() {
        return dominateSide;
    }

    public int getStepCount() {
        return stepStack.size();
    }

    public int[] getStepStackTop() {
        if (stepStack.isEmpty()) {
            return null;
        } else {
            return stepStack.peek();
        }
    }
}

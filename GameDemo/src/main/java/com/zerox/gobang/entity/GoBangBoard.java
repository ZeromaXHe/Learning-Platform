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
     * 右上向左下方向的斜线是否存在棋子
     */
    private boolean[] slashExistChess;
    /**
     * 左上向右下方向的斜线是否存在棋子
     */
    private boolean[] backSlashExistChess;
    /**
     * 每一行是否存在棋子
     */
    private boolean[] rowExistChess;
    /**
     * 每一列是否存在棋子
     */
    private boolean[] columnExistChess;
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

        slashExistChess = new boolean[29];
        backSlashExistChess = new boolean[29];
        rowExistChess = new boolean[15];
        columnExistChess = new boolean[15];
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
        if (dominateSide != GoBangEnum.EMPTY
                || x >= 15 || y >= 15 || x < 0 || y < 0
                || board[x][y] != GoBangEnum.EMPTY.getVal()) {
            throw new IllegalArgumentException("Illegal movement [x,y]:" + x + "," + y);
        }
        GoBangEnum nowTurn = getNowTurn();
        board[x][y] = nowTurn.getVal();

        saveExistInfo(x, y);

        dominateSide = calcNewDominateSide(x, y);
        stepStack.push(new int[]{x, y});
    }

    /**
     * 保存行、列、斜线棋子存在的信息
     *
     * @param x
     * @param y
     */
    private void saveExistInfo(int x, int y) {
        slashExistChess[x + y] = true;
        backSlashExistChess[x - y + 14] = true;
        rowExistChess[x] = true;
        columnExistChess[y] = true;
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
        int x = pop[0];
        int y = pop[1];
        board[x][y] = GoBangEnum.EMPTY.getVal();

        rollbackExistInfo(x, y);

        return pop;
    }

    /**
     * 回滚行、列、斜线棋子存在的信息
     *
     * @param x
     * @param y
     */
    private void rollbackExistInfo(int x, int y) {
        boolean slashExist = false;
        boolean backSlashExist = false;
        boolean rowExist = false;
        boolean columnExist = false;
        for (int i = 0; i < 15; i++) {
            if (board[x][i] != GoBangEnum.EMPTY.getVal()) {
                rowExist = true;
            }
            if (board[i][y] != GoBangEnum.EMPTY.getVal()) {
                columnExist = true;
            }
            if (x + y - i >= 0 && x + y - i < 15 && board[i][x + y - i] != GoBangEnum.EMPTY.getVal()) {
                slashExist = true;
            }
            if (i + y - x >= 0 && i + y - x < 15 && board[i][i + y - x] != GoBangEnum.EMPTY.getVal()) {
                backSlashExist = true;
            }
        }
        if (!slashExist) {
            slashExistChess[x + y] = false;
        }
        if (!backSlashExist) {
            backSlashExistChess[x - y + 14] = false;
        }
        if (!rowExist) {
            rowExistChess[x] = false;
        }
        if (!columnExist) {
            columnExistChess[y] = false;
        }
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

    /**
     * 获取当前盘面拷贝
     *
     * @return
     */
    public int[][] getBoardCopy() {
        int[][] result = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                result[i][j] = board[i][j];
            }
        }
        return result;
    }
}

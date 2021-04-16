package com.zerox.gobang.entity.ai;

import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.utils.BoardUtils;
import com.zerox.gobang.utils.ValueUtils;

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
    private int step;
    private boolean terminal = false;
    private GoBangEnum side;

    private int lastX = -1;
    private int lastY = -1;
    private int nextX = -1;
    private int nextY = -1;
    private Integer value;
    private int alpha = Integer.MIN_VALUE;
    private int beta = Integer.MAX_VALUE;

    public MinMaxTreeNode() {
    }

    public MinMaxTreeNode(int[][] board, GoBangEnum side, int stepCount, int lastX, int lastY) {
        init(board, side, stepCount, lastX, lastY);
    }

    public void init(int[][] board, GoBangEnum side, int stepCount, int lastX, int lastY) {
        this.board = board;
        this.step = stepCount;
        this.side = side;
        this.lastX = lastX;
        this.lastY = lastY;
        if (stepCount >= 9) {
            GoBangEnum dominateSide = BoardUtils.calcNewDominateSide(board, side, lastX, lastY, step);
            if (!GoBangEnum.EMPTY.equals(dominateSide)) {
                this.terminal = true;
            }
        }
    }

    public void initChildren() {
        if (board == null) {
            throw new IllegalStateException("need initBoard first");
        }
        if (terminal) {
            return;
        }
        // 子节点颜色相反
        GoBangEnum childrenSide = GoBangEnum.BLACK;
        if (GoBangEnum.BLACK.equals(side)) {
            childrenSide = GoBangEnum.WHITE;
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] != GoBangEnum.EMPTY.getVal()) {
                    for (int m = Math.max(0, i - 2); m <= Math.min(14, i + 2); m++) {
                        for (int n = Math.max(0, j - 2); n <= Math.min(14, j + 2); n++) {
                            if (board[m][n] == GoBangEnum.EMPTY.getVal() && children[m][n] == null) {
                                int[][] boardNode = BoardUtils.copyBoard(board);
                                boardNode[m][n] = childrenSide.getVal();
                                children[m][n] = new MinMaxTreeNode(boardNode, childrenSide, step + 1, m, n);
                                childrenCount++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void initValue() {
        if (board == null) {
            throw new IllegalStateException("need initBoard first");
        }
        this.value = ValueUtils.value(board);
    }

    public void addStepValue(int x, int y, GoBangEnum side) {
        this.value += ValueUtils.valueStep(board, x, y, side);
    }

    public StringBuilder toMinmaxTreeString(String spaces, StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        if (spaces == null) {
            spaces = "";
        }
        sb.append(spaces).append(this.toString()).append('\n');
        if (this.getChildrenCount() > 0) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (children[i][j] != null) {
                        children[i][j].toMinmaxTreeString(spaces + "    ", sb);
                    }
                }
            }
        }
        return sb;
    }

    @Override
    public String toString() {
        return "(" + side + ") last[" + lastX + "," + lastY + "] " +
                "next[" + nextX + "," + nextY + "] " +
                "value:" + value + " alpha:" + alpha + " beta:" + beta;
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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public GoBangEnum getSide() {
        return side;
    }

    public void setSide(GoBangEnum side) {
        this.side = side;
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

    public int getNextX() {
        return nextX;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }
}

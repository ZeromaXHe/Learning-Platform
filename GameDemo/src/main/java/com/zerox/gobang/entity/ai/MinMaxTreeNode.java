package com.zerox.gobang.entity.ai;

import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.utils.BoardUtils;
import com.zerox.gobang.utils.ValueFromWebUtils;
import com.zerox.gobang.utils.ValueUtils;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2021/4/15 14:27
 * @Description:
 * @Modified By: zhuxi
 */
public class MinMaxTreeNode {
    private int[][] board;
    private MinMaxTreeNode[][] children = new MinMaxTreeNode[15][15];
    private LinkedList<MinMaxTreeNode> childrenList = new LinkedList<>();
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

    public void minmax(int depth) {
        // 如果能得到确定的结果或者深度为零，使用评估函数返回局面得分
        if (terminal || depth == 0) {
            initValue();
            return;
        }

        initChildren();
        if (childrenList.isEmpty()) {
            initValue();
            return;
        }

        LinkedList<MinMaxTreeNode> list = new LinkedList<>();

        if (GoBangEnum.WHITE == side) {
            // 如果轮到白方走棋，是极小节点，选择一个得分最小的走法
            int min = Integer.MAX_VALUE;
            for (MinMaxTreeNode child : childrenList) {
                child.minmax(depth - 1);
                if (child.value < min) {
                    list.clear();
                    list.add(child);
                    min = child.value;
                } else if (child.value == min) {
                    list.add(child);
                }
            }
        } else {
            // 如果轮到黑方走棋，是极大节点，选择一个得分最大的走法
            int max = Integer.MIN_VALUE;
            for (MinMaxTreeNode child : childrenList) {
                child.minmax(depth - 1);
                if (child.value > max) {
                    list.clear();
                    list.add(child);
                    max = child.value;
                } else if (child.value == max) {
                    list.add(child);
                }
            }
        }
        MinMaxTreeNode chosen = list.get((int) (Math.random() * list.size()));
        value = chosen.getValue();
        nextX = chosen.getLastX();
        nextY = chosen.getLastY();
    }

    /**
     * alpha-beta剪枝的minmax
     *
     * @param depth
     */
    public void minmax_alphaBeta(int depth) {
        // 如果能得到确定的结果或者深度为零，使用评估函数返回局面得分
        if (terminal || depth == 0) {
            initValue();
            return;
        }

        initChildren();
        if (childrenList.isEmpty()) {
            initValue();
            return;
        }

        LinkedList<MinMaxTreeNode> list = new LinkedList<>();

        if (GoBangEnum.WHITE == side) {
            // 如果轮到白方走棋，是极小节点，选择一个得分最小的走法
            for (MinMaxTreeNode child : childrenList) {
                child.minmax_alphaBeta(depth - 1);
                if (child.getValue() <= beta) {
                    if (child.getValue() < beta) {
                        list.clear();
                        beta = child.getValue();
                    }
                    list.add(child);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        } else {
            // 如果轮到黑方走棋，是极大节点，选择一个得分最大的走法
            for (MinMaxTreeNode child : childrenList) {
                child.minmax_alphaBeta(depth - 1);
                if (child.getValue() >= alpha) {
                    if (child.getValue() > alpha) {
                        list.clear();
                        alpha = child.getValue();
                    }
                    list.add(child);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }
        // 这种情况应该对应被α-β剪枝了，所以应该不会采用本节点？
        if (list.isEmpty()) {
            value = GoBangEnum.BLACK == side ? beta : alpha;
        } else {
            MinMaxTreeNode chosen = list.get((int) (Math.random() * list.size()));
            value = chosen.getValue();
            nextX = chosen.getLastX();
            nextY = chosen.getLastY();
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
        if (GoBangEnum.BLACK == side) {
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
                                childrenList.add(children[m][n]);
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
        /// 原来自己写的评估函数
//        this.value = ValueUtils.value(board);
        // 网上的评估函数改的java版本（仅计算走的那一步的价值）
        this.value = ValueFromWebUtils.value(lastX, lastY, board, side);
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
        if (!this.childrenList.isEmpty()) {
            for (MinMaxTreeNode child : childrenList) {
                child.toMinmaxTreeString(spaces + "    ", sb);
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

    public LinkedList<MinMaxTreeNode> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(LinkedList<MinMaxTreeNode> childrenList) {
        this.childrenList = childrenList;
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

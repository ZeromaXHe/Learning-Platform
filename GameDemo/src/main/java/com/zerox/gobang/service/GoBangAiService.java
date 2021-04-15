package com.zerox.gobang.service;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.dao.GoBangBoard;
import com.zerox.gobang.entity.ai.MinMaxTreeNode;
import com.zerox.gobang.utils.BoardUtils;
import com.zerox.gobang.utils.ScoreMapUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * @Author: zhuxi
 * @Time: 2021/3/12 18:15
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangAiService {
    private int thinkProcess;
    private GoBangAiStrategyEnum strategy = GoBangAiStrategyEnum.RANDOM;
    private final static HashMap<Integer, Integer> scoreMap = ScoreMapUtils.initScoreMap();

    private final static int MINMAX_DEPTH = 6;

    public int getThinkProcess() {
        return thinkProcess;
    }

    public int[] nextStep() {
        GoBangBoard goBangBoard = GoBangBoard.getInstance();
        int[][] currentBoard = goBangBoard.getBoardCopy();
        GoBangEnum side = goBangBoard.getNowTurn();
        switch (strategy) {
            case RANDOM:
                return randomNextStep(currentBoard, side);
            case FIRST_EMPTY:
                return firstEmptyNextStep(currentBoard, side);
            case MINMAX:
                return minmaxNextStep(currentBoard, side);
            default:
                throw new IllegalArgumentException("策略配置错误");
        }
    }

    private int[] minmaxNextStep(int[][] currentBoard, GoBangEnum side) {
        thinkProcess = 0;
        MinMaxTreeNode root = new MinMaxTreeNode();
        int[] result = minmax(root, MINMAX_DEPTH, GoBangEnum.BLACK.equals(side), currentBoard, -1, -1);
        return new int[]{result[1], result[2]};
    }

    private int[] minmax(MinMaxTreeNode node, int depth, boolean isBlack, int[][] board, int lastX, int lastY) {
        node.init(board, lastX, lastY);
        // 如果能得到确定的结果或者深度为零，使用评估函数返回局面得分
        if (node.getChildrenCount() == 0 || depth == 0) {
            return new int[]{value(node.getBoard()), node.getLastX(), node.getLastY()};
        }
        LinkedList<int[]> list = new LinkedList<>();
        // 如果轮到黑方走棋，是极小节点，选择一个得分最小的走法
        if (isBlack) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (node.getChildren()[i][j] != null) {
                        int[][] boardNode = BoardUtils.copyBoard(board);
                        boardNode[i][j] = GoBangEnum.BLACK.getVal();
                        int[] minmax = minmax(node.getChildren()[i][j], depth - 1, false, boardNode, i, j);
                        minmax[1] = i;
                        minmax[2] = j;
                        if (minmax[0] < min) {
                            list.clear();
                            list.add(minmax);
                            min = minmax[0];
                        } else if (minmax[0] == min) {
                            list.add(minmax);
                        }
                    }
                }
            }
        }
        // 如果轮到白方走棋，是极大节点，选择一个得分最大的走法
        else {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (node.getChildren()[i][j] != null) {
                        int[][] boardNode = BoardUtils.copyBoard(board);
                        boardNode[i][j] = GoBangEnum.WHITE.getVal();
                        int[] minmax = minmax(node.getChildren()[i][j], depth - 1, true, boardNode, i, j);
                        minmax[1] = i;
                        minmax[2] = j;
                        if (minmax[0] > max) {
                            list.clear();
                            list.add(minmax);
                            max = minmax[0];
                        } else if (minmax[0] == max) {
                            list.add(minmax);
                        }
                    }
                }
            }
        }
        return list.get((int) (Math.random() * list.size()));
    }

    private int[] firstEmptyNextStep(int[][] currentBoard, GoBangEnum side) {
        thinkProcess = 0;
        int[] result = new int[2];
        loop:
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (currentBoard[i][j] == GoBangEnum.EMPTY.getVal()) {
                    result[0] = i;
                    result[1] = j;
                    break loop;
                }
            }
        }
        // 模拟计算时间
        while (thinkProcess < 100) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thinkProcess++;
        }
        return result;
    }

    /**
     * 随机下一步
     *
     * @param currentBoard
     * @return
     */
    private int[] randomNextStep(int[][] currentBoard, GoBangEnum side) {
        thinkProcess = 0;
        Random random = new Random();
        int[] result = new int[2];
        do {
            result[0] = random.nextInt(currentBoard.length);
            result[1] = random.nextInt(currentBoard[0].length);
        } while (currentBoard[result[0]][result[1]] != GoBangEnum.EMPTY.getVal());
        // 模拟计算时间
        while (thinkProcess < 100) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thinkProcess++;
        }
        return result;
    }

    private int valueStep(int[][] board, int x, int y, GoBangEnum side) {
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
    private int value(int[][] board) {
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
    private int valueLine(int pos1, int pos2, int pos3, int pos4, int pos5) {
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
    private int valueRow(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x][y + 1], board[x][y + 2], board[x][y + 3], board[x][y + 4]);
    }

    private int valueRowWithStep(int[][] board, int x, int y, GoBangEnum side, int stepY) {
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
    private int valueColumn(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x + 1][y], board[x + 2][y], board[x + 3][y], board[x + 4][y]);
    }

    private int valueColumnWithStep(int[][] board, int x, int y, GoBangEnum side, int stepX) {
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
    private int valueSlash(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x - 1][y + 1], board[x - 2][y + 2], board[x - 3][y + 3], board[x - 4][y + 4]);
    }

    private int valueSlashWithStep(int[][] board, int x, int y, GoBangEnum side, int stepX) {
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
    private int valueBackSlash(int[][] board, int x, int y) {
        return valueLine(board[x][y], board[x + 1][y + 1], board[x + 2][y + 2], board[x + 3][y + 3], board[x + 4][y + 4]);
    }

    private int valueBackSlashWithStep(int[][] board, int x, int y, GoBangEnum side, int stepX) {
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

    public void setAiStrategy(GoBangAiStrategyEnum strategy) {
        this.strategy = strategy;
    }
}

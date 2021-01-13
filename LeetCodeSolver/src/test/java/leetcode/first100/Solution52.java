package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/1/13 19:07
 * @Description: 52.N皇后II | 难度：困难 | 标签：回溯算法
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution52 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 62.93% 的用户
     * 内存消耗： 34.9 MB , 在所有 Java 提交中击败了 90.44% 的用户
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        int[][] board = new int[n][n];
        return solveNQueensRecursive(board, 0);
    }

    private int solveNQueensRecursive(int[][] board, int i) {
        if (i >= board.length) {
            return 1;
        } else {
            int result = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    placeQueen(board, i, j);
                    result += solveNQueensRecursive(board, i + 1);
                    reverseQueen(board, i, j);
                }
            }
            return result;
        }
    }

    private void placeQueen(int[][] board, int i, int j) {
        board[i][j] = 1;
        int n = board.length;
        for (int k = 0; k < n; k++) {
            if (k != i) {
                if (board[k][j] <= 0) {
                    board[k][j]--;
                } else {
                    board[k][j]++;
                }
                int iToK = i - k;
                if (iToK < 0) {
                    iToK = -iToK;
                }
                int jPlus = j + iToK;
                int jMinus = j - iToK;
                if (jPlus < n) {
                    if (board[k][jPlus] <= 0) {
                        board[k][jPlus]--;
                    } else {
                        board[k][jPlus]++;
                    }
                }
                if (jMinus >= 0) {
                    if (board[k][jMinus] <= 0) {
                        board[k][jMinus]--;
                    } else {
                        board[k][jMinus]++;
                    }
                }
            }
            if (k != j) {
                if (board[i][k] <= 0) {
                    board[i][k]--;
                } else {
                    board[i][k]++;
                }
            }
        }
    }

    private void reverseQueen(int[][] board, int i, int j) {
        if (board[i][j] != 1) {
            throw new IllegalArgumentException();
        }
        board[i][j] = 0;
        int n = board.length;
        for (int k = 0; k < n; k++) {
            if (k != i) {
                if (board[k][j] < 0) {
                    board[k][j]++;
                } else if (board[k][j] > 1) {
                    board[k][j]--;
                } else {
                    throw new IllegalArgumentException();
                }
                int iToK = i - k;
                if (iToK < 0) {
                    iToK = -iToK;
                }
                int jPlus = j + iToK;
                int jMinus = j - iToK;
                if (jPlus < n) {
                    if (board[k][jPlus] < 0) {
                        board[k][jPlus]++;
                    } else if (board[k][jPlus] > 1) {
                        board[k][jPlus]--;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                if (jMinus >= 0) {
                    if (board[k][jMinus] < 0) {
                        board[k][jMinus]++;
                    } else if (board[k][jMinus] > 1) {
                        board[k][jMinus]--;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
            if (k != j) {
                if (board[i][k] < 0) {
                    board[i][k]++;
                } else if (board[k][j] > 1) {
                    board[i][k]--;
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    @Test
    public void solveNQueensTest() {
        Assert.assertEquals(totalNQueens(4), 2);
    }
}

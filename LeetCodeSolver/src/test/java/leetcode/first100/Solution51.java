package leetcode.first100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/13 17:44
 * @Description: 51.N皇后 | 难度：困难 | 标签：回溯算法
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * 提示：
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution51 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 52.17% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 74.88% 的用户
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        int[][] board = new int[n][n];
        List<List<String>> result = new LinkedList<>();
        solveNQueensRecursive(board, 0, result);
        return result;
    }

    private void solveNQueensRecursive(int[][] board, int i, List<List<String>> result) {
        if (i >= board.length) {
            addBoardStringToResult(board, result);
        } else {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    placeQueen(board, i, j);
                    solveNQueensRecursive(board, i + 1, result);
                    reverseQueen(board, i, j);
                }
            }
        }
    }

    private void addBoardStringToResult(int[][] board, List<List<String>> result) {
        int n = board.length;
        List<String> boardStringList = new ArrayList<>(n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.setLength(0);
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            boardStringList.add(sb.toString());
        }
        result.add(boardStringList);
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
        System.out.println(solveNQueens(4));
    }
}

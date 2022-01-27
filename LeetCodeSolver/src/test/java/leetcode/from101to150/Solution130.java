package leetcode.from101to150;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 10:37
 * @Description: 130. 被围绕的区域 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution130 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.84% 的用户
     * 内存消耗： 40.2 MB , 在所有 Java 提交中击败了 85.11% 的用户
     * 通过测试用例： 58 / 58
     *
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1);
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            if (board[m - 1][i] == 'O') {
                dfs(board, m - 1, i);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        board[x][y] = '#';
        if (x > 0 && board[x - 1][y] == 'O') {
            dfs(board, x - 1, y);
        }
        if (x < board.length - 1 && board[x + 1][y] == 'O') {
            dfs(board, x + 1, y);
        }
        if (y > 0 && board[x][y - 1] == 'O') {
            dfs(board, x, y - 1);
        }
        if (y < board[0].length - 1 && board[x][y + 1] == 'O') {
            dfs(board, x, y + 1);
        }
    }
}

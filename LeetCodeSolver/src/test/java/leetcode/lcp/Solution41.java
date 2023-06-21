package leetcode.lcp;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote LCP 41. 黑白翻转棋 | 难度：中等 | 标签：广度优先搜索、数组、矩阵
 * 在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。
 * 当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
 * <p>
 * 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
 * <p>
 * 注意：
 * 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
 * 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
 * <p>
 * 示例 1：
 * 输入：chessboard = ["....X.","....X.","XOOO..","......","......"]
 * 输出：3
 * 解释：
 * 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。
 * <p>
 * 示例 2：
 * 输入：chessboard = [".X.",".O.","XO."]
 * 输出：2
 * 解释：
 * 可以选择下在 [2,2] 处，能够翻转白方两枚棋子。
 * <p>
 * 示例 3：
 * 输入：chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]
 * 输出：4
 * 解释：
 * 可以选择下在 [6,3] 处，能够翻转白方四枚棋子。
 * <p>
 * 提示：
 * 1 <= chessboard.length, chessboard[i].length <= 8
 * chessboard[i] 仅包含 "."、"O" 和 "X"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fHi6rV
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/21 9:52
 */
public class Solution41 {
    @Test
    public void flipChessTest() {
        Assert.assertEquals(3, flipChess(new String[]{"....X.", "....X.", "XOOO..", "......", "......"}));
        Assert.assertEquals(2, flipChess(new String[]{".X.", ".O.", "XO."}));
    }

    int[] dir = new int[]{1, 1, -1, -1, 0, 1, 0, -1};

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了 80.00% 的用户
     * 通过测试用例：154 / 154
     *
     * @param chessboard
     * @return
     */
    public int flipChess(String[] chessboard) {
        int max = 0;
        int n = chessboard.length;
        int m = chessboard[0].length();
        boolean[][] visit = new boolean[n][m];
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = chessboard[i].charAt(j);
            }
        }
        LinkedList<int[]> blackQueue = new LinkedList<>();
        LinkedList<int[]> rollbackStack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 'O') {
                    continue;
                }
                for (int k = 0; k < 8; k++) {
                    int x = i + dir[k];
                    int y = j + dir[(k + 1) % 8];
                    if (x < 0 || y < 0 || x >= n || y >= m
                            || board[x][y] != '.' || visit[x][y]) {
                        continue;
                    }
                    blackQueue.offer(new int[]{x, y});
                    board[x][y] = 'X';
                    max = Math.max(max, countFlip(board, blackQueue, rollbackStack));
                    visit[x][y] = true;
                    board[x][y] = '.';
                }
            }
        }
        return max;
    }

    private int countFlip(char[][] board, LinkedList<int[]> blackQueue, LinkedList<int[]> rollbackStack) {
        int count = 0;
        int n = board.length;
        int m = board[0].length;
        while (!blackQueue.isEmpty()) {
            int[] poll = blackQueue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < 8; i++) {
                int dirX = dir[i];
                int dirY = dir[(i + 1) % 8];
                int nx = x + dirX;
                int ny = y + dirY;
                int countWhite = 0;
                while (nx >= 0 && ny >= 0 && nx < n && ny < m && board[nx][ny] == 'O') {
                    countWhite++;
                    nx += dirX;
                    ny += dirY;
                }
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && board[nx][ny] == 'X') {
                    count += countWhite;
                    nx -= dirX;
                    ny -= dirY;
                    while (nx != x || ny != y) {
                        rollbackStack.push(new int[]{nx, ny});
                        blackQueue.offer(new int[]{nx, ny});
                        board[nx][ny] = 'X';
                        nx -= dirX;
                        ny -= dirY;
                    }
                }
            }
        }
        while (!rollbackStack.isEmpty()) {
            int[] pop = rollbackStack.pop();
            board[pop[0]][pop[1]] = 'O';
        }
        return count;
    }
}

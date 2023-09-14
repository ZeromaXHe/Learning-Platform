package leetcode.from1201to1250;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 1222. 可以攻击国王的皇后 | 难度：中等 | 标签：数组、矩阵、模拟
 * 在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。
 * <p>
 * 给定一个由整数坐标组成的数组 queens ，表示黑皇后的位置；以及一对坐标 king ，表示白国王的位置，返回所有可以攻击国王的皇后的坐标(任意顺序)。
 * <p>
 * 示例 1：
 * 输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * 输出：[[0,1],[1,0],[3,3]]
 * 解释：
 * [0,1] 的皇后可以攻击到国王，因为他们在同一行上。
 * [1,0] 的皇后可以攻击到国王，因为他们在同一列上。
 * [3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。
 * [0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。
 * [4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。
 * [2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
 * <p>
 * 示例 2：
 * 输入：queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * 输出：[[2,2],[3,4],[4,4]]
 * <p>
 * 示例 3：
 * 输入：queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * 输出：[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 * <p>
 * 提示：
 * 1 <= queens.length <= 63
 * queens[i].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * 一个棋盘格上最多只能放置一枚棋子。
 * @implNote
 * @since 2023/9/14 10:07
 */
public class Solution1222 {
    /**
     * 时间 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 39.75 MB
     * 击败 69.23% 使用 Java 的用户
     *
     * @param queens
     * @param king
     * @return
     */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int[] dir = new int[]{1, 0, -1, -1, 1, -1, 0, 1};
        boolean[][] board = new boolean[8][8];
        for (int[] queen : queens) {
            board[queen[0]][queen[1]] = true;
        }
        List<List<Integer>> result = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            int dx = dir[i];
            int dy = dir[(i + 1) % 8];
            int x = king[0] + dx;
            int y = king[1] + dy;
            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (board[x][y]) {
                    ArrayList<Integer> coord = new ArrayList<>(2);
                    coord.add(x);
                    coord.add(y);
                    result.add(coord);
                    break;
                }
                x += dx;
                y += dy;
            }
        }
        return result;
    }
}

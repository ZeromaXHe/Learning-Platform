package leetcode.from2551to2600;

/**
 * @author zhuxi
 * @apiNote 2596. 检查骑士巡视方案 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、数组、矩阵、模拟
 * 骑士在一张 n x n 的棋盘上巡视。在有效的巡视方案中，骑士会从棋盘的 左上角 出发，并且访问棋盘上的每个格子 恰好一次 。
 * <p>
 * 给你一个 n x n 的整数矩阵 grid ，由范围 [0, n * n - 1] 内的不同整数组成，其中 grid[row][col] 表示单元格 (row, col) 是骑士访问的第 grid[row][col] 个单元格。骑士的行动是从下标 0 开始的。
 * <p>
 * 如果 grid 表示了骑士的有效巡视方案，返回 true；否则返回 false。
 * <p>
 * 注意，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。下图展示了骑士从某个格子出发可能的八种行动路线。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
 * 输出：true
 * 解释：grid 如上图所示，可以证明这是一个有效的巡视方案。
 * <p>
 * 示例 2：
 * 输入：grid = [[0,3,6],[5,8,1],[2,7,4]]
 * 输出：false
 * 解释：grid 如上图所示，考虑到骑士第 7 次行动后的位置，第 8 次行动是无效的。
 * <p>
 * 提示：
 * n == grid.length == grid[i].length
 * 3 <= n <= 7
 * 0 <= grid[row][col] < n * n
 * grid 中的所有整数 互不相同
 * @implNote
 * @since 2023/9/13 9:57
 */
public class Solution2596 {
    int[] dir = new int[]{2, 1, 2, -1, -2, 1, -2, -1};

    /**
     * 时间 1 ms
     * 击败 81.40% 使用 Java 的用户
     * 内存 41.00 MB
     * 击败 62.02% 使用 Java 的用户
     *
     * @param grid
     * @return
     */
    public boolean checkValidGrid(int[][] grid) {
        int x = 0;
        int y = 0;
        int n = grid.length;
        if (grid[x][y] != 0) {
            return false;
        }

        for (int i = 1; i < n * n; i++) {
            boolean found = false;
            for (int j = 0; j < 8; j++) {
                int nx = x + dir[j];
                int ny = y + dir[(j + 1) % 8];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (grid[nx][ny] == i) {
                    x = nx;
                    y = ny;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}

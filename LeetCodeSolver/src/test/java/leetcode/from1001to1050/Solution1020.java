package leetcode.from1001to1050;

/**
 * @Author: zhuxi
 * @Time: 2022/2/12 14:34
 * @Description: 1020. 飞地的数量 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * <p>
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * <p>
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * <p>
 * 示例 2：
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-enclaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1020 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 48.51% 的用户
     * 内存消耗： 49 MB , 在所有 Java 提交中击败了 12.28% 的用户
     * 通过测试用例： 57 / 57
     *
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 1) {
                dfs(grid, i, 0);
            }
            if (grid[i][grid[0].length - 1] == 1) {
                dfs(grid, i, grid[0].length - 1);
            }
        }

        for (int i = 1; i < grid[0].length - 1; i++) {
            if (grid[0][i] == 1) {
                dfs(grid, 0, i);
            }
            if (grid[grid.length - 1][i] == 1) {
                dfs(grid, grid.length - 1, i);
            }
        }

        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (grid[i][j] == 1) {
                    count += dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private int dfs(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int result = 1;
        if (i > 0 && grid[i - 1][j] == 1) {
            result += dfs(grid, i - 1, j);
        }
        if (j > 0 && grid[i][j - 1] == 1) {
            result += dfs(grid, i, j - 1);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == 1) {
            result += dfs(grid, i + 1, j);
        }
        if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
            result += dfs(grid, i, j + 1);
        }
        return result;
    }
}

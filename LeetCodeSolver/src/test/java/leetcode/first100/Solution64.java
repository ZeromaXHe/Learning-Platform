package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/12 16:20
 * @Description: 64. 最小路径和 | 难度：中等 | 标签：数组、动态规划、矩阵
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution64 {
    @Test
    public void minPathSumTest() {
        Assert.assertEquals(7, minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        Assert.assertEquals(12, minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 96.48% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 13.59% 的用户
     * 通过测试用例： 61 / 61
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                arr[i] = arr[i - 1] + grid[0][i];
            } else {
                arr[i] = grid[0][0];
            }
        }
        for (int i = 1; i < m; i++) {
            arr[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                arr[j] = Math.min(arr[j], arr[j - 1]) + grid[i][j];
            }
        }
        return arr[n - 1];
    }
}

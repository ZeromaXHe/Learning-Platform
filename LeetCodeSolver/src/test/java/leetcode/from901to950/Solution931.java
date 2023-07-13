package leetcode.from901to950;

/**
 * @author zhuxi
 * @apiNote 931. 下降路径最小和 | 难度：中等 | 标签：数组、动态规划、矩阵
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * <p>
 * 示例 1：
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * <p>
 * 示例 2：
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 * <p>
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-falling-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/13 10:15
 */
public class Solution931 {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 17.89% 的用户
     * 内存消耗：43.4 MB, 在所有 Java 提交中击败了 14.73% 的用户
     * 通过测试用例：50 / 50
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j];
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    }
                    if (j < m - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                    }
                }
                dp[i][j] += matrix[i][j];
            }
        }
        int result = dp[n - 1][0];
        for (int i = 1; i < m; i++) {
            result = Math.min(dp[n - 1][i], result);
        }
        return result;
    }
}

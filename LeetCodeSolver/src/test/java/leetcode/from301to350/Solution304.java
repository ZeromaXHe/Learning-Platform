package leetcode.from301to350;

/**
 * @Author: zhuxi
 * @Time: 2021/3/2 11:56
 * @Description: 304.二维区域和检索 - 矩阵不可变 | 难度：中等 | 标签：动态规划
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * <p>
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * <p>
 * 示例：
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * <p>
 * 提示：
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution304 {
    /**
     * 执行用时： 16 ms , 在所有 Java 提交中击败了 39.83% 的用户
     * 内存消耗： 43.9 MB , 在所有 Java 提交中击败了 83.81% 的用户
     * <p>
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
    class NumMatrix {
        private int[][] sum;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                sum = null;
                return;
            }
            sum = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i == 0 && j == 0) {
                        sum[i + 1][j + 1] = matrix[i][j];
                    } else if (i == 0) {
                        sum[i + 1][j + 1] = sum[i + 1][j] + matrix[i][j];
                    } else if (j == 0) {
                        sum[i + 1][j + 1] = sum[i][j + 1] + matrix[i][j];
                    } else {
                        // 其实保留这一个就可以，其他判断多余
                        // 删除其他分支后：
                        // 执行用时： 14 ms , 在所有 Java 提交中击败了 98.81% 的用户
                        // 内存消耗： 44.1 MB , 在所有 Java 提交中击败了 46.31% 的用户
                        sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (sum == null) {
                return 0;
            }
            return sum[row2 + 1][col2 + 1] + sum[row1][col1] - sum[row2 + 1][col1] - sum[row1][col2 + 1];
        }
    }
}

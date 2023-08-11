package leetcode.from1551to1600;

/**
 * @author zhuxi
 * @apiNote 1572. 矩阵对角线元素的和 | 难度：简单 | 标签：数组、矩阵
 * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
 * <p>
 * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
 * <p>
 * 示例  1：
 * | 输入：mat = [[1,2,3],
 * |             [4,5,6],
 * |             [7,8,9]]
 * 输出：25
 * 解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
 * 请注意，元素 mat[1][1] = 5 只会被计算一次。
 * <p>
 * 示例  2：
 * | 输入：mat = [[1,1,1,1],
 * |             [1,1,1,1],
 * |             [1,1,1,1],
 * |             [1,1,1,1]]
 * 输出：8
 * <p>
 * 示例 3：
 * 输入：mat = [[5]]
 * 输出：5
 * <p>
 * 提示：
 * n == mat.length == mat[i].length
 * 1 <= n <= 100
 * 1 <= mat[i][j] <= 100
 * @implNote
 * @since 2023/8/11 9:50
 */
public class Solution1572 {
    /**
     * 时间 - ms
     * 击败 100.00%使用 Java 的用户
     * <p>
     * 内存 41.61 mb
     * 击败 55.09% 使用 Java 的用户
     *
     * @param mat
     * @return
     */
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i];
            if (i != n - 1 - i) {
                sum += mat[i][n - 1 - i];
            }
        }
        return sum;
    }
}

package leetcode.from1351to1400;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/2/15 10:04
 * @Description: 1380. 矩阵中的幸运数 | 难度：简单 | 标签：数组、矩阵
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * <p>
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * <p>
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 * <p>
 * 示例 1：
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * <p>
 * 示例 3：
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1380 {
    @Test
    public void luckyNumbersTest() {
        System.out.println(luckyNumbers(new int[][]{{3, 7, 8}, {9, 11, 13}, {15, 16, 17}}));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 84.14% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 14.14% 的用户
     * 通过测试用例： 104 / 104
     * <p>
     * 看题解，可以数学证明最多只有一个幸运数
     *
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        int[][] min = new int[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            min[i][0] = Integer.MAX_VALUE;
        }
        int[] max = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < min[i][0]) {
                    min[i][0] = matrix[i][j];
                    min[i][1] = j;
                }
                if (matrix[i][j] > max[j]) {
                    max[j] = matrix[i][j];
                }
            }
        }
        for (int[] ints : min) {
            if (max[ints[1]] == ints[0] && ints[0] != 0) {
                result.add(ints[0]);
            }
        }
        return result;
    }
}

package leetcode.from2451to2500;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote 2500. 删除每行中的最大值 | 难度：简单 | 标签：数组、矩阵、排序
 * 给你一个 m x n 大小的矩阵 grid ，由若干正整数组成。
 * <p>
 * 执行下述操作，直到 grid 变为空矩阵：
 * <p>
 * 从每一行删除值最大的元素。如果存在多个这样的值，删除其中任何一个。
 * 将删除元素中的最大值与答案相加。
 * 注意 每执行一次操作，矩阵中列的数据就会减 1 。
 * <p>
 * 返回执行上述操作后的答案。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,2,4],[3,3,1]]
 * 输出：8
 * 解释：上图展示在每一步中需要移除的值。
 * - 在第一步操作中，从第一行删除 4 ，从第二行删除 3（注意，有两个单元格中的值为 3 ，我们可以删除任一）。在答案上加 4 。
 * - 在第二步操作中，从第一行删除 2 ，从第二行删除 3 。在答案上加 3 。
 * - 在第三步操作中，从第一行删除 1 ，从第二行删除 1 。在答案上加 1 。
 * 最终，答案 = 4 + 3 + 1 = 8 。
 * <p>
 * 示例 2：
 * 输入：grid = [[10]]
 * 输出：10
 * 解释：上图展示在每一步中需要移除的值。
 * - 在第一步操作中，从第一行删除 10 。在答案上加 10 。
 * 最终，答案 = 10 。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/delete-greatest-value-in-each-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/27 10:04
 */
public class Solution2500 {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 77.63% 的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了 25.76% 的用户
     * 通过测试用例：55 / 55
     *
     * @param grid
     * @return
     */
    public int deleteGreatestValue(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            Arrays.sort(grid[i]);
        }
        int sum = 0;
        for (int i = 0; i < grid[0].length; i++) {
            int max = grid[0][i];
            for (int j = 1; j < grid.length; j++) {
                max = Math.max(max, grid[j][i]);
            }
            sum += max;
        }
        return sum;
    }
}

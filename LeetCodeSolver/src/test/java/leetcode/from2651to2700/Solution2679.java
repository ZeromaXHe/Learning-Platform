package leetcode.from2651to2700;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote 2679. 矩阵中的和 | 难度：中等 | 标签：数组、矩阵、排序、模拟、堆（优先队列）
 * 给你一个下标从 0 开始的二维整数数组 nums 。一开始你的分数为 0 。你需要执行以下操作直到矩阵变为空：
 * <p>
 * 矩阵中每一行选取最大的一个数，并删除它。如果一行中有多个最大的数，选择任意一个并删除。
 * 在步骤 1 删除的所有数字中找到最大的一个数字，将它添加到你的 分数 中。
 * 请你返回最后的 分数 。
 * <p>
 * 示例 1：
 * 输入：nums = [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
 * 输出：15
 * 解释：第一步操作中，我们删除 7 ，6 ，6 和 3 ，将分数增加 7 。下一步操作中，删除 2 ，4 ，5 和 2 ，将分数增加 5 。最后删除 1 ，2 ，3 和 1 ，将分数增加 3 。所以总得分为 7 + 5 + 3 = 15 。
 * <p>
 * 示例 2：
 * 输入：nums = [[1]]
 * 输出：1
 * 解释：我们删除 1 并将分数增加 1 ，所以返回 1 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 300
 * 1 <= nums[i].length <= 500
 * 0 <= nums[i][j] <= 103
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/4 9:59
 */
public class Solution2679 {
    /**
     * 执行用时：14 ms, 在所有 Java 提交中击败了 73.83% 的用户
     * 内存消耗：55.5 MB, 在所有 Java 提交中击败了 24.64% 的用户
     * 通过测试用例：1057 / 1057
     *
     * @param nums
     * @return
     */
    public int matrixSum(int[][] nums) {
        int n = nums.length;
        for (int[] num : nums) {
            Arrays.sort(num);
        }
        int m = nums[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            int max = nums[0][i];
            for (int j = 1; j < n; j++) {
                max = Math.max(max, nums[j][i]);
            }
            result += max;
        }
        return result;
    }
}

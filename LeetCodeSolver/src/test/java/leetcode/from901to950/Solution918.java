package leetcode.from901to950;

/**
 * @author zhuxi
 * @apiNote 918. 环形子数组的最大和 | 难度：中等 | 标签：队列、数组、分治、动态规划、单调队列
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * <p>
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * <p>
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * <p>
 * 示例 2：
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * <p>
 * 示例 3：
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104​​​​​​​
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-sum-circular-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/20 10:07
 */
public class Solution918 {
    /**
     * 如果用最大等于总和减最小的思路会清晰很多。这个代码写的有点屎
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了 69.51% 的用户
     * 内存消耗：46.7 MB, 在所有 Java 提交中击败了 35.88% 的用户
     * 通过测试用例：111 / 111
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0;
        int max = nums[0];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (sum + nums[i] < 0) {
                sum = 0;
            } else {
                sum += nums[i];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        int[] preSumMax = new int[n + 1];
        preSumMax[0] = Integer.MIN_VALUE / 2;
        int preSum = 0;
        int[] postSumMax = new int[n + 1];
        postSumMax[n] = Integer.MIN_VALUE / 2;
        int postSum = 0;
        for (int i = 0; i < n; i++) {
            preSum += nums[i];
            preSumMax[i + 1] = Math.max(preSumMax[i], preSum == 0 ? Integer.MIN_VALUE / 2 : preSum);
            postSum += nums[n - 1 - i];
            postSumMax[n - 1 - i] = Math.max(postSumMax[n - i], postSum == 0 ? Integer.MIN_VALUE / 2 : postSum);
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(preSumMax[i] + postSumMax[i], max);
        }
        return max;
    }
}

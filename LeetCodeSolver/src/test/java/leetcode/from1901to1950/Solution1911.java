package leetcode.from1901to1950;

/**
 * @author zhuxi
 * @apiNote 1911. 最大子序列交替和 | 难度：中等 | 标签：数组、动态规划
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 * <p>
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 * <p>
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。
 * 比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 * <p>
 * 示例 1：
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 * <p>
 * 示例 2：
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 * <p>
 * 示例 3：
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-alternating-subsequence-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/11 9:44
 */
public class Solution1911 {
    /**
     * 题解评论区里面提到 122. 买卖股票的最佳时机 II，直接把正的前后差加起来就行了，会很快。
     * 提交记录里面 3 ms 的就是这个思路。
     * <p>
     * 执行用时：12 ms, 在所有 Java 提交中击败了 43.40% 的用户
     * 内存消耗：56.8 MB, 在所有 Java 提交中击败了 47.17% 的用户
     * 通过测试用例：65 / 65
     *
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        long[][] dp = new long[2][2];
        // 结尾是加时的最大值
        dp[0][0] = nums[0];
        // 结尾是减时的最大值
        dp[0][1] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i % 2][0] = Math.max(dp[(i + 1) % 2][0], dp[(i + 1) % 2][1] + nums[i]);
            dp[i % 2][1] = Math.max(dp[(i + 1) % 2][1], dp[(i + 1) % 2][0] - nums[i]);
        }
        return Math.max(dp[(nums.length - 1) % 2][0], dp[(nums.length - 1) % 2][1]);
    }
}
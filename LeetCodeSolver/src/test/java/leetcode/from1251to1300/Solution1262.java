package leetcode.from1251to1300;

/**
 * @author zhuxi
 * @apiNote 1262. 可被三整除的最大和 | 难度：中等 | 标签：贪心、动态规划、数组、排序
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 * <p>
 * 示例 1：
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * <p>
 * 示例 2：
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 * <p>
 * 提示：
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/greatest-sum-divisible-by-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/19 10:06
 */
public class Solution1262 {
    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了 64.34% 的用户
     * 内存消耗：43.8 MB, 在所有 Java 提交中击败了 78.28% 的用户
     * 通过测试用例：42 / 42
     *
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int[][] dp = new int[2][3];
        for (int i = 0; i < nums.length; i++) {
            System.arraycopy(dp[i % 2], 0, dp[(i + 1) % 2], 0, 3);
            for (int j = 0; j < 3; j++) {
                int sum = dp[i % 2][j] + nums[i];
                if (sum > dp[(i + 1) % 2][sum % 3]) {
                    dp[(i + 1) % 2][sum % 3] = sum;
                }
            }
        }
        return dp[nums.length % 2][0];
    }
}

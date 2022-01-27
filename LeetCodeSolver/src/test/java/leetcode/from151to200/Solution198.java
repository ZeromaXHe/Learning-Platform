package leetcode.from151to200;

/**
 * @Author: zhuxi
 * @Time: 2022/1/26 11:13
 * @Description: 198. 打家劫舍 | 难度：中等 | 标签：数组、动态数组
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution198 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 5.50% 的用户
     * 通过测试用例： 68 / 68
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        } else if (nums.length == 3) {
            return Math.max(nums[0] + nums[2], nums[1]);
        }
        int[] dp = new int[4];
        dp[1] = nums[0];
        dp[2] = nums[1];
        dp[3] = nums[0] + nums[2];
        for (int i = 3; i < nums.length; i++) {
            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = dp[3];
            dp[3] = Math.max(Math.max(dp[1], dp[0]) + nums[i], dp[2]);
        }
        return dp[3];
    }
}

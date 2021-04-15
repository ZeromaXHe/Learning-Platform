package leetcode.from201to300;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/4/15 11:08
 * @Description: 213.打家劫舍II | 难度：中等 | 标签：动态规划
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution213 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 21.31% 的用户
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[1], nums[0]);
        }
        int[] dpNoRobFirst = new int[n];
        int[] dpRobFirst = new int[n];
        dpNoRobFirst[0] = 0;
        dpNoRobFirst[1] = nums[1];
        dpRobFirst[0] = nums[0];
        dpRobFirst[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < n; i++) {
            dpRobFirst[i] = Math.max(dpRobFirst[i - 2] + nums[i], dpRobFirst[i - 1]);
            dpNoRobFirst[i] = Math.max(dpNoRobFirst[i - 2] + nums[i], dpNoRobFirst[i - 1]);
        }
        return Math.max(dpNoRobFirst[n - 1], dpRobFirst[n - 2]);
    }

    /**
     * 把问题想复杂了，而且初始的dp[1]应该拿nums[0]和nums[1]的最大值。
     *
     * @param nums
     * @return
     */
    public int rob_falseAnswer(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[1], nums[0]);
        }
        int[] dpNoRobFirst = new int[n];
        boolean[] robbedNo = new boolean[n];
        int[] dpRobFirst = new int[n];
        boolean[] robbed = new boolean[n];
        dpNoRobFirst[0] = 0;
        dpNoRobFirst[1] = nums[1];
        robbedNo[1] = true;
        dpRobFirst[0] = nums[0];
        dpRobFirst[1] = nums[1];
        robbed[0] = true;
        robbed[1] = true;
        for (int i = 2; i < n; i++) {
            int comp1 = dpRobFirst[i - 2] + nums[i];
            int comp2 = dpRobFirst[i - 1] + (robbed[i - 1] ? 0 : nums[i]);
            dpRobFirst[i] = Math.max(comp1, comp2);
            if (comp2 < comp1 || !robbed[i - 1]) {
                robbed[i] = true;
            }
            int compNo1 = dpNoRobFirst[i - 2] + nums[i];
            int compNo2 = dpNoRobFirst[i - 1] + (robbedNo[i - 1] ? 0 : nums[i]);
            dpNoRobFirst[i] = Math.max(compNo1, compNo2);
            if (compNo2 < compNo1 || !robbedNo[i - 1]) {
                robbedNo[i] = true;
            }
        }
        return Math.max(dpNoRobFirst[n - 1], dpRobFirst[n - 2]);
    }

    @Test
    public void robTest() {
        // [4,1,2,7,5,3,1] 预期 14 输出 11
        Assert.assertEquals(14, rob(new int[]{4, 1, 2, 7, 5, 3, 1}));
    }
}

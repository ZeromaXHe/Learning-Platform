package leetcode.from151to200;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 14:10
 * @Description: 152. 乘积最大子数组 | 难度：中等 | 标签：数组、动态规划
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution152 {
    @Test
    public void maxProductTest() {
        // 通过测试用例： 165 / 188
        Assert.assertEquals(0, maxProduct(new int[]{-2, 0}));
        // 通过测试用例： 166 / 188
        Assert.assertEquals(24, maxProduct(new int[]{2, -5, -2, -4, 3}));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 79.21% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 28.49% 的用户
     * 通过测试用例： 188 / 188
     * <p>
     * 参考官方题解
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}

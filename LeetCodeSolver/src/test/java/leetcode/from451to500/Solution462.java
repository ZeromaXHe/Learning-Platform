package leetcode.from451to500;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/5/19 10:02
 * @Description: 462. 最少移动次数使数组元素相等 II | 难度：中等 | 标签：数组、数学、排序
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * <p>
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * <p>
 * 示例 2：
 * 输入：nums = [1,10,2,9]
 * 输出：16
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution462 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 74.85% 的用户
     * 内存消耗： 42.4 MB , 在所有 Java 提交中击败了 5.00% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 得用中位数而不是平均数，这个没看题解前弄错了。
     * 而且一开始以为要分 nums 长度为奇数还是偶数，但最后发现偶数情况下，两个最中间的数为中心，计算出的结果也是一样的
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int count1 = 0;
        for (int num : nums) {
            count1 += Math.abs(num - nums[nums.length / 2]);
        }
        return count1;
    }
}

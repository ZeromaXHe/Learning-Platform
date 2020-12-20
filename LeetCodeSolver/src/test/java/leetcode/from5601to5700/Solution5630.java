package leetcode.from5601to5700;

import java.util.HashSet;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/20 10:46
 * @Description: 5630. 删除子数组的最大得分 | 难度：中等
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
 *
 * 返回 只删除一个 子数组可获得的 最大得分 。
 *
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 *
 * 示例 1：
 * 输入：nums = [4,2,4,5,6]
 * 输出：17
 * 解释：最优子数组是 [2,4,5,6]
 *
 * 示例 2：
 * 输入：nums = [5,2,1,2,5,2,1,2,5]
 * 输出：8
 * 解释：最优子数组是 [5,2,1] 或 [1,2,5]
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * @Modified By: ZeromaXHe
 */
public class Solution5630 {
    /**
     * 62 / 62 个通过测试用例
     * 状态：通过
     * 执行用时: 39 ms
     * 内存消耗: 54.6 MB
     *
     * @param nums
     * @return
     */
    public int maximumUniqueSubarray(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int left = 0;
        int sum = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                while (nums[left] != nums[i]) {
                    set.remove(nums[left]);
                    sum -= nums[left++];
                }
                left++;
            } else {
                sum += nums[i];
                set.add(nums[i]);
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    // [187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169,743,78,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434]
    // 预期 16911
}

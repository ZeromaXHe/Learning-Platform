package leetcode.from5701to5750;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/21 10:30
 * @Description: 5709.最大升序子数组和 | 难度：简单 | 标签：双指针
 * 给你一个正整数组成的数组 nums ，返回 nums 中一个 升序 子数组的最大可能元素和。
 * <p>
 * 子数组是数组中的一个连续数字序列。
 * <p>
 * 已知子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，若对所有 i（l <= i < r），numsi < numsi+1 都成立，则称这一子数组为 升序 子数组。注意，大小为 1 的子数组也视作 升序 子数组。
 * <p>
 * 示例 1：
 * 输入：nums = [10,20,30,5,10,50]
 * 输出：65
 * 解释：[5,10,50] 是元素和最大的升序子数组，最大元素和为 65 。
 * <p>
 * 示例 2：
 * 输入：nums = [10,20,30,40,50]
 * 输出：150
 * 解释：[10,20,30,40,50] 是元素和最大的升序子数组，最大元素和为 150 。
 * <p>
 * 示例 3：
 * 输入：nums = [12,17,15,13,10,11,12]
 * 输出：33
 * 解释：[10,11,12] 是元素和最大的升序子数组，最大元素和为 33 。
 * <p>
 * 示例 4：
 * 输入：nums = [100,10,1]
 * 输出：100
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-ascending-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5709 {
    /**
     * 104 / 104 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 36.3 MB
     *
     * @param nums
     * @return
     */
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int temp = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                temp += nums[i];
            } else {
                if (max < temp) {
                    max = temp;
                }
                temp = nums[i];
            }
        }
        return Math.max(max, temp);
    }

    public int maxAscendingSum_wrong(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + nums[i]) {
                    dp[i] = dp[j] + nums[i];
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}

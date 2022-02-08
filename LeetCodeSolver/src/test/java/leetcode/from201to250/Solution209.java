package leetcode.from201to250;

/**
 * @Author: zhuxi
 * @Time: 2022/2/8 10:27
 * @Description: 209. 长度最小的子数组 | 难度：中等 | 标签：数组、二分查找、前缀和、滑动窗口
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution209 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.99% 的用户
     * 内存消耗： 41 MB , 在所有 Java 提交中击败了 8.60% 的用户
     * 通过测试用例： 19 / 19
     * <p>
     * 题目里面 O(n Log n) 的算法是前缀和+二分查找。
     * 前缀和倒是想到了可以做，不过没想到可以用二分查找优化，以为是O(n ^ 2); 直接想到滑动窗口就没继续想了。
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r++];
            while (l < r && sum - nums[l] >= target) {
                sum -= nums[l++];
            }
            if (target <= sum && r - l < min) {
                min = r - l;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

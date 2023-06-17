package leetcode.jianzhi_offer_II;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 008. 和大于等于 target 的最短子数组 | 难度：中等 | 标签：
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
 * 注意：本题与主站 209 题相同：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/2VG8Kg
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/13 19:23
 */
public class SolutionOfferII8 {
    @Test
    public void testMinSubArrayLen() {
        Assert.assertEquals(2, minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(3, minSubArrayLen(11, new int[]{1, 2, 3, 4, 5}));
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了 34.64% 的用户
     * 通过测试用例：19 / 19
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int l = 0;
        int r = 1;
        int sum = nums[0];
        while (r < nums.length) {
            while (sum < target && r < nums.length) {
                sum += nums[r++];
            }
            if (sum >= target) {
                min = Math.min(min, r - l);
            }
            sum -= nums[l++];
            if (r == l) {
                sum += nums[r++];
            }
        }
        while (l < r && sum >= target) {
            min = Math.min(min, r - l);
            sum -= nums[l++];
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 009. 乘积小于 K 的子数组 | 难度：中等 | 标签：数组、滑动窗口
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 * <p>
 * 示例 1:
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * <p>
 * 示例 2:
 * 输入: nums = [1,2,3], k = 0
 * 输出: 0
 * <p>
 * 提示: 
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 * <p>
 * 注意：本题与主站 713 题相同：https://leetcode-cn.com/problems/subarray-product-less-than-k/ 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ZVAVXX
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/14 16:05
 */
public class SolutionOfferII9 {
    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了 32.87% 的用户
     * 内存消耗：44.9 MB, 在所有 Java 提交中击败了 96.47% 的用户
     * 通过测试用例：95 / 95
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int prod = 1;
        int count = 0;
        int l = 0;
        int r = 0;
        while (r < nums.length) {
            while (r < nums.length && prod * nums[r] < k) {
                prod *= nums[r++];
            }
            count += r - l;
            prod /= nums[l++];
            if (l > r) {
                r++;
            }
        }
        while (l < r) {
            count += r - l;
            l++;
        }
        return count;
    }
}

package leetcode.from701to750;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 9:44
 * @Description: 713. 乘积小于 K 的子数组 | 难度：中等 | 标签：数组、滑动窗口
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 * <p>
 * 提示: 
 * 1 <= nums.length <= 3 * 10^4
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution713 {
    @Test
    public void numSubarrayProductLessThanKTest() {
        Assert.assertEquals(8, numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        Assert.assertEquals(0, numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.95% 的用户
     * 内存消耗： 47.7 MB , 在所有 Java 提交中击败了 70.35% 的用户
     * 通过测试用例： 97 / 97
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1;
        int count = 0;
        int from = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                product = 1;
                from = i + 1;
                continue;
            }
            product *= nums[i];
            while (product >= k && from <= i) {
                product /= nums[from];
                from++;
            }
            count += i - from + 1;
        }
        return count;
    }
}

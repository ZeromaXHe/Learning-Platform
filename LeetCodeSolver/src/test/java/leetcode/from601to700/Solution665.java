package leetcode.from601to700;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/2/7 9:51
 * @Description: 665.非递减数列 | 难度：简单 | 标签：数组
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * <p>
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * <p>
 * 说明：
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution665 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.54% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 36.50% 的用户
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        boolean changed = false;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                if (nums[i] < nums[i - 1]) {
                    if (changed) {
                        return false;
                    }
                    nums[i - 1] = nums[i];
                    changed = true;
                }
            }
            if (i < nums.length - 1) {
                if (nums[i] > nums[i + 1]) {
                    if (changed) {
                        return false;
                    }
                    if (i > 0 && nums[i + 1] < nums[i - 1]) {
                        nums[i + 1] = nums[i];
                    } else {
                        nums[i] = nums[i + 1];
                    }
                    changed = true;
                }
            }
        }
        return true;
    }

    @Test
    public void checkPossibilityTest() {
        Assert.assertTrue(checkPossibility(new int[]{4, 2, 3}));
        Assert.assertFalse(checkPossibility(new int[]{4, 2, 1}));
        Assert.assertFalse(checkPossibility(new int[]{3, 4, 2, 3}));
        Assert.assertTrue(checkPossibility(new int[]{-1, 4, 2, 3}));
        Assert.assertTrue(checkPossibility(new int[]{1, 4, 1, 2}));
    }
}

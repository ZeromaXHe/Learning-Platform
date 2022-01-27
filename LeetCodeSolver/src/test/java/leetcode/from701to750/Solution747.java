package leetcode.from701to750;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/13 9:58
 * @Description: 747. 至少是其他数字两倍的最大数 | 难度：简单 | 标签：数组、排序
 * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
 * <p>
 * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [3,6,1,0]
 * 输出：1
 * 解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：-1
 * 解释：4 没有超过 3 的两倍大，所以返回 -1 。
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 * 解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
 * <p>
 * 提示：
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 100
 * nums 中的最大元素是唯一的
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution747 {
    @Test
    public void dominantIndexTest() {
        Assert.assertEquals(1, dominantIndex(new int[]{3, 6, 1, 0}));
        Assert.assertEquals(-1, dominantIndex(new int[]{1, 2, 3, 4}));
        Assert.assertEquals(0, dominantIndex(new int[]{1}));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 57.34% 的用户
     * 通过测试用例： 232 / 232
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int max = -1;
        int maxIndex = -1;
        int second = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                second = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        return max >= 2 * second ? maxIndex : -1;
    }
}

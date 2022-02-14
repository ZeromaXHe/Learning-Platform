package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/2/14 10:00
 * @Description: 540. 有序数组中的单一元素 | 难度：中等 | 标签：数组、二分查找
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution540 {
    @Test
    public void singleNonDuplicateTest() {
        Assert.assertEquals(2, singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        Assert.assertEquals(10, singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 47.1 MB , 在所有 Java 提交中击败了 8.84% 的用户
     * 通过测试用例： 15 / 15
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (mid > 0 && nums[mid - 1] == nums[mid]) {
                if ((r - l) / 2 % 2 == 1) {
                    l = mid + 1;
                } else {
                    r = mid - 2;
                }
            } else if (mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                if ((r - l) / 2 % 2 == 1) {
                    r = mid - 1;
                } else {
                    l = mid + 2;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[l];
    }
}

package leetcode.from1to50;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/11 10:00
 * @Description: 35. 搜索插入位置 | 难度：简单 | 标签：数组、二分查找
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * 示例 4:
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * <p>
 * 示例 5:
 * 输入: nums = [1], target = 0
 * 输出: 0
 * <p>
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution35 {
    @Test
    public void searchInsertTest() {
        Assert.assertEquals(2, searchInsert(new int[]{1, 3, 5, 6}, 5));
        Assert.assertEquals(1, searchInsert(new int[]{1, 3, 5, 6}, 2));
        Assert.assertEquals(4, searchInsert(new int[]{1, 3, 5, 6}, 7));
        Assert.assertEquals(0, searchInsert(new int[]{1, 3, 5, 6}, 0));
        Assert.assertEquals(0, searchInsert(new int[]{1}, 0));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 61.66% 的用户
     * 通过测试用例： 64 / 64
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}

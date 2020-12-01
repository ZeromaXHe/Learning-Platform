package leetcode.first100;

import java.util.Arrays;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/1 11:41
 * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置 | 难度：中等 | 标签： 数组、二分查找
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution34 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了 87.91% 的用户
     * 实际写代码需要注意findFirst和findLast的收敛条件，不然就循环了。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            return new int[]{-1, -1};
        }
        return new int[]{findFirst(nums, target, 0, index + 1), findLast(nums, target, index, nums.length)};
    }

    private int findFirst(int[] nums, int target, int from, int to) {
        if (from == to - 1) {
            return from;
        }
        if (nums[(from + to - 1) / 2] == target) {
            return findFirst(nums, target, from, (from + to - 1) / 2 + 1);
        } else {
            return findFirst(nums, target, (from + to - 1) / 2 + 1, to);
        }
    }

    private int findLast(int[] nums, int target, int from, int to) {
        if (from == to - 1) {
            return from;
        }
        if (nums[(from + to) / 2] == target) {
            return findLast(nums, target, (from + to) / 2, to);
        } else {
            return findLast(nums, target, from, (from + to) / 2);
        }
    }

    public static void main(String[] args) {
        Solution34 solution34 = new Solution34();
        // [1,1]
        System.out.println(Arrays.toString(solution34.searchRange(new int[]{1, 4}, 4)));
        // [0,0]
        System.out.println(Arrays.toString(solution34.searchRange(new int[]{4}, 4)));
        // [3,4]
        System.out.println(Arrays.toString(solution34.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}

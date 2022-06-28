package leetcode.from301to350;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote 324. 摆动排序 II | 难度：中等 | 标签：数组、分治、快速选择、排序
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * <p>
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * <p>
 * 示例 2：
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * <p>
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/wiggle-sort-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/28 10:22
 */
public class Solution324 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：44.8 MB, 在所有 Java 提交中击败了 69.53% 的用户
     * 通过测试用例： 52 / 52
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] count = new int[5001];
        for (int num : nums) {
            count[num]++;
        }
        int ptr = 5000;
        for (int i = 1; i < n; i += 2) {
            while (count[ptr] == 0) {
                ptr--;
            }
            nums[i] = ptr;
            count[ptr]--;
        }
        for (int i = 0; i < n; i += 2) {
            while (count[ptr] == 0) {
                ptr--;
            }
            nums[i] = ptr;
            count[ptr]--;
        }
    }

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了 56.63% 的用户
     * 内存消耗：45.4 MB, 在所有 Java 提交中击败了 15.49% 的用户
     * 通过测试用例：52 / 52
     *
     * @param nums
     */
    public void wiggleSort_sort(int[] nums) {
        int[] sort = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sort);
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = sort[(nums.length - 1) / 2 - i / 2];
            } else {
                nums[i] = sort[nums.length - 1 - i / 2];
            }
        }
    }
}

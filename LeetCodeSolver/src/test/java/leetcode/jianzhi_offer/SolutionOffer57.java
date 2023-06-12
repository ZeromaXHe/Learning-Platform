package leetcode.jianzhi_offer;

import java.util.HashSet;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 57. 和为s的两个数字 | 难度：简单 | 标签：数组、双指针、二分查找
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * 示例 2：
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 * <p>
 * 限制：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 9:58
 */
public class SolutionOffer57 {
    /**
     * 双指针，参考别人题解做的
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.46% 的用户
     * 内存消耗：61.6 MB, 在所有 Java 提交中击败了 16.02% 的用户
     * 通过测试用例：36 / 36
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                return new int[]{nums[l], nums[r]};
            }
        }
        return new int[0];
    }

    /**
     * 执行用时：41 ms, 在所有 Java 提交中击败了 17.24% 的用户
     * 内存消耗：60.9 MB, 在所有 Java 提交中击败了 42.64% 的用户
     * 通过测试用例：36 / 36
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum_hashSet(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(target - num)) {
                return new int[]{target - num, num};
            }
            set.add(num);
        }
        return new int[0];
    }
}

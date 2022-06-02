package leetcode.from401to450;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/6/2 11:38
 * @Description: 448. 找到所有数组中消失的数字 | 难度：简单 | 标签：数组、哈希表
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * <p>
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution448 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 99.72% 的用户
     * 内存消耗： 49.1 MB , 在所有 Java 提交中击败了 77.56% 的用户
     * 通过测试用例： 33 / 33
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int realValue = (num - 1) % n;
            nums[realValue] += n;
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 51.46% 的用户
     * 内存消耗： 49.5 MB , 在所有 Java 提交中击败了 26.67% 的用户
     * 通过测试用例： 33 / 33
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers_negative(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 39.86% 的用户
     * 内存消耗： 49.2 MB , 在所有 Java 提交中击败了 60.50% 的用户
     * 通过测试用例： 33 / 33
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers_swap(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }
}

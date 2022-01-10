package leetcode.first100;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 16:20
 * @Description: 31. 下一个排列 | 难度：中等 | 标签：数组、双指针
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution31 {
    @Test
    public void nextPermutationTest() {
        int[] ints = {2, 3, 1};
        nextPermutation(ints);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 43.33% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 20.20% 的用户
     * 通过测试用例： 265 / 265
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        boolean allReverse = true;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] >= nums[i]) {
                continue;
            }
            for (int j = nums.length - 1; j >= i; j--) {
                if (nums[j] <= nums[i - 1]) {
                    continue;
                }
                int temp = nums[i - 1];
                nums[i - 1] = nums[j];
                nums[j] = temp;
                Arrays.sort(nums, i, nums.length);
                break;
            }
            allReverse = false;
            break;
        }
        if (allReverse) {
            Arrays.sort(nums);
        }
    }
}

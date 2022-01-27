package leetcode.from1to50;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/5 17:41
 * @Description: 41.缺失的第一个正数 | 难度：困难 | 标签：数组
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * <p>
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * <p>
 * 提示：
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution41 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 72.53% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 86.63% 的用户
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums.length || nums[i] <= 0) {
                nums[i] = 0;
            } else if (nums[i] != i + 1) {
                temp = nums[i];
                if (nums[temp - 1] == temp) {
                    nums[i] = 0;
                } else {
                    nums[i] = nums[temp - 1];
                    nums[temp - 1] = temp;
                    i--;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    @Test
    public void firstMissingPositiveTest() {
        Assert.assertEquals(firstMissingPositive(new int[]{1, 2, 0}), 3);
        Assert.assertEquals(firstMissingPositive(new int[]{3, 4, -1, 1}), 2);
        Assert.assertEquals(firstMissingPositive(new int[]{7, 8, 9, 11, 12}), 1);
        Assert.assertEquals(firstMissingPositive(new int[]{1, 1}), 2);
    }
}

package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/5/14 17:04
 * @Description: 面试题 10.11. 峰与谷 | 难度：中等 | 标签：贪心、数组、排序
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
 * 例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 * <p>
 * 示例:
 * 输入: [5, 3, 1, 2, 3]
 * 输出: [5, 1, 3, 2, 3]
 * <p>
 * 提示：
 * nums.length <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/peaks-and-valleys-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution10_11 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.3 MB , 在所有 Java 提交中击败了 9.21% 的用户
     * 通过测试用例： 126 / 126
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (nums.length <= 2) {
            return;
        }
        boolean hill = nums[1] > nums[0];
        for (int i = 2; i < nums.length; i++) {
            if ((hill && nums[i] > nums[i - 1]) || (!hill && nums[i] < nums[i - 1])) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
            hill = !hill;
        }
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 54.90% 的用户
     * 内存消耗： 41.7 MB , 在所有 Java 提交中击败了 88.63% 的用户
     * 通过测试用例： 126 / 126
     *
     * @param nums
     */
    public void wiggleSort_sort(int[] nums) {
        if (nums.length <= 2) {
            return;
        }
        Arrays.sort(nums);
        int s = 1;
        int b = nums.length / 2;
        if (b % 2 == 1) {
            b++;
        }
        while (b < nums.length) {
            int temp = nums[b];
            nums[b] = nums[s];
            nums[s] = temp;
            b += 2;
            s += 2;
        }
    }
}

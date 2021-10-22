package leetcode.from201to300;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/10/22 9:58
 * @Description: 229. 求众数 II | 难度：中等 | 标签：
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：[3]
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution229 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.4 MB , 在所有 Java 提交中击败了 36.11% 的用户
     * 通过测试用例： 82 / 82
     * <p>
     * 摩尔投票法，参考题解
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int num1 = 0;
        int num2 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (count1 != 0 && num1 == num) {
                count1++;
            } else if (count2 != 0 && num2 == num) {
                count2++;
            } else if (count1 == 0 && ++count1 >= 0) {
                num1 = num;
            } else if (count2 == 0 && ++count2 >= 0) {
                num2 = num;
            } else {
                count1--;
                count2--;
            }
        }
        // 因为可能最后几次都是已有的候选数字，导致另一个候选数字可能不一定满足计数
        // 所以最后再对候选数字记一遍数
        count1 = 0;
        count2 = 0;
        for (int i : nums) {
            if (num1 == i) {
                count1++;
            } else if (num2 == i) {
                count2++;
            }
        }
        List<Integer> result = new ArrayList<>();
        if (count1 > n / 3) result.add(num1);
        if (count2 > n / 3) result.add(num2);
        return result;
    }
}

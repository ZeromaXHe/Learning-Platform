package leetcode.from101to150;

import java.util.HashSet;

/**
 * @Author: zhuxi
 * @Time: 2021/4/30 9:50
 * @Description: 137.只出现一次的数字II | 难度：中等 | 标签：位运算
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 * <p>
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution137 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 25.10% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 5.02% 的用户
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashSet<Integer> single = new HashSet<>();
        HashSet<Integer> multiple = new HashSet<>();
        for (int num : nums) {
            if (single.contains(num)) {
                multiple.add(num);
                single.remove(num);
            } else if (!multiple.contains(num)) {
                single.add(num);
            }
        }
        return single.iterator().next();
    }
}

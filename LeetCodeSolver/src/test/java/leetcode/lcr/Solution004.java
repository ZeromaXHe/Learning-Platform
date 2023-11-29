package leetcode.lcr;

/**
 * @author zhuxi
 * @apiNote LCR 004. 只出现一次的数字 II | 难度：中等 | 标签：位运算、数组
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,100]
 * 输出：100
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 * <p>
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 注意：本题与主站 137 题相同：https://leetcode-cn.com/problems/single-number-ii/
 * @implNote
 * @since 2023/11/29 11:17
 */
public class Solution004 {
    /**
     * 参考题解做的
     * <p>
     * 执行用时分布 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 消耗内存分布 43.29 MB
     * 击败 5.08% 使用 Java 的用户
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int one = 0, two = 0;
        for (int num : nums) {
            one = one ^ num & ~two;
            two = two ^ num & ~one;
        }
        return one;
    }
}

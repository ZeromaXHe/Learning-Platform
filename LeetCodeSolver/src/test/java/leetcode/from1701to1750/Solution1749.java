package leetcode.from1701to1750;

/**
 * @author zhuxi
 * @apiNote 1749. 任意子数组和的绝对值的最大值 | 难度：中等 | 标签：数组、动态规划
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 * <p>
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 * <p>
 * abs(x) 定义如下：
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * <p>
 * 示例 2：
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * @implNote
 * @since 2023/8/8 9:48
 */
public class Solution1749 {
    /**
     * 时间 2 ms
     * 击败 93.88% 使用 Java 的用户
     * <p>
     * 内存 52.26 mb
     * 击败 47.96% 使用 Java 的用户
     *
     * @param nums
     * @return
     */
    public int maxAbsoluteSum(int[] nums) {
        int positive = 0;
        int max = 0;
        int negative = 0;
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            positive += nums[i];
            if (positive > max) {
                max = positive;
            }
            if (positive < 0) {
                positive = 0;
            }
            negative += nums[i];
            if (negative < min) {
                min = negative;
            }
            if (negative > 0) {
                negative = 0;
            }
        }
        return Math.max(max, -min);
    }
}

package leetcode.from5701to5800;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/11 10:30
 * @Description: 5726.数组元素积的符号 | 难度：简单 | 标签：
 * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
 * <p>
 * 如果 x 是正数，返回 1 。
 * 如果 x 是负数，返回 -1 。
 * 如果 x 是等于 0 ，返回 0 。
 * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
 * <p>
 * 返回 signFunc(product) 。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,-2,-3,-4,3,2,1]
 * 输出：1
 * 解释：数组中所有值的乘积是 144 ，且 signFunc(144) = 1
 * <p>
 * 示例 2：
 * 输入：nums = [1,5,0,2,-3]
 * 输出：0
 * 解释：数组中所有值的乘积是 0 ，且 signFunc(0) = 0
 * <p>
 * 示例 3：
 * 输入：nums = [-1,1,-1,1,-1]
 * 输出：-1
 * 解释：数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * -100 <= nums[i] <= 100
 * @Modified By: ZeromaXHe
 */
public class Solution5726 {
    /**
     * 74 / 74 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 38.2 MB
     *
     * @param nums
     * @return
     */
    public int arraySign(int[] nums) {
        int result = 1;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                result = -result;
            }
        }
        return result;
    }
}

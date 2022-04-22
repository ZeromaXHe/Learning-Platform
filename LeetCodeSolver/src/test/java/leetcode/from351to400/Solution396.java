package leetcode.from351to400;

/**
 * @Author: zhuxi
 * @Time: 2022/4/22 9:50
 * @Description: 396. 旋转函数 | 难度：中等 | 标签：数组、数学、动态规划
 * 给定一个长度为 n 的整数数组 nums 。
 * <p>
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 * <p>
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 * <p>
 * 生成的测试用例让答案符合 32 位 整数。
 * <p>
 * 示例 1:
 * 输入: nums = [4,3,2,6]
 * 输出: 26
 * 解释:
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 * <p>
 * 示例 2:
 * 输入: nums = [100]
 * 输出: 0
 * <p>
 * 提示:
 * n == nums.length
 * 1 <= n <= 105
 * -100 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-function
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution396 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 53.1 MB , 在所有 Java 提交中击败了 53.65% 的用户
     * 通过测试用例： 58 / 58
     *
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        int sum = 0;
        int test = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            test += i * nums[i];
        }
        int max = test;
        for (int i = nums.length - 1; i > 0; i--) {
            test = test + sum - nums.length * nums[i];
            max = Math.max(max, test);
        }
        return max;
    }
}

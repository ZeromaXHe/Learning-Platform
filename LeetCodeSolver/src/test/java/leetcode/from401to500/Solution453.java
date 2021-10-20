package leetcode.from401to500;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/10/20 9:55
 * @Description: 453. 最小操作次数使数组元素相等 | 难度：简单 | 标签：
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 答案保证符合 32-bit 整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution453 {
    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 52.52% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 5.04% 的用户
     * 通过测试用例： 84 / 84
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int min = Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
        return Arrays.stream(nums).map(num -> num - min).sum();
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 73.12% 的用户
     * 通过测试用例： 84 / 84
     *
     * @param nums
     * @return
     */
    public int minMoves_for(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        int sum = 0;
        for (int num : nums) {
            sum += num - min;
        }
        return sum;
    }
}

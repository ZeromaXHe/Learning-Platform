package leetcode.from5701to5750;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/21 10:45
 * @Description: 5711.有界数组中指定下标处的最大值 | 难度：中等 | 标签：贪心算法、二分查找
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 * <p>
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 * <p>
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 * <p>
 * 示例 1：
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 * <p>
 * 示例 2：
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 * <p>
 * 提示：
 * 1 <= n <= maxSum <= 109
 * 0 <= index < n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-value-at-a-given-index-in-a-bounded-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5711 {
    /**
     * 370 / 370 个通过测试用例
     * 状态：通过
     * 执行用时: 21 ms
     * 内存消耗: 35.5 MB
     *
     * @param n
     * @param index
     * @param maxSum
     * @return
     */
    public int maxValue(int n, int index, int maxSum) {
        int result = maxSum / n;
        while (addByMax(Math.min(result, index), Math.min(result, n - index - 1), result + 1) + n <= maxSum) {
            result++;
        }
        return result;
    }

    private long addByMax(int left, int right, int max) {
        // 1 = 0
        // 2 = 1
        // 3 = 4 = 1 + 3
        // 4 = 9 = 1 + 3 + 5
        return (long) (2 * max - left) * (left + 1) / 2 + (long) (2 * max - 1 - right) * right / 2 - left - right - 1;
    }

    @Test
    public void maxValueTest() {
        // 输入： 3 2 18
        // 输出： 6
        // 预期： 7
        Assert.assertEquals(7, maxValue(3, 2, 18));
        // 输入：4 0 4
        // 输出：
        // 2
        // 预期：
        // 1
        Assert.assertEquals(1, maxValue(4, 0, 4));
        // 超时
        System.out.println(maxValue(7, 0, 930041194));
    }
}

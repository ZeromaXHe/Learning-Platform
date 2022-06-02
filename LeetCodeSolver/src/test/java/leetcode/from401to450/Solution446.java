package leetcode.from401to450;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/6/2 13:59
 * @Description: 446. 等差数列划分 II - 子序列 | 难度：困难 | 标签：数组、动态规划
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 * <p>
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * <p>
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 * <p>
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 * <p>
 * 示例 1：
 * 输入：nums = [2,4,6,8,10]
 * 输出：7
 * 解释：所有的等差子序列为：
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 * <p>
 * 示例 2：
 * 输入：nums = [7,7,7,7,7]
 * 输出：16
 * 解释：数组中的任意子序列都是等差子序列。
 * <p>
 * 提示：
 * 1  <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/arithmetic-slices-ii-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution446 {
    /**
     * 执行用时： 198 ms , 在所有 Java 提交中击败了 45.82% 的用户
     * 内存消耗： 118.2 MB , 在所有 Java 提交中击败了 5.03% 的用户
     * 通过测试用例： 101 / 101
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int ans = 0;
        int n = nums.length;
        Map<Long, Integer> f = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long d = (long) nums[i] - nums[j];
                int cnt = f.getOrDefault(d + j * 5_000_000_000L, 0);
                ans += cnt;
                f.put(d + i * 5_000_000_000L, f.getOrDefault(d + i * 5_000_000_000L, 0) + cnt + 1);
            }
        }
        return ans;
    }
}

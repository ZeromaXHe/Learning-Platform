package leetcode.from301to400;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhuxi
 * @Time: 2021/3/4 10:10
 * @Description: 354.俄罗斯套娃信封 | 难度：困难 | 标签：二分查找、动态规划
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution354 {
    /**
     * 执行用时： 20 ms , 在所有 Java 提交中击败了 68.80% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 34.01% 的用户
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len == 0) {
            return 0;
        }
        // 根据e[0]正序，e[1]倒序
        Arrays.sort(envelopes, Comparator.comparingInt((int[] e) -> e[0]).reversed().thenComparingInt((int[] e) -> e[1]).reversed());

        // 对高度数组寻找 LIS
        int[] height = new int[len];
        for (int i = 0; i < len; i++)
            height[i] = envelopes[i][1];

        return lengthOfLIS(height);
    }

    /**
     * 执行用时： 266 ms , 在所有 Java 提交中击败了 31.80% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 40.20% 的用户
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes_slow(int[][] envelopes) {
        int len = envelopes.length;
        if (len == 0) {
            return 0;
        }
        // 根据e[0]正序，e[1]倒序
        Arrays.sort(envelopes, Comparator.comparingInt((int[] e) -> e[0]).reversed().thenComparingInt((int[] e) -> e[1]).reversed());
        int[] f = new int[len];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < len; ++i) {
            // 这一步可以用最长递增子序列来做
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    /**
     * 最大递增子序列 模板
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int piles = 0, n = nums.length;
        int[] top = new int[n];
        for (int i = 0; i < n; i++) {
            // 要处理的扑克牌
            int poker = nums[i];
            int left = 0, right = piles;
            // 二分查找插入位置
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= poker)
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }
}

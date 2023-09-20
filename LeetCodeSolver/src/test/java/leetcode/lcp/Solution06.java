package leetcode.lcp;

/**
 * @author zhuxi
 * @apiNote LCP 06. 拿硬币 | 难度：简单 | 标签：数组、数学
 * 桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
 * <p>
 * 示例 1：
 * 输入：[4,2,1]
 * 输出：4
 * 解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。
 * <p>
 * 示例 2：
 * 输入：[2,3,10]
 * 输出：8
 * <p>
 * 限制：
 * 1 <= n <= 4
 * 1 <= coins[i] <= 10
 * @implNote
 * @since 2023/9/20 9:57
 */
public class Solution06 {
    /**
     * 时间 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 37.82 MB
     * 击败 49.67% 使用 Java 的用户
     *
     * @param coins
     * @return
     */
    public int minCount(int[] coins) {
        int result = 0;
        for (int coin : coins) {
            result += (coin + 1) / 2;
        }
        return result;
    }
}

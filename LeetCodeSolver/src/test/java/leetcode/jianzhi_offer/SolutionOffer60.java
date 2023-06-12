package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 60. n个骰子的点数 | 难度：中等 | 标签：数学、动态规划、概率与统计
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * 示例 1:
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * <p>
 * 示例 2:
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * <p>
 * 限制：
 * 1 <= n <= 11
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 14:18
 */
public class SolutionOffer60 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了 74.53% 的用户
     * 通过测试用例：11 / 11
     *
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        double[][] dp = new double[n][5 * n + 1];
        for (int i = 0; i < 6; i++) {
            dp[0][i] = 1.0 / 6.0;
        }
        if (n == 1) {
            return dp[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 5 * (i + 1); j++) {
                for (int k = 0; k < 6; k++) {
                    if (j - k < 0) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - k] / 6.0;
                }
            }
        }
        return dp[n - 1];
    }
}

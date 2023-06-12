package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 63. 股票的最大利润 | 难度：中等 | 标签：数组、动态规划
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 限制：
 * 0 <= 数组长度 <= 10^5
 * <p>
 * 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 15:01
 */
public class SolutionOffer63 {
    /**
     * 一次遍历
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了 51.88% 的用户
     * 内存消耗：43.2 MB, 在所有 Java 提交中击败了 9.38% 的用户
     * 通过测试用例：200 / 200
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return profit;
    }

    /**
     * 两次遍历
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：43.3 MB, 在所有 Java 提交中击败了 5.16% 的用户
     * 通过测试用例：200 / 200
     *
     * @param prices
     * @return
     */
    public int maxProfit_2times(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] maxRight = new int[n];
        maxRight[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], prices[i]);
        }
        int profit = 0;
        for (int i = 0; i < n - 1; i++) {
            profit = Math.max(profit, maxRight[i + 1] - prices[i]);
        }
        return profit;
    }
}

package leetcode.from701to750;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/17 20:54
 * @Description: 714. 买卖股票的最佳时机含手续费 | 难度：中等 | 标签：贪心算法、数组、动态规划
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * <p>
 * 注意:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution714 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.77% 的用户
     * 内存消耗： 47.7 MB , 在所有 Java 提交中击败了 67.17% 的用户
     * 贪心算法
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                // 价格低的话，就用新的price[i]+fee替代买价
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                // 每次保留之前的prices[i], 以防止连续上涨的情况多耗费手续费
                buy = prices[i];
            }
        }
        return profit;
    }
}

package leetcode.from101to150;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/1/9 11:55
 * @Description: 123. 买卖股票的最佳时机 III | 难度：困难 | 标签：数组，动态规划
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution123 {
    /**
     * 代码改自188.买卖股票的最佳时机 IV，即 k = 2 的情况
     * 执行用时： 51 ms , 在所有 Java 提交中击败了 42.34% 的用户
     * 内存消耗： 51.7 MB , 在所有 Java 提交中击败了 79.89% 的用户
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int sum = 0;
        if (n <= 5) {
            for (int i = 1; i < n; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        int[][] buy = new int[n][3];
        int[][] sell = new int[n][3];
        // 对于 buy[0][0]，它的值为 −prices[0]，即「我们在第 0 天以 prices[0] 的价格买入股票」是唯一满足手上持有股票的方法。
        buy[0][0] = -prices[0];
        // 而对于 sell[0][0]，它的值为 0，即「我们在第 0 天不做任何事」是唯一满足手上不持有股票的方法。
        sell[0][0] = 0;
        for (int i = 1; i <= 2; ++i) {
            // 对于 buy[0][0..2]，由于只有 prices[0] 唯一的股价，因此我们不可能进行过任何交易，那么我们可以将所有的 buy[0][1..k] 设置为一个非常小的值，表示不合法的状态。
            // 对于 sell[0][0..2]，同理我们可以将所有的 sell[0][1..2] 设置为一个非常小的值，表示不合法的状态。
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            buy[i][1] = Math.max(buy[i - 1][1], sell[i - 1][1] - prices[i]);
            sell[i][1] = Math.max(sell[i - 1][1], buy[i - 1][0] + prices[i]);
            buy[i][2] = Math.max(buy[i - 1][2], sell[i - 1][2] - prices[i]);
            sell[i][2] = Math.max(sell[i - 1][2], buy[i - 1][1] + prices[i]);
        }

        return Arrays.stream(sell[n - 1])
                .max()
                .getAsInt();
    }
}

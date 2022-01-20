package leetcode.from101to200;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 18:28
 * @Description: 121. 买卖股票的最佳时机 | 难度：简单 | 标签：数组、动态规划
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution121 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 89.11% 的用户
     * 内存消耗： 51.4 MB , 在所有 Java 提交中击败了 36.00% 的用户
     * 通过测试用例： 211 / 211
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int base = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < base) {
                base = prices[i];
            }
            if (prices[i] - base > maxProfit) {
                maxProfit = prices[i] - base;
            }
        }
        return maxProfit;
    }
}

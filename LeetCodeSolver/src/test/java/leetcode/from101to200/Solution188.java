package leetcode.from101to200;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/28 16:17
 * @Description: 188.买卖股票的最佳时机 IV | 难度：困难 | 标签：动态规划
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * <p>
 * 提示：
 * 0 <= k <= 109
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution188 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 52.90% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 64.22% 的用户
     * <p>
     * 感觉动态规划推转移方程的过程自己还是不怎么会，答案是参考题解加注释的。
     * 看题解就会感觉动态规划非常的精妙，简单寻找到状态的转移过程和边界条件，基本就可以直接写代码了，具体的推断过程就交由程序自己执行。
     * 哎，还是得多练练动规的题目。
     * <p>
     * 此外，看题解区，这道题貌似还有一个叫wqs的更高阶的解法。
     * 不过对于我连动态规划都掌握得不利索的程度，还是日后再来了解吧~
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int sum = 0;
        if (k == 0 || n == 0) {
            return sum;
        }
        if (k >= n / 2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];
        // 对于 buy[0][0]，它的值为 −prices[0]，即「我们在第 0 天以 prices[0] 的价格买入股票」是唯一满足手上持有股票的方法。
        buy[0][0] = -prices[0];
        // 而对于 sell[0][0]，它的值为 0，即「我们在第 0 天不做任何事」是唯一满足手上不持有股票的方法。
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            // 对于 buy[0][0..k]，由于只有 prices[0] 唯一的股价，因此我们不可能进行过任何交易，那么我们可以将所有的 buy[0][1..k] 设置为一个非常小的值，表示不合法的状态。
            // 对于 sell[0][0..k]，同理我们可以将所有的 sell[0][1..k] 设置为一个非常小的值，表示不合法的状态。
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                // 对于 buy[i][j]，我们考虑当前手上持有的股票是否是在第 i 天买入的。
                // 如果是第 i 天买入的，那么在第 i−1 天时，我们手上不持有股票，对应状态 sell[i−1][j]，并且需要扣除 prices[i] 的买入花费；
                // 如果不是第 i 天买入的，那么在第 i-1 天时，我们手上持有股票，对应状态 buy[i][j]。
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                // 同理对于 sell[i][j]，如果是第 i 天卖出的，那么在第 i−1 天时，我们手上持有股票，对应状态 buy[i−1][j−1]，并且需要增加 prices[i] 的卖出收益；
                // 如果不是第 ii 天卖出的，那么在第 i-1i−1 天时，我们手上不持有股票，对应状态 \textit{sell}[i-1][j]sell[i−1][j]。
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell[n - 1])
                .max()
                .getAsInt();
    }

    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 52.90% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 81.95% 的用户
     * <p>
     * 看题解区有个python实现的贪心算法，试着也实现一下。
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit_greedy(int k, int[] prices) {
        int n = prices.length;
        int sum = 0;
        if (k == 0 || n == 0) {
            return sum;
        }
        if (k >= n / 2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        ArrayList<Integer> up_num = new ArrayList<>(n);
        ArrayList<Integer> down_num = new ArrayList<>(n);
        int low = prices[0];
        int high = -1;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                if (i + 1 == n || prices[i + 1] <= prices[i]) {
                    up_num.add(prices[i] - low);
                    if (high >= 0) {
                        down_num.add(high - low);
                    }
                    high = prices[i];
                }
            } else {
                low = prices[i];
            }
        }
        while (up_num.size() > k) {
            int[] min_up = min(up_num);
            int[] min_down = min(down_num);
            if (min_up[0] <= min_down[0]) {
                int index = min_up[1];
                if (index == 0) {
                    down_num.remove(0);
                } else if (index == up_num.size() - 1) {
                    down_num.remove(down_num.size() - 1);
                } else {
                    down_num.set(index - 1, down_num.get(index - 1) + down_num.get(index) - up_num.get(index));
                    down_num.remove(index);
                }
                up_num.remove(index);
            } else {
                int index = min_down[1];
                up_num.set(index + 1, up_num.get(index + 1) + up_num.get(index) - down_num.get(index));
                up_num.remove(index);
                down_num.remove(index);
            }
        }

        return sum(up_num);
    }

    private int sum(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (int i : arrayList) {
            sum += i;
        }
        return sum;
    }

    /**
     * @param arrayList
     * @return 数组下标0的数为最小值，下标1的数为最小值所在位置
     */
    private int[] min(ArrayList<Integer> arrayList) {
        int[] result = new int[2];
        result[0] = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i) < result[0]) {
                result[0] = arrayList.get(i);
                result[1] = i;
            }
        }
        return result;
    }

    @Test
    public void maxProfit_greedyTest() {
        //1
        //[6,1,6,4,3,0,2]
        System.out.println(maxProfit_greedy(1, new int[]{6, 1, 6, 4, 3, 0, 2}));
    }
}

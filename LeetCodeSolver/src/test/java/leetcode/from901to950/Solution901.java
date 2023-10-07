package leetcode.from901to950;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 901. 股票价格跨度 | 难度：中等 | 标签：栈、设计、数据流、单调栈
 * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
 * <p>
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * <p>
 * 例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
 * <p>
 * 实现 StockSpanner 类：
 * <p>
 * StockSpanner() 初始化类对象。
 * int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 * <p>
 * 示例：
 * 输入：
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * 输出：
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * <p>
 * 解释：
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // 返回 1
 * stockSpanner.next(80);  // 返回 1
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(70);  // 返回 2
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
 * stockSpanner.next(85);  // 返回 6
 * <p>
 * 提示：
 * 1 <= price <= 105
 * 最多调用 next 方法 104 次
 * @implNote
 * @since 2023/10/7 9:57
 */
public class Solution901 {
    /**
     * 参考题解做的，单调栈一下没理清咋弄
     * <p>
     * 时间 20 ms
     * 击败 94.81% 使用 Java 的用户
     * 内存 52.13 MB
     * 击败 37.93% 使用 Java 的用户
     */
    class StockSpanner {
        private LinkedList<int[]> stack = new LinkedList<>();

        public StockSpanner() {

        }

        public int next(int price) {
            int count = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                count += stack.pop()[1];
            }
            stack.push(new int[]{price, count});
            return count;
        }
    }
}

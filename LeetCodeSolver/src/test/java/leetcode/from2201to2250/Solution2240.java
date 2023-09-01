package leetcode.from2201to2250;

/**
 * @author zhuxi
 * @apiNote 2240. 买钢笔和铅笔的方案数 | 难度：中等 | 标签：数学、枚举
 * 给你一个整数 total ，表示你拥有的总钱数。同时给你两个整数 cost1 和 cost2 ，分别表示一支钢笔和一支铅笔的价格。你可以花费你部分或者全部的钱，去买任意数目的两种笔。
 * <p>
 * 请你返回购买钢笔和铅笔的 不同方案数目 。
 * <p>
 * 示例 1：
 * 输入：total = 20, cost1 = 10, cost2 = 5
 * 输出：9
 * 解释：一支钢笔的价格为 10 ，一支铅笔的价格为 5 。
 * - 如果你买 0 支钢笔，那么你可以买 0 ，1 ，2 ，3 或者 4 支铅笔。
 * - 如果你买 1 支钢笔，那么你可以买 0 ，1 或者 2 支铅笔。
 * - 如果你买 2 支钢笔，那么你没法买任何铅笔。
 * 所以买钢笔和铅笔的总方案数为 5 + 3 + 1 = 9 种。
 * <p>
 * 示例 2：
 * 输入：total = 5, cost1 = 10, cost2 = 10
 * 输出：1
 * 解释：钢笔和铅笔的价格都为 10 ，都比拥有的钱数多，所以你没法购买任何文具。所以只有 1 种方案：买 0 支钢笔和 0 支铅笔。
 * <p>
 * 提示：
 * 1 <= total, cost1, cost2 <= 106
 * @implNote
 * @since 2023/9/1 10:17
 */
public class Solution2240 {
    /**
     * 时间 7 ms
     * 击败 95.71% 使用 Java 的用户
     * 内存 37.45 MB
     * 击败 26.99% 使用 Java 的用户
     * <p>
     * for 循环条件用“i <= total / maxCost”的话，除法比较慢：
     * 时间 12 ms
     * 击败 36.20% 使用 Java 的用户
     * 内存 37.28 MB
     * 击败 65.03% 使用 Java 的用户
     *
     * @param total
     * @param cost1
     * @param cost2
     * @return
     */
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long result = 0;
        int maxCost = Math.max(cost1, cost2);
        int minCost = Math.min(cost1, cost2);
        for (int i = 0; i * maxCost <= total; i++) {
            result += (total - maxCost * i) / minCost + 1;
        }
        return result;
    }

    /**
     * 时间 19 ms
     * 击败 17.79% 使用 Java 的用户
     * 内存 37.21 MB
     * 击败 78.53% 使用 Java 的用户
     *
     * @param total
     * @param cost1
     * @param cost2
     * @return
     */
    public long waysToBuyPensPencils_slow(int total, int cost1, int cost2) {
        long result = 0;
        for (int i = 0; i <= total / cost1; i++) {
            result += (total - cost1 * i) / cost2 + 1;
        }
        return result;
    }
}

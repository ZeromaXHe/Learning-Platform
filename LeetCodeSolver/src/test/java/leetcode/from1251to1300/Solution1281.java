package leetcode.from1251to1300;

/**
 * @author zhuxi
 * @apiNote 1281. 整数的各位积和之差 | 难度：简单 | 标签：数学
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * <p>
 * 示例 1：
 * 输入：n = 234
 * 输出：15
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24
 * 各位数之和 = 2 + 3 + 4 = 9
 * 结果 = 24 - 9 = 15
 * <p>
 * 示例 2：
 * 输入：n = 4421
 * 输出：21
 * 解释：
 * 各位数之积 = 4 * 4 * 2 * 1 = 32
 * 各位数之和 = 4 + 4 + 2 + 1 = 11
 * 结果 = 32 - 11 = 21
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 * @implNote
 * @since 2023/8/9 9:45
 */
public class Solution1281 {
    /**
     * 时间 - ms
     * 击败 100.00%使用 Java 的用户
     * <p>
     * 内存 37.36 mb
     * 击败 59.11%使用 Java 的用户
     *
     * @param n
     * @return
     */
    public int subtractProductAndSum(int n) {
        int prod = 1;
        int sum = 0;
        while (n > 0) {
            prod *= n % 10;
            sum += n % 10;
            n /= 10;
        }
        return prod - sum;
    }
}

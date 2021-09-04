package leetcode.jianzhi_offer;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/9/4 22:53
 * @Description: 剑指 Offer 10- I. 斐波那契数列 | 难度：简单 | 标签：记忆化搜索、数学、动态规划
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 * <p>
 * 提示：
 * 0 <= n <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class SolutionOffer10_I {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 20.60% 的用户
     * 通过测试用例： 51 / 51
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int i1 = 0;
        int i2 = 1;
        int result = 1;
        for (int i = 2; i < n; i++) {
            i1 = i2;
            i2 = result;
            result = (i1 + i2) % 1_000_000_007;
        }
        return result;
    }
}

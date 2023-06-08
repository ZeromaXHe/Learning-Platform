package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 16. 数值的整数次方 | 难度：中等 | 标签：递归、数学
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * <p>
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 提示：
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * <p>
 * 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/8 11:07
 */
public class SolutionOffer16 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了 95.03% 的用户
     * 通过测试用例：304 / 304
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        return myPowL(x, n);
    }

    private double myPowL(double x, long n) {
        if (n < 0) {
            return 1.0 / myPowL(x, -n);
        }
        double result = 1;
        if (n % 2 == 1) {
            result *= x;
        }
        if (n <= 1) {
            return result;
        }
        result *= myPowL(x * x, n / 2);
        return result;
    }
}

package leetcode.jianzhi_offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 14- II. 剪绳子 II | 难度：中等 | 标签：数学、动态规划
 * @implNote 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * <p>
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * <p>
 * 提示：
 * 2 <= n <= 1000
 * 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/6/8 10:31
 */
public class SolutionOffer14_II {
    @Test
    public void cuttingRopeTest() {
        Assert.assertEquals(620946522, cuttingRope(1000));
    }

    int MOD = (int) 1e9 + 7;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了 42.86% 的用户
     * 通过测试用例：55 / 55
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int count3 = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) power3mod(count3);
        } else if (remainder == 1) {
            return (int) (power3mod(count3 - 1) * 4 % MOD);
        } else {
            return (int) (power3mod(count3) * 2 % MOD);
        }
    }

    private long power3mod(int pow) {
        if (pow == 0) {
            return 1;
        }
        long result = 1L;
        for (int i = 0; i < pow; i++) {
            result *= 3;
            result %= MOD;
        }
        return result;
    }
}

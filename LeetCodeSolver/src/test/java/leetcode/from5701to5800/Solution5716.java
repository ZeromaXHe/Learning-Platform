package leetcode.from5701to5800;

import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/28 10:50
 * @Description: 5716. 好因子的最大数目 | 难度：困难 | 标签：数学
 * 给你一个正整数 primeFactors 。你需要构造一个正整数 n ，它满足以下条件：
 * <p>
 * n 质因数（质因数需要考虑重复的情况）的数目 不超过 primeFactors 个。
 * n 好因子的数目最大化。如果 n 的一个因子可以被 n 的每一个质因数整除，我们称这个因子是 好因子 。比方说，如果 n = 12 ，那么它的质因数为 [2,2,3] ，那么 6 和 12 是好因子，但 3 和 4 不是。
 * 请你返回 n 的好因子的数目。由于答案可能会很大，请返回答案对 10^9 + 7 取余 的结果。
 * <p>
 * 请注意，一个质数的定义是大于 1 ，且不能被分解为两个小于该数的自然数相乘。一个数 n 的质因子是将 n 分解为若干个质因子，且它们的乘积为 n 。
 * <p>
 * 示例 1：
 * 输入：primeFactors = 5
 * 输出：6
 * 解释：200 是一个可行的 n 。
 * 它有 5 个质因子：[2,2,2,5,5] ，且有 6 个好因子：[10,20,40,50,100,200] 。
 * 不存在别的 n 有至多 5 个质因子，且同时有更多的好因子。
 * <p>
 * 示例 2：
 * 输入：primeFactors = 8
 * 输出：18
 * <p>
 * 提示：
 * 1 <= primeFactors <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximize-number-of-nice-divisors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5716 {
    /**
     * 未完成
     *
     * @param primeFactors
     * @return
     */
    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors == 1) {
            return 1;
        }
        if (primeFactors == 2) {
            return 2;
        }
        int count = primeFactors / 3;
        int remain = primeFactors % 3;
        if (remain == 1) {
            count--;
            remain += 3;
        }
        long sum = 1;
        while (count > 0) {
            sum *= 3;
            if (sum > Long.MAX_VALUE / 1000000007) {
                sum %= 1000000007;
            }
            count--;
        }
        if (remain > 0) {
            sum *= remain;
            sum %= 1000000007;
        }
        return (int) sum;

        // BigInteger integer = BigInteger.valueOf(3).pow(count).multiply(new BigInteger(""+remain)).mod(BigInteger.valueOf(1000000007));
        // return Integer.parseInt(integer.toString());
    }

    /**
     * 太大的数会OOM
     *
     * @param primeFactors
     * @return
     */
    public int maxNiceDivisors_oom(int primeFactors) {
        if (primeFactors == 1) {
            return 1;
        }
        if (primeFactors == 2) {
            return 2;
        }
        long[] dp = new long[primeFactors + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < primeFactors + 1; i++) {
            dp[i] = i;
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return (int) (dp[primeFactors] % 1000000007);
    }

    @Test
    public void maxNiceDivisorsTest() {
        System.out.println(maxNiceDivisors(10) == maxNiceDivisors_oom(10));
        System.out.println(maxNiceDivisors(25) == maxNiceDivisors_oom(25));
        System.out.println(maxNiceDivisors(50) == maxNiceDivisors_oom(50));
        System.out.println(maxNiceDivisors(99) == maxNiceDivisors_oom(99));
        System.out.println(maxNiceDivisors(100) == maxNiceDivisors_oom(100));
        System.out.println(maxNiceDivisors(545) == maxNiceDivisors_oom(545));
        // 102 / 213 个通过测试用例
        // 状态：超出内存限制
        System.out.println(maxNiceDivisors(545918790));
        // 104 / 213 个通过测试用例
        // 状态：超出时间限制
        System.out.println(maxNiceDivisors(835846393));
    }
}

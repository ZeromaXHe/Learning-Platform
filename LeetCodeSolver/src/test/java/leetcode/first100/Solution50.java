package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/12 13:57
 * @Description: 50. Pow(x, n) | 难度：中等 | 标签：递归、数学
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
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
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution50 {
    @Test
    public void myPowTest() {
        Assert.assertEquals(1024.0, myPow(2, 10), 0.0001);
        Assert.assertEquals(9.261, myPow(2.1, 3), 0.0001);
        Assert.assertEquals(0.25, myPow(2, -2), 0.0001);
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.6 MB , 在所有 Java 提交中击败了 40.73% 的用户
     * 通过测试用例： 305 / 305
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        if (N == 0) {
            return 1;
        } else if (N < 0) {
            return 1 / myPow(x, -N);
        }
        return myPow(x, N);
    }

    private double myPow(double x, long n) {
        if (n == 1) {
            return x;
        }
        double half = myPow(x, n / 2);
        return half * half * (n % 2 == 1 ? x : 1);
    }
}

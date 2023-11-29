package leetcode.lcr;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote LCR 001. 两数相除 | 难度：简单 | 标签：数学
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 * <p>
 * 注意：
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1
 * <p>
 * 示例 1：
 * 输入：a = 15, b = 2
 * 输出：7
 * 解释：15/2 = truncate(7.5) = 7
 * <p>
 * 示例 2：
 * 输入：a = 7, b = -3
 * 输出：-2
 * 解释：7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 示例 3：
 * 输入：a = 0, b = 1
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：a = 1, b = 1
 * 输出：1
 * <p>
 * 提示:
 * -231 <= a, b <= 231 - 1
 * b != 0
 * <p>
 * 注意：本题与主站 29 题相同：https://leetcode-cn.com/problems/divide-two-integers/
 * @implNote
 * @since 2023/11/29 10:25
 */
public class Solution001 {
    @Test
    public void divideTest() {
        Assert.assertEquals(7, divide(15, 2));
        Assert.assertEquals(-2, divide(-7, 3));
        Assert.assertEquals(715827882, divide(2147483647, 3));
        Assert.assertEquals(-2147483648, divide(-2147483648, 1));
    }

    /**
     * 执行用时分布 1 ms
     * 击败 66.67% 使用 Java 的用户
     * 消耗内存分布 38.90 MB
     * 击败 10.33% 使用 Java 的用户
     *
     * @param a
     * @param b
     * @return
     */
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE) {
            if (b == -1) {
                return Integer.MAX_VALUE;
            } else if (b == 1) {
                return Integer.MIN_VALUE;
            }
        }
        if (a > 0) {
            return -divide(-a, b);
        }
        if (b > 0) {
            return -divide(a, -b);
        }
        // a、b 将都变成负数，防止溢出
        if (b < a) {
            return 0;
        }
        int[] pow = new int[32];
        pow[0] = b;
        int max = 0;
        while (pow[max] + pow[max] < pow[max] && pow[max] + pow[max] >= a) {
            pow[max + 1] = pow[max] + pow[max];
            max++;
        }
        int result = 0;
        int sum = 0;
        int i = max;
        while (i >= 0) {
            int newSum = sum + pow[i];
            if (newSum < sum) {
                if (newSum == a) {
                    return result + 1 << i;
                } else if (newSum > a) {
                    result += 1 << i;
                    sum = newSum;
                }
            }
            i--;
        }
        return result;
    }
}

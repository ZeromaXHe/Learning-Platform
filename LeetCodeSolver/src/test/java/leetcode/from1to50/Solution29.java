package leetcode.from1to50;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/10/12 19:12
 * @Description: 29. 两数相除 | 难度：中等 | 标签：位运算、数学
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p>
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution29 {
    @Test
    public void testDivide() {
        Assert.assertEquals(3, divide(10, 3));
        Assert.assertEquals(-2, divide(7, -3));
        // 通过测试用例： 698 / 992
        Assert.assertEquals(-1073741824, divide(-2147483648, 2));
        // 通过测试用例： 770 / 992
        Assert.assertEquals(715827882, divide(2147483647, 3));
        // 通过测试用例： 889 / 992
        Assert.assertEquals(-77, divide(-231, 3));
        // 通过测试用例： 890 / 992
        Assert.assertEquals(1, divide(1530093783, 1493139203));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.6 MB , 在所有 Java 提交中击败了 26.46% 的用户
     * 通过测试用例： 992 / 992
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE || dividend == Integer.MIN_VALUE + 1) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
            if (divisor == 1) {
                return dividend;
            }
        }
        if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dividend == 0) {
            return 0;
        }
        boolean negative = false;
        if (dividend > 0) {
            dividend = -dividend;
        } else {
            negative = true;
        }
        if (divisor > 0) {
            divisor = -divisor;
        } else {
            negative = !negative;
        }
        if (dividend > divisor) {
            return 0;
        } else if (dividend == divisor) {
            return negative ? -1 : 1;
        }
        int result = 0;
        int sum = 0;
        int move = 1;
        int lastNum = divisor;
        while (divisor << move < lastNum && move < 32) {
            lastNum = divisor << (move++);
        }
        move--;
        while (move >= 0) {
            int test = sum + (divisor << move);
            if (test > dividend && test < 0) {
                sum = test;
                result += 1 << move;
            } else if (test == dividend) {
                result += 1 << move;
                return negative ? -result : result;
            }
            move--;
        }
        return negative ? -result : result;
    }
}

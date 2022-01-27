package leetcode.from51to100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/12 17:15
 * @Description: 69. Sqrt(x) | 难度：简单 | 标签：数学、二分查找
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 提示：
 * 0 <= x <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution69 {
    @Test
    public void mySqrtTest() {
        Assert.assertEquals(2, mySqrt(4));
        Assert.assertEquals(2, mySqrt(8));
        Assert.assertEquals(10, mySqrt(101));
        Assert.assertEquals(11, mySqrt(121));
        Assert.assertEquals(10, mySqrt(120));
        Assert.assertEquals(1000, mySqrt(1_000_001));
        // 通过测试用例： 1016 / 1017
        Assert.assertEquals(46340, mySqrt(2147483647));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.59% 的用户
     * 内存消耗： 35.6 MB , 在所有 Java 提交中击败了 18.29% 的用户
     * 通过测试用例： 1017 / 1017
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 4) {
            return 1;
        }
        int test = 0;
        int delta = (int) (((long) x + 1) / 2);
        while (delta > 0) {
            long testSquare = ((long) test + delta) * (test + delta);
            if (testSquare == x) {
                return test + delta;
            } else if (testSquare < x) {
                test += delta;
            }
            if (delta > 1) {
                delta = (delta + 1) / 2;
            } else {
                delta = 0;
            }
        }
        return test;
    }
}

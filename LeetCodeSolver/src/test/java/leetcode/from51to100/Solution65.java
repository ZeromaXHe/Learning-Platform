package leetcode.from51to100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/1/14 10:55
 * @Description: 65.有效数字 | 难度：困难 | 标签：数字、字符串
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution65 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 60.87% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 43.74% 的用户
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        int eIndex = -1;
        int dotIndex = -1;
        int[] plusOrMinusIndex = new int[]{-1, -1};
        boolean[] digit = new boolean[2];
        String str = s.trim();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                if (eIndex == -1) {
                    digit[0] = true;
                } else {
                    digit[1] = true;
                }
            } else if (chars[i] == '.') {
                if (eIndex == -1) {
                    if (dotIndex != -1) {
                        return false;
                    } else {
                        dotIndex = i;
                    }
                } else {
                    return false;
                }
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                if (eIndex != -1) {
                    return false;
                }
                eIndex = i;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (i == 0) {
                    plusOrMinusIndex[0] = 0;
                } else if (eIndex == -1 || i != eIndex + 1) {
                    return false;
                } else {
                    plusOrMinusIndex[1] = i;
                }
            } else {
                return false;
            }
        }
        if (eIndex != -1) {
            if (!(digit[0] && digit[1])) {
                return false;
            }
        } else if (!(digit[0] || digit[1])) {
            return false;
        }
        return true;
    }

    @Test
    public void isNumberTest() {
        // * "0" => true
        // * " 0.1 " => true
        // * "abc" => false
        // * "1 a" => false
        // * "2e10" => true
        // * " -90e3   " => true
        // * " 1e" => false
        // * "e3" => false
        // * " 6e-1" => true
        // * " 99e2.5 " => false
        // * "53.5e93" => true
        // * " --6 " => false
        // * "-+3" => false
        // * "95a54e53" => false
        Assert.assertTrue(isNumber("0"));
        Assert.assertTrue(isNumber(" 0.1 "));
        Assert.assertFalse(isNumber("abc"));
        Assert.assertFalse(isNumber("1 a"));
        Assert.assertTrue(isNumber("2e10"));
        Assert.assertTrue(isNumber(" -90e3   "));
        Assert.assertFalse(isNumber(" 1e"));
        Assert.assertFalse(isNumber("e3"));
        Assert.assertTrue(isNumber(" 6e-1"));
        Assert.assertFalse(isNumber(" 99e2.5 "));
        Assert.assertTrue(isNumber("53.5e93"));
        Assert.assertFalse(isNumber(" --6 "));
        Assert.assertFalse(isNumber("-+3"));
        Assert.assertFalse(isNumber("95a54e53"));

        Assert.assertFalse(isNumber("."));
        Assert.assertFalse(isNumber("+."));
        Assert.assertTrue(isNumber("1E9"));
    }
}

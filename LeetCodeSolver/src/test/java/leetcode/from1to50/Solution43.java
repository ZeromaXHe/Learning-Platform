package leetcode.from1to50;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/11 18:25
 * @Description: 43. 字符串相乘 | 难度：中等 | 标签：数学、字符串、模拟
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution43 {
    @Test
    public void multiplyTest() {
        Assert.assertEquals("6", multiply2("2", "3"));
        Assert.assertEquals("56088", multiply2("123", "456"));
        Assert.assertEquals("10000", multiply2("100", "100"));
        Assert.assertEquals("0", multiply2("100", "0"));
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 48.53% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 76.05% 的用户
     * 通过测试用例： 311 / 311
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] result = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                multiply(num1, i, num2, j, result);
            }
        }
        return resultAsString(result);
    }

    private String resultAsString(int[] result) {
        StringBuilder sb = new StringBuilder();
        for (int i = result.length - 1; i > 0; i--) {
            if (result[i] / 10 > 0) {
                result[i - 1] += result[i] / 10;
            }
            result[i] %= 10;
        }
        if (result[0] != 0) {
            sb.append(result[0]);
        }
        for (int i = 1; i < result.length; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }

    private void multiply(String num1, int index1, String num2, int index2, int[] result) {
        int i1 = num1.charAt(index1) - '0';
        int i2 = num2.charAt(index2) - '0';
        int multi = i1 * i2;
        result[index1 + index2 + 1] += multi % 10;
        result[index1 + index2] += multi / 10;
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 93.99% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 41.94% 的用户
     * 通过测试用例： 311 / 311
     * <p>
     * 看样子拆分方法对耗时还是有影响啊
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] result = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int i1 = num1.charAt(i) - '0';
                int i2 = num2.charAt(j) - '0';
                int multi = i1 * i2;
                result[i + j + 1] += multi % 10;
                result[i + j] += multi / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = result.length - 1; i > 0; i--) {
            if (result[i] / 10 > 0) {
                result[i - 1] += result[i] / 10;
            }
            result[i] %= 10;
        }
        if (result[0] != 0) {
            sb.append(result[0]);
        }
        for (int i = 1; i < result.length; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }
}

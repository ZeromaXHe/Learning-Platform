package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/2/25 9:55
 * @Description: 537. 复数乘法 | 难度:中等 | 标签：数学、字符串、模拟
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * <p>
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i^2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * <p>
 * 示例 1：
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * <p>
 * 示例 2：
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * <p>
 * 提示：
 * num1 和 num2 都是有效的复数表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/complex-number-multiplication
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution537 {
    @Test
    public void complexNumberMultiplyTest() {
        // 通过测试用例： 16 / 55
        Assert.assertEquals("-1236+12152i", complexNumberMultiply("78+-76i", "-86+72i"));
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 84.80% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 31.46% 的用户
     * 通过测试用例： 55 / 55
     *
     * @param num1
     * @param num2
     * @return
     */
    public String complexNumberMultiply(String num1, String num2) {
        int[] complex1 = transStringToComplex(num1);
        int[] complex2 = transStringToComplex(num2);
        int real = complex1[0] * complex2[0] - complex1[1] * complex2[1];
        int imaginary = complex1[0] * complex2[1] + complex1[1] * complex2[0];
        return real + "+" + imaginary + "i";
    }

    private int[] transStringToComplex(String num) {
        // 虚数为 0 也一样有 +0i 格式
//        if (num.charAt(num.length() - 1) != 'i') {
//            int real = 0;
//            if (num.charAt(0) == '-') {
//                for (int i = 1; i < num.length(); i++) {
//                    real *= 10;
//                    real += num.charAt(i) - '0';
//                }
//                real = -real;
//            } else {
//                for (int i = 0; i < num.length(); i++) {
//                    real *= 10;
//                    real += num.charAt(i) - '0';
//                }
//            }
//            return new int[]{real, 0};
//        }
        int plusIndex = num.indexOf('+');
        int real = 0;
        int imaginary = 0;
        if (num.charAt(0) == '-') {
            for (int i = 1; i < plusIndex; i++) {
                real *= 10;
                real += num.charAt(i) - '0';
            }
            real = -real;
        } else {
            for (int i = 0; i < plusIndex; i++) {
                real *= 10;
                real += num.charAt(i) - '0';
            }
        }
        if (num.charAt(plusIndex + 1) == '-') {
            for (int i = plusIndex + 2; i < num.length() - 1; i++) {
                imaginary *= 10;
                imaginary += num.charAt(i) - '0';
            }
            imaginary = -imaginary;
        } else {
            for (int i = plusIndex + 1; i < num.length() - 1; i++) {
                imaginary *= 10;
                imaginary += num.charAt(i) - '0';
            }
        }
        return new int[]{real, imaginary};
    }
}

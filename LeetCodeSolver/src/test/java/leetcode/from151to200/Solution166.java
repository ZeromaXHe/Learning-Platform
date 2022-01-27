package leetcode.from151to200;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/3 17:04
 * @Description: 166.分数到小数 | 难度：中等 | 标签：哈希表、数字、字符串
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * <p>
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * <p>
 * 如果存在多个答案，只需返回 任意一个 。
 * <p>
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 * <p>
 * 示例 1：
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * <p>
 * 示例 2：
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * <p>
 * 示例 3：
 * 输入：numerator = 2, denominator = 3
 * 输出："0.(6)"
 * <p>
 * 示例 4：
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * <p>
 * 示例 5：
 * 输入：numerator = 1, denominator = 5
 * 输出："0.2"
 * <p>
 * 提示：
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution166 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 17.07% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 14.79% 的用户
     * 通过测试用例： 39 / 39
     * <p>
     * 参考了《【宫水三叶】模拟竖式计算（除法）》题解来处理循环小数部分
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        long numeratorL = numerator;
        long denominatorL = denominator;
        if (numeratorL % denominatorL == 0) {
            return String.valueOf(numeratorL / denominatorL);
        }
        numeratorL = Math.abs(numeratorL);
        denominatorL = Math.abs(denominatorL);
        StringBuilder sb = new StringBuilder();
        boolean positive = (numerator > 0 && denominator > 0) || (numerator < 0 && denominator < 0);
        if (!positive) {
            sb.append('-');
        }
        if (numeratorL > denominatorL) {
            sb.append(numeratorL / denominatorL).append('.');
            numeratorL = numeratorL % denominatorL;
        } else {
            sb.append("0.");
        }
        Map<Long, Integer> map = new HashMap<>();
        while (numeratorL != 0) {
            // 记录当前余数所在答案的位置，并继续模拟除法运算
            map.put(numeratorL, sb.length());
            numeratorL *= 10;
            sb.append(numeratorL / denominatorL);
            numeratorL %= denominatorL;
            // 如果当前余数之前出现过，则将 [出现位置 到 当前位置] 的部分抠出来（循环小数部分）
            if (map.containsKey(numeratorL)) {
                int u = map.get(numeratorL);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }
        return sb.toString();
    }
}

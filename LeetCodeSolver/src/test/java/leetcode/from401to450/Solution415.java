package leetcode.from401to450;

/**
 * @author zhuxi
 * @apiNote 415. 字符串相加 | 难度：简单 | 标签：数学、字符串、模拟
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * <p>
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 示例 1：
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * <p>
 * 示例 2：
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * <p>
 * 示例 3：
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 * <p>
 * 提示：
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/17 9:55
 */
public class Solution415 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了 98.32% 的用户
     * 通过测试用例：317 / 317
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++) {
            int sum = carry;
            if (i < num1.length()) {
                sum += num1.charAt(num1.length() - 1 - i) - '0';
            }
            if (i < num2.length()) {
                sum += num2.charAt(num2.length() - 1 - i) - '0';
            }
            carry = sum / 10;
            builder.append(sum % 10);
        }
        if (carry > 0) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }
}

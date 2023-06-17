package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 002. 二进制加法 | 难度：简单 | 标签：位运算、数学、字符串、模拟
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * 输入: a = "11", b = "10"
 * 输出: "101"
 * <p>
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 注意：本题与主站 67 题相同：https://leetcode-cn.com/problems/add-binary/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/JFETK5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/13 17:38
 */
public class SolutionOfferII2 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.61% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了 95.57% 的用户
     * 通过测试用例：294 / 294
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        int carry = 0;
        while (ia >= 0 && ib >= 0) {
            int sum = a.charAt(ia) - '0' + b.charAt(ib) - '0' + carry;
            carry = sum / 2;
            sb.append(sum % 2);
            ia--;
            ib--;
        }
        while (ia >= 0) {
            int sum = a.charAt(ia) - '0' + carry;
            carry = sum / 2;
            sb.append(sum % 2);
            ia--;
        }
        while (ib >= 0) {
            int sum = b.charAt(ib) - '0' + carry;
            carry = sum / 2;
            sb.append(sum % 2);
            ib--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}

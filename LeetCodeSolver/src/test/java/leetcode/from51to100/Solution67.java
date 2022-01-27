package leetcode.from51to100;

/**
 * @Author: zhuxi
 * @Time: 2022/1/12 17:00
 * @Description: 67. 二进制求和 | 难度：简单 | 标签：位运算、数学、字符串、模拟
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
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
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution67 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 91.09% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 19.04% 的用户
     * 通过测试用例： 294 / 294
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int carry = 0;
        while (index < a.length() && index < b.length()) {
            int sum = a.charAt(a.length() - 1 - index) - '0' + b.charAt(b.length() - 1 - index) - '0' + carry;
            carry = sum >> 1;
            sb.append(sum & 1);
            index++;
        }
        while (index < a.length()) {
            int sum = a.charAt(a.length() - 1 - index) - '0' + carry;
            carry = sum >> 1;
            sb.append(sum & 1);
            index++;
        }
        while (index < b.length()) {
            int sum = b.charAt(b.length() - 1 - index) - '0' + carry;
            carry = sum >> 1;
            sb.append(sum & 1);
            index++;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}

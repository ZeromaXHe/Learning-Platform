package leetcode.from901to950;

/**
 * @Author: zhuxi
 * @Time: 2022/2/23 10:01
 * @Description: 917. 仅仅反转字母 | 难度：简单 | 标签：双指针、字符串
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * <p>
 * 示例 1：
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * <p>
 * 示例 2：
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * <p>
 * 示例 3：
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * <p>
 * 提示
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-only-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution917 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 23.56% 的用户
     * 通过测试用例： 115 / 115
     *
     * @param s
     * @return
     */
    public String reverseOnlyLetters(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] chars = s.toCharArray();
        while (l < r) {
            while (l < chars.length && !Character.isLetter(chars[l])) {
                l++;
            }
            while (r >= 0 && !Character.isLetter(chars[r])) {
                r--;
            }
            if (l < r) {
                char c = chars[l];
                chars[l] = chars[r];
                chars[r] = c;
            }
            l++;
            r--;
        }
        return new String(chars);
    }
}

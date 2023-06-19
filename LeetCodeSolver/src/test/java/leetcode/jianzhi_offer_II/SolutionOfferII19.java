package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 019. 最多删除一个字符得到回文 | 难度：简单 | 标签：贪心、双指针、字符串
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
 * <p>
 * 示例 1:
 * 输入: s = "aba"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "abca"
 * 输出: true
 * 解释: 可以删除 "c" 字符 或者 "b" 字符
 * <p>
 * 示例 3:
 * 输入: s = "abc"
 * 输出: false
 * <p>
 * 提示:
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 * <p>
 * 注意：本题与主站 680 题相同： https://leetcode-cn.com/problems/valid-palindrome-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/RQku0D
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/19 11:09
 */
public class SolutionOfferII19 {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 57.71% 的用户
     * 内存消耗：43.1 MB, 在所有 Java 提交中击败了 36.51% 的用户
     * 通过测试用例：466 / 466
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return validPalindrome(s, l + 1, r) || validPalindrome(s, l, r - 1);
            }
        }
        return true;
    }

    private boolean validPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}

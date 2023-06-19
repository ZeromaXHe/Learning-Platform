package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 018. 有效的回文 | 难度：简单 | 标签：双指针、字符串
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 本题中，将空字符串定义为有效的 回文串 。
 * <p>
 * 示例 1:
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * <p>
 * 示例 2:
 * 输入: s = "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * <p>
 * 提示：
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 * <p>
 * 注意：本题与主站 125 题相同： https://leetcode-cn.com/problems/valid-palindrome/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/XltzEq
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/19 10:54
 */
public class SolutionOfferII18 {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 95.25% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了 89.16% 的用户
     * 通过测试用例：480 / 480
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (r > l && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

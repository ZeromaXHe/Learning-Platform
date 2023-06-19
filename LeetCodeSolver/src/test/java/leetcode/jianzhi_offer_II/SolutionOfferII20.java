package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 020. 回文子字符串的个数 | 难度：中等 | 标签：字符串、动态规划
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * <p>
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 * <p>
 * 注意：本题与主站 647 题相同：https://leetcode-cn.com/problems/palindromic-substrings/ 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/a7VOhD
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/19 11:20
 */
public class SolutionOfferII20 {
    /**
     * 执行用时：10 ms, 在所有 Java 提交中击败了 34.15% 的用户
     * 内存消耗：42.6 MB, 在所有 Java 提交中击败了 15.71% 的用户
     * 通过测试用例：130 / 130
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (i >= j - 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}

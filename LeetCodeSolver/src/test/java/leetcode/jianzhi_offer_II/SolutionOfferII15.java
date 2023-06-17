package leetcode.jianzhi_offer_II;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 015. 字符串中的所有变位词 | 难度：中等 | 标签：哈希表、字符串、滑动窗口
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 变位词 指字母相同，但排列不同的字符串。
 * <p>
 * 示例 1：
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
 * <p>
 *  示例 2：
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * 注意：本题与主站 438 题相同： https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/VabMRr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/17 18:12
 */
public class SolutionOfferII15 {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 99.76% 的用户
     * 内存消耗：42.6 MB, 在所有 Java 提交中击败了 47.44% 的用户
     * 通过测试用例：60 / 60
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        int len1 = p.length();
        int len2 = s.length();
        if (len1 > len2) {
            return result;
        }
        int[] count = new int[26];
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i = 0; i < len1; i++) {
            count[s.charAt(i) - 'a']--;
        }
        boolean all0 = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                all0 = false;
                break;
            }
        }
        if (all0) {
            result.add(0);
        }
        for (int i = len1; i < len2; i++) {
            count[s.charAt(i - len1) - 'a']++;
            count[s.charAt(i) - 'a']--;
            all0 = true;
            for (int j = 0; j < 26; j++) {
                if (count[j] != 0) {
                    all0 = false;
                    break;
                }
            }
            if (all0) {
                result.add(i - len1 + 1);
            }
        }
        return result;
    }
}

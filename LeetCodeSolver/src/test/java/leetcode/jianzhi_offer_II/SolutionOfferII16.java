package leetcode.jianzhi_offer_II;

import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 016. 不含重复字符的最长子字符串 | 难度：中等 | 标签：哈希表、字符串、滑动窗口
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * 注意：本题与主站 3 题相同： https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/wtcaE1
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/17 18:21
 */
public class SolutionOfferII16 {
    /**
     * 可以用 HashSet 简化，甚至是 boolean[128]
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了 23.29% 的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了 31.15% 的用户
     * 通过测试用例：987 / 987
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        int len = s.length();
        int from = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            count.merge(s.charAt(i), 1, Integer::sum);
            while (count.get(s.charAt(i)) > 1) {
                count.put(s.charAt(from), count.get(s.charAt(from)) - 1);
                from++;
            }
            max = Math.max(max, i + 1 - from);
        }
        return max;
    }
}

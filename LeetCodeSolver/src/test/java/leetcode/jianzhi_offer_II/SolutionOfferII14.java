package leetcode.jianzhi_offer_II;

import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 014. 字符串中的变位词 | 难度：中等 | 标签：哈希表、双指针、字符串、滑动窗口
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * <p>
 * 示例 1：
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * 示例 2：
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * 提示：
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 * <p>
 * 注意：本题与主站 567 题相同： https://leetcode-cn.com/problems/permutation-in-string/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/MPnaiL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/17 17:48
 */
public class SolutionOfferII14 {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 98.24% 的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了 82.12% 的用户
     * 通过测试用例：106 / 106
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] count = new int[26];
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i = 0; i < len1; i++) {
            count[s2.charAt(i) - 'a']--;
        }
        boolean all0 = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                all0 = false;
                break;
            }
        }
        if (all0) {
            return true;
        }
        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i - len1) - 'a']++;
            count[s2.charAt(i) - 'a']--;
            all0 = true;
            for (int j = 0; j < 26; j++) {
                if (count[j] != 0) {
                    all0 = false;
                    break;
                }
            }
            if (all0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 执行用时：31 ms, 在所有 Java 提交中击败了 14.42% 的用户
     * 内存消耗：43.1 MB, 在所有 Java 提交中击败了 5.01% 的用户
     * 通过测试用例：106 / 106
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion_hashMap(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] count = new int[26];
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            map.merge(s2.charAt(i), 1, Integer::sum);
        }
        if (map.entrySet().stream().allMatch(e -> count[e.getKey() - 'a'] == e.getValue())) {
            return true;
        }
        for (int i = len1; i < len2; i++) {
            char removeKey = s2.charAt(i - len1);
            if (map.get(removeKey) == 1) {
                map.remove(removeKey);
            } else {
                map.put(removeKey, map.get(removeKey) - 1);
            }
            map.merge(s2.charAt(i), 1, Integer::sum);
            if (map.entrySet().stream().allMatch(e -> count[e.getKey() - 'a'] == e.getValue())) {
                return true;
            }
        }
        return false;
    }
}

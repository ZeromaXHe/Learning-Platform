package leetcode.first100;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2021/1/15 14:09
 * @Description: 87.扰乱字符串 | 难度：困难 | 标签：字符串、动态规划
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * <p>
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 * <p>
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *           / \
 *          a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * <p>
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 * <p>
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *           / \
 *          a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * <p>
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 * <p>
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *       / \
 *      t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * <p>
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * <p>
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/scramble-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution87 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 98.61% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 18.53% 的用户
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        for (int i = 1; i < s1.length(); i++) {
            // 本轮迭代不扰乱
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            // 本轮迭代进行扰乱
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

    HashMap<String, Boolean> map = new HashMap<>();

    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 53.80% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 18.53% 的用户
     * 用map缓存了结果，但实际上感觉没必要，速度还慢了
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble_mapped(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        String key = s1 + "#" + s2;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                map.put(key, false);
                return false;
            }
        }
        for (int i = 1; i < s1.length(); i++) {
            // 本轮迭代不扰乱
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                map.put(key, true);
                return true;
            }
            // 本轮迭代进行扰乱
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                map.put(key, true);
                return true;
            }
        }
        map.put(key, false);
        return false;
    }
}

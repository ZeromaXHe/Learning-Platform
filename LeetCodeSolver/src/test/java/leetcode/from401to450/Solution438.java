package leetcode.from401to450;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/6/2 15:38
 * @Description: 438. 找到字符串中所有字母异位词 | 难度：中等 | 标签：哈希表、字符串、滑动窗口
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution438 {
    @Test
    public void findAnagramsTest() {
        // [0, 6]
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        // [1]
        System.out.println(findAnagrams("baa", "aa"));
    }

    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 86.19% 的用户
     * 内存消耗： 42.5 MB , 在所有 Java 提交中击败了 31.47% 的用户
     * 通过测试用例： 61 / 61
     * <p>
     * 最快的方法是在每次 s 的滑动窗口循环过程中，保证当前字符在两个数组的值相等，然后检查 s 窗口长度是否等于 p 的长度。这个 5 ms
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int pLen = p.length();
        int sLen = s.length();
        List<Integer> result = new LinkedList<>();
        if (sLen < pLen) {
            return result;
        }
        int[] pArr = new int[26];
        int[] sArr = new int[26];
        for (char c : p.toCharArray()) {
            pArr[c - 'a']++;
        }
        for (int i = 0; i < pLen; i++) {
            sArr[s.charAt(i) - 'a']++;
        }
        for (int i = pLen; i < sLen; i++) {
            if (Arrays.equals(pArr, sArr)) {
                result.add(i - pLen);
            }
            sArr[s.charAt(i) - 'a']++;
            sArr[s.charAt(i - pLen) - 'a']--;
        }
        if (Arrays.equals(pArr, sArr)) {
            result.add(sLen - pLen);
        }
        return result;
    }

    /**
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 43.01% 的用户
     * 内存消耗： 42.2 MB , 在所有 Java 提交中击败了 73.38% 的用户
     * 通过测试用例： 61 / 61
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams_oneArray(String s, String p) {
        int pLen = p.length();
        int sLen = s.length();
        List<Integer> result = new LinkedList<>();
        if (sLen < pLen) {
            return result;
        }
        int[] count = new int[26];
        int diff = 0;
        for (char c : p.toCharArray()) {
            if (count[c - 'a'] == 0) {
                diff++;
            }
            count[c - 'a']++;
            if (count[c - 'a'] == 0) {
                diff--;
            }
        }
        for (int i = 0; i < pLen; i++) {
            if (count[s.charAt(i) - 'a'] == 0) {
                diff++;
            }
            count[s.charAt(i) - 'a']--;
            if (count[s.charAt(i) - 'a'] == 0) {
                diff--;
            }
        }
        for (int i = pLen; i < sLen; i++) {
            if (diff == 0) {
                result.add(i - pLen);
            }
            if (count[s.charAt(i) - 'a'] == 0) {
                diff++;
            }
            count[s.charAt(i) - 'a']--;
            if (count[s.charAt(i) - 'a'] == 0) {
                diff--;
            }
            if (count[s.charAt(i - pLen) - 'a'] == 0) {
                diff++;
            }
            count[s.charAt(i - pLen) - 'a']++;
            if (count[s.charAt(i - pLen) - 'a'] == 0) {
                diff--;
            }
        }
        if (diff == 0) {
            result.add(sLen - pLen);
        }
        return result;
    }

    /**
     * 执行用时： 50 ms , 在所有 Java 提交中击败了 18.27% 的用户
     * 内存消耗： 42.8 MB , 在所有 Java 提交中击败了 5.04% 的用户
     * 通过测试用例： 61 / 61
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams_hashMap(String s, String p) {
        int pLen = p.length();
        int sLen = s.length();
        List<Integer> result = new LinkedList<>();
        if (sLen < pLen) {
            return result;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < pLen; i++) {
            window.put(s.charAt(i), window.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = pLen; i < sLen; i++) {
            if (map.equals(window)) {
                result.add(i - pLen);
            }
            int newValue = window.getOrDefault(s.charAt(i), 0) + 1;
            if (newValue == 0) {
                window.remove(s.charAt(i));
            } else {
                window.put(s.charAt(i), newValue);
            }
            int oldValue = window.getOrDefault(s.charAt(i - pLen), 0) - 1;
            if (oldValue == 0) {
                window.remove(s.charAt(i - pLen));
            } else {
                window.put(s.charAt(i - pLen), oldValue);
            }
        }
        if (map.equals(window)) {
            result.add(sLen - pLen);
        }
        return result;
    }
}

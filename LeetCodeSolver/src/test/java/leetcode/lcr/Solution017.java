package leetcode.lcr;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author zhuxi
 * @apiNote LCR 017. 最小覆盖子串 | 难度：困难 | 标签：哈希表、字符串、滑动窗口
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * <p>
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * <p>
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出：""
 * 解释：t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * 注意：本题与主站 76 题相似（本题答案不唯一）：https://leetcode-cn.com/problems/minimum-window-substring/
 * @implNote
 * @since 2023/11/29 16:15
 */
public class Solution017 {
    /**
     * 执行用时分布 9 ms
     * 击败 71.38% 使用 Java 的用户
     * 消耗内存分布 42.65 MB
     * 击败 61.89% 使用 Java 的用户
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int tLen = t.length();
        int sLen = s.length();
        if (tLen > sLen) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        int l = 0;
        while (l < sLen && !map.containsKey(s.charAt(l))) {
            l++;
        }
        if (sLen - l < tLen) {
            return "";
        }
        String result = "";
        int r = l;
        HashSet<Character> checkSet = new HashSet<>(map.keySet());
        while (r < sLen) {
            while (r < sLen && !checkSet.isEmpty()) {
                char rc = s.charAt(r);
                if (map.containsKey(rc)) {
                    int value = map.get(rc) - 1;
                    map.put(rc, value);
                    if (value == 0) {
                        checkSet.remove(rc);
                    }
                }
                r++;
            }
            if (r == sLen && !checkSet.isEmpty()) {
                return result;
            }
            while (checkSet.isEmpty()) {
                char lc = s.charAt(l);
                if (map.containsKey(lc)) {
                    int value = map.get(lc) + 1;
                    map.put(lc, value);
                    if (value == 1) {
                        checkSet.add(lc);
                        if ("".equals(result) || r - l < result.length()) {
                            result = s.substring(l, r);
                        }
                    }
                }
                l++;
            }
        }
        return result;
    }
}

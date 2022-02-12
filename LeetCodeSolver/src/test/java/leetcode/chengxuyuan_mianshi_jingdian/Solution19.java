package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/2/11 10:14
 * @Description: 面试题19. 正则表达式匹配 | 难度：困难 | 标签：递归、字符串、动态规划
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * <p>
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution19 {
    @Test
    public void isMatchTest() {
        Assert.assertTrue(isMatch("aa", "a*"));
        Assert.assertTrue(isMatch("ab", ".*"));
        Assert.assertTrue(isMatch("aab", "c*a*b"));
        Assert.assertFalse(isMatch("mississippi", "mis*is*p*."));
        // 通过测试用例： 432 / 448
        Assert.assertTrue(isMatch("aaa", "a*a"));
        // 通过测试用例： 439 / 448
        Assert.assertFalse(isMatch("aaba", "ab*a*c*a"));
        // 通过测试用例： 43 / 448
        Assert.assertTrue(isMatch("a", "ab*"));
    }

    /**
     * NfaNode 的 next Map 的 key 使用 char 会更快：
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 89.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 5.03% 的用户
     * 通过测试用例： 448 / 448
     * <p>
     * 使用 String 的话，多了拼接字符串的时间，比较慢：
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 19.70% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 5.03% 的用户
     * 通过测试用例： 448 / 448
     * <p>
     * 题解使用的动态规划，我使用的自动机（不过感觉实现的不太标准，其实估计NFA都谈不上，就这样命名了）
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        NfaNode node = formDfa(p);
        return isMatch(s, 0, node, null);
    }

    private boolean isMatch(String s, int i, NfaNode node, NfaTrans pre) {
        if (i == s.length()) {
            return node.end;
        }
        NfaTrans next = node.next.get(s.charAt(i));
        NfaTrans nextStar = node.next.get(Character.toUpperCase(s.charAt(i)));
        NfaTrans nextDot = node.next.get('.');
        NfaTrans nextDotStar = node.next.get('*');
        if (next != null && (pre == null || pre.node != next.node || pre.sequence <= next.sequence)
                && isMatch(s, i + 1, next.node, null)) {
            return true;
        }
        if (nextStar != null && (pre == null || pre.node != nextStar.node || pre.sequence <= nextStar.sequence)
                && isMatch(s, i + 1, nextStar.node, nextStar)) {
            return true;
        }
        if (nextDot != null && (pre == null || pre.node != nextDot.node || pre.sequence <= nextDot.sequence)
                && isMatch(s, i + 1, nextDot.node, null)) {
            return true;
        }
        if (nextDotStar != null && (pre == null || pre.node != nextDotStar.node || pre.sequence <= nextDotStar.sequence)
                && isMatch(s, i + 1, nextDotStar.node, nextDotStar)) {
            return true;
        }
        return false;
    }

    private NfaNode formDfa(String p) {
        NfaNode start = new NfaNode();
        NfaNode node = start;
        int seq = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                char key;
                if (p.charAt(i) == '.') {
                    // * 表示匹配零到n个任意字符
                    key = '*';
                } else {
                    // 大写字符表示匹配零到n个对应小写字母
                    key = Character.toUpperCase(p.charAt(i));
                }
                node.next.put(key, new NfaTrans(seq++, node));
                i++;
            } else {
                NfaNode temp = new NfaNode();
                node.next.put(p.charAt(i), new NfaTrans(seq, temp));
                seq = 0;
                node = temp;
            }
        }
        node.end = true;
        return start;
    }

    /**
     * DFA：确定的有穷自动机
     * NFA：不确定的有穷自动机
     */
    static class NfaNode {
        boolean end;
        Map<Character, NfaTrans> next = new HashMap<>();
    }

    static class NfaTrans {
        int sequence;
        NfaNode node;

        NfaTrans(int sequence, NfaNode node) {
            this.sequence = sequence;
            this.node = node;
        }
    }

    /**
     * 超时
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_overtime(String s, String p) {
        List<Character> node = formPList(p);
        return isMatch_overtime(s, 0, node, 0);
    }

    private boolean isMatch_overtime(String s, int i, List<Character> pList, int listIndex) {
        if (i == s.length()) {
            while (listIndex < pList.size() && (pList.get(listIndex) == '*' || Character.isUpperCase(pList.get(listIndex)))) {
                listIndex++;
            }
            return listIndex == pList.size();
        }
        if (listIndex >= pList.size()) {
            return false;
        }
        char match = pList.get(listIndex);
        if ((match == s.charAt(i) || match == '.') && isMatch_overtime(s, i + 1, pList, listIndex + 1)) {
            return true;
        }
        if ((match == Character.toUpperCase(s.charAt(i)) || match == '*')
                && (isMatch_overtime(s, i + 1, pList, listIndex)
                || isMatch_overtime(s, i + 1, pList, listIndex + 1)
                || isMatch_overtime(s, i, pList, listIndex + 1))) {
            return true;
        }
        return Character.isUpperCase(match) && isMatch_overtime(s, i, pList, listIndex + 1);
    }

    private List<Character> formPList(String p) {
        List<Character> pList = new ArrayList<>(p.length());
        for (int i = 0; i < p.length(); i++) {
            if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                if (p.charAt(i) == '.') {
                    // * 表示匹配零到n个任意字符
                    pList.add('*');
                } else {
                    // 大写字符表示匹配零到n个对应小写字母
                    pList.add(Character.toUpperCase(p.charAt(i)));
                }
                i++;
            } else {
                pList.add(p.charAt(i));
            }
        }
        return pList;
    }
}

package leetcode.from1to50;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/8 11:37
 * @Description: 44.通配符匹配 | 难度：困难 | 标签：贪心算法、字符串、动态规划、回溯算法
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * <p>
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * <p>
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution44 {
    /**
     * 执行用时： 11 ms , 在所有 Java 提交中击败了 91.16% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 81.90% 的用户
     * 基于贪心写的
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        List<String> pWords = dealP(p);
        int index = 0;
        boolean preStar = false;
        boolean hasStar = false;
        for (String word : pWords) {
            if (!"*".equals(word)) {
                int preIndex = index;
//                if ("?".equals(word)) {
//                    index = (index + 1 > s.length()) ? -1 : index;
//                } else {
//                    index = s.indexOf(word, index);
//                }
                index = customisedIndexOf(s, word, index);
                if (index < 0) {
                    return false;
                } else if (preStar || index == preIndex) {
                    index += word.length();
                } else {
                    return false;
                }
                preStar = false;
            } else {
                preStar = true;
                hasStar = true;
            }
        }
        if (preStar) {
            return true;
        } else {
            if (index == s.length()) {
                return true;
            } else if (!hasStar) {
                return false;
            } else {
                String s1 = pWords.get(pWords.size() - 1);
                return customisedIndexOf(s, s1, s.length() - s1.length()) >= 0;
            }
        }
    }

    /**
     * 自定义可以处理.正则的indexOf
     *
     * @param s
     * @param word
     * @param index
     * @return
     */
    private int customisedIndexOf(String s, String word, int index) {
        boolean noDot = true;
        for (char c : word.toCharArray()) {
            if (c == '.') {
                noDot = false;
                break;
            }
        }
        if (noDot) {
            return s.indexOf(word, index);
        } else {
            /// 力扣用不了Pattern类……
//            Pattern pattern = Pattern.compile(word);
//            Matcher matcher = pattern.matcher(s);
//            if (matcher.find(index)) {
//                return matcher.start();
//            } else {
//                return -1;
//            }
            /// 自定义正则表达式解析代码
            if (index + word.length() > s.length()) {
                return -1;
            }
            List<String> list = dealWord(word);
            return customisedIndexOf(s, list, index, word.length());
        }
    }

    @Test
    public void customisedIndexOfTest() {
        System.out.println(customisedIndexOf("abcabczzzde", "abc...de", 0));
    }

    /**
     * 预处理word，变成字母组成的单词和中间间隔的.个数组成的list
     *
     * @param word
     * @return
     */
    private List<String> dealWord(String word) {
        int countDot = 0;
        List<String> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (c == '.') {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb.setLength(0);
                }
                countDot++;
            } else {
                if (countDot > 0) {
                    list.add("" + countDot);
                    countDot = 0;
                }
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
        } else {
            if (countDot > 0) {
                list.add("" + countDot);
            }
        }
        return list;
    }

    private int customisedIndexOf(String s, List<String> list, int index, int length) {
        if (Character.isDigit(list.get(0).charAt(0))) {
            int first = Integer.parseInt(list.get(0));
            if (list.size() == 1) {
                return index;
            }
            if (list.size() == 2) {
                return s.indexOf(list.get(1), index + first) - first;
            }
            list.remove(0);
            return customisedIndexOf(s, list, index + first, length - first) - first;
        }
        // 开头一定是单词了
        String first = list.get(0);
        int pos = index - 1;
        while ((pos = s.indexOf(first, pos + 1)) != -1 && pos <= s.length() - length) {
            if (testPos(s, list, pos)) {
                return pos;
            }
        }
        return -1;
    }

    private boolean testPos(String s, List<String> list, int pos) {
        Iterator<String> iterator = list.iterator();
        // list的iterator索引位置
        int i = 0;
        // s匹配到的索引位置
        int index = pos;
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (i % 2 == 0) {
                // str是单词
                if (!str.equals(s.substring(index, index + str.length()))) {
                    return false;
                } else {
                    index += str.length();
                }
            } else {
                // str是代表.个数的数字
                index += Integer.parseInt(str);
                if (index > s.length()) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    @Test
    public void regexMatcherTest() {
        Pattern pattern = Pattern.compile("a..b");
        Matcher matcher = pattern.matcher("csdfaabbbabkbabab");
        Matcher matcher2 = pattern.matcher("csdf");
        if (matcher.find(0)) {
            System.out.println(matcher.start());
        } else {
            System.out.println(-1);
        }
        if (matcher2.find(0)) {
            System.out.println(matcher2.start());
        } else {
            System.out.println(-1);
        }
    }

    @Test
    public void stringSplitTest() {
        String s = "..123.23.....244";
        String[] split = s.split("\\.");
        // ""
        System.out.println(split[0]);
        // false
        System.out.println(split[0] == null);
        // true
        System.out.println("".equals(split[0]));
        // [, , 123, 23, , , , , 244]
        System.out.println(Arrays.toString(split));
    }

    private List<String> dealP(String p) {
        StringBuilder sb = new StringBuilder();
        List<String> list = new LinkedList<>();
        boolean preStar = false;
        for (char c : p.toCharArray()) {
            if (c == '*') {
                if (!preStar && sb.length() > 0) {
                    list.add(sb.toString());
                    sb.setLength(0);
                }
                preStar = true;
            } else {
                if (preStar) {
                    list.add("*");
                    preStar = false;
                }
                if (c == '?') {
                    /// ？必须和字母联系在一起匹配，不然出现字母+？+字母的情况就可能会导致贪心贪错了的情况
//                    if (sb.length() > 0) {
//                        list.add(sb.toString());
//                        sb.setLength(0);
//                    }
//                    list.add("?");
                    // 自己实现实在太恶心了，直接用正则吧……
                    sb.append('.');
                } else {
                    sb.append(c);
                }
            }
        }
        // 下面两个情况只可能执行其一
        if (preStar) {
            list.add("*");
        } else if (sb.length() > 0) {
            list.add(sb.toString());
        }
        return list;
    }

    @Test
    public void charStringTest() {
        String s = "" + 'c';
        // "c"
        System.out.println(s);
    }

    @Test
    public void isMatchTest() {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        System.out.println("test1");
        Assert.assertTrue(isMatch("aa", "*"));
        long timestamp2 = System.currentTimeMillis();
        System.out.println(timestamp2 - timestamp);
        timestamp = timestamp2;
        System.out.println("test2");
        Assert.assertFalse(isMatch("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab", "b*b*ab**ba*b**b***bba"));
        timestamp2 = System.currentTimeMillis();
        System.out.println(timestamp2 - timestamp);
        timestamp = timestamp2;
        System.out.println("test3");
        Assert.assertFalse(isMatch("abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb",
                "ab*aaba**abbaaaa**b*b****aa***a*b**ba*a**ba*baaa*b*ab*"));
        timestamp2 = System.currentTimeMillis();
        System.out.println(timestamp2 - timestamp);
        timestamp = timestamp2;
        System.out.println("test4");
        // 1710 / 1811 个通过测试用例
        Assert.assertFalse(isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
        timestamp2 = System.currentTimeMillis();
        System.out.println(timestamp2 - timestamp);

        System.out.println("test5");
        Assert.assertFalse(isMatch("acdcbmissi", "a*c?b********"));
        System.out.println("test6");
        Assert.assertFalse(isMatch("aa", "a"));
        System.out.println("test7");
        Assert.assertFalse(isMatch("cb", "?a"));
        System.out.println("test8");
        Assert.assertTrue(isMatch("adceb", "a*b"));
        System.out.println("test9");
        // 1488 / 1811 个通过测试用例
        Assert.assertTrue(isMatch("ab", "?*"));
        System.out.println("test10");
        // 1627 / 1811 个通过测试用例
        Assert.assertTrue(isMatch("abcabczzzde", "*abc???de*"));
        System.out.println("test11");
        // 1635 / 1811 个通过测试用例
        Assert.assertTrue(isMatch("aaaa", "***a"));
    }

    public boolean isMatch_oneCharPerRecur(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        return isMatchRecursive_oneCharPerRecur(sChars, 0, sChars.length, pChars, 0, pChars.length);
    }

    /**
     * 一次迭代只检查一个字符，太慢了
     *
     * @param sChars
     * @param startS
     * @param endS
     * @param pChars
     * @param startP
     * @param endP
     * @return
     */
    private boolean isMatchRecursive_oneCharPerRecur(char[] sChars, int startS, int endS, char[] pChars, int startP, int endP) {
        if (startS >= endS) {
            if (startP >= endP) {
                return true;
            }
            int j = startP;
            while (j < endP) {
                if (pChars[j++] != '*') {
                    return false;
                }
            }
            return true;
        }

        if (startP < endP) {
            if (pChars[startP] == '*') {
                // 合并多个*的情况
                while (startP + 1 < endP && pChars[startP + 1] == '*') {
                    startP++;
                }
                if (startP == endP - 1) {
                    return true;
                }
                // 先考虑能不能从后往前推，节省效率，毕竟*的推断涉及回溯，太慢了。
                if (pChars[endP - 1] == '?') {
                    return isMatchRecursive_oneCharPerRecur(sChars, startS, endS - 1, pChars, startP, endP - 1);
                } else if (pChars[endP - 1] != '*') {
                    if (sChars[endS - 1] == pChars[endP - 1]) {
                        return isMatchRecursive_oneCharPerRecur(sChars, startS, endS - 1, pChars, startP, endP - 1);
                    } else {
                        return false;
                    }
                }
                // 回溯法判断*匹配不同数量的字符能否匹配
                int test = 0;
                while (startS + test < endS && !isMatchRecursive_oneCharPerRecur(sChars, startS + test, endS, pChars, startP + 1, endP)) {
                    test++;
                }
                return startS + test < endS;
            } else if (pChars[startP] == '?') {
                return isMatchRecursive_oneCharPerRecur(sChars, startS + 1, endS, pChars, startP + 1, endP);
            } else {
                if (sChars[startS] != pChars[startP]) {
                    return false;
                } else {
                    return isMatchRecursive_oneCharPerRecur(sChars, startS + 1, endS, pChars, startP + 1, endP);
                }
            }
        } else {
            return false;
        }
    }

    @Test
    public void isMatch_oneCharPerRecurTest() {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        Assert.assertTrue(isMatch_oneCharPerRecur("aa", "*"));
        long timestamp2 = System.currentTimeMillis();
        System.out.println(timestamp2 - timestamp);
        timestamp = timestamp2;
        Assert.assertFalse(isMatch_oneCharPerRecur("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab", "b*b*ab**ba*b**b***bba"));
        timestamp2 = System.currentTimeMillis();
        System.out.println(timestamp2 - timestamp);
        timestamp = timestamp2;
        Assert.assertFalse(isMatch_oneCharPerRecur("abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb",
                "ab*aaba**abbaaaa**b*b****aa***a*b**ba*a**ba*baaa*b*ab*"));
        timestamp2 = System.currentTimeMillis();
        System.out.println(timestamp2 - timestamp);
        timestamp = timestamp2;
        // 19s!!!
//        Assert.assertFalse(isMatch_oneCharPerRecur("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
//                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
//        timestamp2 = System.currentTimeMillis();
//        System.out.println(timestamp2 - timestamp);
    }
}

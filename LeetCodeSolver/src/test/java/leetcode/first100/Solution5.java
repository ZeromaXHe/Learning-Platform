package leetcode.first100;

import com.zerox.entity.Tester;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2020/11/10 14:07
 * @Description: 5. 最长回文子串 | 难度：中等 | 标签：字符串、动态规划
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution5 {
    /**
     * 执行用时： 17 ms, 在所有 Java 提交中击败了 89.93% 的用户
     * 内存消耗： 38.8 MB, 在所有 Java 提交中击败了 66.14% 的用户
     * 主要注意要从中心向外找最大，而不是从两头往中间找。最一开始思路不对，从两侧开始比对，导致算法很慢。
     * 进阶的话，有时间可以复习一下马拉车（Manacher）算法。
     * （简单来说，就是在每个字符间插入#的基础上，记录一个辅助数组，把前面计算的最大回文数记录下来，之后再计算其他中心时就可以利用到前面的这些？）
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }
        char[] chars = s.toCharArray();
        String singularLen = longestPalindromeWithSingularLen(chars);
        String doubleLen = longestPalindromeWithDoubleLen(chars);
        if (doubleLen.length() > singularLen.length()) {
            return doubleLen;
        } else {
            return singularLen;
        }
    }

    /**
     * 寻找奇数长度的回文
     *
     * @param chars 原字符串对应字符数组
     * @return
     */
    private String longestPalindromeWithSingularLen(char[] chars) {
        int leftIndex = -1;
        int maxLen = -1;
        for (int centerIndex = 0; centerIndex < chars.length; centerIndex++) {
            int len = -1;
            int halfLen = 0;
            while (centerIndex - halfLen >= 0 && centerIndex + halfLen < chars.length) {
                if (chars[centerIndex - halfLen] == chars[centerIndex + halfLen]) {
                    halfLen++;
                    len += 2;
                } else {
                    break;
                }
            }
            if (len > maxLen) {
                maxLen = len;
                leftIndex = centerIndex - halfLen + 1;
            }
        }
        return new String(Arrays.copyOfRange(chars, leftIndex, leftIndex + maxLen));
    }

    /**
     * 寻找偶数长度的回文
     * <p>
     * 我们可以通过一个特别的操作将奇偶数的情况统一起来：
     * 我们向字符串的头尾以及每两个字符中间添加一个特殊字符 #，比如字符串 aaba 处理后会变成 #a#a#b#a#。
     * 那么原先长度为偶数的回文字符串 aa 会变成长度为奇数的回文字符串 #a#a#，
     * 而长度为奇数的回文字符串 aba 会变成长度仍然为奇数的回文字符串 #a#b#a#，
     * 我们就不需要再考虑长度为偶数的回文字符串了。
     *
     * @param chars 原字符串对应字符数组
     * @return
     */
    private String longestPalindromeWithDoubleLen(char[] chars) {
        int leftIndex = -1;
        int maxLen = 0;
        for (int ctrLeftIndex = 0; ctrLeftIndex < chars.length; ctrLeftIndex++) {
            int len = 0;
            int halfLen = 0;
            while (ctrLeftIndex - halfLen >= 0 && ctrLeftIndex + halfLen + 1 < chars.length) {
                if (chars[ctrLeftIndex - halfLen] == chars[ctrLeftIndex + halfLen + 1]) {
                    halfLen++;
                    len += 2;
                } else {
                    break;
                }
            }
            if (len > maxLen) {
                maxLen = len;
                leftIndex = ctrLeftIndex - halfLen + 1;
            }
        }

        if (maxLen == 0) {
            return "";
        } else {
            return new String(Arrays.copyOfRange(chars, leftIndex, leftIndex + maxLen));
        }
    }

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        Tester<String, String> tester = new Tester<>();
        tester.addCase("babad", "bab", "aba");
        tester.addCase("cbbd", "bb");
        tester.setFunction(solution5::longestPalindrome);
        System.out.println(tester.testAll());
    }
}
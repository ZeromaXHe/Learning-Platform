package leetcode.from301to350;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/20 12:10
 * @Description: 316. 去除重复字母 | 难度：中等 | 标签：栈、贪心算法、字符串
 * @Modified By: ZeromaXHe
 */
public class Solution316 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 97.17% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 98.73% 的用户
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        boolean[] exist = new boolean[26];
        int[] remainingCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            remainingCount[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!exist[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    // 最后一个字符比新加入的字符大
                    if (remainingCount[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        // 最后一个字符有多个的话，就弹出最后一个字符
                        exist[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        // 否则保持现状
                        break;
                    }
                }
                exist[ch - 'a'] = true;
                sb.append(ch);
            }
            remainingCount[ch - 'a']--;
        }
        return sb.toString();
    }
}

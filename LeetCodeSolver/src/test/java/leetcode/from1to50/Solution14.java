package leetcode.from1to50;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/23 14:55
 * @Description: 14. 最长公共前缀 | 难度：简单 | 标签：字符串
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * <p>
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution14 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 87.11% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 72.18% 的用户
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String s : strs) {
            if (s.length() < minLen) {
                minLen = s.length();
            }
        }
        int i = 0;
        while (i < minLen) {
            char c = '#';
            for (String s : strs) {
                if (c == '#') {
                    c = s.charAt(i);
                } else if (c != s.charAt(i)) {
                    if (i == 0) {
                        return "";
                    } else {
                        return s.substring(0, i);
                    }
                }
            }
            i++;
        }
        return strs[0].substring(0, minLen);
    }
}

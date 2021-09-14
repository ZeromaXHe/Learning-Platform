package leetcode.from501to600;

import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/9/14 9:47
 * @Description: 524. 通过删除字母匹配到字典里最长单词 | 难度：中等 | 标签：数组、双指针、字符串、排序
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * <p>
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 * 示例 1：
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * <p>
 * 示例 2：
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution524 {
    /**
     * 执行用时： 20 ms , 在所有 Java 提交中击败了 58.99% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 98.51% 的用户
     * 通过测试用例： 31 / 31
     *
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        String longestWord = "";
        for (String dict : dictionary) {
            boolean lengthAndSortSatisfied = dict.length() > longestWord.length()
                    || (dict.length() == longestWord.length() && dict.compareTo(longestWord) < 0);
            if (lengthAndSortSatisfied && formable(s, dict)) {
                longestWord = dict;
            }
        }
        return longestWord;
    }

    private boolean formable(String s, String dict) {
        int sIndex = 0;
        int dictIndex = 0;
        while (sIndex < s.length() && dictIndex < dict.length()) {
            if (s.charAt(sIndex) == dict.charAt(dictIndex)) {
                dictIndex++;
            }
            sIndex++;
        }
        return dictIndex == dict.length();
    }
}

package leetcode.from5601to5700;

import java.util.HashSet;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/12 22:31
 * @Description: 5609. 统计一致字符串的数目 | 难度：简单
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致 字符串。
 * <p>
 * 请你返回 words 数组中 一致 字符串的数目。
 * <p>
 * 示例 1：
 * 输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * 输出：2
 * 解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
 * <p>
 * 示例 2：
 * 输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * 输出：7
 * 解释：所有字符串都是一致的。
 * <p>
 * 示例 3：
 * 输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
 * 输出：4
 * 解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
 *  
 * 提示：
 * 1 <= words.length <= 104
 * 1 <= allowed.length <= 26
 * 1 <= words[i].length <= 10
 * allowed 中的字符 互不相同 。
 * words[i] 和 allowed 只包含小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-the-number-of-consistent-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5609 {
    /**
     * 74 / 74 个通过测试用例
     * 状态：通过
     * 执行用时: 13 ms
     * 内存消耗: 39.3 MB
     *
     * @param allowed
     * @param words
     * @return
     */
    public int countConsistentStrings(String allowed, String[] words) {
        HashSet<Character> allowedSet = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            allowedSet.add(c);
        }
        int count = 0;
        for (String word : words) {
            if (verifyWord(word, allowedSet)) {
                count++;
            }
        }
        return count;
    }

    private boolean verifyWord(String word, HashSet<Character> allowed) {
        for (char c : word.toCharArray()) {
            if (!allowed.contains(c)) {
                return false;
            }
        }
        return true;
    }
}

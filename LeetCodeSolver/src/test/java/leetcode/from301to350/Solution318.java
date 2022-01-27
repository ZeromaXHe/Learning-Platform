package leetcode.from301to350;

/**
 * @Author: zhuxi
 * @Time: 2021/11/17 10:22
 * @Description: 318. 最大单词长度乘积 | 难度：中等 | 标签：位运算、数组、字符串
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * <p>
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * <p>
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * <p>
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 * <p>
 * 提示：
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution318 {
    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 93.50% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 62.33% 的用户
     * 通过测试用例： 167 / 167
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int[] bit = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                bit[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < bit.length; i++) {
            for (int j = i + 1; j < bit.length; j++) {
                if ((bit[i] & bit[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}

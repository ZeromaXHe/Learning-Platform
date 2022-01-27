package leetcode.from5551to5600;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/28 22:30
 * @Description: 5557. 最大重复子字符串
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 * <p>
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：sequence = "ababc", word = "ab"
 * 输出：2
 * 解释："abab" 是 "ababc" 的子字符串。
 * 示例 2：
 * <p>
 * 输入：sequence = "ababc", word = "ba"
 * 输出：1
 * 解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
 * 示例 3：
 * <p>
 * 输入：sequence = "ababc", word = "ac"
 * 输出：0
 * 解释："ac" 不是 "ababc" 的子字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= sequence.length <= 100
 * 1 <= word.length <= 100
 * sequence 和 word 都只包含小写英文字母。
 * @Modified By: ZeromaXHe
 */
public class Solution5557 {
    /**
     * 210 / 210 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 37 MB
     *
     * @param sequence
     * @param word
     * @return
     */
    public int maxRepeating(String sequence, String word) {
        int i = 0;
        int count = 0;
        int max = 0;
        int wordLen = word.length();
        int seqLen = sequence.length();
        while (i < seqLen && (i = sequence.indexOf(word, i)) != -1) {
            while (i + wordLen <= seqLen && sequence.substring(i, i + wordLen).equals(word)) {
                count++;
                i += wordLen;
            }
            if (count > max) {
                max = count;
            }
            count = 0;
            i++;
        }
        return max;
    }

    // case 1:
    //"baba"
    //"b"
    // 预期：1

    // case 2:
    // "a"
    // "a"
    // 预期：1
}

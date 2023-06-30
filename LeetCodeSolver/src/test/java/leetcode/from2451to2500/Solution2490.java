package leetcode.from2451to2500;

/**
 * @author zhuxi
 * @apiNote 2490. 回环句 | 难度：简单 | 标签：字符串
 * 句子 是由单个空格分隔的一组单词，且不含前导或尾随空格。
 * <p>
 * 例如，"Hello World"、"HELLO"、"hello world hello world" 都是符合要求的句子。
 * 单词 仅 由大写和小写英文字母组成。且大写和小写字母会视作不同字符。
 * <p>
 * 如果句子满足下述全部条件，则认为它是一个 回环句 ：
 * <p>
 * 单词的最后一个字符和下一个单词的第一个字符相等。
 * 最后一个单词的最后一个字符和第一个单词的第一个字符相等。
 * 例如，"leetcode exercises sound delightful"、"eetcode"、"leetcode eats soul" 都是回环句。然而，"Leetcode is cool"、"happy Leetcode"、"Leetcode" 和 "I like Leetcode" 都 不 是回环句。
 * <p>
 * 给你一个字符串 sentence ，请你判断它是不是一个回环句。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：sentence = "leetcode exercises sound delightful"
 * 输出：true
 * 解释：句子中的单词是 ["leetcode", "exercises", "sound", "delightful"] 。
 * - leetcode 的最后一个字符和 exercises 的第一个字符相等。
 * - exercises 的最后一个字符和 sound 的第一个字符相等。
 * - sound 的最后一个字符和 delightful 的第一个字符相等。
 * - delightful 的最后一个字符和 leetcode 的第一个字符相等。
 * 这个句子是回环句。
 * <p>
 * 示例 2：
 * 输入：sentence = "eetcode"
 * 输出：true
 * 解释：句子中的单词是 ["eetcode"] 。
 * - eetcode 的最后一个字符和 eetcode 的第一个字符相等。
 * 这个句子是回环句。
 * <p>
 * 示例 3：
 * 输入：sentence = "Leetcode is cool"
 * 输出：false
 * 解释：句子中的单词是 ["Leetcode", "is", "cool"] 。
 * - Leetcode 的最后一个字符和 is 的第一个字符 不 相等。
 * 这个句子 不 是回环句。
 * <p>
 * 提示：
 * 1 <= sentence.length <= 500
 * sentence 仅由大小写英文字母和空格组成
 * sentence 中的单词由单个空格进行分隔
 * 不含任何前导或尾随空格
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/circular-sentence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/30 10:08
 */
public class Solution2490 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 96.22% 的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了 57.56% 的用户
     * 通过测试用例：266 / 266
     *
     * @param sentence
     * @return
     */
    public boolean isCircularSentence(String sentence) {
        int n = sentence.length();
        if (sentence.charAt(0) != sentence.charAt(n - 1)) {
            return false;
        }
        for (int i = 1; i < n - 1; i++) {
            if (sentence.charAt(i) == ' ' && sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 3.78% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了 5.04% 的用户
     * 通过测试用例：266 / 266
     *
     * @param sentence
     * @return
     */
    public boolean isCircularSentence_split(String sentence) {
        String[] split = sentence.split("\\s+");
        int n = split.length;
        for (int i = 0; i < n; i++) {
            if (split[i].charAt(split[i].length() - 1) != split[(i + 1) % n].charAt(0)) {
                return false;
            }
        }
        return true;
    }
}

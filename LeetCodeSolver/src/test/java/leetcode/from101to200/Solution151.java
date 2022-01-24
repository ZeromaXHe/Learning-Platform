package leetcode.from101to200;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 13:45
 * @Description: 151. 翻转字符串里的单词 | 难度：中等 | 标签：双指针、字符串
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * <p>
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * <p>
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * <p>
 * 说明：
 * <p>
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 * <p>
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * <p>
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 * <p>
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
 * <p>
 * 示例 4：
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * <p>
 * 示例 5：
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * <p>
 * 进阶：
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution151 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 78.33% 的用户
     * 通过测试用例： 58 / 58
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] chars = s.trim().toCharArray();
        char[] charsNew = new char[chars.length];
        int from = chars.length;
        int index = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                if (from - i > 1) {
                    if (index > 0) {
                        charsNew[index++] = ' ';
                    }
                    for (int j = i + 1; j < from; j++) {
                        charsNew[index++] = chars[j];
                    }
                }
                from = i;
            }
        }
        if (from > 0) {
            if (index > 0) {
                charsNew[index++] = ' ';
            }
            for (int j = 0; j < from; j++) {
                charsNew[index++] = chars[j];
            }
        }
        return new String(charsNew, 0, index);
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 73.39% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 57.35% 的用户
     * 通过测试用例： 58 / 58
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        StringBuilder wordBuilder = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (wordBuilder.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append(' ');
                    }
                    sb.append(wordBuilder.reverse().toString());
                    wordBuilder.delete(0, wordBuilder.length());
                }
            } else {
                wordBuilder.append(s.charAt(i));
            }
        }
        if (wordBuilder.length() > 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(wordBuilder.reverse().toString());
            wordBuilder.delete(0, wordBuilder.length());
        }
        return sb.toString();
    }
}

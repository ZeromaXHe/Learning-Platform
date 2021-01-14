package leetcode.first100;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/14 11:45
 * @Description: 68.文本左右对齐 | 难度：困难 | 标签：字符串
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 说明:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * <p>
 * 示例:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * <p>
 * 示例 2:
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * <p>
 * 示例 3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution68 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.1 MB , 在所有 Java 提交中击败了 18.40% 的用户
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder spaceStringBuilder = new StringBuilder();
        int from = 0;
        int wordsLen = 0;
        int wordsCount = 0;
        for (int i = 0; i < words.length; i++) {
            if (wordsLen + wordsCount + words[i].length() <= maxWidth) {
                wordsCount++;
                wordsLen += words[i].length();
            } else {
                if (wordsCount == 1) {
                    sb.append(words[from]);
                    addSpaces(sb, maxWidth - wordsLen);
                } else {
                    int spaceTotal = maxWidth - wordsLen;
                    int moreSpaceCount = spaceTotal % (wordsCount - 1);
                    addSpaces(spaceStringBuilder, spaceTotal / (wordsCount - 1));
                    String lessSpaceString = spaceStringBuilder.toString();
                    spaceStringBuilder.setLength(0);
                    for (int j = from; j < i; j++) {
                        sb.append(words[j]);
                        if (j != i - 1) {
                            sb.append(lessSpaceString);
                            if (j - from < moreSpaceCount) {
                                sb.append(' ');
                            }
                        }
                    }
                }
                list.add(sb.toString());
                sb.setLength(0);
                from = i;
                wordsCount = 1;
                wordsLen = words[i].length();
            }
        }
        if (wordsCount == 1) {
            sb.append(words[from]);
            addSpaces(sb, maxWidth - wordsLen);
        } else {
            for (int j = from; j < words.length; j++) {
                sb.append(words[j]);
                if (j != words.length - 1) {
                    sb.append(' ');
                } else {
                    addSpaces(sb, maxWidth - wordsLen - wordsCount + 1);
                }
            }
        }
        list.add(sb.toString());
        return list;
    }

    private void addSpaces(StringBuilder sb, int spaceCount) {
        for (int i = 0; i < spaceCount; i++) {
            sb.append(' ');
        }
    }

    @Test
    public void fullJustifyTest() {
        List<String> strings = fullJustify(new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"}, 20);
        for (String s : strings) {
            System.out.println(s + "|");
        }
        System.out.println("============================");
        List<String> strings2 = fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16);
        for (String s : strings2) {
            System.out.println(s + "|");
        }
    }
}

package leetcode.from1801to1850;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/12/6 9:54
 * @Description: 1819. 截断句子 | 难度：简单 | 标签：数组、字符串
 * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 * <p>
 * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
 * 给你一个句子 s​​​​​​ 和一个整数 k​​​​​​ ，请你将 s​​ 截断 ​，​​​使截断后的句子仅含 前 k​​​​​​ 个单词。返回 截断 s​​​​​​ 后得到的句子。
 * <p>
 * 示例 1：
 * 输入：s = "Hello how are you Contestant", k = 4
 * 输出："Hello how are you"
 * 解释：
 * s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
 * 前 4 个单词为 ["Hello", "how", "are", "you"]
 * 因此，应当返回 "Hello how are you"
 * <p>
 * 示例 2：
 * 输入：s = "What is the solution to this problem", k = 4
 * 输出："What is the solution"
 * 解释：
 * s 中的单词为 ["What", "is" "the", "solution", "to", "this", "problem"]
 * 前 4 个单词为 ["What", "is", "the", "solution"]
 * 因此，应当返回 "What is the solution"
 * <p>
 * 示例 3：
 * 输入：s = "chopper is not a tanuki", k = 5
 * 输出："chopper is not a tanuki"
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * k 的取值范围是 [1,  s 中单词的数目]
 * s 仅由大小写英文字母和空格组成
 * s 中的单词之间由单个空格隔开
 * 不存在前导或尾随空格
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/truncate-sentence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1819 {
    @Test
    public void truncateSentenceTest() {
        Assert.assertEquals("Hello how are you", truncateSentence("Hello how are you Contestant", 4));
        Assert.assertEquals("What is the solution", truncateSentence("What is the solution to this problem", 4));
        Assert.assertEquals("chopper is not a tanuki", truncateSentence("chopper is not a tanuki", 5));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 66.75% 的用户
     * 通过测试用例： 72 / 72
     *
     * @param s
     * @param k
     * @return
     */
    public String truncateSentence(String s, int k) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
            if (count == k) {
                return s.substring(0, i);
            }
        }
        return s;
    }
}

package leetcode.from401to500;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/7 15:32
 * @Description: 434. 字符串中的单词数 | 难度：简单 | 标签：字符串
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * <p>
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution434 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 28.55% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 5.02% 的用户
     * 通过测试用例： 27 / 27
     *
     * @param s
     * @return
     */
    public int countSegments(String s) {
        String trim = s.trim();
        return "".equals(trim) ? 0 : trim.split("\\s+").length;
    }
}

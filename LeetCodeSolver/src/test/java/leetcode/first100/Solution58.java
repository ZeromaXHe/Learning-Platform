package leetcode.first100;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/9/21 18:54
 * @Description: 58. 最后一个单词的长度 | 难度：简单 | 标签：字符串
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution58 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 5.97% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 18.62% 的用户
     * 通过测试用例： 58 / 58
     * 估计从后面扫过来会快一点，但懒得写了
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String[] strs = s.trim().split("\\s+");
        return strs[strs.length - 1].length();
    }
}

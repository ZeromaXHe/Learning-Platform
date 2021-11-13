package leetcode.from501to600;

/**
 * @Author: zhuxi
 * @Time: 2021/11/13 9:45
 * @Description: 520. 检测大写字母 | 难度：简单 | 标签：字符串
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * <p>
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：word = "USA"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：word = "FlaG"
 * 输出：false
 * <p>
 * 提示：
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/detect-capital
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution520 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.19% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 53.44% 的用户
     * 通过测试用例： 550 / 550
     *
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        boolean firstUpper = false;
        boolean allUpper = true;
        boolean anyOtherUpper = false;
        if (Character.isUpperCase(word.charAt(0))) {
            firstUpper = true;
        } else {
            allUpper = false;
        }
        for (int i = 1; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                anyOtherUpper = true;
            } else {
                allUpper = false;
            }
        }
        return !anyOtherUpper || (firstUpper && allUpper);
    }
}

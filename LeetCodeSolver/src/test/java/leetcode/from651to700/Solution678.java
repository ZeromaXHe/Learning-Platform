package leetcode.from651to700;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/9/12 13:27
 * @Description: 678. 有效的括号字符串 | 难度：中等 | 标签：栈、贪心、字符串、动态规划
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * <p>
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * <p>
 * 示例 1:
 * 输入: "()"
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: "(*)"
 * 输出: True
 * <p>
 * 示例 3:
 * 输入: "(*))"
 * 输出: True
 * <p>
 * 注意:
 * 字符串大小将在 [1，100] 范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution678 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 57.43% 的用户
     * 通过测试用例： 83 / 83
     *
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        int n = s.length();
        int rightCount = 0;
        int starCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                rightCount--;
            } else if (s.charAt(i) == ')') {
                rightCount++;
            } else {
                starCount++;
            }
            if (rightCount > 0 && rightCount > starCount) {
                return false;
            }
        }
        if (-rightCount > starCount) {
            return false;
        }
        int leftCount = 0;
        starCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (s.charAt(i) == ')') {
                leftCount--;
            } else {
                starCount++;
            }
            if (leftCount > 0 && leftCount > starCount) {
                return false;
            }
        }
        return -leftCount <= starCount;
    }
}

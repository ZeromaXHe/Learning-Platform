package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 14:35
 * @Description: 20. 有效的括号 | 难度：简单 | 标签：栈、字符串
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 * <p>
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution20 {
    @Test
    public void isValidTest() {
        Assert.assertTrue(isValid("()"));
        Assert.assertTrue(isValid("()[]{}"));
        Assert.assertFalse(isValid("(]"));
        Assert.assertFalse(isValid("([)]"));
        Assert.assertTrue(isValid("{[]}"));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.84% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 45.27% 的用户
     * 通过测试用例： 91 / 91
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.isEmpty()
                    || (c == ')' && stack.peek() != '(')
                    || (c == ']' && stack.peek() != '[')
                    || (c == '}' && stack.peek() != '{')) {
                return false;
            } else {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}

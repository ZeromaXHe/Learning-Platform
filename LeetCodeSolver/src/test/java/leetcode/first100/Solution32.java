package leetcode.first100;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2020/11/11 10:56
 * @Description: 32.最长有效括号 | 难度：困难 | 标签：字符串、动态规划
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * <p>
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution32 {
//    动态规划？
//    /**
//     * 数组存储左括号对应的最长的下一个匹配位置到本地距离
//     * 右括号存储负数，绝对值是上一个匹配的左括号位置到本地距离
//     */
//    int[] availableIndex;
//
//    public int longestValidParentheses(String s) {
//        char[] chars = s.toCharArray();
//
//        availableIndex = new int[chars.length];
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i] == ')') {
//                if (i > 0 && chars[i - 1] == '(') {
//                    availableIndex[i] = -1;
//                }
//            } else {
//                if (i < chars.length - 1 && chars[i + 1] == ')') {
//                    availableIndex[i] = 1;
//                }
//            }
//        }
//    }

    /**
     * 执行用时： 3 ms, 在所有 Java 提交中击败了 52.33% 的用户
     * 内存消耗： 38.6 MB, 在所有 Java 提交中击败了 66.96% 的用户
     * TODO: 除了用栈解决，题解里面还有动态规划和正向逆向两次扫描的方法，有空尝试实现一下
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null) {
            return 0;
        }
        // 最大长度
        int longestLen = 0;
        // 存放被左括号分割的匹配长度
        LinkedList<Integer> stack = new LinkedList<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(-1);
            } else {
                // 目前为右括号
                if (!stack.isEmpty()) {
                    // 如果 栈非空
                    int stackTop = stack.pop();
                    if (stackTop == -1) {
                        // 如果 栈顶为未匹配的左括号
                        if (stack.isEmpty() || stack.peek() == -1) {
                            // 如果 栈中只有栈顶一个元素 或 次栈顶也是未匹配的左括号
                            stack.push(2);
                        } else {
                            // 如果 栈顶以外还有其他匹配了的数字
                            stack.push(stack.pop() + 2);
                        }
                    } else {
                        // 如果 栈顶为匹配数字
                        // 先合并所有堆栈中的匹配数字
                        while (!stack.isEmpty() && stack.peek() != -1) {
                            stackTop += stack.pop();
                        }
                        if (stack.isEmpty()) {
                            // 如果 只有栈顶
                            if (stackTop > longestLen) {
                                longestLen = stackTop;
                            }
                        } else {
                            // 如果 还有次栈顶
                            // 因为前面已经合并了所有匹配数字，所以次栈顶一定为未匹配左括号（-1）
                            stack.pop();
                            stack.push(stackTop + 2);
                        }
                    }
                }
            }
        }

        int len = 0;
        while (!stack.isEmpty()) {
            int stackPop = stack.pop();
            if (stackPop == -1) {
                if (len > longestLen) {
                    longestLen = len;
                }
                len = 0;
            } else {
                len += stackPop;
            }
        }
        if (len > longestLen) {
            return len;
        } else {
            return longestLen;
        }
    }

    public static void main(String[] args) {
        Solution32 solution32 = new Solution32();
        // 2
        System.out.println(solution32.longestValidParentheses("(()"));
        // 4
        System.out.println(solution32.longestValidParentheses(")()())"));
        // 10
        System.out.println(solution32.longestValidParentheses(")(()())()())(()())"));
        // 0
        System.out.println(solution32.longestValidParentheses(""));
        // 2
        System.out.println(solution32.longestValidParentheses("()(()"));
        // 4
        System.out.println(solution32.longestValidParentheses("())(())((()("));

    }
}

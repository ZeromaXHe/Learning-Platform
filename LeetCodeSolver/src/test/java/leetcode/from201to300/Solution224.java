package leetcode.from201to300;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhuxi
 * @Time: 2021/3/10 9:48
 * @Description: 224.基本计算器 | 难度：困难 | 标签：栈、数学
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * <p>
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution224 {
    /**
     * 官方题解
     * <p>
     * 执行用时： 13 ms , 在所有 Java 提交中击败了 46.97% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 45.41% 的用户
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                // 将+视为正数的表示，之后计算全用加计算
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                // 将-视为负数的表示，之后计算全用加计算
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                // 当前题目的操作符，括号只对正负有影响
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                // 计算的时候把数字乘上正负直接加起来
                ret += sign * num;
            }
        }
        return ret;
    }


    /**
     * copy的网上逆波兰表达式解析的代码，在负数的情况下失败了
     *
     * @param s
     * @return
     */
    public int calculate_failedNegative(String s) {
        return calculate(parseToSuffixExpression(transStrToExpressionList(s)));
    }

    private List<String> transStrToExpressionList(String s) {
        LinkedList<String> expList = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            }
            if (Character.isDigit(chars[i])) {
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(chars[i])) {
                    sb.append(chars[i++]);
                    if (i + 1 > chars.length) {
                        break;
                    }
                }
                i--;
                expList.add(sb.toString());
            } else {
                expList.add("" + chars[i]);
            }
        }
        return expList;
    }

    private List<String> parseToSuffixExpression(List<String> expressionList) {
        //创建一个栈用于保存操作符
        Stack<String> opStack = new Stack<>();
        //创建一个list用于保存后缀表达式
        List<String> suffixList = new ArrayList<>();
        for (String item : expressionList) {
            //得到数或操作符
            if (isOperator(item)) {
                //是操作符 判断操作符栈是否为空
                if (opStack.isEmpty() || "(".equals(opStack.peek()) || priority(item) > priority(opStack.peek())) {
                    //为空或者栈顶元素为左括号或者当前操作符大于栈顶操作符直接压栈
                    opStack.push(item);
                } else {
                    //否则将栈中元素出栈如队，直到遇到大于当前操作符或者遇到左括号时
                    while (!opStack.isEmpty() && !"(".equals(opStack.peek())) {
                        if (priority(item) <= priority(opStack.peek())) {
                            suffixList.add(opStack.pop());
                        }
                    }
                    //当前操作符压栈
                    opStack.push(item);
                }
            } else if (isNumber(item)) {
                //是数字则直接入队
                suffixList.add(item);
            } else if ("(".equals(item)) {
                //是左括号，压栈
                opStack.push(item);
            } else if (")".equals(item)) {
                //是右括号 ，将栈中元素弹出入队，直到遇到左括号，左括号出栈，但不入队
                while (!opStack.isEmpty()) {
                    if ("(".equals(opStack.peek())) {
                        opStack.pop();
                        break;
                    } else {
                        suffixList.add(opStack.pop());
                    }
                }
            } else {
                throw new RuntimeException("有非法字符！");
            }
        }
        //循环完毕，如果操作符栈中元素不为空，将栈中元素出栈入队
        while (!opStack.isEmpty()) {
            suffixList.add(opStack.pop());
        }
        return suffixList;
    }

    /**
     * 判断字符串是否为操作符
     *
     * @param op
     * @return
     */
    private boolean isOperator(String op) {
        return op.equals("+") || op.equals("-") /*|| op.equals("*") || op.equals("/")*/;
    }

    /**
     * 判断是否为数字
     *
     * @param num
     * @return
     */
    private boolean isNumber(String num) {
        return num.matches("\\d+");
    }

    /**
     * 获取操作符的优先级
     *
     * @param op
     * @return
     */
    private int priority(String op) {
        /*if (op.equals("*") || op.equals("/")) {
            return 1;
        } else*/
        if (op.equals("+") || op.equals("-")) {
            return 0;
        }
        return -1;
    }

    /**
     * 根据后缀表达式list计算结果
     *
     * @param list
     * @return
     */
    private static int calculate(List<String> list) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            if (item.matches("\\d+")) {
                //是数字
                stack.push(Integer.parseInt(item));
            } else {
                //是操作符，取出栈顶两个元素
                int num2 = stack.pop();
                int num1 = stack.pop();
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
//                } else if (item.equals("*")) {
//                    res = num1 * num2;
//                } else if (item.equals("/")) {
//                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符错误！");
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }

    @Test
    public void calculateTest() {
        Assert.assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
        Assert.assertEquals(2, calculate("1+1"));
        Assert.assertEquals(-1, calculate("-2+ 1"));
    }
}

package leetcode.from201to300;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/10/16 9:39
 * @Description: 282. 给表达式添加运算符 | 难度：困难 | 标签：数学、字符串、回溯
 * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
 * <p>
 * 示例 1:
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * <p>
 * 示例 2:
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * <p>
 * 示例 3:
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * <p>
 * 示例 4:
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * <p>
 * 示例 5:
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 * <p>
 * 提示：
 * 1 <= num.length <= 10
 * num 仅含数字
 * -231 <= target <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/expression-add-operators
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution282 {
    @Test
    public void addOperatorsTest() {
        System.out.println(addOperators("123", 6));
        System.out.println(addOperators("232", 8));
        System.out.println(addOperators("105", 5));
        System.out.println(addOperators("00", 0));
        System.out.println(addOperators("3456237490", 9191));
        // 通过测试用例： 15 / 23
        // 通过测试用例： 19 / 23
        // 序号会变？！
        List<String> list = addOperators("123456789", 45);
        System.out.println(list);
        System.out.println("=========");
        // 类似1*234这种乘多位数会出问题
        Solution227 solution227 = new Solution227();
        for (String s : list) {
            int calculate = solution227.calculate(s);
            if (calculate != 45) {
                System.out.println(s + " = " + calculate);
            }
        }
        // 通过测试用例： 20 / 23
        // 2147483648 在 int 会溢出变成 -2147483648
        // 预计：[]
        System.out.println(addOperators("2147483648", -2147483648));
        // 通过测试用例： 22 / 23
        // 预计：["0*1*0","0*1+0","0*1-0","0*10","0+1*0","0-1*0"]
        System.out.println(addOperators("010", 0));
    }

    /**
     * 执行用时： 82 ms , 在所有 Java 提交中击败了 50.39% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 96.83% 的用户
     * 通过测试用例： 23 / 23
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        List<String> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        char c0 = num.charAt(0);
        sb.append(c0);

        LinkedList<Long> temp = new LinkedList<>();
        long li = (long) c0 - '0';
        temp.push(li);

        LinkedList<Long> sum = new LinkedList<>();
        sum.push(0L);
        backtrace(num, target, result, temp, sum, sb, 1, false, 1, li);
        return result;
    }

    private void backtrace(String num, int target, List<String> result, LinkedList<Long> temp, LinkedList<Long> sum,
                           StringBuilder sb, int index, boolean multiply, long multiBefore, long multiAfter) {
        if (index == num.length()) {
            if (temp.peek() + sum.peek() == target) {
                result.add(sb.toString());
            }
            return;
        }
        char ci = num.charAt(index);
        long li = (long) ci - '0';
        if (temp.peek() != 0 || (multiply && multiAfter != 0)) {
            long tempPop = temp.pop();
            temp.push(tempPop * 10 + multiBefore * ((multiBefore < 0 || tempPop > 0) ? li : -li));
            sb.append(ci);
            backtrace(num, target, result, temp, sum, sb, index + 1, multiply, multiply ? multiBefore : 1, multiAfter * 10 + li);
            temp.pop();
            temp.push(tempPop);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 尝试添加加号
        sum.push(sum.peek() + temp.peek());
        temp.push(li);
        sb.append('+').append(ci);
        backtrace(num, target, result, temp, sum, sb, index + 1, false, 1, li);
        sum.pop();
        temp.pop();
        sb.delete(sb.length() - 2, sb.length());
        // 尝试添加减号
        sum.push(sum.peek() + temp.peek());
        temp.push(-li);
        sb.append('-').append(ci);
        backtrace(num, target, result, temp, sum, sb, index + 1, false, 1, li);
        sum.pop();
        temp.pop();
        sb.delete(sb.length() - 2, sb.length());
        // 尝试添加乘号
        long before = temp.pop();
        temp.push(before * li);
        sb.append('*').append(ci);
        backtrace(num, target, result, temp, sum, sb, index + 1, true, before, li);
        temp.pop();
        temp.push(before);
        sb.delete(sb.length() - 2, sb.length());
    }
}

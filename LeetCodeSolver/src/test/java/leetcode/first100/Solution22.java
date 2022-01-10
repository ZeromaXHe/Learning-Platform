package leetcode.first100;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 15:43
 * @Description: 22. 括号生成 | 难度：中等 | 标签：字符串、动态规划、回溯
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution22 {
    @Test
    public void generateParenthesisTest() {
        System.out.println(generateParenthesis(3));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 36.14% 的用户
     * 通过测试用例： 8 / 8
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        if (n == 1) {
            result.add("()");
            return result;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        int count = 0;
        int left = 1;
        generateParenthesis(n, count, left, sb, result);
        return result;
    }

    private void generateParenthesis(int n, int count, int left, StringBuilder sb, List<String> result) {
        if (count == n) {
            result.add(sb.toString());
            return;
        }
        if (left + count < n) {
            sb.append('(');
            generateParenthesis(n, count, left + 1, sb, result);
            sb.delete(sb.length() - 1, sb.length());
        }
        if (left > 0) {
            sb.append(')');
            generateParenthesis(n, count + 1, left - 1, sb, result);
            sb.delete(sb.length() - 1, sb.length());
        }
    }
}

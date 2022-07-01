package leetcode.from201to250;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 241. 为运算表达式设计优先级 | 难度：中等 | 标签：递归、记忆化搜索、数学、字符串、动态规划
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 * <p>
 * 示例 1：
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * <p>
 * 示例 2：
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * 提示：
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99] 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/7/1 11:47
 */
public class Solution241 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.54% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了 89.12% 的用户
     * 通过测试用例： 25 / 25
     *
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute(String expression) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        return compute(expression, 0, expression.length(), map);
    }

    private List<Integer> compute(String expression, int from, int to, HashMap<Integer, List<Integer>> map) {
        if (map.containsKey(from * 100 + to)) return map.get(from * 100 + to);
        List<Integer> result = new LinkedList<>();
        for (int i = from; i < to; i++) {
            if (Character.isDigit(expression.charAt(i))) {
                continue;
            }
            List<Integer> rightList = compute(expression, i + 1, to, map);
            List<Integer> leftList = compute(expression, from, i, map);
            switch (expression.charAt(i)) {
                case '+':
                    for (int l : leftList) {
                        for (int r : rightList) {
                            result.add(l + r);
                        }
                    }
                    break;
                case '-':
                    for (int l : leftList) {
                        for (int r : rightList) {
                            result.add(l - r);
                        }
                    }
                    break;
                case '*':
                    for (int l : leftList) {
                        for (int r : rightList) {
                            result.add(l * r);
                        }
                    }
                    break;
                case '/':
                    for (int l : leftList) {
                        for (int r : rightList) {
                            result.add(l / r);
                        }
                    }
                    break;
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression.substring(from, to)));
        }
        map.put(from * 100 + to, result);
        return result;
    }
}

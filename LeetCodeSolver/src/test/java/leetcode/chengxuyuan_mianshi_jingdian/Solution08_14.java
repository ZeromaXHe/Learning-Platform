package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/13 10:37
 * @Description: 面试题 08.14. 布尔运算 | 难度：中等 | 标签：记忆化搜索、字符串、动态规划
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 * <p>
 * 示例 1:
 * 输入: s = "1^0|0|1", result = 0
 * 输出: 2
 * 解释: 两种可能的括号方法是
 * 1^(0|(0|1))
 * 1^((0|0)|1)
 * <p>
 * 示例 2:
 * 输入: s = "0&0&0&1^1|0", result = 1
 * 输出: 10
 * <p>
 * 提示：
 * 运算符的数量不超过 19 个
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/boolean-evaluation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_14 {
    @Test
    public void countEvalTest() {
        Assert.assertEquals(2, countEval("0|0|1", 1));
        Assert.assertEquals(2, countEval("1^0|0|1", 0));
        Assert.assertEquals(10, countEval("0&0&0&1^1|0", 1));
    }

    private final static int[] ONE_ZERO = {1, 0};
    private final static int[] ZERO_ONE = {0, 1};
    private int[][][] dp;

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.7 MB , 在所有 Java 提交中击败了 75.72% 的用户
     * 通过测试用例： 78 / 78
     *
     * @param s
     * @param result
     * @return
     */
    public int countEval(String s, int result) {
        dp = new int[s.length() / 2][s.length() / 2][2];
        return countEval(s, 0, s.length())[result];
    }

    public int[] countEval(String s, int from, int to) {
        if (to - from == 1) {
            return s.charAt(from) == '0' ? ONE_ZERO : ZERO_ONE;
        }
        if (dp[from / 2][to / 2 - 1][0] != 0 || dp[from / 2][to / 2 - 1][1] != 0) {
            return dp[from / 2][to / 2 - 1];
        }
        if (to - from == 3) {
            int calc;
            if (s.charAt(from + 1) == '^') {
                calc = (s.charAt(from) - '0') ^ (s.charAt(to - 1) - '0');

            } else if (s.charAt(from + 1) == '|') {
                calc = (s.charAt(from) - '0') | (s.charAt(to - 1) - '0');
            } else {
                calc = (s.charAt(from) - '0') & (s.charAt(to - 1) - '0');
            }
            dp[from / 2][to / 2 - 1] = calc == 1 ? ZERO_ONE : ONE_ZERO;
            return dp[from / 2][to / 2 - 1];
        }
        int[] result = new int[2];
        for (int i = from + 1; i < to; i += 2) {
            int[] left = countEval(s, from, i);
            int[] right = countEval(s, i + 1, to);
            if (s.charAt(i) == '^') {
                result[0] += left[0] * right[0] + left[1] * right[1];
                result[1] += left[0] * right[1] + left[1] * right[0];
            } else if (s.charAt(i) == '|') {
                result[0] += left[0] * right[0];
                result[1] += left[1] * right[1] + left[0] * right[1] + left[1] * right[0];
            } else {
                result[0] += left[0] * right[0] + left[0] * right[1] + left[1] * right[0];
                result[1] += left[1] * right[1];
            }
        }
        dp[from / 2][to / 2 - 1] = result;
        return result;
    }
}

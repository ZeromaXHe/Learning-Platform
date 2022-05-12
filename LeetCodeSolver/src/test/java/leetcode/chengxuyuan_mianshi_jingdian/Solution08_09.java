package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 17:32
 * @Description: 面试题 08.09. 括号 | 难度：中等 | 标签：字符串、动态回归、回溯
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bracket-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_09 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 78.48% 的用户
     * 通过测试用例： 8 / 8
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        char[] chars = new char[n * 2];
        backtrace(result, chars, 0, n, n);
        return result;
    }

    private void backtrace(List<String> result, char[] chars, int index, int left, int right) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        if (left > 0) {
            chars[index] = '(';
            backtrace(result, chars, index + 1, left - 1, right);
        }
        if (left < right) {
            chars[index] = ')';
            backtrace(result, chars, index + 1, left, right - 1);
        }
    }
}

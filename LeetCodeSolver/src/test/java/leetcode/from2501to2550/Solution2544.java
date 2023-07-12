package leetcode.from2501to2550;

/**
 * @author zhuxi
 * @apiNote 2544. 交替数字和 | 难度：简单 | 标签：数学
 * 给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：
 * <p>
 * 最高有效位 上的数字分配到 正 号。
 * 剩余每位上数字的符号都与其相邻数字相反。
 * 返回所有数字及其对应符号的和。
 * <p>
 * 示例 1：
 * 输入：n = 521
 * 输出：4
 * 解释：(+5) + (-2) + (+1) = 4
 * <p>
 * 示例 2：
 * 输入：n = 111
 * 输出：1
 * 解释：(+1) + (-1) + (+1) = 1
 * <p>
 * 示例 3：
 * 输入：n = 886996
 * 输出：0
 * 解释：(+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0
 * <p>
 * 提示：
 * 1 <= n <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/alternating-digit-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/12 10:11
 */
public class Solution2544 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了 75.25% 的用户
     * 通过测试用例：118 / 118
     *
     * @param n
     * @return
     */
    public int alternateDigitSum(int n) {
        int sum = 0;
        int sign = 1;
        while (n > 0) {
            sum += sign * (n % 10);
            n /= 10;
            sign = -sign;
        }
        return sign == 1 ? -sum : sum;
    }
}

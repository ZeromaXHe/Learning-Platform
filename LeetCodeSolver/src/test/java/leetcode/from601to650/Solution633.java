package leetcode.from601to650;

/**
 * @Author: zhuxi
 * @Time: 2021/4/28 9:57
 * @Description: 633.平方数之和 | 难度：中等 | 标签：数学
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 * <p>
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * <p>
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 * <p>
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 * <p>
 * 提示：
 * 0 <= c <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution633 {
    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 18.46% 的用户
     * 内存消耗： 35.2 MB , 在所有 Java 提交中击败了 52.06% 的用户
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int max = (int) Math.sqrt(c);
        for (int a = 0; a <= max; a++) {
            int b = (int) Math.sqrt(c - a * a);
            if (b * b + a * a == c) {
                return true;
            }
        }
        return false;
    }
}

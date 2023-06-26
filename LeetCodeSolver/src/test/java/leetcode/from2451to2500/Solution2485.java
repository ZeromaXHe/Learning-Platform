package leetcode.from2451to2500;

/**
 * @author zhuxi
 * @apiNote 2485. 找出中枢整数 | 难度：简单 | 标签：数学、前缀和
 * 给你一个正整数 n ，找出满足下述条件的 中枢整数 x ：
 * <p>
 * 1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
 * 返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。
 * <p>
 * 示例 1：
 * 输入：n = 8
 * 输出：6
 * 解释：6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 是中枢整数，因为 1 = 1 。
 * <p>
 * 示例 3：
 * 输入：n = 4
 * 输出：-1
 * 解释：可以证明不存在满足题目要求的整数。
 * <p>
 * 提示：
 * 1 <= n <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-pivot-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/26 9:51
 */
public class Solution2485 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 55.05% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了 55.96% 的用户
     * 通过测试用例：428 / 428
     *
     * @param n
     * @return
     */
    public int pivotInteger(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        int preSum = 0;
        for (int i = 1; 2 * preSum + i <= sum; i++) {
            if (preSum + i == sum - preSum) {
                return i;
            }
            preSum += i;
        }
        return -1;
    }
}

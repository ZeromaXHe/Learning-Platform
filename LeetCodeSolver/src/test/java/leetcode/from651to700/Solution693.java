package leetcode.from651to700;

/**
 * @Author: zhuxi
 * @Time: 2022/3/28 9:47
 * @Description: 693. 交替位二进制数 | 难度：简单 | 标签：
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * <p>
 * 示例 1：
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * <p>
 * 示例 2：
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * <p>
 * 示例 3：
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 * <p>
 * 提示：
 * 1 <= n <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution693 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 20.40% 的用户
     * 通过测试用例： 204 / 204
     *
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        int pre = n % 2;
        n >>= 1;
        while (n > 0) {
            if (pre == n % 2) {
                return false;
            }
            pre = n % 2;
            n >>= 1;
        }
        return true;
    }
}

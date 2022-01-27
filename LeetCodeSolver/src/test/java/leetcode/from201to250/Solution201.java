package leetcode.from201to250;

/**
 * @Author: zhuxi
 * @Time: 2022/1/26 11:51
 * @Description: 201. 数字范围按位与 | 难度：中等 | 标签：位运算
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * <p>
 * 示例 1：
 * 输入：left = 5, right = 7
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：left = 0, right = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：left = 1, right = 2147483647
 * 输出：0
 * <p>
 * 提示：
 * 0 <= left <= right <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution201 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.6 MB , 在所有 Java 提交中击败了 5.15% 的用户
     * 通过测试用例： 8268 / 8268
     * <p>
     * 参考别人题解做的。原理其实就是全部按位与之后，只有公共的前缀会留下来，而left和right一定会在公共前缀的最后一位后不同
     *
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        // 最高位开始
        int mask = 1 << 30;
        int anw = 0;
        // 寻找相同前缀
        while (mask > 0 && (left & mask) == (right & mask)) {
            anw |= left & mask;
            mask >>= 1;
        }
        return anw;
    }
}

package leetcode.from351to400;

/**
 * @Author: zhuxi
 * @Time: 2021/9/26 10:10
 * @Description: 371. 两整数之和 | 难度：中等 | 标签：位运算、数学
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
 * <p>
 * 示例 1：
 * 输入：a = 1, b = 2
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：a = 2, b = 3
 * 输出：5
 * <p>
 * 提示：
 * -1000 <= a, b <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution371 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.1 MB , 在所有 Java 提交中击败了 67.63% 的用户
     * 通过测试用例： 13 / 13
     * <p>
     * 根据题解加注释的
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            // 都是1的位数左移一位
            int carry = (a & b) << 1;
            // 无进位计算出的每一位结果
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}

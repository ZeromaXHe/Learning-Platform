package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 16:31
 * @Description: 面试题 08.05. 递归乘法 | 难度：中等 | 标签：位运算、递归、数学
 * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 * <p>
 * 示例1:
 * 输入：A = 1, B = 10
 * 输出：10
 * <p>
 * 示例2:
 * 输入：A = 3, B = 4
 * 输出：12
 * <p>
 * 提示:
 * 保证乘法范围不会溢出
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/recursive-mulitply-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_05 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 51.86% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param A
     * @param B
     * @return
     */
    public int multiply(int A, int B) {
        if (A > B) {
            int temp = A;
            A = B;
            B = temp;
        }
        int result = 0;
        while (A > 0) {
            if ((A & 1) == 1) {
                result += B;
            }
            B <<= 1;
            A >>= 1;
        }
        return result;
    }
}

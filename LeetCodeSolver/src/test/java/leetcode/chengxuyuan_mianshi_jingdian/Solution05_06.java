package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/10 15:52
 * @Description: 面试题 05.06. 整数转换 | 难度：简单 | 标签：位运算
 * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 * <p>
 * 示例1:
 * 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 * 输出：2
 * <p>
 * 示例2:
 * 输入：A = 1，B = 2
 * 输出：2
 * <p>
 * 提示:
 * A，B范围在[-2147483648, 2147483647]之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/convert-integer-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution05_06 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 55.25% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param A
     * @param B
     * @return
     */
    public int convertInteger(int A, int B) {
        int count = 0;
        while (A != 0 && B != 0) {
            if (((A & 1) ^ (B & 1)) == 1) {
                count++;
            }
            A >>>= 1;
            B >>>= 1;
        }
        while (A != 0) {
            if ((A & 1) == 1) {
                count++;
            }
            A >>>= 1;
        }
        while (B != 0) {
            if ((B & 1) == 1) {
                count++;
            }
            B >>>= 1;
        }
        return count;
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 83.24% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param A
     * @param B
     * @return
     */
    public int convertInteger_xor(int A, int B) {
        int count = 0;
        int xor = A ^ B;
        while (xor != 0) {
            if ((xor & 1) == 1) {
                count++;
            }
            xor >>>= 1;
        }
        return count;
    }
}

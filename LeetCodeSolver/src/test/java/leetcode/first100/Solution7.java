package leetcode.first100;

import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/3 17:34
 * @Description: 7. 整数反转 | 难度：简单 | 标签：数学
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * <p>
 *  示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * <p>
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution7 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 57.99% 的用户
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long reverseX = 0;
        boolean posi = x >= 0;
        while (x != 0) {
            reverseX *= 10;
            reverseX += x % 10;
            x /= 10;
        }
        if (reverseX <= Integer.MAX_VALUE && reverseX >= Integer.MIN_VALUE) {
            return (int) reverseX;
        } else {
            return 0;
        }
    }

    @Test
    public void modTest() {
        System.out.println(-19 % 10);
    }

    @Test
    public void reverseTest() {
        System.out.println(reverse(-123456));
        System.out.println(reverse(Integer.MIN_VALUE));
        System.out.println(reverse(Integer.MAX_VALUE));
    }
}

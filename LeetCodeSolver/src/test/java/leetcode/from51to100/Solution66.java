package leetcode.from51to100;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/10/21 9:50
 * @Description: 66. 加一 | 难度：简单 | 标签：数组、数学
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * <p>
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * <p>
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 * <p>
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution66 {
    @Test
    public void arrTest() {
        int[] digits = new int[1];
        int[] result = plusOne(digits);
        // 传入的数组会被方法内部修改
        System.out.println(Arrays.toString(digits));
        System.out.println(Arrays.toString(result));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 41.00% 的用户
     * 通过测试用例： 111 / 111
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int[] result = new int[len + 1];
        int carry = 1;
        for (int i = len - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            result[i + 1] = digits[i] = sum % 10;
            carry = sum / 10;
            if (carry == 0) {
                break;
            }
        }
        if (carry != 0) {
            result[0] = carry;
            return result;
        } else {
            return digits;
        }
    }
}

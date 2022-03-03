package leetcode.from251to300;

/**
 * @Author: zhuxi
 * @Time: 2022/3/3 14:59
 * @Description: 258. 各位相加 | 难度：简单 | 标签：数学、数论、模拟
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 * 示例 1:
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 * <p>
 * 示例 2:
 * 输入: num = 0
 * 输出: 0
 * <p>
 * 提示：
 * 0 <= num <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution258 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 42.79% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 15.51% 的用户
     * 通过测试用例： 1101 / 1101
     * <p>
     * 数学推断的效率应该比模拟要高
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        int result = 0;
        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        return result >= 10 ? addDigits(result) : result;
    }
}

package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/14 17:27
 * @Description: 面试题 16.01. 交换数字 | 难度：中等 | 标签：位运算、数学
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 * <p>
 * 示例：
 * 输入: numbers = [1,2]
 * 输出: [2,1]
 * <p>
 * 提示：
 * numbers.length == 2
 * -2147483647 <= numbers[i] <= 2147483647
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/swap-numbers-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution16_01 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.7 MB , 在所有 Java 提交中击败了 5.19% 的用户
     * 通过测试用例： 52 / 52
     *
     * @param numbers
     * @return
     */
    public int[] swapNumbers(int[] numbers) {
        numbers[1] ^= numbers[0] ^= numbers[1];
        numbers[0] ^= numbers[1];
        return numbers;
    }
}

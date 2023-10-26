package leetcode.from2501to2550;

/**
 * @author zhuxi
 * @apiNote 2520. 统计能整除数字的位数 | 难度：简单 | 标签：数学
 * 给你一个整数 num ，返回 num 中能整除 num 的数位的数目。
 * <p>
 * 如果满足 nums % val == 0 ，则认为整数 val 可以整除 nums 。
 * <p>
 * 示例 1：
 * 输入：num = 7
 * 输出：1
 * 解释：7 被自己整除，因此答案是 1 。
 * <p>
 * 示例 2：
 * 输入：num = 121
 * 输出：2
 * 解释：121 可以被 1 整除，但无法被 2 整除。由于 1 出现两次，所以返回 2 。
 * <p>
 * 示例 3：
 * 输入：num = 1248
 * 输出：4
 * 解释：1248 可以被它每一位上的数字整除，因此答案是 4 。
 * <p>
 * 提示：
 * 1 <= num <= 109
 * num 的数位中不含 0
 * @implNote
 * @since 2023/10/26 10:33
 */
public class Solution2520 {
    /**
     * 时间 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 37.05 MB
     * 击败 75.57% 使用 Java 的用户
     *
     * @param num
     * @return
     */
    public int countDigits(int num) {
        int temp = num;
        int count = 0;
        while (temp > 0) {
            if (num % (temp % 10) == 0) {
                count++;
            }
            temp /= 10;
        }
        return count;
    }
}

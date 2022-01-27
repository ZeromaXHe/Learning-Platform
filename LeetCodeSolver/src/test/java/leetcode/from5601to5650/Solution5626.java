package leetcode.from5601to5650;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/13 10:35
 * @Description: 5626. 十-二进制数的最少数目 | 难度：中等
 * 如果一个十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1 ，那么该数字就是一个 十-二进制数 。例如，101 和 1100 都是 十-二进制数，而 112 和 3001 不是。
 * <p>
 * 给你一个表示十进制整数的字符串 n ，返回和为 n 的 十-二进制数 的最少数目。
 * <p>
 * 示例 1：
 * 输入：n = "32"
 * 输出：3
 * 解释：10 + 11 + 11 = 32
 * <p>
 * 示例 2：
 * 输入：n = "82734"
 * 输出：8
 * <p>
 * 示例 3：
 * 输入：n = "27346209830709182346"
 * 输出：9
 * <p>
 * 提示：
 * 1 <= n.length <= 105
 * n 仅由数字组成
 * n 不含任何前导零并总是表示正整数
 * @Modified By: ZeromaXHe
 */
public class Solution5626 {
    /**
     * 94 / 94 个通过测试用例
     * 状态：通过
     * 执行用时: 5 ms
     * 内存消耗: 39.1 MB
     *
     * @param n
     * @return
     */
    public int minPartitions(String n) {
        char max = '0';
        for (char c : n.toCharArray()) {
            if (c > max) {
                max = c;
            }
        }
        return max - '0';
    }
}

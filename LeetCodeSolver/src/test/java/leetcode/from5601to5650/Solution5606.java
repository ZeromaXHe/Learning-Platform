package leetcode.from5601to5650;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/22 10:34
 * @Description: 5606.具有给定数值的最小字符串
 * 小写字符 的 数值 是它在字母表中的位置（从 1 开始），因此 a 的数值为 1 ，b 的数值为 2 ，c 的数值为 3 ，以此类推。
 * <p>
 * 字符串由若干小写字符组成，字符串的数值 为各字符的数值之和。例如，字符串 "abe" 的数值等于 1 + 2 + 5 = 8 。
 * <p>
 * 给你两个整数 n 和 k 。返回 长度 等于 n 且 数值 等于 k 的 字典序最小 的字符串。
 * <p>
 * 注意，如果字符串 x 在字典排序中位于 y 之前，就认为 x 字典序比 y 小，有以下两种情况：
 * <p>
 * x 是 y 的一个前缀；
 * 如果 i 是 x[i] != y[i] 的第一个位置，且 x[i] 在字母表中的位置比 y[i] 靠前。
 * <p>
 * 示例 1：
 * 输入：n = 3, k = 27
 * 输出："aay"
 * 解释：字符串的数值为 1 + 1 + 25 = 27，它是数值满足要求且长度等于 3 字典序最小的字符串。
 * <p>
 * 示例 2：
 * 输入：n = 5, k = 73
 * 输出："aaszz"
 * <p>
 * 提示：
 * 1 <= n <= 105
 * n <= k <= 26 * n
 * @Modified By: ZeromaXHe
 */
public class Solution5606 {
    /**
     * 92 / 92 个通过测试用例
     * 状态：通过
     * 执行用时: 38 ms
     * 内存消耗: 38.6 MB
     *
     * @param n
     * @param k
     * @return
     */
    public String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (k > n) {
            if (k - n >= 25) {
                sb.append('z');
                k -= 26;
                n -= 1;
            } else {
                sb.append((char) ('a' + k - n));
                k -= (k - n + 1);
                n--;
            }
        }
        while (n > 0) {
            sb.append('a');
            n--;
        }
        return sb.reverse().toString();
    }
}

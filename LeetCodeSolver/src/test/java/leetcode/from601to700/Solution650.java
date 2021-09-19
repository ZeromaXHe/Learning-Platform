package leetcode.from601to700;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/9/19 8:23
 * @Description: 650. 只有两个键的键盘 | 难度：中等 | 标签：数学、动态规划
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 * <p>
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 * <p>
 * 示例 1：
 * 输入：3
 * 输出：3
 * 解释：
 * 最初, 只有一个字符 'A'。
 * 第 1 步, 使用 Copy All 操作。
 * 第 2 步, 使用 Paste 操作来获得 'AA'。
 * 第 3 步, 使用 Paste 操作来获得 'AAA'。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：0
 * <p>
 * 提示：
 * 1 <= n <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/2-keys-keyboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution650 {
    /**
     * 题解答案。除了动规还有一种分解质因数的解法
     * <p>
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 46.07% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 16.00% 的用户
     * 通过测试用例： 126 / 126
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                if (i % j == 0) {
                    f[i] = Math.min(f[i], f[j] + i / j);
                    f[i] = Math.min(f[i], f[i / j] + j);
                }
            }
        }
        return f[n];
    }
}

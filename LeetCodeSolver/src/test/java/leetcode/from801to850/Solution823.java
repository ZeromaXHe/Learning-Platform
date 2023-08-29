package leetcode.from801to850;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote 823. 带因子的二叉树 | 难度：中等 | 标签：数组、哈希表、动态规划、排序
 * 给出一个含有不重复整数元素的数组 arr ，每个整数 arr[i] 均大于 1。
 * <p>
 * 用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。
 * <p>
 * 满足条件的二叉树一共有多少个？答案可能很大，返回 对 109 + 7 取余 的结果。
 * <p>
 * 示例 1:
 * 输入: arr = [2, 4]
 * 输出: 3
 * 解释: 可以得到这些二叉树: [2], [4], [4, 2, 2]
 * <p>
 * 示例 2:
 * 输入: arr = [2, 4, 5, 10]
 * 输出: 7
 * 解释: 可以得到这些二叉树: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 * <p>
 * 提示：
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 109
 * arr 中的所有值 互不相同
 * @implNote
 * @since 2023/8/29 11:27
 */
public class Solution823 {
    /**
     * 时间 13 ms
     * 击败 94.02% 使用 Java 的用户
     * 内存 39.17 MB
     * 击败 97.44% 使用 Java 的用户
     * <p>
     * 参考题解做的，忘记了可以重复使用的条件……
     *
     * @param arr
     * @return
     */
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        long[] dp = new long[n];
        long res = 0, MOD = 1_000_000_007;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0, k = i - 1; j <= k; j++) {
                while (k >= j && (long) arr[j] * arr[k] > arr[i]) {
                    k--;
                }
                if (k >= j && (long) arr[j] * arr[k] == arr[i]) {
                    if (j != k) {
                        dp[i] += dp[j] * dp[k] * 2;
                    } else {
                        dp[i] += dp[j] * dp[k];
                    }
                    dp[i] %= MOD;
                }
            }
            res += dp[i];
            res %= MOD;
        }
        return (int) res;
    }
}

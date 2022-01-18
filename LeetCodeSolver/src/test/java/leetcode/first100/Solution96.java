package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/17 10:40
 * @Description: 96. 不同的二叉搜索树 | 难度：中等 | 标签：树、二叉搜索树、数学、动态规划、二叉树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 19
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution96 {
    @Test
    public void numTreesTest() {
        Assert.assertEquals(5, numTrees(3));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.1 MB , 在所有 Java 提交中击败了 55.29% 的用户
     * 通过测试用例： 19 / 19
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    dp[i] += dp[i - 1];
                } else {
                    dp[i] += dp[j - 1] * dp[i - 1 - j];
                }
            }
        }
        return dp[n - 1];
    }
}

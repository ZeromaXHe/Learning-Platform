package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 17:56
 * @Description: 面试题 08.11. 硬币 | 难度：中等 | 标签：数组、数学、动态规划
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 * <p>
 * 示例1:
 * 输入: n = 5
 * 输出：2
 * 解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * <p>
 * 示例2:
 * 输入: n = 10
 * 输出：4
 * 解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * <p>
 * 说明：
 * 注意:
 * 你可以假设：
 * 0 <= n (总金额) <= 1000000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_11 {
    /**
     * 执行用时： 15 ms , 在所有 Java 提交中击败了 89.69% 的用户
     * 内存消耗： 45.1 MB , 在所有 Java 提交中击败了 68.95% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 数学直接求解的那个方法感觉很牛逼，不过懒得算了……
     *
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 5; i <= n; ++i) {
            dp[i] = (dp[i] + dp[i - 5]) % 1000000007;
        }
        for (int i = 10; i <= n; ++i) {
            dp[i] = (dp[i] + dp[i - 10]) % 1000000007;
        }
        for (int i = 25; i <= n; ++i) {
            dp[i] = (dp[i] + dp[i - 25]) % 1000000007;
        }
        return dp[n];
    }
}
